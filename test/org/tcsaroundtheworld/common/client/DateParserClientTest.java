package org.tcsaroundtheworld.common.client;

import org.tcsaroundtheworld.common.shared.DateParser;

import com.google.gwt.junit.client.GWTTestCase;

public class DateParserClientTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.tcsaroundtheworld.submit.Submission";
	}

	public void testParse() {
		final DateParser parsed = DateParserClient.parse("03/06/1979");
		assertEquals("03/06/1979", parsed.toString());
		assertEquals( 3, parsed.getDate().getDate() );
		assertEquals( 5, parsed.getDate().getMonth() );
		assertEquals( 79, parsed.getDate().getYear() );
	}

	public void testAgeInYears() {
		final DateParser startDate = DateParserClient.parse("03/06/1979");
		assertEquals(32, startDate.getYearsTill(DateParserClient.parse("29/06/2011").getDate()));
		assertEquals(32, startDate.getYearsTill(DateParserClient.parse("01/06/2012").getDate()));
	}

	public void testAgeInMonths() {
		final DateParser startDate = DateParserClient.parse("03/06/1979");

		assertEquals(1, startDate.getMonthsTill(DateParserClient.parse("05/07/1979").getDate()));
		assertEquals(1, startDate.getMonthsTill(DateParserClient.parse("01/08/1979").getDate()));
		assertEquals(2, startDate.getMonthsTill(DateParserClient.parse("03/08/1979").getDate()));
		assertEquals(6, startDate.getMonthsTill(DateParserClient.parse("01/01/1980").getDate()));
		assertEquals(7, startDate.getMonthsTill(DateParserClient.parse("10/01/1980").getDate()));
		assertEquals(13, startDate.getMonthsTill(DateParserClient.parse("10/07/1980").getDate()));
	}

}
