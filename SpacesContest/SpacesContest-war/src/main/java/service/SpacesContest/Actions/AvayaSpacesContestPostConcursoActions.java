package service.SpacesContest.Actions;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.ssl.util.SSLUtilityException;

import service.SpacesContest.Beans.UserBean;
import service.SpacesContest.SoketIO.EnrollProcessQuiz;
import service.SpacesContest.Util.ConstantsHttpResponse;


/**
 *
 * @author umansilla
 */
public class AvayaSpacesContestPostConcursoActions {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserBean userBeanSession;
    public AvayaSpacesContestPostConcursoActions(HttpServletRequest request, HttpServletResponse response, UserBean userBeanSession) {
        this.request = request;
        this.response = response;
        this.userBeanSession = userBeanSession;
    }
    
    public void doEnrol(JSONObject jsonObject) throws ServletException, IOException, URISyntaxException, NumberFormatException, JSONException, SSLUtilityException{
    	System.out.println("Task scheduled.");
        try {
			new EnrollProcessQuiz(Integer.parseInt(jsonObject.getString("time")), jsonObject.getString("llave"), jsonObject.getString("idConcurso"), jsonObject.getString("idevento"));
		} catch (NumberFormatException | JSONException | URISyntaxException | SSLUtilityException
				| NoAttributeFoundException | ServiceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.getWriter().println(ConstantsHttpResponse.RESPONSE_200("Timers ON"));
    }
}
