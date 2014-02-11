/**
 * 
 */
package org.tcsaroundtheworld.submit.shared;

import com.google.gwt.user.client.rpc.IsSerializable;


public class SubmissionStatus implements IsSerializable {
	public enum Status { SUCCESS, FAILURE };
	public Status status;
	public String message;

	public SubmissionStatus() {
	}


	public static SubmissionStatus success() {
		SubmissionStatus submissionStatus = new SubmissionStatus();
		submissionStatus.status = Status.SUCCESS;
		submissionStatus.message = null;
		return submissionStatus;
	}

	public static SubmissionStatus failure(String message) {
		SubmissionStatus submissionStatus = new SubmissionStatus();
		submissionStatus.status = Status.FAILURE;
		submissionStatus.message = message;
		return submissionStatus;
	}


}