package org.tcsaroundtheworld.submit.server.verify;

import org.tcsaroundtheworld.common.shared.verify.EmailValidator;
import org.tcsaroundtheworld.common.shared.verify.GenderValidator;
import org.tcsaroundtheworld.common.shared.verify.InheritanceValidator;
import org.tcsaroundtheworld.common.shared.verify.LatitudeValidator;
import org.tcsaroundtheworld.common.shared.verify.LongitudeValidator;
import org.tcsaroundtheworld.common.shared.verify.NameValidator;
import org.tcsaroundtheworld.common.shared.verify.OperationsValidator;
import org.tcsaroundtheworld.common.shared.verify.ScentenceValidator;
import org.tcsaroundtheworld.common.shared.verify.SubmissionValueVerifier;
import org.tcsaroundtheworld.common.shared.verify.UrlValidator;
import org.tcsaroundtheworld.submit.shared.PersonSubmission;



public class PersonVerifierServer {

	private static class PersonVerifierChain {
		private PersonVerifierChain next = null;
		private SubmissionValueVerifier verifier = null;
		private final String value;
		private boolean optional = false;
		private final String name;
		public PersonVerifierChain(final SubmissionValueVerifier verifier, final String name, final String value) {
			super();
			this.verifier = verifier;
			this.value = value;
			this.name = name;
			if( value == null ) {
				throw new NullPointerException("value");
			}
		}
		public void verify() {
			if( optional && value.trim().length() == 0 || verifier.isValid(value) ) {
				if( next != null ) {
					next.verify();
				}
			} else {
				throw new RuntimeException("verification of "+name+" : "+value );
			}
		}
		public PersonVerifierChain setNext(final PersonVerifierChain next) {
			this.next = next;
			return next;
		}
		public PersonVerifierChain optional() {
			optional = true;
			return this;
		}
	}

	public void verify(final PersonSubmission p) {
		final PersonVerifierChain chain = new PersonVerifierChain(new NameValidator(), "first name", p.getBase().getName())
		.setNext(new PersonVerifierChain(new NameValidator(), "surname", p.getBase().getSurname()))
		.setNext(new PersonVerifierChain(new EmailValidator(), "email", p.getEmail().getEmail()))
		.setNext(new PersonVerifierChain(new ScentenceValidator(), "occupation", p.getBase().getOccupation()).optional())
		.setNext(new PersonVerifierChain(new DobValidator(), "D.O.B", p.getBase().getDob()).optional())
		.setNext(new PersonVerifierChain(new GenderValidator(), "gender", p.getBase().getGender()))
		.setNext(new PersonVerifierChain(new OperationsValidator(), "operations", p.getBase().getOperations()).optional())
		.setNext(new PersonVerifierChain(new InheritanceValidator(), "inheritance", p.getBase().getInheritance()))
		.setNext(new PersonVerifierChain(new NameValidator(), "city", p.getBase().getCity()))
		.setNext(new PersonVerifierChain(new NameValidator(), "state", p.getBase().getState()))
		.setNext(new PersonVerifierChain(new NameValidator(), "country", p.getBase().getCountry()))
		.setNext(new PersonVerifierChain(new LatitudeValidator(), "latitude", p.getBase().getLatitude()))
		.setNext(new PersonVerifierChain(new LongitudeValidator(), "longitude", p.getBase().getLongitude()))
		.setNext(new PersonVerifierChain(new ScentenceValidator(), "userComments", p.getBase().getUserComments()).optional())
		.setNext(new PersonVerifierChain(new UrlValidator(), "websiteUrl", p.getBase().getWebsiteUrl()).optional())
		//		.setNext(new PersonVerifierChain(PersonVerifier.IMAGE_TYPE_VALIDATOR, "image type", p.getPicture().getPictureContentType()).optional())
		;

		chain.verify();

	}

}
