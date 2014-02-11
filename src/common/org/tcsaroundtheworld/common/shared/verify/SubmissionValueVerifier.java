package org.tcsaroundtheworld.common.shared.verify;



public interface SubmissionValueVerifier {

	boolean isValid(final String text);

	SubmissionValueVerifier EMAIL_VALIDATOR = new SubmissionValueVerifier() {
		public boolean isValid(final String email) {
			if( email != null && email.length() <= 50 && email.trim().toLowerCase().matches("[a-z0-9_.-]+@[a-z0-9_-]+\\.[a-z0-9_.-]+")) {
				return true;
			}
			return false;
		}
	};


	SubmissionValueVerifier OPERATIONS_VALIDATOR = new SubmissionValueVerifier() {
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
	};

	SubmissionValueVerifier GENDER_VALIDATOR = new SubmissionValueVerifier() {
		public boolean isValid(final String gender) {
			if( gender != null && ( gender.trim().toLowerCase().equals("male") || gender.trim().toLowerCase().equals("female") ) ) {
				return true;
			}
			return false;
		}
	};

	SubmissionValueVerifier INHERITANCE_VALIDATOR = new SubmissionValueVerifier() {
		public boolean isValid(final String inh) {
			if( inh != null && ( inh.trim().toLowerCase().equals("inherited") || inh.trim().toLowerCase().equals("sporadic") ) ) {
				return true;
			}
			return false;
		}
	};

	SubmissionValueVerifier LATITUDE_VALIDATOR = new AbstractCoordinatesVerifier() {
		@Override
		protected boolean validCoord(final Double coord) { return coord >= -90 && coord <= 90; };
	};

	SubmissionValueVerifier LONGITUDE_VALIDATOR = new AbstractCoordinatesVerifier() {
		@Override
		protected boolean validCoord(final Double coord) { return coord >= -180 && coord <= 180; };
	};

	SubmissionValueVerifier IMAGE_TYPE_VALIDATOR = new SubmissionValueVerifier() {
		public boolean isValid(final String ctype) {
			if( ctype != null && ctype.startsWith("image/") ) {
				return true;
			}
			return false;
		}
	};

	SubmissionValueVerifier SCENTENCE_VALIDATOR = new SubmissionValueVerifier() {
		public boolean isValid(final String text) {
			if( text != null && text.length() <= 500 && text.matches("[\\w\\- .,!&()'+]+") && text.matches(".*\\w+.*") ) {
				return true;
			}
			return false;
		}

	};

	SubmissionValueVerifier URL_VALIDATOR = new SubmissionValueVerifier() {

		public boolean isValid(final String text) {
			if( text != null && text.length() <= 200 && text.matches("https?\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?") ) {
				return true;
			}
			return false;
		}

	};

}
