package org.tcsaroundtheworld.common.shared.verify;


public class NameValidator implements SubmissionValueVerifier {

	public static int MAX_LENGTH = 50;

	public boolean isValid(final String text) {
		if( text != null && text.trim().length() > 0 && text.length() <= MAX_LENGTH 
				&& !text.contains("<")  && !text.contains(">") 
				&& !text.contains("\"") && !text.contains("\'")
				&& !text.contains(",")) {
			return true;
		}
		return false;
	}
}
