package org.tcsaroundtheworld.common.shared.verify;


public class ImageTypeValidator implements SubmissionValueVerifier {

	public boolean isValid(final String ctype) {
		if( ctype != null && ctype.startsWith("image/") ) {
			return true;
		}
		return false;
	}

}
