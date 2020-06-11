package service.SpacesContest.SoketIO;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.json.JSONArray;
import org.json.JSONObject;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.ssl.util.SSLProtocolType;
import com.avaya.collaboration.ssl.util.SSLUtilityException;
import com.avaya.collaboration.ssl.util.SSLUtilityFactory;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import service.SpacesContest.Entity.Integrantesconcurso;
import service.SpacesContest.Util.AttributeStore;
import service.SpacesContest.Util.Constants;


/**
 *
 * @author umansilla
 */
public class EnrollProcesQuizConfirmation {
    Timer timer;
    private io.socket.client.Socket socketConfirmation;
    private final List<Integrantesconcurso> integrantesConcurso;
    private final String llave;
    public EnrollProcesQuizConfirmation(int seconds, List<Integrantesconcurso> integrantesConcurso, String llave) throws URISyntaxException, SSLUtilityException, NoAttributeFoundException, ServiceNotFoundException {
        timer = new Timer();
        timer.schedule(new EnrollProcesQuizConfirmation.RemindTaskListenMessage(), seconds * 1000);
        this.integrantesConcurso = integrantesConcurso;
        this.llave = llave;
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
		socketConfirmation = IO.socket("https://" + Constants.FQDN_AVAYA_CLOUD + "/chat", opts);

        //socket.on
        socketConfirmation.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("SendResponseRecivedConfirmation Connected");
                suscribeIntegrantesTopicID();
            }

        }).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("SendResponseRecivedConfirmation Disonnected");
            }

        });

        socketConfirmation.on("CHANNEL_SUBSCRIBED", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                System.out.println("CHANNEL_SUBSCRIBED");
                JSONArray jsonArray = new JSONArray(Arrays.toString(os));
                //EJEMPLO PARA RETORNAR MENSAJE
                JSONObject json = new JSONObject()
                        .put("content", new JSONObject().put("bodyText", "You are successfully enrolled in the quiz " + llave))
                        .put("sender", new JSONObject().put("_id", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id")).put("type", "user"))
                        .put("category", "chat")
                        .put("topicId", jsonArray.getJSONObject(0).getJSONObject("channel").getString("_id"));
                socketConfirmation.emit("SEND_MESSAGE", json);
            }

        });

        socketConfirmation.connect();
    }

    public void suscribeIntegrantesTopicID() {
        //NOS SUSCRIBIMOS AL CHAT DE CADA PARTICIPANTE
        if (!integrantesConcurso.isEmpty()) {
            System.out.println("Confirmaciones por enviar: " + integrantesConcurso.size());
            for (Integrantesconcurso integrante : integrantesConcurso) {
                if (!integrante.getTopicid().isEmpty()) {
                    JSONObject payLoad = new JSONObject()
                            .put("channel", new JSONObject()
                                    .put("type", "topic").put("_id", integrante.getTopicid()));
                    socketConfirmation.emit("SUBSCRIBE_CHANNEL", payLoad);
                }
            }
        }
    }

    class RemindTaskListenMessage extends TimerTask {

        public void run() {
            System.out.println("Time's up!");
            socketConfirmation.disconnect();
            timer.cancel(); //Terminate the timer thread
        }
    }
}
