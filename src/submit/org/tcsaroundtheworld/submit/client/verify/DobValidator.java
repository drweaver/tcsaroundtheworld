package org.tcsaroundtheworld.submit.client.verify;

import java.util.Date;

import org.tcsaroundtheworld.common.client.DateParserClient;
import org.tcsaroundtheworld.common.shared.verify.AbstractDobValidator;

public class DobValidator extends AbstractDobValidator {

	@Override
	protected Date parseDate(final String dateString) {
		return DateParserClient.parse(dateString).getDate();
	}

}
