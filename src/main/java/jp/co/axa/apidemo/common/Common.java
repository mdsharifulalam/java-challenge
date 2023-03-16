package jp.co.axa.apidemo.common;

import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;

/**
 * @author md.shariful
 *
 */
public class Common {

	/**
	 * 
	 * @param headers
	 * @param apiKey
	 * @return
	 */
	public static boolean checkXApiKey(HttpHeaders headers, String apiKey) {
		String xKey = "";
		List<String> headerXkey = headers.get("x-api-key");

		if (headerXkey != null) {
			xKey = headerXkey.get(0).trim();
		} else {
			return false;
		}

		if (xKey.isEmpty() || xKey == null) {
			return false;
		}

		if (xKey.equalsIgnoreCase(apiKey)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param headers
	 * @param user
	 * @param pass
	 * @return
	 */
	public static boolean checkBasicAuthorization(HttpHeaders headers, String user, String pass) {
		String key = "", apiUserInHeader = "", apiPass = "";
		List<String> bassicKey = headers.get("Authorization");

		if (bassicKey != null) {
			key = bassicKey.get(0).trim();
		} else {
			return false;
		}

		if (key.isEmpty() || key == null) {
			return false;
		}

		if (key != null && key.startsWith("Basic")) {
			String credentials = key.substring("Basic".length()).trim();
			byte[] decoded = DatatypeConverter.parseBase64Binary(credentials);
			String decodedString = new String(decoded);
			String[] actualCredentials = decodedString.split(":");
			if (actualCredentials.length <= 1) {
				System.out.println("basic authentication error(basicUser,basicPass)");
				return false;
			}

			apiUserInHeader = actualCredentials[0];
			if (!apiUserInHeader.equalsIgnoreCase(user)) {
				System.out.println("basic authentication error(basicUser)");
				return false;
			}

			apiPass = actualCredentials[1];
			if (Strings.isEmpty(actualCredentials[1]) || !apiPass.equalsIgnoreCase(pass)) {
				System.out.println("basic authentication error(basicPass)");
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param headers
	 * @param bearerToken
	 * @return
	 */
	public static boolean checkBearerAuthorization(HttpHeaders headers, String bearerToken) {
		String key = "";
		List<String> bearerKey = headers.get("Authorization");

		if (bearerKey != null) {
			key = bearerKey.get(0).trim();
		} else {
			return false;
		}

		if (key.isEmpty() || key == null) {
			return false;
		}

		if (key != null && key.startsWith("Bearer")) {
			String bearerTokens = key.substring("Bearer".length()).trim();

			if (!bearerTokens.equalsIgnoreCase(bearerToken)) {
				System.out.println("bearer authentication error(bearerToken)");
				return false;
			}
		} else {
			return false;
		}

		return true;
	}

}
