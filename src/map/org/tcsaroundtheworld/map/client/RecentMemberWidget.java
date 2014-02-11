package org.tcsaroundtheworld.map.client;

import java.util.Date;

import org.tcsaroundtheworld.common.client.DateParserClient;
import org.tcsaroundtheworld.common.shared.DateParser;
import org.tcsaroundtheworld.common.shared.PersonPublic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class RecentMemberWidget extends Widget {

	private static RecentMemberWidgetUiBinder uiBinder = GWT
	.create(RecentMemberWidgetUiBinder.class);

	interface RecentMemberWidgetUiBinder extends
	UiBinder<Element, RecentMemberWidget> {
	}

	@UiField SpanElement dateSubmitted;
	@UiField SpanElement personInfo;

	public RecentMemberWidget( final PersonPublic pp, final Date dateSubmitted, final boolean newSubmission ) {
		setElement(uiBinder.createAndBindUi(this));
		personInfo.setInnerHTML( new PersonInfoWindowBuilder().build(pp.getBase(), pp.getId()).asString() );

		final StringBuilder builder = new StringBuilder();
		builder.append( newSubmission ? "Submitted " : "Updated " );
		final Date now = new Date();
		final DateParser dateParser  = new DateParserClient( dateSubmitted );
		int diff;
		if( (diff = dateParser.getYearsTill(now)) > 0 ) {
			builder.append(diff).append(" year").append(pluralModifier(diff)).append(" ago");
		} else if( (diff=dateParser.getMonthsTill(now)) > 0 ) {
			builder.append(diff).append(" month").append(pluralModifier(diff)).append(" ago");
		} else if( (diff=dateParser.getDaysTill(now)) > 0 ) {
			builder.append(diff).append(" day").append(pluralModifier(diff)).append(" ago");
		} else {
			builder.append("Today");
		}
		this.dateSubmitted.setInnerText( builder.toString() );

	}

	private String pluralModifier(final int diff) {
		return diff>1?"s":"";
	}

}
