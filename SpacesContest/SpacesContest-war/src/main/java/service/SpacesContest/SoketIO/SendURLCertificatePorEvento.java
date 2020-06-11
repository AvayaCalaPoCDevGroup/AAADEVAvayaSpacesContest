package service.SpacesContest.SoketIO;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import service.SpacesContest.Entity.Integrantesevento;
import service.SpacesContest.Entity.Respuestas;
import service.SpacesContest.Service.Impl.AvayaSpacesContestImpl;
import service.SpacesContest.Util.Constants;

/**
 *
 * @author umansilla
 */
public class SendURLCertificatePorEvento {

    Timer timer;
    private io.socket.client.Socket socketCertifiates;
    private final String idEvento;
    private final Logger logger = Logger.getLogger(getClass());
    
    public SendURLCertificatePorEvento(int seconds, String idEvento) throws URISyntaxException {
        timer = new Timer();
        timer.schedule(new SendURLCertificatePorEvento.RemindTaskListenMessage(), seconds * 1000);
        this.idEvento = idEvento;
        connectSocketAvayaSpaces();
    }

    public void connectSocketAvayaSpaces() throws URISyntaxException {
        IO.Options opts = new IO.Options();
        String[] web = new String[1];
        web[0] = "websocket";
        opts.query = "tokenType=jwt&token=" + Constants.JWT_EQUIZ_BOT;
        opts.transports = web;
        socketCertifiates = IO.socket("https://" + Constants.FQDN_AVAYA_CLOUD + "/chat", opts);

        //socket.on
        socketCertifiates.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
            	logger.info("SendURLCertificatePorEvento EVENT_CONNECT");
                suscribeIntegrantesTopicID();
            }

        }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
            	logger.info("SendURLCertificatePorEvento EVENT_DISCONNECT");
            }

        });

        socketCertifiates.on("CHANNEL_SUBSCRIBED", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println("CHANNEL_SUBSCRIBED");
                JSONArray jsonArray = new JSONArray(Arrays.toString(os));
                //EJEMPLO PARA RETORNAR MENSAJE
                Respuestas respuesta = new AvayaSpacesContestImpl().obtenerCorreoSpacesByIdEventoAndTopicId(idEvento, jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id"));
                if (respuesta != null) {
                    Integrantesevento integranteEvento = new AvayaSpacesContestImpl().obtenerIntegranteEventoPorCorreoAvayaSpaces(respuesta.getEmailintegrante());
                    if (integranteEvento != null) {
                        JSONObject json = new JSONObject()
                                .put("content", new JSONObject().put("bodyText", "Download your certificate at the following link. https://avayacalaevents.appspot.com/Certificado?u=" + integranteEvento.getIdintegrante()))
                                .put("sender", new JSONObject().put("_id", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id")).put("type", "user"))
                                .put("category", "chat")
                                .put("topicId", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id"));
                        socketCertifiates.emit("SEND_MESSAGE", json);
                    }

                }

            }

        });

        socketCertifiates.connect();
    }

    public void suscribeIntegrantesTopicID() {
        //NOS SUSCRIBIMOS AL CHAT DE CADA PARTICIPANTE
        List<Integrantesevento> integrantesEvento = new AvayaSpacesContestImpl().obtenerIntegrantesEventoPorIdEvento(idEvento);
        for (Integrantesevento integrantesevento : integrantesEvento) {
            Respuestas respuesta = new AvayaSpacesContestImpl().obtenerTipicIdPorIdEventoYCorreoSpaces(idEvento, integrantesevento.getCorreoavayaspaces());
            if (respuesta != null) {
                if (!respuesta.getTopicid().isEmpty()) {
                    JSONObject payLoad = new JSONObject()
                            .put("channel", new JSONObject()
                                    .put("type", "topic").put("_id", respuesta.getTopicid()));
                    socketCertifiates.emit("SUBSCRIBE_CHANNEL", payLoad);
                }
            }

        }
    }

    class RemindTaskListenMessage extends TimerTask {

        public void run() {
        	logger.info("SendURLCertificatePorEvento Time Is up!");
            socketCertifiates.disconnect();
            timer.cancel(); //Terminate the timer thread
        }
    }
}
