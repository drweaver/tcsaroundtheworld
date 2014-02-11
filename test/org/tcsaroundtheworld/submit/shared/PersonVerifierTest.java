package org.tcsaroundtheworld.submit.shared;

import java.util.Arrays;
import java.util.List;

import org.tcsaroundtheworld.common.shared.verify.EmailValidator;
import org.tcsaroundtheworld.common.shared.verify.GenderValidator;
import org.tcsaroundtheworld.common.shared.verify.InheritanceValidator;
import org.tcsaroundtheworld.common.shared.verify.MessageValidator;
import org.tcsaroundtheworld.common.shared.verify.NameValidator;
import org.tcsaroundtheworld.common.shared.verify.OperationsValidator;
import org.tcsaroundtheworld.common.shared.verify.ScentenceValidator;
import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;
import org.tcsaroundtheworld.common.shared.verify.UrlValidator;
import org.tcsaroundtheworld.submit.client.verify.DobValidator;

import com.google.gwt.junit.client.GWTTestCase;

public class PersonVerifierTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "org.tcsaroundtheworld.submit.Submission";
	}

	public void testSimpleText() {
		final SubmissionValueVerifier v = new NameValidator();
		assertVerifier( v, true,  Arrays.asList("Shane", "Vicki", "Sarah-Phillips", "Tommy welsh", "T." ) );
		assertVerifier( v, false, Arrays.asList("<h1>shane</h1>", "inj \"", "<!-- comment -->", " ", ".-", "shane, weaver", null));
	}

	public void testScentence() {
		final SubmissionValueVerifier v = new ScentenceValidator();
		assertVerifier( v, true,  Arrays.asList("Shane", "Vicki", "Sarah-Phillips", "Tommy welsh", "T.", "To meet people!", "I, am not here.", "Aren't you a good boy (well, man)", "You & me", "You + me" ) );
		assertVerifier( v, false, Arrays.asList("<h1>shane</h1>", "inj \"", "<!-- comment -->", " ", ".-", "alternative email s.w@blah.com", "Why?", null));
	}

	public void testEmail() {
		final SubmissionValueVerifier v = new EmailValidator();
		assertVerifier( v, true, Arrays.asList("a@b.c", "s_w@google.co.uk", "s-w@g.org"));
		assertVerifier( v, false, Arrays.asList("a.b@", "@b.com", "a@b", "a@b.", "asdf", "asdf.com", null));
	}

	public void testDob() {
		final SubmissionValueVerifier v = new DobValidator();
		assertVerifier( v, true, Arrays.asList("1/1/2000", "01/01/2000"));
		assertVerifier( v, false, Arrays.asList("1/2000", "2000", "01/01/00", "00/01/2000", "01/01/2090", "01/01/1700", null));
	}

	public void testOperations() {
		final SubmissionValueVerifier v = new OperationsValidator();
		assertVerifier( v, true, Arrays.asList("1", "0", "10", "05"));
		assertVerifier( v, false, Arrays.asList("five", " ", "-5", "500", "+5", null));
	}

	public void testGender() {
		final SubmissionValueVerifier v = new GenderValidator();
		assertVerifier( v, true, Arrays.asList("male", "female"));
		assertVerifier( v, false, Arrays.asList("m", "f", " ", "bi", "1", null));
	}

	public void testInheritance() {
		final SubmissionValueVerifier v = new InheritanceValidator();
		assertVerifier( v, true, Arrays.asList("inherited", "sporadic"));
		assertVerifier( v, false, Arrays.asList("i", "s", "not sure", "pass", "-", null));
	}

	public void testLatitude() {
		final SubmissionValueVerifier v = SubmissionValueVerifier.LONGITUDE_VALIDATOR;
		assertVerifier( v, true, Arrays.asList("1.0", "1.5", "10.234", "0.123123", "-23.23123", "180.0"));
		assertVerifier( v, false, Arrays.asList("five", " ", "-5", "181.0", "-.0", null));
	}

	public void testLongitude() {
		final SubmissionValueVerifier v = SubmissionValueVerifier.LATITUDE_VALIDATOR;
		assertVerifier( v, true, Arrays.asList("1.0", "1.5", "10.234", "0.123123", "-23.23123", "90.0"));
		assertVerifier( v, false, Arrays.asList("five", " ", "-5", "91.0", "-.0", null));
	}

	public void testUrl() {
		final SubmissionValueVerifier v = new UrlValidator();
		assertVerifier( v, true, Arrays.asList("http://google.com", "https://google.com", "http://www.google.co.uk/", "http://google.org.uk", "http://www.google.com/my-site", "http://www.google.com/a/b/c/d"));
		assertVerifier( v, false, Arrays.asList("five", " ", "-5", "91.0", "-.0", null, "www.google.com", "google.com", "ftp://google.com", "http://google", "http://google.c", "http:///google.com", "htp://google.com", "http:/google.com", "http//google.com"));
	}

	public void testMessage() {
		final SubmissionValueVerifier v = new MessageValidator();
		assertVerifier( v, true, Arrays.asList("hi,\nhow are you? my name is john.\n best wishes\nmable."));
		assertVerifier( v, false, Arrays.asList("...\n,,,\n???"));
	}


	public void assertVerifier(final SubmissionValueVerifier verifier, final Boolean expected, final List<String> text) {
		for( final String s : text ) {
			assertVerifier( verifier, expected, s );
		}
	}

	public void assertVerifier(final SubmissionValueVerifier verifier, final Boolean expected, final String text) {
		assertEquals("Verifying '" + text + "'", expected, Boolean.valueOf(verifier.isValid(text)));
	}

}
