package org.tcsaroundtheworld.common.shared.verify;


public class OperationsValidator implements SubmissionValueVerifier {

	public boolean isValid(final String ops) {
		if( ops != null && ops.trim().matches("\\d+") ) {
			try {
				if( Integer.valueOf(ops) > 200 ) {
					return false;
				}
			} catch( final Exception e ) {
				return false;
			}
			return true;
		}
		return false;
	}

}
