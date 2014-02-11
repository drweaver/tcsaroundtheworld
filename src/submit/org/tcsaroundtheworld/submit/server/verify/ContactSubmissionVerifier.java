package org.tcsaroundtheworld.submit.server.verify;

import org.tcsaroundtheworld.common.shared.verify.EmailValidator;
import org.tcsaroundtheworld.common.shared.verify.MessageValidator;
import org.tcsaroundtheworld.common.shared.verify.NameValidator;
import org.tcsaroundtheworld.submit.shared.ContactSubmission;

public class ContactSubmissionVerifier {

	NameValidator nameValidator = new NameValidator();
	MessageValidator messageValidator = new MessageValidator();
	EmailValidator emailValidator = new EmailValidator();

	public void verify( final ContactSubmission submission ) {

		final String name = submission.getName();
		if( !nameValidator.isValid( name ) ) {
			error("name", name );
		}
		final String email = submission.getEmail();
		if( !emailValidator.isValid( email ) ) {
			error("email", email);
		}
		final String message = submission.getMessage();
		if( !messageValidator.isValid(message) ) {
			error("message", message);
		}

	}

	private void error( final String field, final String value ) {
		throw new RuntimeException("verification of " + field + ": "+value);
	}

}
