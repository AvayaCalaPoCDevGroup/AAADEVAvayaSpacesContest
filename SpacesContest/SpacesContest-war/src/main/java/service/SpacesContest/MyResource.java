package service.SpacesContest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import service.SpacesContest.API.ApiActions;

/*
 * This class is needed if you are trying to write a JAX-RS based web service. You can enhance this as needed for the application.
 * 
 * For applications which provide call related features only and web service is not required, remove this class.
 * 
 */

@Path("/SpacesContest")
public class MyResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String doGet() {
		return new JSONObject().put("status", "ook").put("message", "The API Works").put("code", 200).toString();
	}

	@POST
	@Path("/Events/{idEvento}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDatosDelEvento(@PathParam("idEvento") String idEvento) {
		return new ApiActions().obtenerEventoPorIdEvento(idEvento);
	}
	
	@POST
	@Path("/Events/RegisterPageInfo/{idEvento}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDatosParaPaginaDeRegistroDelEvento(@PathParam("idEvento") String idEvento) {
		return new ApiActions().obtenerDatosParalaPáginaDeRegistro(idEvento);
	}
	
	@POST
	@Path("/Events/PlayersEvent/{idIntegrante}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIntegranteEventoByIdIntegrante(@PathParam("idIntegrante") String idIntegrante) {
		return new ApiActions().obtenerIntegranteEventoByIdIntegrante(idIntegrante);
	}
	
	@POST
	@Path("/Events/PlayersByEvent/{idEvento}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIntegrantesPorIdEvento(@PathParam("idEvento") String idEvento) {
		return new ApiActions().getIntegrantesPorIdEvento(idEvento);
	}
	
	@POST
	@Path("/Events/PlayersByEvent/{idEvento}/{correoIntegrante}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIntegrantesPorIdEvento(@PathParam("idEvento") String idEvento, @PathParam("correoIntegrante") String correoIntegrante) {
		return new ApiActions().getIntegranteByIdEventoAndCorreoIntegrante(idEvento, correoIntegrante);
	}

	@POST
	@Path("/Events/PlayersEvent/Register")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public String registerIntegranteEvento(String jsonObjectEncrypted) {
		return new ApiActions().registrarIntegranteAEvento(jsonObjectEncrypted);
	}
	

}