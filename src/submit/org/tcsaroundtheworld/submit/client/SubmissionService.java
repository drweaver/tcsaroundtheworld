package org.tcsaroundtheworld.submit.client;

import org.tcsaroundtheworld.submit.shared.ContactSubmission;
import org.tcsaroundtheworld.submit.shared.FamilySubmission;
import org.tcsaroundtheworld.submit.shared.ReCaptchaFields;
import org.tcsaroundtheworld.submit.shared.SubmissionStatus;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("submissionservice")
public interface SubmissionService extends RemoteService {

	boolean isCaptchaValid(ReCaptchaFields fields);

	SubmissionStatus submitNewFamily(FamilySubmission nf, ReCaptchaFields reCaptchaFields);

	SubmissionStatus submitContactRequest(ContactSubmission contactSubmission,	ReCaptchaFields reCaptchaFields);

}
