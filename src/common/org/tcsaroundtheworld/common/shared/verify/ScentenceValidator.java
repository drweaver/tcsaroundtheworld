package org.tcsaroundtheworld.common.shared.verify;


public class ScentenceValidator implements SubmissionValueVerifier {

	public static final int MAX_LENGTH = 500;

	public boolean isValid(final String text) {
		if( text != null && text.length() <= MAX_LENGTH && text.matches("[\\w\\- .,!&()'+]+") && text.matches(".*\\w+.*") ) {
			return true;
		}
		return false;
	}
}
