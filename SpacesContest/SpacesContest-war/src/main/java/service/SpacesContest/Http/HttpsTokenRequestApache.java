package service.SpacesContest.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.avaya.collaboration.ssl.util.SSLProtocolType;
import com.avaya.collaboration.ssl.util.SSLUtilityException;
import com.avaya.collaboration.ssl.util.SSLUtilityFactory;
import com.avaya.collaboration.util.logger.Logger;

import service.SpacesContest.Beans.UserBean;
import service.SpacesContest.Util.Constants;


public class HttpsTokenRequestApache {
	
	private final SSLProtocolType protocolTypeAssistant;
	private final SSLContext sslContextAssistant;
	public HttpsTokenRequestApache() throws SSLUtilityException {
		this.protocolTypeAssistant = SSLProtocolType.TLSv1_2;
		this.sslContextAssistant = SSLUtilityFactory.createSSLContext(this.protocolTypeAssistant);
	}

	public JSONObject accessRequest(UserBean userBean, String password, String pais, String cliente)
			throws UnsupportedOperationException, IOException {
		final String URI = Constants.TOKEN_END_POINT_197;
		final HttpClient client = HttpClients.custom()
				.setSSLContext(sslContextAssistant)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "Basic " + Constants.TOKEN_OAUTH_197);
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();  
		StringBody userName = new StringBody(userBean.getUserName(), ContentType.TEXT_PLAIN);
		StringBody passwordBody = new StringBody(password, ContentType.TEXT_PLAIN);
		StringBody paisBody = new StringBody(pais, ContentType.TEXT_PLAIN);
		StringBody clienteBody = new StringBody(cliente, ContentType.TEXT_PLAIN);
		StringBody grantType = new StringBody(Constants.GRANT_TYPE_ONE, ContentType.TEXT_PLAIN);
		reqEntity.addPart(Constants.USER_PART, userName);
		reqEntity.addPart(Constants.PASSWORD_PART, passwordBody);
		reqEntity.addPart("pais", paisBody);
		reqEntity.addPart("cliente", clienteBody);
		reqEntity.addPart(Constants.GRANT_TYPE , grantType);
        HttpEntity entity = reqEntity.build();	
        postMethod.setEntity(entity);
        final HttpResponse response = client.execute(postMethod);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.ISO_8859_1));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        System.out.println("Access  " + result.toString());
		return new JSONObject(result.toString()).put("code", response.getStatusLine().getStatusCode());
	}

	public JSONObject refreshAccessRequest(String refreshToken) throws UnsupportedOperationException, IOException {
		final String URI = Constants.TOKEN_END_POINT_197;
		final HttpClient client = HttpClients.custom()
				.setSSLContext(sslContextAssistant)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "Basic " + Constants.TOKEN_OAUTH_197);
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();  
		StringBody refreshTokenPart = new StringBody(refreshToken, ContentType.TEXT_PLAIN);
		StringBody grantType = new StringBody(Constants.GRANT_TYPE_TWO, ContentType.TEXT_PLAIN);
		reqEntity.addPart(Constants.REFRESH_TOKEN, refreshTokenPart);
		reqEntity.addPart(Constants.GRANT_TYPE, grantType);
		HttpEntity entity = reqEntity.build();	
        postMethod.setEntity(entity);
        final HttpResponse response = client.execute(postMethod);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.ISO_8859_1));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        System.out.println("Refresh  " + result.toString());
        return new JSONObject(result.toString()).put("code", response.getStatusLine().getStatusCode());
	}

	public JSONObject validateAccessToken(String accessToken) throws ClientProtocolException, IOException {
		final String URI = Constants.TOKEN_END_POINT_197;
		final HttpClient client = HttpClients.custom()
				.setSSLContext(sslContextAssistant)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
		final HttpPost postMethod = new HttpPost(URI);
		postMethod.addHeader("Authorization", "Basic " + Constants.TOKEN_OAUTH_197);
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
		StringBody accessTokenPart = new StringBody(accessToken, ContentType.TEXT_PLAIN);
		StringBody grantType = new StringBody(Constants.GRANT_TYPE_THREE, ContentType.TEXT_PLAIN);
		reqEntity.addPart(Constants.ACCESS_TOKEN, accessTokenPart);
		reqEntity.addPart(Constants.GRANT_TYPE, grantType);
		HttpEntity entity = reqEntity.build();	
        postMethod.setEntity(entity);
        final HttpResponse response = client.execute(postMethod);
        final BufferedReader inputStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), StandardCharsets.ISO_8859_1));
        String line = "";
        final StringBuilder result = new StringBuilder();
        while ((line = inputStream.readLine()) != null) {
            result.append(line);
        }
        System.out.println("Validate  " + result.toString());
        return new JSONObject(result.toString()).put("code", response.getStatusLine().getStatusCode());
	}
}
