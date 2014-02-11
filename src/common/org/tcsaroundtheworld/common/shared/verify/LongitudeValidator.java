package org.tcsaroundtheworld.common.shared.verify;

public class LongitudeValidator extends AbstractCoordinatesVerifier {

	@Override
	protected boolean validCoord(final Double coord) {
		return coord >= -180 && coord <= 180;
	};

}
