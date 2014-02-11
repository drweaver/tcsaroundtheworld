package org.tcsaroundtheworld;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.tcsaroundtheworld.admin.shared.PersonFullPersonCopyFromVisitorTest;
import org.tcsaroundtheworld.common.client.DateParserClientTest;
import org.tcsaroundtheworld.map.client.PersonInfoWindowBuilderTest;
import org.tcsaroundtheworld.submit.client.PersonSubmissionEmailImportTest;
import org.tcsaroundtheworld.submit.shared.PersonSubmissionPersonCopyFromVisitorTest;
import org.tcsaroundtheworld.submit.shared.PersonVerifierTest;

import com.google.gwt.junit.tools.GWTTestSuite;

public class AllClientTests extends GWTTestSuite {

	public static Test suite() {
		final TestSuite suite = new TestSuite("Test for uk.co.treachercollins.map.client");
		//$JUnit-BEGIN$
		suite.addTestSuite(PersonInfoWindowBuilderTest.class);
		suite.addTestSuite(PersonVerifierTest.class);
		suite.addTestSuite(PersonSubmissionEmailImportTest.class);
		suite.addTestSuite(PersonFullPersonCopyFromVisitorTest.class);
		suite.addTestSuite(PersonSubmissionPersonCopyFromVisitorTest.class);
		suite.addTestSuite(DateParserClientTest.class);
		//$JUnit-END$
		return suite;
	}

}
