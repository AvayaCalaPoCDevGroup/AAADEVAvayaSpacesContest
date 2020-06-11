package service.SpacesContest.SoketIO;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.ssl.util.SSLProtocolType;
import com.avaya.collaboration.ssl.util.SSLUtilityException;
import com.avaya.collaboration.ssl.util.SSLUtilityFactory;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import service.SpacesContest.Entity.Integrantesconcurso;
import service.SpacesContest.Entity.Preguntas;
import service.SpacesContest.Entity.Respuestas;
import service.SpacesContest.Service.Impl.AvayaSpacesContestImpl;
import service.SpacesContest.Util.AttributeStore;
import service.SpacesContest.Util.Constants;
import service.SpacesContest.Util.DateFormatedNow;


/**
 *
 * @author umansilla
 */
public class SendQuestionAndManageResponseQuiz {

    Timer timer;
    private io.socket.client.Socket socketQANDR;
    private final Preguntas pregunta;
    public SendQuestionAndManageResponseQuiz(int seconds, String llave, Preguntas pregunta) throws URISyntaxException, SSLUtilityException, NoAttributeFoundException, ServiceNotFoundException {
        timer = new Timer();
        timer.schedule(new SendQuestionAndManageResponseQuiz.RemindTaskListenMessage(), seconds * 1000);
        this.pregunta = pregunta;
        connectSocketAvayaSpaces();
    }

    public void connectSocketAvayaSpaces() throws URISyntaxException, SSLUtilityException, NoAttributeFoundException, ServiceNotFoundException {
		final SSLProtocolType protocol = SSLProtocolType.TLSv1_2;
		final SSLContext sslContext = SSLUtilityFactory
				.createSSLContext(protocol);
		
		IO.Options opts = new IO.Options();
		String[] web = new String[1];
		web[0] = "websocket";
		opts.query = "tokenType=jwt&token=" + AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_JWT_AVAYA_CLOUD);
		opts.transports = web;
		opts.sslContext = sslContext;
		opts.hostnameVerifier = NoopHostnameVerifier.INSTANCE;
		socketQANDR = IO.socket("https://" + Constants.FQDN_AVAYA_CLOUD + "/chat", opts);

        //socket.on
        socketQANDR.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("SendQuestionAndManageResponseQuiz Connected");
                suscribeIntegrantesTopicID();
            }

        }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("SendQuestionAndManageResponseQuiz Disonnected");
            }

        });

        socketQANDR.on("CHANNEL_SUBSCRIBED", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println("CHANNEL_SUBSCRIBED");
                JSONArray jsonArray = new JSONArray(Arrays.toString(os));
                //EJEMPLO PARA RETORNAR MENSAJE
                JSONObject json = new JSONObject()
                        .put("content", new JSONObject().put("bodyText", getPregunta()))
                        .put("sender", new JSONObject().put("_id", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id")).put("type", "user"))
                        .put("category", "chat")
                        .put("topicId", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id"));
                socketQANDR.emit("SEND_MESSAGE", json);
            }

        });

        socketQANDR.on("MESSAGE_SENT", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                JSONArray jsonArray = new JSONArray(Arrays.toString(os));
                for (int i = 0; i < jsonArray.length(); i++) {
                    //VALIDAMOS QUE EL PARTICIPANTE ESTE ENROLADO
                    Integrantesconcurso integranteEnrolado = new AvayaSpacesContestImpl().obtenerIntegranteConcursoPorEmailSpacesAndIdConcurso(jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"), pregunta.getIdconcurso(), pregunta.getLlave());
                    if (integranteEnrolado != null) {
                        if (!jsonArray.getJSONObject(i).getJSONObject("sender").getString("username").equals("equiz.cala@gmail.com")) {
                            if (jsonArray.getJSONObject(i).getJSONObject("content").has("bodyText")) {
                                String replaceJumps = jsonArray.getJSONObject(i).getJSONObject("content").getString("bodyText").replaceAll("(\n|\r)", "");
                                String replaceSpaces = replaceJumps.replaceAll(" ", "").toUpperCase();
                                String respuesta;
                                try {
                                    respuesta = replaceSpaces.substring(0, 1);
                                    System.out.println("RESPUESTA " + respuesta);
                                    //VALIDAMOS QUE LA RESPUESTA ENVIADA TENGA CONTENIDO.
                                    if (!respuesta.isEmpty()) {
                                        //VALIDAMOS QUE EL PARTICIPANTE HAYA RESPONDIDO ANTERIORMENTE
                                        Respuestas respuestaProcesada = new AvayaSpacesContestImpl().obtenerRespuestasPorCorreoParticipanteIdPreguntaIdConcurso(jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"), pregunta.getIdpregunta(), pregunta.getIdconcurso());
                                        if (respuestaProcesada == null) {
                                            //EL PARTICIPANTE NO HA CONTESTADO
                                            int acierto = 0;
                                            int error = 0;
                                            //DEBEMOS VALIDAR LA RESPUESSTA
                                            if (pregunta.getRespuestauno().equals("true") && respuesta.equals("A")) {
                                                if (acierto == 0) {
                                                    acierto = pregunta.getPoints();
                                                }
                                            } else if (pregunta.getRespuestados().equals("true") && respuesta.equals("B")) {
                                                if (acierto == 0) {
                                                    acierto = pregunta.getPoints();
                                                }
                                            } else if (pregunta.getRespuestatres().equals("true") && respuesta.equals("C")) {
                                                if (acierto == 0) {
                                                    acierto = pregunta.getPoints();
                                                }
                                            } else if (pregunta.getRespuestacuatro().equals("true") && respuesta.equals("D")) {
                                                if (acierto == 0) {
                                                    acierto = pregunta.getPoints();
                                                }
                                            } else {
                                                error = 1;
                                            }
                                            try {

                                                new AvayaSpacesContestImpl().crearRespuestaPorPorticipante(
                                                        new Respuestas(
                                                                UUID.randomUUID().toString(),
                                                                pregunta.getIdpregunta(),
                                                                pregunta.getIdconcurso(),
                                                                integranteEnrolado.getIdintegrante(),
                                                                jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"),
                                                                jsonArray.getJSONObject(i).getJSONObject("sender").getString("displayname"),
                                                                jsonArray.getJSONObject(i).getJSONObject("sender").getString("picture_url"),
                                                                acierto,
                                                                error,
                                                                jsonArray.getJSONObject(i).getJSONObject("content").getString("bodyText"),
                                                                new DateFormatedNow().getDateNow(),
                                                                "ACTIVO",
                                                                jsonArray.getJSONObject(i).getString("topicId"),
                                                                pregunta.getIdevento(),
                                                                pregunta.getLlave()
                                                        ));

                                            } catch (JSONException e) {
                                                System.out.println("Error al crear respuesta Integrante: " + e.toString());
                                            }

                                        } else {
                                            //EL PARTICIPANTE YA RESPONDIO DEBEMOS DE EDITAR SU RESPUESTA...
                                            System.out.println("EL PARTICIPANTE YA RESPONDIO");
                                            try {
                                                //REINICIAMOS LA RESPUESTA ACIERTOS Y ERRORES A 0
//                                        try {
//                                            new AvayaSpacesContestImpl().resetAciertosAndErrorsByIdColumn(respuestaProcesada.getId());
//                                        } catch (Exception e) {
//                                            System.out.println("Error al reiniciar aciertos : " + e.toString());
//                                        }
                                                System.out.println("Reiniciamos Los Puntos");
                                                int updateAcierto = 0;
                                                int updateError = 0;
                                                //DEBEMOS VALIDAR LA RESPUESSTA
                                                if (pregunta.getRespuestauno().equals("true") && respuesta.equals("A")) {
                                                    if (updateAcierto == 0) {
                                                        updateAcierto = pregunta.getPoints();
                                                    }
                                                } else if (pregunta.getRespuestados().equals("true") && respuesta.equals("B")) {
                                                    if (updateAcierto == 0) {
                                                        updateAcierto = pregunta.getPoints();
                                                    }
                                                } else if (pregunta.getRespuestatres().equals("true") && respuesta.equals("C")) {
                                                    if (updateAcierto == 0) {
                                                        updateAcierto = pregunta.getPoints();
                                                    }
                                                } else if (pregunta.getRespuestacuatro().equals("true") && respuesta.equals("D")) {
                                                    if (updateAcierto == 0) {
                                                        updateAcierto = pregunta.getPoints();
                                                    }
                                                } else {
                                                    updateError = 1;
                                                }
                                                //EDITAMOS EL REGISTRO DEL PARTICIPANTE CON LA NUEVA RESPUESTA ENVIADA...
                                                new AvayaSpacesContestImpl().updateAciertosAndErroresByRespuestasObject(respuestaProcesada, updateAcierto, updateError, jsonArray.getJSONObject(i).getJSONObject("content").getString("bodyText"), pregunta.getLlave());
                                            } catch (JSONException ex) {
                                                System.out.println("Error al reiniciar aciertos y errores: " + ex.toString());
                                            }
                                        }
                                    } else {
                                        //EL MENSAJE NO CONTIENE LA LLAVE
                                        System.out.println("LA RESPUESTA ESTA VACIA");
                                    }
                                } catch (JSONException e) {
                                    System.out.println("NO HAY RESPUESTA");
                                }
                            } else {
                                System.out.println("NO HAY BODY TEXT");
                            }
                        }
                    } else {
                        System.out.println("MENSAJE DE PARTICIPANTE NO ENROLADO : " + jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"));
                    }
                }
            }

        });

        socketQANDR.connect();
    }

    public void suscribeIntegrantesTopicID() {
        //NOS SUSCRIBIMOS AL CHAT DE CADA PARTICIPANTE
        List<Integrantesconcurso> integrantesconcursos = new AvayaSpacesContestImpl().obtenerIntegrantesPorIdConcursoAsistenciaTrueAndStatusActive(pregunta.getIdconcurso(), pregunta.getIdevento(), pregunta.getLlave());
        if (!integrantesconcursos.isEmpty()) {
            for (Integrantesconcurso integrantesconcurso : integrantesconcursos) {
                if (!integrantesconcurso.getTopicid().isEmpty()) {
                    JSONObject payLoad = new JSONObject()
                            .put("channel", new JSONObject()
                                    .put("type", "topic").put("_id", integrantesconcurso.getTopicid()));
                    socketQANDR.emit("SUBSCRIBE_CHANNEL", payLoad);
                }
            }

        }
    }

    public void sendMessage(String Message, String topicID) {

    }

    class RemindTaskListenMessage extends TimerTask {

        public void run() {
            System.out.println("Time's up!");
            socketQANDR.disconnect();
            timer.cancel(); //Terminate the timer thread
        }
    }

    private String getPregunta() {
        return "<small>&#10067<mark><b>" + pregunta.getPregunta() + "</b></mark><br>"
                + "<b>A</big></b>.- " + pregunta.getOpciona() + "<br>"
                + "<b>B</big></b>.- " + pregunta.getOpcionb() + "<br>"
                + "<b>C</big></b>.- " + pregunta.getOpcionc() + "<br>"
                + "<b>D</big></b>.- " + pregunta.getOpciond() + "<br>"
                + "Use la letra de su eleccion</small>";
    }
}
