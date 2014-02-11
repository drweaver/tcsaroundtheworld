package org.tcsaroundtheworld.common.shared;

import java.util.Date;
import java.util.List;
import com.google.gwt.user.client.rpc.IsSerializable;

public class Family implements IsSerializable, HasFamilySetters, HasFamilyGetters {

	private List<PersonPublic> members;
	
	private boolean newSubmission;
	Date dateSubmitted;

	public Family() {}

	public Family(final HasFamilyGetters f) {
		newSubmission = f.isNewSubmission();
		dateSubmitted = f.getDateSubmitted();
		fixMissingDate();
	}

	public void copyTo(final HasFamilySetters f) {
		f.setNewSubmission(newSubmission);
		f.setDateSubmitted(dateSubmitted);
	}

	public boolean isNewSubmission() {
		return newSubmission;
	}

	public void setNewSubmission(final boolean newSubmission) {
		this.newSubmission = newSubmission;
	}

	public List<PersonPublic> getMembers() {
		return members;
	}

	public void setMembers(final List<PersonPublic> members) {
		this.members = members;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(final Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}
	
	/**
	 * If date is <missing> it default to current time.
	 * this messes up the recently added list
	 * reset them to 0
	 */
	private void fixMissingDate() {
		if( Math.abs(dateSubmitted.getTime()-new Date().getTime()) < 10000 ) {
			dateSubmitted = new Date(0);
		}
	}

}
