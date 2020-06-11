package service.SpacesContest.API.Util;

import java.util.ArrayList;
import java.util.List;

public class ApiUtil {
	
	public static String getExceptionMessageChain(Throwable throwable) {
		StringBuilder sb = new StringBuilder();
		List<String> result = new ArrayList<String>();
		while (throwable != null) {
			result.add(throwable.getMessage());
			throwable = throwable.getCause();
		}
		for (String string : result) {
			sb.append(string);
		}
		
		return sb.toString(); // ["THIRD EXCEPTION", "SECOND EXCEPTION", "FIRST EXCEPTION"]
	}
}
