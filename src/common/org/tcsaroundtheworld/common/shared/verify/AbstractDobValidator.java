package org.tcsaroundtheworld.common.shared.verify;

import java.util.Date;


public abstract class AbstractDobValidator implements SubmissionValueVerifier {
	Date twoHundredYearsAgo = new Date( System.currentTimeMillis() - 6307200000000l );
	Date now = new Date();
	public boolean isValid(final String dob) {
		if( dob != null && dob.trim().matches("[0-3]?\\d/[01]?\\d/\\d\\d\\d\\d") && validDateString(dob)) {
			return true;
		}
		return false;
	}
	private boolean validDateString(final String dateString) {
		try {
			final Date date = parseDate(dateString);
			if( date.after( twoHundredYearsAgo ) && date.before( now ) ) {
				return true;
			}
		} catch (final Exception e) {
			return false;
		}
		return false;
	}
	protected abstract Date parseDate(String dateString) throws Exception;
}