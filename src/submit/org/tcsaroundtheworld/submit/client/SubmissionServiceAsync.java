package org.tcsaroundtheworld.submit.client;

import org.tcsaroundtheworld.submit.shared.ContactSubmission;
import org.tcsaroundtheworld.submit.shared.FamilySubmission;
import org.tcsaroundtheworld.submit.shared.ReCaptchaFields;
import org.tcsaroundtheworld.submit.shared.SubmissionStatus;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SubmissionServiceAsync {

	void isCaptchaValid(ReCaptchaFields fields, AsyncCallback<Boolean> callback);

	void submitNewFamily(FamilySubmission nf, ReCaptchaFields reCaptchaFields,	AsyncCallback<SubmissionStatus> asyncCallback);

	void getPictureUploadPath(AsyncCallback<String> asyncCallback);

	void submitContactRequest(ContactSubmission contactSubmission,
			ReCaptchaFields reCaptchaFields,
			AsyncCallback<SubmissionStatus> asyncCallback);

}
