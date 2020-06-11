package service.SpacesContest.Controller;


import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.avaya.collaboration.util.logger.Logger;

import service.SpacesContest.Actions.AvayaSpacesContestGetConcursoActions;
import service.SpacesContest.Actions.AvayaSpacesContestGetQuizActions;
import service.SpacesContest.Actions.AvayaSpacesContestPostConcursoActions;
import service.SpacesContest.Actions.AvayaSpacesContestPostQuizAactions;
import service.SpacesContest.Beans.UserBean;
import service.SpacesContest.Util.Constants;
import service.SpacesContest.Util.ConstantsHttpResponse;
import service.SpacesContest.Util.PartToString;


/**
 *
 * @author umansilla
 */
@MultipartConfig
@WebServlet(name = "AvayaSpacesContestController", urlPatterns = {"/AvayaSpacesContest"})
public class AvayaSpacesContestController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(getClass());
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
        UserBean userBeanSession = (UserBean) session
                .getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
        // USERBEAN
        if (userBeanSession == null) {
            removeCookie(request);
            request.getRequestDispatcher("/LogIn/").forward(request, response);
        } else {
            try {
                if (request.getParameter("p") != null) {
                    switch (request.getParameter("p")) {
                        case "concurso":
                            new AvayaSpacesContestGetConcursoActions(request, response, userBeanSession).getConcurso();
                            break;
                        case "quizes":
                            new AvayaSpacesContestGetQuizActions(request, response, userBeanSession).getQuizes();
                            break;
                        case "createQuiz":
                            new AvayaSpacesContestGetQuizActions(request, response, userBeanSession).createQuizPage();
                            break;
                        case "playQuiz":
                            new AvayaSpacesContestGetQuizActions(request, response, userBeanSession).playQuiz();
                            break;
                            /*  08 De Junio 2020 */
                        case "enroledPeople":
                            new AvayaSpacesContestGetQuizActions(request, response, userBeanSession).enroledPeaople();
                            break;
                        default:
                            request.getRequestDispatcher("/LogIn/").forward(request, response);
                            break;
                    }
                } else {
                    request.getRequestDispatcher("/LogIn/").forward(request, response);
                }
            } catch (IOException | ServletException e) {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_500(e.toString()));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(); // RECUPERAMOS LA SESION
        UserBean userBeanSession = (UserBean) session
                .getAttribute("UserBeanSession"); // RECUPERAMOS EL OBJETO
        // USERBEAN
        if (userBeanSession == null) {
            response.getWriter().println(Constants.HTTP_RESPONSE_LOGIN_ERROR_UNAUTHORIZED);
        } else {
            try {
                JSONObject jsonObject = new JSONObject(new PartToString(request).getStringValue("jsonObject"));
                if (jsonObject.has("action")) {
                    switch (jsonObject.getString("action")) {
                        case "createEvent":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).createEvent(jsonObject);
                            break;
                        case "enroll":
                            new AvayaSpacesContestPostConcursoActions(request, response, userBeanSession).doEnrol(jsonObject);
                            break;
                        case "createQuiz":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).createQuiz(jsonObject);
                            break;
                        case "publicQuestionQuiz":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).publicQuestionQuiz(jsonObject);
                            break;
                        case "getCurrentResponses":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).obtenerRespuestas(jsonObject);
                            break;
                        case "confirmationResponse":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).mandarConfirmacionDeRespuestaProcesada(jsonObject);
                            break;
                        case "closeQuiz":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).cerrarElQuiz(jsonObject);
                            break;
                        case "associateAnEvent":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).associarEventoAQuiz(jsonObject);
                            break;
                        case "uploadFileToGetQuestions":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).uploadFileToGetQuestions(jsonObject);
                            break;
                        case "deleteEvent":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).deleteEvent(jsonObject);
                            break;
                        case "deleteQuiz":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).deleteQuiz(jsonObject);
                            break;
                        case "enrolledPeople":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).enrolledPeople(jsonObject);
                            break;
                        case "sendEnrollConfirmation":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).sendDirectMessageToConfirmEnroll(jsonObject);
                            break;
                        case "obtenerJoinURL":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).obtnerJoinURL(jsonObject);
                            break;
                            /*  08 De Junio 2020 */
                        case "obtenerEnroledPeople":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).obtenerEnroledPeople(jsonObject);
                            break;
                        case "obtenerResponsesLive":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).obtenerRespuestasLive(jsonObject);
                            break;
                        case "obtenerGitHubVersion":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).obtenerGitubVersion();
                            break;
                            /* 09 De Junio 2020 */
                        case "cleanRespuestaActual":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).cleanRespuestaActualSession();
                            break;
                            /*10 De Junio 2020 */
                        case "obtenerDatosPaginaRegistroPorEvento":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).obtenerDatosRegistro(jsonObject);
                            break;
                        case "createSpeaker":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).createNewSpeaker(jsonObject);
                            break;
                        case "obtenerSpeakerPorIdSpeaker":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).obtenerSpeaker(jsonObject);
                            break;
                        case "updateSpeaker":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).actualizarSpeakerInfo(jsonObject);
                            break;
                        case "agregarSpeakersAlEvento":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).agregarSpeakersAlEvento(jsonObject);
                            break;
                        case "updateTitles":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).actualizarTitlesEvent(jsonObject);
                            break;
                            /*11 De Junio 2020 */
                        case "sendCertificateViaSpaces":
                            new AvayaSpacesContestPostQuizAactions(request, response, userBeanSession).enviarCertificadosViaSpaces(jsonObject);
                            break;
                        default:
                        	 response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
                            break;
                    }
                } else {
                    response.getWriter().println(ConstantsHttpResponse.RESPONSE_400("Bad Request"));
                }
            } catch (IOException | URISyntaxException | ServletException | JSONException e) {
                response.getWriter().println(ConstantsHttpResponse.RESPONSE_500(e.toString()));
            } catch (Exception ex) {
            	logger.error("Error: " + ex.toString());
            	response.getWriter().println(ConstantsHttpResponse.RESPONSE_500(ex.toString()));
            }
        }

    }

    private void setAccessControlHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", Constants.ACCESS_ORIGIN);
        response.setHeader("Access-Control-Allow-Credentials", Constants.ACCESS_CREDENTIALS);
        response.setHeader("Access-Control-Allow-Methods", Constants.ACCESS_METHODS);
        response.setHeader("Access-Control-Allow-Headers", Constants.ACCESS_HEADERS);
        response.setHeader("Content-Type", Constants.CONTENT_TYPE);
    }

    private void removeCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieName")) {
                    // do something
                    // value can be retrieved using #cookie.getValue()
                    if (cookie.getName().equals("JWT")) {
                        cookie.setMaxAge(0); // ELIMINAMOS LA COOKIE
                    }
                }
            }
        }

    }

}
