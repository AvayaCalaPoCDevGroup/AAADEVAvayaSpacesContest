package service.SpacesContest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import service.SpacesContest.Security.AES;
import service.SpacesContest.Util.Constants;

public class Test {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
//		JSONObject jsonObject = new JSONObject()
//				.put("idEvento", "26a76520-39f2-4c34-820a-2f66f1cacecf")
//		.put("nombreintegrante", "Integrante Falso")
//		.put("apellidointegrante", "Fake")
//		.put("correointegrante", "fake@gmail.com")
//		.put("avayaSpacesUserName", "fake@gmail.com")
//		.put("role", "rol")
//		.put("pictureurl", "https://breeze2-132.collaboratory.avaya.com")
//		.put("empresa", "AVAYA")
//		.put("puesto", "Developer")
//		.put("pais", "CDMX")
//		.put("ciudad", "CDMX")
//		.put("estado", "CDMX")
//		.put("cp", "15550")
//		.put("telefonoOficina", "55635556882")
//		.put("telefonoMovil", "55635556882")
//		.put("facebook", "kumb")
//		.put("twitter", "kumn")
//		.put("companysFunction", "companysFunction")
//		.put("companysEmployees", "companysEmployees")
//		.put("companysJobLevel", "companysJobLevel")
//		.put("companysJobFunction", "companysJobFunction")
//		.put("companyITBudget", "companyITBudget")
//		.put("companyPurchaseRole", "companyPurchaseRole")
//		.put("attendedYears", "attendedYears")
//		.put("chekBoxTechologies", false)
//		.put("checkBoxOEM", false)
//		.put("checkBoxAvayaDevConnect", false);
//		
//		System.out.println(jsonObject.toString(2));
//		
//		System.out.println(new AES().encrypt(jsonObject.toString()));

        final String URI = Constants.TOKEN_END_POINT_132;
        CloseableHttpClient client = HttpClients.createDefault();
        final HttpPost postMethod = new HttpPost(URI);
        postMethod.addHeader("Authorization", "Basic " + Constants.TOKEN_OAUTH_132);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        StringBody userName = new StringBody("umansilla@avaya.com", ContentType.TEXT_PLAIN);
        StringBody passwordBody = new StringBody("123465", ContentType.TEXT_PLAIN);
        StringBody paisBody = new StringBody("CDMX", ContentType.TEXT_PLAIN);
        StringBody clienteBody = new StringBody("Developers", ContentType.TEXT_PLAIN);
        StringBody grantType = new StringBody(Constants.GRANT_TYPE_ONE, ContentType.TEXT_PLAIN);
        reqEntity.addPart(Constants.USER_PART, userName);
        reqEntity.addPart(Constants.PASSWORD_PART, passwordBody);
        reqEntity.addPart("pais", paisBody);
        reqEntity.addPart("cliente", clienteBody);
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
        System.out.println("Access  " + result.toString());
        System.out.println("TEST");
    }

}
