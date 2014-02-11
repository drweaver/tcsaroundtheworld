package org.tcsaroundtheworld.admin.shared;

import org.tcsaroundtheworld.common.shared.PictureReference;

import com.google.gwt.junit.client.GWTTestCase;

public class PersonFullPersonCopyFromVisitorTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.tcsaroundtheworld.admin.Administrator";
	}

	public void testCopy() {

		final PersonFull pf = new PersonFull();
		pf.getBase().setName("n");
		pf.getBase().setSurname("s");
		pf.getBase().setDob("dob");
		pf.getBase().setGender("m");
		pf.getBase().setInheritance("i");
		pf.getBase().setOccupation("working");
		pf.getBase().setOperations("1");
		pf.getBase().setContactable(true);
		pf.getBase().setUserComments("comments");
		pf.getBase().setWebsiteUrl("url");
		pf.getBase().setFacebookId("fb");
		pf.getBase().setCity("city");
		pf.getBase().setState("state");
		pf.getBase().setCountry("country");
		pf.getBase().setLatitude("1.5");
		pf.getBase().setLongitude("2.5");
		pf.getBase().setPictureReference(new PictureReference("pic"));

		pf.getId().setFamilyId(10);
		pf.getId().setId(20);

		pf.getEmail().setEmail("1@1");

		pf.getEnabled().setEnabled(false);

		final PersonFull pfTo = new PersonFull();
		pfTo.accept( pf.createCopyVisitor() );

		assertEquals( "n", pfTo.getBase().getName() );
		assertEquals( "s", pfTo.getBase().getSurname() );
		assertEquals( "dob", pfTo.getBase().getDob() );
		assertEquals( "m", pfTo.getBase().getGender() );
		assertEquals( "i", pfTo.getBase().getInheritance() );
		assertEquals( "working", pfTo.getBase().getOccupation() );
		assertEquals( "1", pfTo.getBase().getOperations() );
		assertEquals( true, pfTo.getBase().isContactable() );
		assertEquals( "comments", pfTo.getBase().getUserComments() );
		assertEquals( "url", pfTo.getBase().getWebsiteUrl() );
		assertEquals( "fb", pfTo.getBase().getFacebookId() );
		assertEquals( "city", pfTo.getBase().getCity() );
		assertEquals( "state", pfTo.getBase().getState() );
		assertEquals( "country", pfTo.getBase().getCountry() );
		assertEquals( "1.5", pfTo.getBase().getLatitude() );
		assertEquals( "2.5", pfTo.getBase().getLongitude() );
		assertEquals( "pic", pfTo.getBase().getPictureReference().getId());

		assertEquals( 10l, pfTo.getId().getFamilyId() );
		assertEquals( 20l, pfTo.getId().getId() );

		assertEquals( "1@1", pfTo.getEmail().getEmail() );

		assertEquals( false, pfTo.getEnabled().isEnabled() );

	}

}
