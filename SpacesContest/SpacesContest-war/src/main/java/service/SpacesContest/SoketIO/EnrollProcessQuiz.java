package service.SpacesContest.SoketIO;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import service.SpacesContest.Entity.Integrantesevento;
import service.SpacesContest.Service.Impl.AvayaSpacesContestImpl;
import service.SpacesContest.Util.AttributeStore;
import service.SpacesContest.Util.Constants;
import service.SpacesContest.Util.DateFormatedNow;


/**
 *
 * @author umansilla
 */
public final class EnrollProcessQuiz {

    Timer timer;
    private io.socket.client.Socket socket;
    private final String regex;
    private final String idConcurso;
    private final String idEvento;
    public EnrollProcessQuiz(int seconds, String llave, String idConcurso, String idEvento) throws URISyntaxException, SSLUtilityException, NoAttributeFoundException, ServiceNotFoundException {
        timer = new Timer();
        timer.schedule(new EnrollProcessQuiz.RemindTaskListenMessage(), seconds * 1000);
        this.regex = llave;
        this.idConcurso = idConcurso;
        this.idEvento = idEvento;
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
		socket = IO.socket("https://" + Constants.FQDN_AVAYA_CLOUD + "/chat", opts);

        //socket.on
        socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("EnrollProcessQuiz Connected");
            }

        }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("EnrollProcessQuiz Disonnected");
            }

        });

        socket.on("SEND_MESSAGE_FAILED", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println("SEND_MESSAGE_FAILED");
                System.out.println(new JSONArray(Arrays.toString(os)).toString(2));;
            }
        });

        socket.on("CHANNEL_SUBSCRIBED", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println("CHANNEL_SUBSCRIBED");
                JSONArray jsonArray = new JSONArray(Arrays.toString(os));
                //EJEMPLO PARA RETORNAR MENSAJE
                JSONObject json = new JSONObject()
                        .put("content", new JSONObject().put("bodyText", "Successfully subscribed to the contest with the key " + regex))
                        .put("sender", new JSONObject().put("_id", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id")).put("type", "user"))
                        .put("category", "chat")
                        .put("topicId", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id"));
                socket.emit("SEND_MESSAGE", json);
            }

        });
        socket.on("MESSAGE_SENT", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                final Pattern pattern = Pattern.compile(regex.toUpperCase());
                JSONArray jsonArray = new JSONArray(Arrays.toString(os));
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Integrantesevento integranteEnroladoEnEvento = new AvayaSpacesContestImpl().obtenerIntegranteEventoPorCorreoAvayaSpacesAndIdEvento(jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"), idEvento);
                        if (integranteEnroladoEnEvento != null) {
                            if (jsonArray.getJSONObject(i).getJSONObject("content").has("bodyText")) {
                                //BUSCAMOS EN EL MENSAJE QUE ENVÍA LA LLAVE
                                final Matcher matcher = pattern.matcher(jsonArray.getJSONObject(i).getJSONObject("content").getString("bodyText").toUpperCase());
                                if (matcher.find()) {
                                    System.out.println("CONTIENE LA LLAVE");
                                    //INTEGRANTE REGISTRADO A UNA SALA
                                    if (jsonArray.getJSONObject(i).has("sender")) {
                                        if (!jsonArray.getJSONObject(i).getJSONObject("sender").getString("username").equals("avaya.notificaciones@gmail.com")) {
                                            Integrantesconcurso integrante = new AvayaSpacesContestImpl().obtenerIntegranteConcursoPorEmailSpacesAndIdConcurso(jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"), idConcurso, regex);
                                            if (integrante == null) {
                                                //INTEGRANTE NO REGISTRADO.
                                                try {
                                                    //CREAMOS INTEGRANTE EN CONCURSO.
                                                    new AvayaSpacesContestImpl().crearIntegrante(
                                                            new Integrantesconcurso(
                                                                    idConcurso,
                                                                    UUID.randomUUID().toString(),
                                                                    jsonArray.getJSONObject(i).getJSONObject("sender").getString("displayname"),
                                                                    jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"),
                                                                    "00000000",
                                                                    "EMPTY",
                                                                    jsonArray.getJSONObject(i).getJSONObject("sender").getString("picture_url"),
                                                                    0,
                                                                    0,
                                                                    "TRUE",
                                                                    new DateFormatedNow().getDateNow(),
                                                                    "ACTIVO",
                                                                    "umansilla@avaya.com",
                                                                    jsonArray.getJSONObject(i).getString("topicId"),
                                                                    idEvento,
                                                                    regex
                                                            )
                                                    );

                                                } catch (JSONException e) {
                                                    System.out.println("Error al crear integrante " + e.toString());
                                                }

                                                //REPONDEMOS POR WEB SOCKET, ES CUESTION DE SUSCRIBIRSE
                                                JSONObject payLoad = new JSONObject()
                                                        .put("channel", new JSONObject()
                                                                .put("type", "topic").put("_id", jsonArray.getJSONObject(i).getString("topicId")));
                                                socket.emit("SUBSCRIBE_CHANNEL", payLoad);

                                            } else {
                                                //INTEGRANTE YA REGISTRADO.
                                                System.out.println("USUARIO REGISTRADO ACTUALMENTE EN EL CONCURSO");
                                            }

                                        }
                                    }
                                } else {
                                    //EL MENSAJE NO CONTIENE LA LLAVE
                                    System.out.println("ENROLL QUIZ NO CONTIENE LA LLAVE");
                                }
                            } else {
                                System.out.println("NO HAY BODY TEXT");
                            }
                        } else {
                            //LA PERSONA SE QUIERE ENROLAR Y NO ESTA REGISTRADA EN EL EVENTO
                            System.out.println("LA PERSONA SE QUIERE ENROLAR Y NO ESTA REGISTRADA EN EL EVENTO " + jsonArray.getJSONObject(i).getJSONObject("sender").getString("username"));
                        }
                    } catch (JSONException e) {
                        System.out.println(jsonArray.getJSONObject(i).toString(2));
                        System.out.println("Error Enroll: " + e.toString());
                    }

                }
            }

        });

        socket.connect();
    }

    class RemindTaskListenMessage extends TimerTask {

        public void run() {
            System.out.println("Time's up!");
            socket.disconnect();
            timer.cancel(); //Terminate the timer thread
        }
    }

}
