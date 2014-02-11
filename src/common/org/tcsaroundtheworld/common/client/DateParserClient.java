package org.tcsaroundtheworld.common.client;

import java.util.Date;

import org.tcsaroundtheworld.common.shared.DateParser;


import com.google.gwt.i18n.client.DateTimeFormat;

public class DateParserClient implements DateParser {

	public static final DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
	public static final DateTimeFormat dateTimeFormatFriendly = DateTimeFormat.getFormat("dd/MM/yyyy");
	private final Date date;

	public DateParserClient(final Date date) {
		this.date = date;
	}

	public static DateParser parse(final String dateString) {
		return new DateParserClient( dateTimeFormat.parseStrict(dateString) );
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return dateTimeFormat.format(date);
	}

	@SuppressWarnings("deprecation")
	public int getMonthsTill(final Date tillDate) {
		int months = tillDate.getYear()*12+tillDate.getMonth() - (date.getYear()*12+date.getMonth());
		if( tillDate.before( new Date(tillDate.getYear(), tillDate.getMonth(), date.getDate()))) {
			months--;
		}
		return months;
	}

	@SuppressWarnings("deprecation")
	public int getYearsTill(final Date tillDate) {
		int years = tillDate.getYear() - date.getYear();
		final Date dateTest = new Date(tillDate.getYear(), date.getMonth(), date.getDate());
		if( tillDate.before( dateTest)) {
			years--;
		}
		return years;
	}

	public int getDaysTill(final Date tillDate) {
		return new Long((tillDate.getTime() - date.getTime()) / 86400000L).intValue();
	}

}
