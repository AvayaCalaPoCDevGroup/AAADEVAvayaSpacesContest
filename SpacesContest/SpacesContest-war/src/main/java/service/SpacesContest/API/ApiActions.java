package service.SpacesContest.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import com.avaya.collaboration.businessdata.api.NoAttributeFoundException;
import com.avaya.collaboration.businessdata.api.ServiceNotFoundException;
import com.avaya.collaboration.ssl.util.SSLUtilityException;
import com.google.gson.Gson;

import service.SpacesContest.API.Util.ApiUtil;
import service.SpacesContest.Beans.CetificateBean;
import service.SpacesContest.Entity.Eventos;
import service.SpacesContest.Entity.Integrantesevento;
import service.SpacesContest.Entity.Speakers;
import service.SpacesContest.Entity.Titulosevento;
import service.SpacesContest.Http.AvayaSpacesAPIBreeze;
import service.SpacesContest.Http.Util.UtilHttpAvayaSpacesApi;
import service.SpacesContest.Security.AES;
import service.SpacesContest.Security.XSSPrevent;
import service.SpacesContest.Service.Impl.AvayaSpacesContestImpl;
import service.SpacesContest.Util.ConstantsHttpResponse;
import service.SpacesContest.Util.DateFormatedNow;

public class ApiActions {
	public String registrarIntegranteAEvento(String jsonObjectEncrypted) {
		try {
			JSONObject jsonObjectRequest = new JSONObject(XSSPrevent.stripXSS(new AES().decrypt(jsonObjectEncrypted)));
			Eventos evento = new AvayaSpacesContestImpl()
					.ObtenerEventoPorIdEvento(jsonObjectRequest.getString("idEvento"));
			if (evento != null) {
				Integrantesevento integranteEvento = new AvayaSpacesContestImpl()
						.obtenerIntegranteEventoPorCorreoRegistroAndIdEvento(
								jsonObjectRequest.getString("correointegrante"),
								jsonObjectRequest.getString("idEvento"));
				if (integranteEvento == null) {
					String uuidIntegrante = UUID.randomUUID().toString();
					new AvayaSpacesContestImpl().crearIntegranteEvento(new Integrantesevento(uuidIntegrante,
							jsonObjectRequest.getString("idEvento"), UUID.randomUUID().toString(), evento.getIdspace(),
							jsonObjectRequest.getString("nombreintegrante"),
							jsonObjectRequest.getString("apellidointegrante"),
							jsonObjectRequest.getString("correointegrante"),
							jsonObjectRequest.getString("avayaSpacesUserName"), jsonObjectRequest.getString("role"),
							jsonObjectRequest.getString("pictureurl"), jsonObjectRequest.getString("empresa"),
							new DateFormatedNow().getDateNow(), jsonObjectRequest.getString("puesto"),
							jsonObjectRequest.getString("pais"), jsonObjectRequest.getString("ciudad"),
							jsonObjectRequest.getString("estado"), jsonObjectRequest.getString("cp"),
							jsonObjectRequest.getString("telefonoOficina").replaceAll("[^\\dA-Za-z]", ""),
							jsonObjectRequest.getString("telefonoMovil").replaceAll("[^\\dA-Za-z]", ""),
							jsonObjectRequest.getString("facebook"), jsonObjectRequest.getString("twitter"),
							jsonObjectRequest.getString("companysFunction"),
							jsonObjectRequest.getString("companysEmployees"),
							jsonObjectRequest.getString("companysJobLevel"),
							jsonObjectRequest.getString("companysJobFunction"),
							jsonObjectRequest.getString("companyITBudget"),
							jsonObjectRequest.getString("companyPurchaseRole"),
							jsonObjectRequest.getString("attendedYears"),
							jsonObjectRequest.getBoolean("chekBoxTechologies"),
							jsonObjectRequest.getBoolean("checkBoxOEM"),
							jsonObjectRequest.getBoolean("checkBoxAvayaDevConnect"), 0, 0, "TRUE", "ACTIVO"));

					JSONObject jsonResponse = new AvayaSpacesAPIBreeze().enviarInvitaciónInformChannelClient(
							jsonObjectRequest.getString("avayaSpacesUserName"), evento.getIdspace());
					String joinUrl = new UtilHttpAvayaSpacesApi().getJoinURL(jsonResponse);
					return new JSONObject().put("status", "ok").put("message", "Usuario registrado").put("code", 201)
							.put("idUsuario", uuidIntegrante).put("joinURL", joinUrl).toString();

				} else {
					return ConstantsHttpResponse.RESPONSE_400("The user is already register").toString();
				}
			} else {
				return ConstantsHttpResponse.RESPONSE_400("Bad Request").toString();
			}
		} catch (Exception e) {
			return ConstantsHttpResponse.RESPONSE_500(ApiUtil.getExceptionMessageChain(e)).toString();
		}
	}

	public String obtenerIntegranteEventoByIdIntegrante(String idIntegrante) {
		Integrantesevento integranteEvento = new AvayaSpacesContestImpl()
				.obtenerIntegranteeventoPorIdIntegrante(idIntegrante);
		if (integranteEvento != null) {
			return new JSONObject().put("status", "ok").put("message", "Evento encontrado").put("code", 200)
					.put("integrante",
							new JSONObject()
									.put("nombreUsuario",
											integranteEvento.getNombreintegrante() + " "
													+ integranteEvento.getApelllidos())
									.put("imageURL", integranteEvento.getPictureurl())
									.put("correoUsuario", integranteEvento.getCorreo())
									.put("nombreDelEevnto", new AvayaSpacesContestImpl()
											.ObtenerEventoPorIdEvento(integranteEvento.getIdevento()).getNombreevento())
									.put("claveDelEvento", integranteEvento.getIdevento()))
					.toString();
		} else {
			return ConstantsHttpResponse.RESPONSE_400("The player not exists").toString();
		}
	}

	public String obtenerEventoPorIdEvento(String idEvento) {
		Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(idEvento);
		if (evento != null) {
			return new JSONObject().put("status", "ok").put("message", "Evento encontrado").put("code", 200)
					.put("evento", new JSONObject().put("nombreEvento", evento.getNombreevento()).put("idEvento",
							evento.getIdevento()))
					.toString();
		} else {
			return ConstantsHttpResponse.RESPONSE_400("The event not exists").toString();
		}
	}

	public String getIntegrantesPorIdEvento(String idEvento) {
		Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(idEvento);
		if (evento != null) {
			List<Integrantesevento> integrantes = new AvayaSpacesContestImpl()
					.obtenerIntegrantesPorIdEvento(evento.getIdevento());
			List<CetificateBean> listCertificados = new ArrayList<>();
			for (Integrantesevento integrantesevento : integrantes) {
				listCertificados.add(new CetificateBean(integrantesevento.getNombreintegrante(),
						integrantesevento.getApelllidos(), integrantesevento.getEmpresa(), evento.getFechacreacion(),
						integrantesevento.getCorreo()));
			}
			return new JSONObject().put("integrantes", new JSONArray(new Gson().toJson(listCertificados))).toString();
		} else {
			return ConstantsHttpResponse.RESPONSE_400("The event not exists").toString();
		}
	}

	public String getIntegranteByIdEventoAndCorreoIntegrante(String idEvento, String correoIntegrante) {
		Integrantesevento integranteEvento = new AvayaSpacesContestImpl()
				.obtenerIntegranteEventoPorCorreoRegistroAndIdEvento(correoIntegrante, idEvento);
		if (integranteEvento != null) {
			JSONObject jsonResponse;
			try {
				Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(integranteEvento.getIdevento());
				if (evento != null) {
					jsonResponse = new AvayaSpacesAPIBreeze().enviarInvitaciónInformChannelClient(correoIntegrante,
							evento.getIdspace());
					String joinUrl = new UtilHttpAvayaSpacesApi().getJoinURL(jsonResponse);
					return new JSONObject()
							.put("nombreUsuario",
									integranteEvento.getNombreintegrante() + " " + integranteEvento.getApelllidos())
							.put("nombreDelEevnto", evento.getNombreevento()).put("joinURL", joinUrl).toString();
				} else {
					return ConstantsHttpResponse.RESPONSE_400("The event does not exist").toString();
				}
			} catch (IOException | SSLUtilityException | NoAttributeFoundException | ServiceNotFoundException e) {
				return ConstantsHttpResponse.RESPONSE_500(ApiUtil.getExceptionMessageChain(e)).toString();
			}
		} else {
			return ConstantsHttpResponse.RESPONSE_400("The member does not exist.").toString();
		}
	}

	public String obtenerDatosParalaPáginaDeRegistro(String idEvento) {

		Eventos evento = new AvayaSpacesContestImpl().ObtenerEventoPorIdEvento(idEvento);
		if (evento != null) {
			Titulosevento titulosPorEvento = new AvayaSpacesContestImpl()
					.obtenerTitulosEventoPorIdEventoAndCreadoPor(evento.getIdevento(), evento.getCreadopor());
			JSONObject jsonObjectResponse = new JSONObject();
			jsonObjectResponse.put("speakersEvento", new JSONArray(evento.getArrayidspeakers()));
			if (titulosPorEvento != null) {
				jsonObjectResponse.put("titulosArray", new JSONArray(titulosPorEvento.getTitulos()));
				jsonObjectResponse.put("descripcion", titulosPorEvento.getDescripcion());
			} else {
				jsonObjectResponse.put("titulosArray", new JSONArray());
				jsonObjectResponse.put("descripcion", "");
			}

			return ConstantsHttpResponse.RESPONSE_200("Titles and Speakers", jsonObjectResponse, "registerInfo").toString();
		} else {
			return ConstantsHttpResponse.RESPONSE_400("The Event not Exists").toString();
		}

	}

}
