package org.tcsaroundtheworld.submit.client.verify;

import java.util.ArrayList;
import java.util.List;

public class SubmissionVerifier {

	private final List<FieldVerifier> verifiers = new ArrayList<FieldVerifier>();

	public void add( final FieldVerifier verifier ) {
		verifiers.add( verifier );
	}

	public boolean isValid() {
		boolean valid = true;
		for( final FieldVerifier verifier : verifiers ) {
			if( !verifier.checkField() ) {
				valid = false;
			}
		}
		return valid;
	}

}
