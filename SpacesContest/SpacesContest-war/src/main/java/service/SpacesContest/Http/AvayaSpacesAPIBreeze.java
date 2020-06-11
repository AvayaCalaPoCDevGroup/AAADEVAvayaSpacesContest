package service.SpacesContest.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.ssl.util.SSLProtocolType;
import com.avaya.collaboration.ssl.util.SSLUtilityException;
import com.avaya.collaboration.ssl.util.SSLUtilityFactory;
import com.avaya.collaboration.util.logger.Logger;

import service.SpacesContest.Util.AttributeStore;
import service.SpacesContest.Util.Constants;

public class AvayaSpacesAPIBreeze {

	private final SSLProtocolType protocolType;
	private final SSLContext sslContext;
	private final Logger logger = Logger.getLogger(getClass());

	public AvayaSpacesAPIBreeze() throws SSLUtilityException {
		this.protocolType = SSLProtocolType.TLSv1_2;
		this.sslContext = SSLUtilityFactory.createSSLContext(this.protocolType);
	}

	public JSONObject createSpaceAndInviteUser(String nombre, String fecha)
			throws ClientProtocolException, IOException, NoAttributeFoundException, ServiceNotFoundException {
		final String URI = "https://spaces.avayacloud.com/api/spaces/invite";
		final HttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);
		JSONObject jsonPayLoad = new JSONObject()
				.put("topic",
						new JSONObject().put("title", nombre).put("description", nombre + " " + fecha).put("type",
								"group"))
				.put("invitees",
						new JSONArray().put(new JSONObject().put("inviteeType", "email")
								.put("invitee", "umansilla@avaya.com").put("role", "admin")))
				.put("informChannel", "server");
		postMethod.addHeader("Authorization", "jwt " + AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_JWT_AVAYA_CLOUD));
		postMethod.addHeader("Content-Type", "application/json");
		final StringEntity payload = new StringEntity(jsonPayLoad.toString(), StandardCharsets.ISO_8859_1);
		postMethod.setEntity(payload);

		final HttpResponse response = client.execute(postMethod);

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "";
		final StringBuilder result = new StringBuilder();
		while ((line = inputStream.readLine()) != null) {
			result.append(line);
		}
		logger.info(result.toString());
		return new JSONObject(result.toString());
	}

	public JSONObject createNewSpacePorNuevoEventoInformChannelCliente(String nombreEvento, String creadoPor)
			throws ClientProtocolException, IOException, NoAttributeFoundException, ServiceNotFoundException {
		final String URI = "https://spaces.avayacloud.com/api/spaces/invite";
		final HttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);

		JSONObject jsonPayLoad = new JSONObject()
				.put("topic",
						new JSONObject().put("id", "").put("title", nombreEvento).put("description", nombreEvento)
								.put("type", "group"))
				.put("invitees", new JSONArray().put(
						new JSONObject().put("inviteeType", "email").put("invitee", creadoPor).put("role", "admin")))
				.put("informChannel", "client");
		postMethod.addHeader("Authorization", "jwt " + AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_JWT_AVAYA_CLOUD));
		postMethod.addHeader("Content-Type", "application/json");
		final StringEntity payload = new StringEntity(jsonPayLoad.toString(), StandardCharsets.ISO_8859_1);
		postMethod.setEntity(payload);

		final HttpResponse response = client.execute(postMethod);

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "";
		final StringBuilder result = new StringBuilder();
		while ((line = inputStream.readLine()) != null) {
			result.append(line);
		}
		logger.info(result.toString());
		return new JSONObject(result.toString());
	}
	
	public JSONObject changeSpaceToPrivate(String spaceId, String spaceName) throws ClientProtocolException, IOException, NoAttributeFoundException, ServiceNotFoundException {
		JSONObject jsonPayLoad = new JSONObject().put("title", spaceName)
				.put("restrict", new JSONArray().put("deny_guest_access_history_msg"))
				.put("settings",
						new JSONObject().put("memberOnly", true).put("passwordRequired",
								new JSONObject().put("allGuests", true)))
				.put("msgRetention", new JSONObject().put("retentionLength", 0).put("retentionType", "forever")
						.put("notInheritParentPolicy", false));
		final String URI = "https://spaces.avayacloud.com/api/spaces/"+spaceId;
		final HttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "jwt "+ AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_JWT_AVAYA_CLOUD));
		postMethod.addHeader("Content-Type", "application/json");

		final StringEntity payload = new StringEntity(jsonPayLoad.toString(), StandardCharsets.ISO_8859_1);
		postMethod.setEntity(payload);
		final HttpResponse response = client.execute(postMethod);

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "";
		final StringBuilder result = new StringBuilder();
		while ((line = inputStream.readLine()) != null) {
			result.append(line);
		}

		logger.info(result.toString());
		return new JSONObject(result.toString());

	}
	
	public JSONObject changeSpaceToPrivateUserSettings(String spaceId)
			throws ClientProtocolException, IOException, NoAttributeFoundException, ServiceNotFoundException {
		JSONObject jsonPayload = new JSONObject().put("isPinned", false).put("notification", false);
		final String URI = "https://spaces.avayacloud.com/api/users/me/topics/"+spaceId+"/preference";
		final HttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "jwt "+ AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_JWT_AVAYA_CLOUD));
		postMethod.addHeader("Content-Type", "application/json");

		final StringEntity payload = new StringEntity(jsonPayload.toString(), StandardCharsets.ISO_8859_1);
		postMethod.setEntity(payload);
		final HttpResponse response = client.execute(postMethod);

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "";
		final StringBuilder result = new StringBuilder();
		while ((line = inputStream.readLine()) != null) {
			result.append(line);
		}
		logger.info(result.toString());
		return new JSONObject(result.toString());
	}
	
	public JSONObject enviarInvitaciónInformChannelClient(String emailAvayaSpaces, String spaceID)
			throws ClientProtocolException, IOException, NoAttributeFoundException, ServiceNotFoundException {
		JSONObject jsonPayLoad = new JSONObject()
				.put("invitees",
						new JSONArray().put(new JSONObject().put("inviteeType", "email")
								.put("invitee", emailAvayaSpaces).put("role", "member")))
				.put("informChannel", "client");

		final String URI = "https://spaces.avayacloud.com/api/spaces/" + spaceID + "/invite";
		final HttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "jwt "+ AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_JWT_AVAYA_CLOUD));
		postMethod.addHeader("Content-Type", "application/json");

		final StringEntity payload = new StringEntity(jsonPayLoad.toString(), StandardCharsets.ISO_8859_1);
		postMethod.setEntity(payload);
		final HttpResponse response = client.execute(postMethod);

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "";
		final StringBuilder result = new StringBuilder();
		while ((line = inputStream.readLine()) != null) {
			result.append(line);
		}
		logger.info(result.toString());
		return new JSONObject(result.toString());
	}
	
	/*
	 * DELETE REQUEST
	 */

	public JSONObject deleteSpaceBySpaceID(String spaceID) throws ClientProtocolException, IOException, NoAttributeFoundException, ServiceNotFoundException {
		final String URI = "https://spaces.avayacloud.com/api/spaces/" + spaceID;
		final HttpClient client = HttpClients.custom().setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpDelete deleteMethod = new HttpDelete(URI);
		deleteMethod.addHeader("Authorization", "jwt " + AttributeStore.INSTANCE.getAttributeValue(Constants.ATTRIBUTE_JWT_AVAYA_CLOUD));
		deleteMethod.addHeader("Content-Type", "application/json");
		final HttpResponse response = client.execute(deleteMethod);

		final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line = "";
		final StringBuilder result = new StringBuilder();
		while ((line = inputStream.readLine()) != null) {
			result.append(line);
		}
		logger.info(result.toString());
		return new JSONObject(result.toString());
	}
}
