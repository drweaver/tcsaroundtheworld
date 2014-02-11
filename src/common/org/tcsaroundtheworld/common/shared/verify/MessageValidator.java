package org.tcsaroundtheworld.common.shared.verify;


public class MessageValidator implements SubmissionValueVerifier {

	public static final int MAX_LENGTH = 2000;

	public boolean isValid(final String text) {
		if( text != null && text.length() <= MAX_LENGTH && text.matches("(?s)[\\w\\s\\p{Punct}]+") && text.matches("(?s).*\\w+.*") ) {
			return true;
		}
		return false;
	}
}
