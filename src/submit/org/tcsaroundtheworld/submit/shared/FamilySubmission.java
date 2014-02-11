package org.tcsaroundtheworld.submit.shared;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FamilySubmission implements IsSerializable, HasFamilySubmissionGetters, HasFamilySubmissionSetters {

	private List<PersonSubmission> members;
	boolean newSubmission;
	Date dateSubmitted;

	public FamilySubmission() {
	}

	public FamilySubmission(final HasFamilySubmissionGetters f) {
		newSubmission = f.isNewSubmission();
		dateSubmitted = f.getDateSubmitted();
	}

	public void copyTo(final HasFamilySubmissionSetters f) {
		f.setNewSubmission(newSubmission);
		f.setDateSubmitted(dateSubmitted);
	}

	public List<PersonSubmission> getMembers() {
		return members;
	}

	public void setNewSubmission(final boolean newSubmission) {
		this.newSubmission = newSubmission;
	}

	public boolean isNewSubmission() {
		return newSubmission;
	}

	public void setMembers(final List<PersonSubmission> members) {
		this.members = members;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(final Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
}
