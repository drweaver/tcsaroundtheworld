package org.tcsaroundtheworld.common.shared.verify;


public class UrlValidator implements SubmissionValueVerifier {



	public static final int MAX_LENGTH = 200;

	public boolean isValid(final String text) {
		if( text != null && text.length() <= MAX_LENGTH && text.matches("https?\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?") ) {
			return true;
		}
		return false;
	}
}
