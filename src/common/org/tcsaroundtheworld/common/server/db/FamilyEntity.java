package org.tcsaroundtheworld.common.server.db;

import java.util.Date;

import javax.persistence.Id;

import org.tcsaroundtheworld.admin.shared.HasFamilyFullGetters;
import org.tcsaroundtheworld.admin.shared.HasFamilyFullSetters;
import org.tcsaroundtheworld.submit.shared.HasFamilySubmissionGetters;
import org.tcsaroundtheworld.submit.shared.HasFamilySubmissionSetters;

import com.googlecode.objectify.annotation.Unindexed;

public class FamilyEntity implements HasFamilyFullSetters, HasFamilyFullGetters, HasFamilySubmissionGetters, HasFamilySubmissionSetters {

	@Id
	Long id;

	boolean approved = false;

	@Unindexed
	boolean newSubmission = false;

	@Unindexed
	Date dateSubmitted = new Date();

	public FamilyEntity() {
	}

	public void setNewSubmission(boolean newSubmission) {
		this.newSubmission = newSubmission;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public boolean isApproved() {
		return approved;
	}

	public boolean isNewSubmission() {
		return newSubmission;
	}



}
