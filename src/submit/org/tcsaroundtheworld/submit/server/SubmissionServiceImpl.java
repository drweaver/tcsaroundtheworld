package org.tcsaroundtheworld.submit.server;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.tcsaroundtheworld.common.server.EmailProperties;
import org.tcsaroundtheworld.common.server.db.DAO;
import org.tcsaroundtheworld.submit.client.SubmissionService;
import org.tcsaroundtheworld.submit.server.verify.ContactSubmissionVerifier;
import org.tcsaroundtheworld.submit.server.verify.PersonVerifierServer;
import org.tcsaroundtheworld.submit.shared.ContactSubmission;
import org.tcsaroundtheworld.submit.shared.FamilySubmission;
import org.tcsaroundtheworld.submit.shared.PersonSubmission;
import org.tcsaroundtheworld.submit.shared.ReCaptchaFields;
import org.tcsaroundtheworld.submit.shared.ReCaptchaHost;
import org.tcsaroundtheworld.submit.shared.SubmissionStatus;

import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SubmissionServiceImpl extends RemoteServiceServlet implements SubmissionService {

	private static final long serialVersionUID = -4806167515936924401L;

	private final Logger log = Logger.getLogger(SubmissionServiceImpl.class.getName());

	PersonVerifierServer personVerifier = new PersonVerifierServer();

	ContactSubmissionVerifier contactSubmissionVerifier = new ContactSubmissionVerifier();

	EmailProperties emailProperties = new EmailProperties();

	final DAO dao = new DAO();

	public static boolean isProdMode() {
		return SystemProperty.environment.value() == SystemProperty.Environment.Value.Production;
	}

	@Override
	public boolean isCaptchaValid(final ReCaptchaFields fields) {

		if( isProdMode() ) {
			final String remoteAddr = getThreadLocalRequest().getRemoteAddr();
			final ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
			final String host = ReCaptchaHost.clean(this.getThreadLocalRequest().getServerName());
			log.info("Setting recaptcha private key for host: " + host);
			reCaptcha.setPrivateKey(new ReCaptchaPrivateKeys().privateKeys().get(host));
			final ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, fields.getChallenge(), fields.getResponse());
			final boolean valid = reCaptchaResponse.isValid();
			if( !valid ) {
				log.info("ReCaptcha validation failed: " + reCaptchaResponse.getErrorMessage());
			}
			return valid;
		} else {
			return true;
		}
	}

	@Override
	public SubmissionStatus submitNewFamily(final FamilySubmission f, final ReCaptchaFields reCaptchaFields) {
		if( !isCaptchaValid(reCaptchaFields) ) {
			return SubmissionStatus.failure("The ReCaptcha challenge was incorrect");
		}

		final List<PersonSubmission> members = f.getMembers();
		if( members.size() < 1 ) {
			log.warning("No family members were recieved by the server");
			return SubmissionStatus.failure("An error occurred - no family members were recieved by the server");
		}

		for( int i=0; i<members.size(); i++ ) {
			final PersonSubmission p = members.get(i);
			try {
				personVerifier.verify(p);
			} catch(final Exception e ) {
				log.log(Level.WARNING, "Failed to validate family member #"+(i+1), e);
				return SubmissionStatus.failure("An error occurred during validation - family member #"+(i+1)+" - " + e.getMessage());
			}
		}

		try {

			dao.storeFamily(f);
		} catch( final Exception e ) {
			log.log(Level.WARNING, "An error occurred in the DAO layer", e);
			return SubmissionStatus.failure("An error occurred in the DAO layer - "+e.getMessage());
		}

		return SubmissionStatus.success();
	}

	@Override
	public SubmissionStatus submitContactRequest(final ContactSubmission contactSubmission,	final ReCaptchaFields reCaptchaFields) {
		if( !isCaptchaValid(reCaptchaFields) ) {
			return SubmissionStatus.failure("The ReCaptcha challenge was incorrect");
		}
		try {
			contactSubmissionVerifier.verify(contactSubmission);
		} catch(final Exception e ) {
			log.log(Level.WARNING, "Failed to validate contact submission ", e);
			return SubmissionStatus.failure("An error occurred during validation - " + e.getMessage());
		}

		try {
			final Long familyId = contactSubmission.getFamilyId();
			final Long personId = contactSubmission.getPersonId();
			final boolean forAdmin = familyId == null || personId == null;
			final String email = forAdmin ?	emailProperties.getAdminAddress() : dao.getPersonEmail(familyId, personId);
			if( email == null ) {
				return SubmissionStatus.failure("An error occurred whilst retrieving contact details");
			}
			final Properties props = new Properties();
			final Session session = Session.getDefaultInstance(props, null);
			final Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailProperties.getAdminAddress(), "TCS Around the World Admin"));
			msg.setReplyTo( new Address[] {
					new InternetAddress(contactSubmission.getEmail(), contactSubmission.getName())
			} );
			msg.addRecipient(Message.RecipientType.TO,	new InternetAddress(email));
			if( !forAdmin ) {
				msg.addRecipient(Message.RecipientType.CC,
						new InternetAddress(emailProperties.getAdminAddress(), "TCS Around the World Admin"));
			}
			msg.setSubject("Contact Messsage from TCS Around the World");
			msg.setText( contactSubmission.getMessage() );
			Transport.send(msg);

		} catch (final Exception e) {
			log.log(Level.SEVERE, "Failed to send email to contact: " + e.getMessage(), e);
			return SubmissionStatus.failure("An error occurred whilst constructing the message on the server:" + e.getMessage() );
		}

		return SubmissionStatus.success();
	}


}
