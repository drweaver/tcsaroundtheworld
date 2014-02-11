package org.tcsaroundtheworld.common.shared.verify;


public class EmailValidator implements SubmissionValueVerifier {

	public static int MAX_LENGTH = 50;

	public boolean isValid(final String email) {
		if( email != null && email.length() <= MAX_LENGTH && email.trim().toLowerCase().matches("[a-z0-9_.-]+@[a-z0-9_-]+\\.[a-z0-9_.-]+")) {
			return true;
		}
		return false;
	}

}
