package service.SpacesContest.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author umansilla
 */
public class PartToString {

	private HttpServletRequest request;
	public PartToString(HttpServletRequest request) {
		this.request = request;
	}

	public PartToString() {
		super();
	}

	public String getStringValue(String partName) throws IOException,
			ServletException {
		return getStringValue(request.getPart(partName));
	}

	public String getStringValue(final Part part) {
		BufferedReader bufferedReader = null;
		final StringBuilder stringBuilder = new StringBuilder();
		String line;
		final String partName = part.getName();
		try {
			final InputStream inputStream = part.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(
					inputStream, StandardCharsets.UTF_8));
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
		} catch (final IOException e) {
			System.out
					.println("getStringValue - IOException while reading inputStream. Part name : "
							+ partName + " " + e.toString());
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (final IOException e) {
					System.out
							.println("getStringValue - IOException while closing bufferedReader. Part name : "
									+ partName + " " + e.toString());
				}
			}
		}
		return stringBuilder.toString();
	}
}
