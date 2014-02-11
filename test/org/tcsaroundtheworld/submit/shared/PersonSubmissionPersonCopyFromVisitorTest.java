package org.tcsaroundtheworld.submit.shared;

import com.google.gwt.junit.client.GWTTestCase;

public class PersonSubmissionPersonCopyFromVisitorTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.tcsaroundtheworld.submit.Submission";
	}

	public void testCopy() {

		final PersonSubmission ps = new PersonSubmission();
		ps.getBase().setName("n");
		ps.getBase().setSurname("s");
		ps.getBase().setDob("dob");
		ps.getBase().setGender("m");
		ps.getBase().setInheritance("i");
		ps.getBase().setOccupation("working");
		ps.getBase().setOperations("1");
		ps.getBase().setContactable(true);
		ps.getBase().setUserComments("comments");
		ps.getBase().setWebsiteUrl("url");
		ps.getBase().setFacebookId("fb");
		ps.getBase().setCity("city");
		ps.getBase().setState("state");
		ps.getBase().setCountry("country");
		ps.getBase().setLatitude("1.5");
		ps.getBase().setLongitude("2.5");

		ps.getEmail().setEmail("1@1");

		final PersonSubmission psTo = new PersonSubmission();
		psTo.accept( ps.createCopyVisitor() );

		assertEquals( "n", psTo.getBase().getName() );
		assertEquals( "s", psTo.getBase().getSurname() );
		assertEquals( "dob", psTo.getBase().getDob() );
		assertEquals( "m", psTo.getBase().getGender() );
		assertEquals( "i", psTo.getBase().getInheritance() );
		assertEquals( "working", psTo.getBase().getOccupation() );
		assertEquals( "1", psTo.getBase().getOperations() );
		assertEquals( true, psTo.getBase().isContactable() );
		assertEquals( "comments", psTo.getBase().getUserComments() );
		assertEquals( "url", psTo.getBase().getWebsiteUrl() );
		assertEquals( "fb", psTo.getBase().getFacebookId() );
		assertEquals( "city", psTo.getBase().getCity() );
		assertEquals( "state", psTo.getBase().getState() );
		assertEquals( "country", psTo.getBase().getCountry() );
		assertEquals( "1.5", psTo.getBase().getLatitude() );
		assertEquals( "2.5", psTo.getBase().getLongitude() );

		assertEquals( "1@1", psTo.getEmail().getEmail() );

	}

}
