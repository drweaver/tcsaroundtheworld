package org.tcsaroundtheworld.common.shared.verify;


public class NameValidator implements SubmissionValueVerifier {

	public static int MAX_LENGTH = 50;

	public boolean isValid(final String text) {
		if( text != null && text.length() <= MAX_LENGTH && text.matches("[\\w\\- .]+") && text.matches(".*\\w+.*") ) {
			return true;
		}
		return false;
	}
}
