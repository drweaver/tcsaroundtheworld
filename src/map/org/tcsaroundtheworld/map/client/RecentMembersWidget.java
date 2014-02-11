package org.tcsaroundtheworld.map.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.tcsaroundtheworld.common.shared.Family;
import org.tcsaroundtheworld.common.shared.PersonPublic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class RecentMembersWidget extends Composite {

	private static RecentMembersWidgetUiBinder uiBinder = GWT.create(RecentMembersWidgetUiBinder.class);

	interface RecentMembersWidgetUiBinder extends UiBinder<Widget, RecentMembersWidget> {
	}

	@UiField VerticalPanel recentMembers;

	public RecentMembersWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public void populate(final List<Family> families, final int max) {

		final List<Family> sortedFamilies = new ArrayList<Family>(families);
		Collections.sort(sortedFamilies, new Comparator<Family>() {
			public int compare(final Family arg0, final Family arg1) {
				return arg1.getDateSubmitted().compareTo(arg0.getDateSubmitted());
			}
		});

		int added = 0;
		for( final Family f : sortedFamilies ) {
			if( added + f.getMembers().size() > max ) {
				break;
			}
			for( final PersonPublic pp : f.getMembers() ) {
				recentMembers.add( new RecentMemberWidget( pp, f.getDateSubmitted(), f.isNewSubmission()));
				added++;
			}
		}
	}

}
