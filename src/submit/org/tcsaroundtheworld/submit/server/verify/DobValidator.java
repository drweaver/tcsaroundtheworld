package org.tcsaroundtheworld.submit.server.verify;

import java.text.ParseException;
import java.util.Date;

import org.tcsaroundtheworld.common.shared.verify.AbstractDobValidator;
import org.tcsaroundtheworld.submit.server.DateParserServer;

public class DobValidator extends AbstractDobValidator {

	@Override
	protected Date parseDate(final String dateString) throws ParseException {
		return DateParserServer.parse(dateString).getDate();
	}

}
