package org.tcsaroundtheworld.map.client;

import java.util.Date;

import org.tcsaroundtheworld.common.client.DateParserClient;
import org.tcsaroundtheworld.common.shared.DateParser;
import org.tcsaroundtheworld.common.shared.PersonBase;
import org.tcsaroundtheworld.common.shared.PersonId;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;

public class PersonInfoWindowBuilder {

	public interface HtmlTemplates extends SafeHtmlTemplates {
		@Template("<img style='padding: 3px;' width=\"{0}\" height=\"{0}\" src=\"{1}\"/>")
		SafeHtml img( int size, SafeUri url );
		@Template("<span class='person-info-name'>{0} {1}</span>")
		SafeHtml name( String name, String surname);
		@Template("<span>{1}, {2}</span>")
		SafeHtml address( String city, String state, String country);
		@Template("<span>{0} years</span>")
		SafeHtml ageInYears( int age );
		@Template("<span>{0} months</span>")
		SafeHtml ageInMonths( int months );
		@Template("<span>{0}</span>")
		SafeHtml occupation(String occupation);
		@Template("<span><a target='_blank' href='http://www.treachercollins.co.uk/gene/genes.htm' onclick=\"_gaq.push(['_trackEvent', 'Members', 'Inheritance Link Clicked']);\">{0}</a></span>")
		SafeHtml inheritance(String inheritance);
		@Template("<span>{0} ops</span>")
		SafeHtml ops(String ops);
		@Template("<span class=\"userComments\">\"{0}\"</span>")
		SafeHtml userComments(String userComments);
		@Template("<div class='person-info'>{0}</div>")
		SafeHtml wrapperDiv(SafeHtml content);
	}

	private static HtmlTemplates htmlTemplates = GWT.create(HtmlTemplates.class);

	private final static int IMG_SIZE = 80;

	public PersonInfoWindowBuilder() {
		super();
	}

	public SafeHtml build(final PersonBase p, final PersonId id) {
		final SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<div style='float:right'>");
		if( p.getPictureReference() != null  ) {
			sb.append(htmlTemplates.img(IMG_SIZE, UriUtils.fromString(p.getPictureReference().getServingUrl(IMG_SIZE))));
			sb.appendHtmlConstant("<br/>");
		}
		final String name = p.getName() + ' ' + p.getSurname();
		if( isNotEmpty(p.getWebsiteUrl() ) ) {
			sb.append(websiteLink(p.getWebsiteUrl(), name));
		}
		if( isNotEmpty(p.getFacebookId() ) ) {
			sb.append(facebookProfileLink(p.getFacebookId(), name));
		}
		if( p.isContactable() ) {
			sb.append(contactLink(id.getFamilyId(), id.getId(), p.getName() + " " + p.getSurname()));
		}
		sb.appendHtmlConstant("</div>");
		sb.append(htmlTemplates.name(p.getName(),p.getSurname()));
		if( p.getGender().equals("Male") ) {
			sb.appendHtmlConstant("<img src='img/male.png' title='Male'/>");
		} else {
			sb.appendHtmlConstant("<img src='img/female.png' title='Female'/>");
		}
		if( p.getDob() != null && p.getDob().length() > 0 ) {
			final DateParser dob = DateParserClient.parse(p.getDob());
			final Date now = new Date();
			final int ageInYears = dob.getYearsTill(now);
			sb.append(ageInYears<1?htmlTemplates.ageInMonths(dob.getMonthsTill(now)):htmlTemplates.ageInYears(ageInYears));

		}
		sb.appendHtmlConstant("<br/>");
		sb.append(htmlTemplates.address(p.getCity(),p.getState(),p.getCountry())).appendHtmlConstant("<br/>");
		if( p.getOccupation() != null && p.getOccupation().length() > 0 ) {
			sb.append(htmlTemplates.occupation(p.getOccupation())).appendHtmlConstant("<br/>");
		}
		sb.append(htmlTemplates.inheritance(p.getInheritance()));
		if( isNotEmpty( p.getOperations() ) ) {
			sb.appendHtmlConstant(" ").append(htmlTemplates.ops(p.getOperations()));
		}
		if( isNotEmpty( p.getUserComments() ) ) {
			sb.append(htmlTemplates.userComments(p.getUserComments()));
		}
		sb.appendHtmlConstant("<br/>");

		return htmlTemplates.wrapperDiv( sb.toSafeHtml() );
	}

	public boolean isNotEmpty(final String text) {
		return text != null && text.length() > 0;
	}

	private SafeHtml websiteLink(final String link, final String name) {
		return SafeHtmlUtils.fromTrustedString("<a href='"+link.replace("'", "")+"' target='_blank' onclick=\"_gaq.push(['_trackEvent', 'Members', 'Website', 'Name', '"+name.replace("'", "")+"']);\"><img style='padding: 3px;' src='img/url.gif'/></a>");
	}

	private SafeHtml facebookProfileLink(final String fbId, final String name) {
		return SafeHtmlUtils.fromTrustedString("<a href='http://www.facebook.com/profile.php?id="+fbId.replace("'", "")+"' target='_blank' onclick=\"_gaq.push(['_trackEvent', 'Members', 'Facebook Profile', 'Name', '"+name.replace("'", "")+"']);\"><img style='padding: 3px;'  src='img/facebook.gif'/></a>");
	}

	private SafeHtml contactLink(final Long familyId, final Long personId, final String name) {
		return SafeHtmlUtils.fromTrustedString("<img style='padding: 3px;cursor:pointer;'  src='img/email.png' onClick=\"javascript:openContactFormDialog("+familyId+","+personId+",'"+name.replace("'", "")+"');\"/>");
	}

	public static void openContactFormDialog(final double familyId, final double personId, final String name ) {
		new ContactFormDialog(Double.valueOf(familyId).longValue(), Double.valueOf(personId).longValue(), name);
	}

	public static native void exportOpenContactFormDialog() /*-{
		$wnd.openContactFormDialog = $entry(@org.tcsaroundtheworld.map.client.PersonInfoWindowBuilder::openContactFormDialog(DDLjava/lang/String;));
	}-*/;

}
