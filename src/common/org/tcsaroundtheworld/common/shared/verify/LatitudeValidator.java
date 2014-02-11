package org.tcsaroundtheworld.common.shared.verify;

public class LatitudeValidator extends AbstractCoordinatesVerifier {

	@Override
	protected boolean validCoord(final Double coord) {
		return coord >= -90 && coord <= 90;
	};

}
