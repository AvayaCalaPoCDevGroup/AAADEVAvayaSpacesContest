package service.SpacesContest.Actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import service.SpacesContest.Beans.CurrentQuestion;
import service.SpacesContest.Beans.UserBean;
import service.SpacesContest.Entity.Concursos;
import service.SpacesContest.Entity.Eventos;
import service.SpacesContest.Entity.Integrantesconcurso;
import service.SpacesContest.Entity.Preguntas;
import service.SpacesContest.Entity.Respuestas;
import service.SpacesContest.Service.Impl.AvayaSpacesContestImpl;
import service.SpacesContest.Util.ConstantsHttpResponse;
import service.SpacesContest.Util.RandomAlphaNumeric;

/**
 *
 * @author umansilla
 */
public class AvayaSpacesContestGetQuizActions {

	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final UserBean userBeanSession;
	private final Gson gson = new Gson();

	public AvayaSpacesContestGetQuizActions(HttpServletRequest request, HttpServletResponse response,
			UserBean userBeanSession) {
		this.request = request;
		this.response = response;
		this.userBeanSession = userBeanSession;
	}

	public void playQuiz() throws ServletException, IOException {
		// VALIDAMOS QUE EL PARÁMETRO Q (QUIZ) EXISTA EN LA URL
		if (request.getParameter("q") != null) {
			// VALIDAMOS QUE EL PARÁMETRO E (EVENT) EXISTA EN LA URL
			if (request.getParameter("e") != null) {
				// VALIDAMOS SI EL ID DEL EVENTO EXISTE
				Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(request.getParameter("e"));
				if (evento != null) {
					// VALIDAMOS QUE EXISTA UN "CHILDREN" DEL QUIZ QUE SE ESTA SOLICITANDO
					Concursos concursoChildren = new AvayaSpacesContestImpl()
							.obtenerConcursoPorIdConcursoAndIdEventoAndFamilyChildren(request.getParameter("q"),
									request.getParameter("e"));
					if (concursoChildren != null) {
						// EXISTE EL CONCURSO COMO CHILDREN
						List<Preguntas> preguntas = new AvayaSpacesContestImpl()
								.obtenerPreguntasPorIdConcursoAndIdEventoAndFamilyChildren(
										concursoChildren.getIdconcurso(), request.getParameter("e"));
						JSONObject jsonResponse = new JSONObject().put("preguntas",
								new JSONArray(gson.toJson(preguntas)));
						request.setAttribute("nombreQuiz", concursoChildren.getNombreconcurso());
						request.setAttribute("estatusQuiz", concursoChildren.getEstatus());
						request.setAttribute("nombreEvento", evento.getNombreevento());
						request.setAttribute("preguntas", jsonResponse.toString());
						request.getSession().setMaxInactiveInterval(36000);
						request.getRequestDispatcher("/Home/QuizView.jsp").forward(request, response);
					} else {
						// NO EXISTE EL CONCURSO COMO CHILDREN POR LO QUE TOMAREMOS AL PADRE PARA HACER
						// UN HIJO Y AL IGUAL QUE SUS PREGUNTAS CORRESPONDIENTES
						Concursos concursoParent = new AvayaSpacesContestImpl()
								.obtenerConcursoPorIdConcursoFamilyParent(request.getParameter("q"));
						if (concursoParent != null) {
							String nuevaLlave = new RandomAlphaNumeric().randomAlphaNumeric(6);
							String nuevoUUIDConcurso = concursoParent.getIdconcurso();
							try {
								new AvayaSpacesContestImpl().crearConcursoTypeQuizChildren(evento.getIdevento(),
										concursoParent, userBeanSession.getUserName(), evento.getIdspace(), nuevaLlave,
										nuevoUUIDConcurso);
							} catch (Exception e) {
								System.out.println("Error al crear al hijo: " + e.toString());
							}
							List<Preguntas> preguntas = new AvayaSpacesContestImpl()
									.obtenerPreguntsPorIdConcursoAndFamilyParent(concursoParent.getIdconcurso(),
											userBeanSession.getUserName());
							for (Preguntas pregunta : preguntas) {
								try {
									new AvayaSpacesContestImpl().crearPreguntaChildren(pregunta, evento.getIdevento(),
											userBeanSession.getUserName(), nuevaLlave, nuevoUUIDConcurso);
								} catch (Exception e) {
									System.out.println("Error al crear pregunta como family children");
								}
							}
							// AHORA RESPONDEMOS CON LAS PREGUNTAS RECIEN CREADAS COMO FAMILY CHILDREN
							List<Preguntas> preguntasChildren = new AvayaSpacesContestImpl()
									.obtenerPreguntasPorIdConcursoAndIdEventoAndFamilyChildren(
											concursoParent.getIdconcurso(), request.getParameter("e"));
							JSONObject jsonResponse = new JSONObject().put("preguntas",
									new JSONArray(gson.toJson(preguntasChildren)));
							request.setAttribute("nombreQuiz", concursoParent.getNombreconcurso());
							request.setAttribute("estatusQuiz", concursoParent.getEstatus());
							request.setAttribute("nombreEvento", evento.getNombreevento());
							request.setAttribute("preguntas", jsonResponse.toString());
							request.getSession().setMaxInactiveInterval(36000);
							request.getRequestDispatcher("/Home/QuizView.jsp").forward(request, response);
						} else {
							// EL ID DEL CONCURSO NO EXISTE COMO PARENT NO COMO CHILDREN
							getQuizes();
						}
					}
				} else {
					// NO EXISTE EL ID DEL EVENTO
					getQuizes();
				}
			} else {
				// EL PARÁMETRO E (EVENT) NO EXISTe EN LA URL
				getQuizes();
			}
		} else {
			// EL PARÁMETRO Q (QUIZ) NO EXISTe EN LA URL
			getQuizes();
		}
	}

	public void getQuizes() throws ServletException, IOException {
		List<Concursos> quizes = new AvayaSpacesContestImpl()
				.obtenerConcursosTypeQuizesActivo(userBeanSession.getUserName());
		List<Eventos> eventos = new AvayaSpacesContestImpl()
				.obtenerEventosPorEstatusActivoAndCreadoPor(userBeanSession.getUserName());
		JSONObject jsonResponse = new JSONObject().put("quizes", new JSONArray(gson.toJson(quizes)))
				.put("eventos", new JSONArray(gson.toJson(eventos))).put("questions", quizes);
		request.setAttribute("quizes", jsonResponse.toString());
		request.getSession().setMaxInactiveInterval(36000);
		request.getRequestDispatcher("/Home/QuizMenu.jsp").forward(request, response);
	}

	public void createQuizPage() throws ServletException, IOException {
		if (request.getParameter("q") != null) {
			request.setAttribute("quizName", request.getParameter("q"));
			request.getSession().setMaxInactiveInterval(36000);
			request.getRequestDispatcher("/Home/QuizCreate.jsp").forward(request, response);
		} else {
			List<Concursos> quizes = new AvayaSpacesContestImpl().obtenerConcursosTypeQuizesActivo();
			JSONObject jsonResponse = new JSONObject().put("quizes", new JSONArray(gson.toJson(quizes)));
			request.setAttribute("quizes", jsonResponse.toString());
			request.getSession().setMaxInactiveInterval(36000);
			request.getRequestDispatcher("/Home/QuizMenu.jsp").forward(request, response);
		}
	}

	 public void enroledPeaople() throws ServletException, IOException {
	        if (request.getParameter("q") != null || request.getParameter("e") != null) {
	            Concursos concurso = new AvayaSpacesContestImpl().obtenerConcursoPorIdConcursoAndIdEventoAndFamilyChildren(request.getParameter("q"), request.getParameter("e"));
	            if (concurso != null) {
	                List<Integrantesconcurso> integrantes = new AvayaSpacesContestImpl().obtenerIntegrantesEnrolladoPirLlaveDelConcurso(concurso.getLlave());
	                Long integrantesEvento = new AvayaSpacesContestImpl().obtenerTodosLosResgistradosAEventoporIdEvento(request.getParameter("e"));
	                if (request.getSession().getAttribute("currentQuestion") != null) {
	                    CurrentQuestion currentQuestion = (CurrentQuestion) request.getSession().getAttribute("currentQuestion");
	                    Preguntas pregunta = new AvayaSpacesContestImpl().obtenerPreguntaPorIdPreguntaAndIdConcrusoAndFamilyAsChildren(currentQuestion.getIdPregunta(), currentQuestion.getIdConcurso(), currentQuestion.getIdEvento());
	                    List<Respuestas> respuestas = new AvayaSpacesContestImpl().obtenerRespuestasPorIdPreguntaAndIdIdConcursoAndIdEvento(pregunta.getIdpregunta(), pregunta.getIdconcurso(), pregunta.getIdevento());
	                    JSONObject jsonResponse = new JSONObject().put("answers", new JSONArray(gson.toJson(respuestas)));
	                    request.setAttribute("pregunta", pregunta.getPregunta());
	                    request.setAttribute("answers", jsonResponse.toString());
	                } else {
	                    JSONObject jsonResponse = new JSONObject().put("answers", new JSONArray());
	                    request.setAttribute("pregunta", "There is no current question");
	                    request.setAttribute("answers", jsonResponse.toString());
	                }
	                JSONObject jsonResponse = new JSONObject().put("enrolados", new JSONArray(gson.toJson(integrantes)));
	                request.setAttribute("integrantesEvento", Long.toString(integrantesEvento));
	                request.setAttribute("llave", concurso.getLlave());
	                request.setAttribute("enrolados", jsonResponse.toString());
	                request.getSession().setMaxInactiveInterval(36000);
	                request.getRequestDispatcher("/Home/QuizViewQuestionLive.jsp").forward(request, response);
	            } else {
	                response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The Quiz does not exist"));
	            }
	        } else {
	            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("There is no event or quiz"));
	        }
	    }
}
