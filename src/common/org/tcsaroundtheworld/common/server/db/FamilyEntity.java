package org.tcsaroundtheworld.common.server.db;

import java.util.Date;

import javax.persistence.Id;

import org.tcsaroundtheworld.admin.shared.HasFamilyFullGetters;
import org.tcsaroundtheworld.admin.shared.HasFamilyFullSetters;
import org.tcsaroundtheworld.submit.shared.HasFamilySubmissionGetters;
import org.tcsaroundtheworld.submit.shared.HasFamilySubmissionSetters;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Unindexed;

@Cached
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

	@Override
	public void setNewSubmission(boolean newSubmission) {
		this.newSubmission = newSubmission;
	}

	@Override
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	@Override
	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	@Override
	public boolean isApproved() {
		return approved;
	}

	@Override
	public boolean isNewSubmission() {
		return newSubmission;
	}



}
