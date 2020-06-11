package service.SpacesContest.Actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

import service.SpacesContest.Beans.UserBean;
import service.SpacesContest.Entity.Concursos;
import service.SpacesContest.Service.Impl.AvayaSpacesContestImpl;

/**
 *
 * @author umansilla
 */
public class AvayaSpacesContestGetConcursoActions {

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final UserBean userBeanSession;
    private final Gson gson = new Gson();
    public AvayaSpacesContestGetConcursoActions(HttpServletRequest request, HttpServletResponse response, UserBean userBeanSession) {
        this.request = request;
        this.response = response;
        this.userBeanSession = userBeanSession;
    }

    public void getConcurso() throws ServletException, IOException {
        if (request.getParameter("c") != null) {
            Concursos concurso = new AvayaSpacesContestImpl().obtenerConcursoPorIdConcurso(request.getParameter("c"));
            if(concurso != null){
                JSONObject jsonResponse = new JSONObject().put("concurso", new JSONObject(gson.toJson(concurso)));
                request.setAttribute("concurso", jsonResponse.toString());
                request.getRequestDispatcher("/Home/pages/concurso.jsp").forward(request, response);
            }else{
                request.getRequestDispatcher("/Home/pages/404.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/Home/pages/404.jsp").forward(request, response);
        }
    }
}
