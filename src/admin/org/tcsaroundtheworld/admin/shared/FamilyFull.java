package org.tcsaroundtheworld.admin.shared;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;


public class FamilyFull implements IsSerializable, HasFamilyFullSetters, HasFamilyFullGetters {

	long id;
	boolean approved;
	Date dateSubmitted;
	boolean newSubmission;
	private List<PersonFull> members;

	public FamilyFull() {
	}

	public FamilyFull(HasFamilyFullGetters f) {
		id = f.getId();
		approved = f.isApproved();
		newSubmission = f.isNewSubmission();
		dateSubmitted = f.getDateSubmitted();
	}

	public List<PersonFull> getMembersFull() {
		return members;
	}

	public void setMembersFull(List<PersonFull> members) {
		this.members = members;
	}

	public void setNewSubmission(boolean newSubmission) {
		this.newSubmission = newSubmission;
	}

	public boolean isNewSubmission() {
		return newSubmission;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	@Override
	public String toString() {
		return "FamilyFull= ID="+id+" approved="+approved+" ";
	}



}
