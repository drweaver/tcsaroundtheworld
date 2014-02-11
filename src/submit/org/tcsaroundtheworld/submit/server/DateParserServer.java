package org.tcsaroundtheworld.submit.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.tcsaroundtheworld.common.shared.DateParser;

public class DateParserServer implements DateParser {
	public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private final Date date;

	public static DateParser parse(final String dateString) {
		try {
			return new DateParserServer(dateFormat.parse(dateString));
		} catch (final ParseException e) {
			throw new RuntimeException("Failed to parse date: "+dateString, e);
		}
	}

	public DateParserServer(final Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return dateFormat.format(date);
	}
	public int toAgeInYears(final Date parse) {
		throw new RuntimeException("toAge not yet implemented");
	}

	public Date getDate() {
		return date;
	}

	public int getMonthsTill(final Date tillDate) {
		throw new RuntimeException(" not yet implemented");
	}

	public int getYearsTill(final Date tillDate) {
		throw new RuntimeException(" not yet implemented");
	}

	public int getDaysTill(final Date tillDate) {
		throw new RuntimeException(" not yet implemented");
	}

}
