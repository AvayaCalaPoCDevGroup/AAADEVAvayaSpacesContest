package service.SpacesContest.Actions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.ssl.util.SSLUtilityException;
import com.google.gson.Gson;

import service.SpacesContest.Beans.CurrentQuestion;
import service.SpacesContest.Beans.UserBean;
import service.SpacesContest.Entity.Concursos;
import service.SpacesContest.Entity.Eventos;
import service.SpacesContest.Entity.Integrantesconcurso;
import service.SpacesContest.Entity.Preguntas;
import service.SpacesContest.Entity.Respuestas;
import service.SpacesContest.Entity.Speakers;
import service.SpacesContest.Entity.Titulosevento;
import service.SpacesContest.Http.AvayaSpacesAPIBreeze;
import service.SpacesContest.Http.Util.UtilHttpAvayaSpacesApi;
import service.SpacesContest.ReadExcell.ReadExcelTemplateImportQuestionsFile;
import service.SpacesContest.Service.Impl.AvayaSpacesContestImpl;
import service.SpacesContest.SoketIO.EnrollProcesQuizConfirmation;
import service.SpacesContest.SoketIO.SendQuestionAndManageResponseQuiz;
import service.SpacesContest.SoketIO.SendResponseRecivedConfirmation;
import service.SpacesContest.SoketIO.SendURLCertificatePorEvento;
import service.SpacesContest.Util.ApplicationPath;
import service.SpacesContest.Util.AttributeStore;
import service.SpacesContest.Util.Constants;
import service.SpacesContest.Util.ConstantsHttpResponse;
import service.SpacesContest.Util.ConstantsMessagesToFront;
import service.SpacesContest.Util.RandomAlphaNumeric;

/**
 *
 * @author umansilla
 */
public class AvayaSpacesContestPostQuizAactions {

	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final UserBean userBeanSession;
	private final Logger logger = Logger.getLogger(getClass());

	public AvayaSpacesContestPostQuizAactions(HttpServletRequest request, HttpServletResponse response,
			UserBean userBeanSession) {
		this.request = request;
		this.response = response;
		this.userBeanSession = userBeanSession;
	}

	public void createQuiz(JSONObject jsonObject) throws IOException {
		System.out.println(jsonObject.toString(2));
		// VALIDMOS QUE EL JSON DE ENTRADA CONTENGA LAS LLAVES MÍNIMAS DE FUNCINAMIENTO
		if (jsonObject.has("nombre") && jsonObject.has("preguntas") && jsonObject.has("fechaApertura")
				&& jsonObject.has("imageQuiz") && jsonObject.has("quizColor") && jsonObject.has("isFavorite")
				&& jsonObject.has("fontColor") && jsonObject.has("isFake")) {
			// PRIMERO NECESITAMOS CREAR EL SPACE E INVITAR AL USUARIO QUE ESTÁ CREANDO EL
			// QUIZ.
			// JSONObject jsonNewSpace = new
			// AvayaSpacesAPI().createSpaceAndInviteUser(jsonObject.getString("nombre"),
			// jsonObject.getString("fechaApertura"));
			// VALIDAMOS QUE EL REQUEST HAYA SIDO EXITOSO.
			// if (jsonNewSpace.has("data")) {
			// EL SPACE SE HA CREADO CORRECTAMENTE.
			// CREAMOS EL UUID DEL CONCURSO
			String uuid = UUID.randomUUID().toString();
			String llave = new RandomAlphaNumeric().randomAlphaNumeric(6);
			try {
				// CREAREMOS EL NUEVO CONCURSO TIPO QUIZ
				new AvayaSpacesContestImpl().crearConcursoTypeQuiz(jsonObject, uuid,
						"00000000-0000-0000-0000-000000000000", llave, jsonObject.getBoolean("isFake"),
						userBeanSession.getUserName());

				// GUARDAREMOS LAS PREGUNTAS CREADAS.
				for (int i = 0; i < jsonObject.getJSONArray("preguntas").length(); i++) {
					try {
						// OBTENEMOS EL OBJETO DE LA PREGUNTA E ITERAMOS HASTA CREAR TODAS LAS PREGUNTAS
						new AvayaSpacesContestImpl().crearPregunta(
								jsonObject.getJSONArray("preguntas").getJSONObject(i), uuid, llave,
								userBeanSession.getUserName());
					} catch (JSONException e) {
						System.out.println("Error al crear pregunta " + e.toString());
					}
				}

			} catch (JSONException e) {
				System.out.println("Error al crear el concurso tipo Quuiz " + e.toString());
			}
			// RESPONDEMOS OK Y REGRESAMOS EL UUID DEL NUEVO CONCURSO TIPO QUIZ
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Quiz created Successfully",
					new JSONObject().put("quizUUID", uuid)
							.put("modalHeader", ConstantsMessagesToFront.QUIZ_CREATED_SUCCESSFULLY_MODAL_HEADER)
							.put("modalBody", ConstantsMessagesToFront.QUIZ_CREATED_SUCCESSFULLY_MODAL_BODY),
					"quiz"));
//            } else {
//                //ERROR AL CREAR SPACE EN AVAYA SPACES
//                response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Authentication error with Avaya Spaces, please contact the administrator."));
//            }
		} else {
			// EL JSON DE ENTRADA NO CONTIENE LAS LLAVES MÍNIMAS.
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void publicQuestionQuiz(JSONObject jsonObject) throws IOException, URISyntaxException, NumberFormatException,
			JSONException, SSLUtilityException, NoAttributeFoundException, ServiceNotFoundException {
		// VALIDAMOS QUE CONTENGA LOS PARAMETROS MINIMOS PARA HACER LAS CONSULTAS
		if (jsonObject.has("idpregunta") && jsonObject.has("idconcurso") && jsonObject.has("idevento")) {
			// RECUPERAMOS LA PREGUNTA DE ACUERDO AL ID PREGUNTA Y AL ID CONCURSO
			Preguntas pregunta = new AvayaSpacesContestImpl()
					.obtenerPreguntaPorIdPreguntaAndIdConcrusoAndFamilyAsChildren(jsonObject.getString("idpregunta"),
							jsonObject.getString("idconcurso"), jsonObject.getString("idevento"));
			if (pregunta != null && pregunta.getEstatus().equals("ACTIVO")) {
				int cantidadEnrolados = new AvayaSpacesContestImpl()
						.obtenerIntegrantesEnroladosPorLlaveDelConcurso(pregunta.getLlave());
				if (cantidadEnrolados != 0) {
					// SI LA PREGUNTA ESTA EN UN ESTATUS VALIDO ACTIVE, EDITAMOS LA PREGUNTA A
					// TERMINATED
					try {
						new AvayaSpacesContestImpl().editarPreguntaEstatusTerminated(pregunta.getId());
					} catch (Exception e) {
						System.out.println("Error al editar Pregunta: " + e.toString());
					}
					// ENVIAMOS LA PREGUNTA A TODOS LOS PARTICIPANTES
					new SendQuestionAndManageResponseQuiz(
							Integer.parseInt(jsonObject.getString("txtTime").replace("s", "")), pregunta.getLlave(),
							pregunta);
					response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("The time to respond has begun"));
				} else {
					response.getWriter().println(ConstantsHttpResponse.RESPONSE_400(
							"The question has not been submitted because there are no enrollments in the Quiz."));
				}
			} else {
				response.getWriter()
						.println(ConstantsHttpResponse.RESPONSE_400("The question does not have ACTIVE estatus"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void obtenerRespuestas(JSONObject jsonObject) throws IOException, URISyntaxException {
		// VALIDAMOS QUE CONTENGA LOS PARAMETROS MINIMOS PARA HACER LAS CONSULTAS
		if (jsonObject.has("idpregunta") && jsonObject.has("idconcurso") && jsonObject.has("idevento")) {
			Preguntas pregunta = new AvayaSpacesContestImpl()
					.obtenerPreguntaPorIdPreguntaAndIdConcrusoAndFamilyAsChildren(jsonObject.getString("idpregunta"),
							jsonObject.getString("idconcurso"), jsonObject.getString("idevento"));
			if (pregunta != null && pregunta.getEstatus().equals("TERMINATED")) {
				int intgrantesPorConcurso = new AvayaSpacesContestImpl()
						.obtenerIntegrantesEnroladosPorLlaveDelConcurso(pregunta.getLlave());
				List<Respuestas> respuestas = new AvayaSpacesContestImpl()
						.obtenerRespuestasPorIdPreguntaAndIdIdConcursoAndIdEvento(jsonObject.getString("idpregunta"),
								jsonObject.getString("idconcurso"), pregunta.getIdevento());
				Gson gson = new Gson();
				JSONObject jsonResponse = new JSONObject().put("quizes", new JSONArray(gson.toJson(respuestas)))
						.put("totalEnrolados", intgrantesPorConcurso);
				request.getSession().setAttribute("currentQuestion",
						new CurrentQuestion(jsonObject.getString("idpregunta"), jsonObject.getString("idconcurso"),
								jsonObject.getString("idevento")));
				response.getWriter().println(
						ConstantsHttpResponse.RESPONSE_200("Transaction successfull", jsonResponse, "responses"));
			} else {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The question still active"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void mandarConfirmacionDeRespuestaProcesada(JSONObject jsonObject) throws IOException, URISyntaxException,
			SSLUtilityException, NoAttributeFoundException, ServiceNotFoundException {
		if (jsonObject.has("idpregunta") && jsonObject.has("idconcurso") && jsonObject.has("idevento")) {
			Preguntas pregunta = new AvayaSpacesContestImpl()
					.obtenerPreguntaPorIdPreguntaAndIdConcrusoAndFamilyAsChildren(jsonObject.getString("idpregunta"),
							jsonObject.getString("idconcurso"), jsonObject.getString("idevento"));
			if (pregunta != null && pregunta.getEstatus().equals("TERMINATED")) {
				List<Respuestas> respuestas = new AvayaSpacesContestImpl()
						.obtenerRespuestasPorIdPreguntaAndIdIdConcursoAndIdEvento(jsonObject.getString("idpregunta"),
								jsonObject.getString("idconcurso"), pregunta.getIdevento());
				new SendResponseRecivedConfirmation(120, respuestas, pregunta.getIdpregunta());
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Confirmation responses sent"));
			} else {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The question still active"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void cerrarElQuiz(JSONObject jsonObject) throws IOException {
		if (jsonObject.has("idconcurso") && jsonObject.has("idevento") && jsonObject.has("llave")) {
			// CERRAREMOS TODAS LAS PREGUNTAS
			System.out.println(jsonObject.getString("llave"));
			System.out.println("jsonObject.getString(\"llave\")");
			List<Preguntas> preguntas = new AvayaSpacesContestImpl()
					.obtenerPreguntsPorIdConcursoAndFamilyChildrenForClose(jsonObject.getString("idconcurso"),
							userBeanSession.getUserName(), jsonObject.getString("llave"));
			if (!preguntas.isEmpty()) {
				for (Preguntas pregunta : preguntas) {
					try {
						if (!pregunta.getEstatus().equals("TERMINATED")) {
							new AvayaSpacesContestImpl().editarPreguntaEstatusTerminated(pregunta.getId());
						}
					} catch (Exception ex) {
						System.out.println("Error al cerrar pregunta " + ex.toString());
					}
				}
			}
			try {
				new AvayaSpacesContestImpl().editarConcursoTypeChildrenToTerminated(jsonObject.getString("idconcurso"),
						jsonObject.getString("llave"));
			} catch (Exception ex) {
				System.out.println("Error al cerrar el QUIZ: " + ex.toString());
			}
			List<Respuestas> respuestas = new AvayaSpacesContestImpl()
					.obtenerRespuestasPorIdConcursoAndIdEventoAndLlaveConcurso(jsonObject.getString("idconcurso"),
							jsonObject.getString("idevento"), jsonObject.getString("llave"));
			Gson gson = new Gson();
			JSONObject jsonResponse = new JSONObject().put("quizes", new JSONArray(gson.toJson(respuestas)));
			response.getWriter()
					.println(ConstantsHttpResponse.RESPONSE_200("Transaction successfull", jsonResponse, "responses"));
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void createEvent(JSONObject jsonObject) throws IOException, JSONException, SSLUtilityException,
			NoAttributeFoundException, ServiceNotFoundException {
		if (jsonObject.has("eventName")) {
			JSONObject jsonResponseAvayaSpaces = new AvayaSpacesAPIBreeze()
					.createNewSpacePorNuevoEventoInformChannelCliente(jsonObject.getString("eventName"),
							userBeanSession.getUserName());
			new AvayaSpacesAPIBreeze().changeSpaceToPrivate(
					new UtilHttpAvayaSpacesApi().getSpaceIdAfterCreateSpace(jsonResponseAvayaSpaces),
					jsonObject.getString("eventName"));
			new AvayaSpacesAPIBreeze().changeSpaceToPrivateUserSettings(
					new UtilHttpAvayaSpacesApi().getSpaceIdAfterCreateSpace(jsonResponseAvayaSpaces));
			if (jsonResponseAvayaSpaces.has("data") && jsonResponseAvayaSpaces.has("inviteContent")) {
				String joinURL = new UtilHttpAvayaSpacesApi().getJoinURL(jsonResponseAvayaSpaces);
				if (!joinURL.isEmpty()) {
					String uuid = UUID.randomUUID().toString();
					try {
						new AvayaSpacesContestImpl().CrearEventoParaQuiz(uuid, jsonObject.getString("eventName"),
								userBeanSession.getUserName(),
								new UtilHttpAvayaSpacesApi().getSpaceIdAfterCreateSpace(jsonResponseAvayaSpaces),
								joinURL);
					} catch (JSONException e) {
						System.out.println("Error al crear evento: " + e.toString());
					}
					response.getWriter()
							.println(ConstantsHttpResponse
									.RESPONSE_200("Avaya Spaces created successfully",
											new JSONObject().put("joinURL", joinURL), "avayaSpace")
									.put("uuid", uuid).put("nombre", jsonObject.getString("eventName")));
				} else {
					response.getWriter().println(
							ConstantsHttpResponse.RESPONSE_400("Error creating space in Avaya Spaces not Join URL"));
				}
			} else {
				response.getWriter()
						.println(ConstantsHttpResponse.RESPONSE_400("Error creating space in Avaya Spaces"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void associarEventoAQuiz(JSONObject jsonObject) throws IOException {
		if (jsonObject.has("idevento") && jsonObject.has("idconcurso") && jsonObject.has("haseventid")) {
			if (jsonObject.getString("haseventid").equals("true")) {
				// VAMOS A SETEAR EL ID DEL ESPACIO AL ID SPACE DEL EVENTO
				Eventos evento = new AvayaSpacesContestImpl()
						.ObtenerEventoPorIdEvento(jsonObject.getString("idevento"));
				if (evento != null) {
					Concursos concurso = new AvayaSpacesContestImpl()
							.obtenerConcursoPorIdConcurso(jsonObject.getString("idconcurso"));
					if (concurso != null) {
						try {
							new AvayaSpacesContestImpl().editarConcursoToNewIdAvayaSpaceAndIdConcurso(concurso,
									evento.getIdspace(), evento.getIdevento());
						} catch (Exception e) {
							System.out.println("Error al editar Quiz: " + e.toString());
						}
						response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Quiz updated",
								new JSONObject().put("idEvent", evento.getIdevento()), "event"));
					} else {
						response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The quiz not exists"));
					}
				} else {
					response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The event not exists"));
				}
			} else {
				// VAMOS A SETEAR EL ID DEL ESPACIO A VACIO
				Concursos concurso = new AvayaSpacesContestImpl()
						.obtenerConcursoPorIdConcurso(jsonObject.getString("idconcurso"));
				if (concurso != null) {
					try {
						new AvayaSpacesContestImpl().editarCocurdoIdSpacesEmpty(concurso.getId());
					} catch (Exception e) {
						System.out.println("Error al editar Quiz: " + e.toString());
					}
					response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Quiz id set to empty",
							new JSONObject().put("idEvent", "noEvent"), "event"));
				} else {
					response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The quiz not exists"));
				}
			}

		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void uploadFileToGetQuestions(JSONObject jsonObject)
			throws UnsupportedEncodingException, IOException, Exception {
		if (jsonObject.has("arrayFiles")) {
			JSONArray jsonArray = new JSONArray(jsonObject.getString("arrayFiles"));
			String realPath = ApplicationPath.getApplcatonPath();
			String[] split = realPath.split("/");
			StringBuilder path = new StringBuilder();
			for (int k = 1; k < split.length - 1; k++) {
				path.append("/");
				path.append(split[k]);
			}
			if (jsonArray.length() != 0) {
				for (int i = 0; i < jsonArray.length(); i++) {
					byte[] encodedText = Base64.getMimeDecoder().decode(
							jsonArray.getJSONObject(i).getString("base64item").trim().split(",")[1].getBytes("UTF-8"));

					try (OutputStream stream = new FileOutputStream(
							path.toString() + "/" + userBeanSession.getUserName() + "UploadedFile.xlsx")) {
						stream.write(encodedText);
					} catch (Exception e) {
						System.out.println("Error: " + e.toString());
					}
				}
				response.getWriter()
						.println(new ReadExcelTemplateImportQuestionsFile()
								.read(path.toString() + "/" + userBeanSession.getUserName() + "UploadedFile.xlsx")
								.put("status", "ok"));
			}
		}
	}

	public void deleteEvent(JSONObject jsonobject) throws IOException {
		if (jsonobject.has("idevent")) {
			Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(jsonobject.getString("idevent"));
			if (evento != null) {
				try {
					new AvayaSpacesContestImpl().borrarEvento(evento.getId());
					/*
					 * BORRAR ESPACIO
					 */
					new AvayaSpacesAPIBreeze().deleteSpaceBySpaceID(evento.getIdspace());
					// System.out.println(jsonDeleteResponse.toString(2));
				} catch (Exception e) {
					System.out.println("Error: " + e.toString());
				}
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Evento borrado"));
			} else {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("El evento no existe"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void deleteQuiz(JSONObject jsonObject) throws IOException {
		if (jsonObject.has("idquiz")) {
			Concursos quiz = new AvayaSpacesContestImpl()
					.obtenerConcursoPorIdConcursoFamilyParent(jsonObject.getString("idquiz"));
			if (quiz != null) {
				try {
					new AvayaSpacesContestImpl().editarConcursoFamimlyParentToInactivoById(quiz.getId());
				} catch (Exception e) {
					System.out.println("Error al actualizar estatus del Quiz : " + e.toString());
				}
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Quiz deleted"));
			} else {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The Quiz not exists"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void enrolledPeople(JSONObject jsonObject) throws IOException {
		if (jsonObject.has("llave")) {
			response.getWriter()
					.println(ConstantsHttpResponse.RESPONSE_200("Players found",
							new JSONObject().put("count", new AvayaSpacesContestImpl()
									.obtenerIntegrantesEnroladosPorLlaveDelConcurso(jsonObject.getString("llave"))),
							"players"));
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void sendDirectMessageToConfirmEnroll(JSONObject jsonObject) throws IOException, URISyntaxException,
			JSONException, SSLUtilityException, NoAttributeFoundException, ServiceNotFoundException {
		if (jsonObject.has("llave")) {
			List<Integrantesconcurso> integrantesConcurso = new AvayaSpacesContestImpl()
					.obtenerIntegrantesEnrolladoPirLlaveDelConcurso(jsonObject.getString("llave"));
			if (!integrantesConcurso.isEmpty()) {
				Concursos consurso = new AvayaSpacesContestImpl()
						.obtenerConcursoPorIdConcursoFamilyChildren(integrantesConcurso.get(0).getIdconcurso());
				if (consurso != null) {
					if (consurso.getEstatus().equals("ACTIVO")) {
						new EnrollProcesQuizConfirmation(120, integrantesConcurso, jsonObject.getString("llave"));
						response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Messages send"));
					} else {
						response.getWriter()
								.println(ConstantsHttpResponse.RESPONSE_400("The quiz is already TERMINATED"));
					}
				} else {
					response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Th quiz not exists"));
				}
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void obtnerJoinURL(JSONObject jsonObject) throws JSONException, IOException {
		if (jsonObject.has("idevent")) {
			Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(jsonObject.getString("idevent"));
			if (evento != null) {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Event Found",
						new JSONObject().put("joinurl", evento.getJoinurl()), "event"));
			} else {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The event not exists"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void obtenerEnroledPeople(JSONObject jsonObject) throws IOException {
		if (jsonObject.has("llave")) {
			List<Integrantesconcurso> integrantes = new AvayaSpacesContestImpl()
					.obtenerIntegrantesEnrolladoPirLlaveDelConcurso(jsonObject.getString("llave"));
			if (integrantes != null) {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Ok",
						new JSONArray(new Gson().toJson(integrantes)), "enrolados"));
			} else {
				response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
			}
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
		}
	}

	public void obtenerRespuestasLive(JSONObject jsonObject) throws IOException {
		if (request.getSession().getAttribute("currentQuestion") != null) {
			CurrentQuestion currentQuestion = (CurrentQuestion) request.getSession().getAttribute("currentQuestion");
			Preguntas pregunta = new AvayaSpacesContestImpl()
					.obtenerPreguntaPorIdPreguntaAndIdConcrusoAndFamilyAsChildren(currentQuestion.getIdPregunta(),
							currentQuestion.getIdConcurso(), currentQuestion.getIdEvento());
			List<Respuestas> respuestas = new AvayaSpacesContestImpl()
					.obtenerRespuestasPorIdPreguntaAndIdIdConcursoAndIdEvento(pregunta.getIdpregunta(),
							pregunta.getIdconcurso(), pregunta.getIdevento());
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_200(pregunta.getPregunta(),
					new JSONArray(new Gson().toJson(respuestas)), "answers"));
		} else {
			response.getWriter().println(
					ConstantsHttpResponse.RESPONSE_200("There is no current question", new JSONArray(), "answers"));
		}
	}

	public void obtenerGitubVersion() throws IOException, NoAttributeFoundException, ServiceNotFoundException {
		response.getWriter().println(ConstantsHttpResponse
				.RESPONSE_200(AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_GIT_HUB_VERSION)));
	}
	
    public void createNewSpeaker(JSONObject jsonObject) throws IOException {
        if (jsonObject.has("imageSpeaker") && jsonObject.has("nombreSpeaker") && jsonObject.has("posicionSpeaker") && jsonObject.has("companySpeaker") && jsonObject.has("descriptionSpeaker")) {
            try {
                String idSpeaker = new AvayaSpacesContestImpl().crearSpeaker(jsonObject.getString("imageSpeaker"), jsonObject.getString("nombreSpeaker"), jsonObject.getString("posicionSpeaker"), jsonObject.getString("companySpeaker"), jsonObject.getString("descriptionSpeaker"), userBeanSession.getUserName());
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_200(idSpeaker));
            } catch (JSONException e) {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_500(e.toString()));
            }
        } else {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
        }
    }


	public void cleanRespuestaActualSession() throws IOException {
		if (request.getSession().getAttribute("currentQuestion") != null) {
			request.getSession().removeAttribute("currentQuestion");
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Clean completed"));
		} else {
			response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Clean completed"));
		}
	}
	
	public void obtenerDatosRegistro(JSONObject jsonObject) throws IOException {
		logger.info("obtenerDatosRegistro");
        if (jsonObject.has("idEvento")) {
            Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(jsonObject.getString("idEvento"));
            if (evento != null) {
            	
            	logger.info("evento != null");
                Titulosevento titulosPorEvento = new AvayaSpacesContestImpl().obtenerTitulosEventoPorIdEventoAndCreadoPor(jsonObject.getString("idEvento"), userBeanSession.getUserName());
                
                JSONObject jsonObjectResponse = new JSONObject();
                jsonObjectResponse.put("speakersEvento", new JSONArray(evento.getArrayidspeakers()));
                if (titulosPorEvento != null) {
                	logger.info("(titulosPorEvento != null");
                    jsonObjectResponse.put("titulosArray", titulosPorEvento.getTitulos());
                    jsonObjectResponse.put("descripcion", titulosPorEvento.getDescripcion());
                } else {
                	logger.info("(titulosPorEvento == null");
                    jsonObjectResponse.put("titulosArray", new JSONArray());
                    jsonObjectResponse.put("descripcion", "");
                }
                List<Speakers> speaersCreadosPorUsuario = new AvayaSpacesContestImpl().obtenerSpeakersByCreadoPor(userBeanSession.getUserName());
                if (!speaersCreadosPorUsuario.isEmpty()) {
                	logger.info("!speaersCreadosPorUsuario.isEmpty()");
                    jsonObjectResponse.put("speakersUsuario", new JSONArray(new Gson().toJson(speaersCreadosPorUsuario)));
                } else {
                	logger.info("speaersCreadosPorUsuario.isEmpty()");
                    jsonObjectResponse.put("speakersUsuario", new JSONArray());
                }
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Titles and Speakers", jsonObjectResponse, "registerInfo"));
            } else {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The Event not Exists"));
            }
        } else {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
        }
    }

    public void obtenerSpeaker(JSONObject jsonObject) throws IOException {
        if (jsonObject.has("idSpeaker")) {
            Speakers speaker = new AvayaSpacesContestImpl().obtenerSpealerPorIdSpeakerAndCreadoPor(jsonObject.getString("idSpeaker"), userBeanSession.getUserName());
            if (speaker != null) {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Speaker found", new JSONObject(new Gson().toJson(speaker)), "Speaker"));
            } else {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("The Speaker not exists"));
            }
        } else {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
        }
    }

    public void actualizarSpeakerInfo(JSONObject jsonObject) throws IOException {
        if (jsonObject.has("imageSpeaker")
                && jsonObject.has("nombreSpeaker")
                && jsonObject.has("posicionSpeaker")
                && jsonObject.has("companySpeaker")
                && jsonObject.has("descriptionSpeaker")
                && jsonObject.has("idOfSpeaker")) {
            Speakers speaker = new AvayaSpacesContestImpl().obtenerSpealerPorIdSpeakerAndCreadoPor(jsonObject.getString("idOfSpeaker"), userBeanSession.getUserName());
            if (speaker != null) {
                try {
                    new AvayaSpacesContestImpl().actualizarSpeaker(
                            speaker,
                            jsonObject.getString("imageSpeaker"),
                            jsonObject.getString("nombreSpeaker"),
                            jsonObject.getString("posicionSpeaker"),
                            jsonObject.getString("companySpeaker"),
                            jsonObject.getString("descriptionSpeaker")
                    );
                    response.getWriter().println(ConstantsHttpResponse.RESPONSE_200(jsonObject.getString("nombreSpeaker") + " info has been updated."));
                } catch (Exception e) {
                    System.out.println("Error: " + e.toString());
                }
            } else {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Speaker not exists"));
            }
        } else {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
        }
    }

    public void agregarSpeakersAlEvento(JSONObject jsonObject) throws IOException {
        if (jsonObject.has("idEvento") && jsonObject.has("idSpeakersArray")) {
            Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(jsonObject.getString("idEvento"));
            if (evento != null) {
                JSONArray idSpeakersArray = jsonObject.getJSONArray("idSpeakersArray");
                JSONArray speakersArray = new JSONArray();
                if (idSpeakersArray.length() != 0) {
                    for (int i = 0; i < idSpeakersArray.length(); i++) {
                        Speakers speaker = new AvayaSpacesContestImpl().obtenerSpealerPorIdSpeakerAndCreadoPor(idSpeakersArray.getString(i), userBeanSession.getUserName());
                        if (speaker != null) {
                            speakersArray.put(new Gson().toJson(speaker));
                        }
                    }
                }
                try {
                    new AvayaSpacesContestImpl().editarSpeakersDelEvento(evento, speakersArray.toString());
                } catch (Exception e) {
                    System.out.println("Error al actualizar Speakers");
                }
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Speakers updated"));
            } else {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Event not Exists"));
            }
        } else {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
        }
    }

    public void actualizarTitlesEvent(JSONObject jsonObject) throws IOException {
        if (jsonObject.has("inputSubTitles")
                && jsonObject.has("textAreaEvent")
                && jsonObject.has("idEvent")) {
            Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(jsonObject.getString("idEvent"));
            if (evento != null) {
                Titulosevento tituloPorEvento = new AvayaSpacesContestImpl().obtenerTitulosEventoPorIdEventoAndCreadoPor(evento.getIdevento(), userBeanSession.getUserName());
                if( tituloPorEvento != null){
                    //EDITAMOS EL TITULO
                    try{
                        new AvayaSpacesContestImpl().editarTituloPorIDTituloYIdEventoYCradoPor(tituloPorEvento, jsonObject.getJSONArray("inputSubTitles").toString(), jsonObject.getString("textAreaEvent"));
                    }catch(Exception e){
                        System.out.println("Error al editar titulos: " + e.toString());
                    }
                }else{
                    //CREAMOS EL TITULO
                    new AvayaSpacesContestImpl().crearTituloEvento(evento.getIdevento(), jsonObject.getJSONArray("inputSubTitles").toString(), jsonObject.getString("textAreaEvent"), userBeanSession.getUserName());
                }
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Changes saved"));
            } else {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Event not Exists"));
            }
        } else {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
        }
    }

    public void enviarCertificadosViaSpaces(JSONObject jsonObject) throws IOException, URISyntaxException {
        if (jsonObject.has("idEvent")) {
            //List<Integrantesevento> integrantes = new AvayaSpacesContestImpl().obtenerIntegrantesPorIdEvento(jsonObject.getString("idEvent"));
            new SendURLCertificatePorEvento(120, jsonObject.getString("idEvent"));
             response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Message Certificates Sent"));
        } else {
            response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
        }
    }
}
