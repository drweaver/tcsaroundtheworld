package org.tcsaroundtheworld.common.shared.verify;


public class InheritanceValidator implements SubmissionValueVerifier {

	public boolean isValid(final String inh) {
		if( inh != null && ( inh.trim().toLowerCase().equals("inherited") || inh.trim().toLowerCase().equals("sporadic") ) ) {
			return true;
		}
		return false;
	}

}
