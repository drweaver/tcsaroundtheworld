package org.tcsaroundtheworld.common.shared.verify;


public class GenderValidator implements SubmissionValueVerifier {

	public boolean isValid(final String gender) {
		if( gender != null && ( gender.trim().toLowerCase().equals("male") || gender.trim().toLowerCase().equals("female") ) ) {
			return true;
		}
		return false;
	}

}
