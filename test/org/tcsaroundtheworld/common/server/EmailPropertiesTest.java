package org.tcsaroundtheworld.common.server;

import junit.framework.TestCase;

public class EmailPropertiesTest extends TestCase {

	public void testBadAdminProperty() {
		try { 
			new EmailProperties("email-test1.properties");
			fail("Expected exception");
		} catch( RuntimeException e ) {
			assertEquals("Missing", e.getMessage().substring(0, 7));
		}
	}
	
	public void testEmptyAdminValue() {
		try {
			new EmailProperties("email-test2.properties").getAdminAddress();
			fail("Expected exception");
		} catch( RuntimeException e ) {
			assertEquals("Empty", e.getMessage().substring(0, 5));
		}
	}
	
	public void testFileNotThere() {
		try {
			new EmailProperties("email-not-there-test.properties");
			fail("Expected exception");
		} catch( RuntimeException e ) {
			//assertEquals("File", e.getMessage().substring(0, 5));
		}
	}
	
	public void testValid() {
		assertEquals( "jo@blogs.com", new EmailProperties("email-test3.properties").getAdminAddress());
	}
	
}
