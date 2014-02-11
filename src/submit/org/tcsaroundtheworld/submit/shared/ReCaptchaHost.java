package org.tcsaroundtheworld.submit.shared;

/**
 * Ensure that if we're running on a version subdomain the recaptcha keys are still
 * loaded correctly
 * 
 * @author shane
 *
 */
public class ReCaptchaHost {

	private static final String TCSAROUNDTHEWORLD_APPSPOT_COM = "tcsaroundtheworld.appspot.com";

	public static String clean(String host) {
		if( host.endsWith(TCSAROUNDTHEWORLD_APPSPOT_COM) ) {
			return TCSAROUNDTHEWORLD_APPSPOT_COM;
		} else {
			return host;
		}
	}

}
