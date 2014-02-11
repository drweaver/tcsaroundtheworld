package org.tcsaroundtheworld.common.shared.verify;


public abstract class AbstractCoordinatesVerifier implements SubmissionValueVerifier {

	public boolean isValid(final String coord) {
		if( coord != null && coord.length() <= 50 && coord.trim().matches("-?\\d+\\.\\d+")) {
			try {
				if( validCoord( Double.valueOf(coord) ) ) {
					return true;
				}
			} catch( final Exception e ) {
				return false;
			}
		}
		return false;
	}
	protected abstract boolean validCoord(Double coord);
}