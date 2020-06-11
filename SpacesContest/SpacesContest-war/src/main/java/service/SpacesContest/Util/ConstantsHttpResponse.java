package service.SpacesContest.Util;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author umansilla
 */
public class ConstantsHttpResponse {

	public final static JSONObject RESPONSE_200(String message) {
		return new JSONObject().put("status", "ok").put("message", message).put("code", 200);
	}

	public final static JSONObject RESPONSE_201(String message) {
		return new JSONObject().put("status", "ok").put("message", message).put("code", 201);
	}

	public final static JSONObject RESPONSE_200(String message, JSONObject jsonObject, String jsonObjectKey) {
		return new JSONObject().put("status", "ok").put("message", message).put("code", 200).put(jsonObjectKey,
				jsonObject);
	}

	public final static JSONObject RESPONSE_200(String message, JSONArray jsonArray, String jsonObjectKey) {
		return new JSONObject().put("status", "ok").put("message", message).put("code", 200).put(jsonObjectKey,
				jsonArray);
	}

	public final static JSONObject RESPONSE_201(String message, JSONObject jsonObject, String jsonObjectKey) {
		return new JSONObject().put("status", "ok").put("message", message).put("code", 201).put(jsonObjectKey,
				jsonObject);
	}

	public final static JSONObject RESPONSE_400(String message) {
		return new JSONObject().put("status", "error").put("message", message).put("code", 400);
	}

	public final static JSONObject RESPONSE_401(String message) {
		return new JSONObject().put("status", "error").put("message", message).put("code", 401);
	}

	public final static JSONObject RESPONSE_500(String message) {
		return new JSONObject().put("status", "error").put("message", message).put("code", 500);
	}
}
