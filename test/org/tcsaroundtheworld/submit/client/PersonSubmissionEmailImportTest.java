package org.tcsaroundtheworld.submit.client;

import com.google.gwt.junit.client.GWTTestCase;

public class PersonSubmissionEmailImportTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.tcsaroundtheworld.submit.Submission";
	}

	public void testGetEmail() {
		assertEquals( "hello@world", new PersonSubmissionEmailImport("replyemail = hello@world\n").getEmail() );
	}

	public void testGetCity() {
		assertNull( new PersonSubmissionEmailImport("").getCity());
	}

	public void testGetCountry() {
		assertNull( new PersonSubmissionEmailImport("").getCountry());
	}

	public void testGetDob() {
		assertEquals( "03/06/1979", new PersonSubmissionEmailImport("day = 03\nmonth = 06\nyear1 = 7\nyear2 = 9\n").getDob() );
		assertNull( new PersonSubmissionEmailImport("day = 03\nmonth = 06\nyear1 = 7\n").getDob() );
		assertNull( new PersonSubmissionEmailImport("day = 03\nmonth = 06\nyear2 = 9\n").getDob() );
		assertNull( new PersonSubmissionEmailImport("day = 03\nyear1 = 7\nyear2 = 9\n").getDob() );
		assertNull( new PersonSubmissionEmailImport("month = 06\nyear1 = 7\nyear2 = 9\n").getDob() );
	}

	public void testGetGender() {
		assertEquals( "Male", new PersonSubmissionEmailImport("gender = Male\n").getGender() );
		assertEquals( "Male", new PersonSubmissionEmailImport("gender = male\n").getGender() );
		assertEquals( "Male", new PersonSubmissionEmailImport("gender = M\n").getGender() );
		assertEquals( "Male", new PersonSubmissionEmailImport("gender = m\n").getGender() );

		assertEquals( "Female", new PersonSubmissionEmailImport("gender = Female\n").getGender() );
		assertEquals( "Female", new PersonSubmissionEmailImport("gender = female\n").getGender() );
		assertEquals( "Female", new PersonSubmissionEmailImport("gender = F\n").getGender() );
		assertEquals( "Female", new PersonSubmissionEmailImport("gender = f\n").getGender() );
	}

	public void testGetInheritance() {
		assertEquals( "Inherited", new PersonSubmissionEmailImport("inherit = Inherited\n").getInheritance() );
		assertEquals( "Inherited", new PersonSubmissionEmailImport("inherit = inherited\n").getInheritance() );
		assertEquals( "Inherited", new PersonSubmissionEmailImport("inherit = I\n").getInheritance() );
		assertEquals( "Inherited", new PersonSubmissionEmailImport("inherit = i\n").getInheritance() );

		assertEquals( "Sporadic", new PersonSubmissionEmailImport("inherit = Sporadic\n").getInheritance() );
		assertEquals( "Sporadic", new PersonSubmissionEmailImport("inherit = sporadic\n").getInheritance() );
		assertEquals( "Sporadic", new PersonSubmissionEmailImport("inherit = S\n").getInheritance() );
		assertEquals( "Sporadic", new PersonSubmissionEmailImport("inherit = s\n").getInheritance() );
	}

	public void testGetLatitude() {
		assertNull( new PersonSubmissionEmailImport("").getLatitude());
	}

	public void testGetLongitude() {
		assertNull( new PersonSubmissionEmailImport("").getLongitude());
	}

	public void testGetName() {
		assertEquals( "Bob", new PersonSubmissionEmailImport("fname = Bob\n").getName() );
	}

	public void testGetOccupation() {
		assertEquals( "Student", new PersonSubmissionEmailImport("occupation = Student\n").getOccupation() );
	}

	public void testGetOperations() {
		assertEquals( "5", new PersonSubmissionEmailImport("ops = 5\n").getOperations() );
	}

	public void testGetPictureReference() {
		assertNull( new PersonSubmissionEmailImport("").getPictureReference());
	}

	public void testGetState() {
		assertNull( new PersonSubmissionEmailImport("").getState());
	}

	public void testGetSurname() {
		assertEquals( "Smith", new PersonSubmissionEmailImport("sname = Smith\n").getSurname() );
	}

	public void testGetUserComments() {
		assertEquals( "hello world", new PersonSubmissionEmailImport("comments = hello world\n").getUserComments() );
	}

	public void testIsContactable() {
		assertTrue( new PersonSubmissionEmailImport("").isContactable() );
	}

	public void testGetAddressSearch() {
		assertNull( new PersonSubmissionEmailImport("").getAddressSearch() );
		assertNull( new PersonSubmissionEmailImport("town = london\n").getAddressSearch() );
		assertNull( new PersonSubmissionEmailImport("country = UK\n").getAddressSearch() );
		assertEquals( "london, UK", new PersonSubmissionEmailImport("town = london\ncountry = UK\n").getAddressSearch() );
	}

	public void testGetWebsiteUrl() {
		assertNull( new PersonSubmissionEmailImport("").getWebsiteUrl());
	}

}
