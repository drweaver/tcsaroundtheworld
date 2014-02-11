package org.tcsaroundtheworld.admin.client;

import org.tcsaroundtheworld.common.shared.HasPersonBase;
import org.tcsaroundtheworld.common.shared.HasPersonEmail;
import org.tcsaroundtheworld.common.shared.HasPersonEnabled;
import org.tcsaroundtheworld.common.shared.HasPersonId;
import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonJsonOutputVisitor;
import org.tcsaroundtheworld.common.shared.PersonVisitor;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;
import org.tcsaroundtheworld.common.shared.PictureReference;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class PersonWidget extends Composite implements IsPerson, HasPersonBase, HasPersonId, HasPersonEmail, HasPersonEnabled {

	AdminServiceAsync adminService = GWT.create(AdminService.class);

	private static PersonWidgetUiBinder uiBinder = GWT.create(PersonWidgetUiBinder.class);

	interface PersonWidgetUiBinder extends UiBinder<Widget, PersonWidget> {
	}

	public PersonWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField CheckBox enabled;
	@UiField Label name;
	@UiField Label surname;
	@UiField Label email;
	@UiField Label id;
	@UiField Label city;
	@UiField Label state;
	@UiField Label country;
	@UiField Label lat;
	@UiField Label lng;
	@UiField Label occupation;
	@UiField Label dob;
	@UiField Label gender;
	@UiField Label inheritance;
	@UiField Label userComments;
	@UiField Label operations;
	@UiField Anchor websiteUrl;
	@UiField Anchor facebookUrl;
	@UiField CheckBox contactable;

	@UiField Image picture;

	@UiField Image loading;

	@UiField Button jsonExport;

	@UiField Button delete;

	@UiField Anchor pictureDownload;

	long familyId;

	private String facebookId;

	private PictureReference pictureReference;

	@UiHandler("enabled")
	void onChange(final ClickEvent e) {
		enabled.setEnabled(false);
		loading.setVisible(true);
		adminService.enablePerson(getFamilyId(), getId(), isEnabled(), new AsyncCallback<Void>() {
			public void onFailure(final Throwable caught) {
				Window.alert("Failed to communicate with server, check network connection");
				enabled.setValue(!enabled.getValue());
				enabled.setEnabled(true);
				loading.setVisible(false);
			}
			public void onSuccess(final Void result) {
				loading.setVisible(false);
				enabled.setEnabled(true);
			}
		});
	}

	@UiHandler("jsonExport")
	void jsonExport(final ClickEvent e) {

		final PersonJsonOutputVisitor jsonVisitor = new PersonJsonOutputVisitor();
		accept(jsonVisitor);

		final DialogBox dialog = new DialogBox();
		dialog.setGlassEnabled(true);
		dialog.setAutoHideEnabled(true);
		dialog.setText("JSON format");
		final TextArea ta = new TextArea();
		ta.setHeight("500px");
		ta.setWidth("600px");
		ta.setText(jsonVisitor.toJson());
		final SimplePanel panel = new SimplePanel();
		panel.add( ta );
		dialog.add(panel);
		dialog.setWidth("620px");
		dialog.setHeight("520px");
		dialog.center();
	}

	@UiHandler("delete")
	void deletePerson(final ClickEvent e) {
		if( Window.confirm("Are you sure you want to delete "+getName()+" "+getSurname()+"?") ) {
			loading.setVisible(true);
			adminService.deletePerson(getId(), new AsyncCallback<Void>() {
				public void onFailure(final Throwable caught) {
					Window.alert("Failed to communicate with server, please check your connection");
					loading.setVisible(false);
				}
				public void onSuccess(final Void result) {
					setVisible(false);
				}
			});
		}
	}

	public void setEnabled(final boolean enabled) {
		this.enabled.setValue(enabled);
	}

	public boolean isEnabled() {
		return enabled.getValue();
	}

	public void setEmail(final String email) {
		this.email.setText(email);
	}

	public String getEmail() {
		return email.getText();
	}

	public void setId(final long id) {
		this.id.setText(Long.toString(id));
	}

	public long getId() {
		return Long.valueOf(id.getText());
	}

	public void setFamilyId(final long familyId) {
		this.familyId = familyId;
	}

	public long getFamilyId() {
		return familyId;
	}

	public String getCity() {
		return city.getText();
	}

	public String getState() {
		return state.getText();
	}

	public String getCountry() {
		return country.getText();
	}

	public String getLatitude() {
		return lat.getText();
	}

	public String getLongitude() {
		return lng.getText();
	}

	public String getName() {
		return name.getText();
	}

	public String getSurname() {
		return surname.getText();
	}

	public String getOccupation() {
		return occupation.getText();
	}

	public String getDob() {
		return dob.getText();
	}

	public String getGender() {
		return gender.getText();
	}

	public String getOperations() {
		return operations.getText();
	}

	public String getInheritance() {
		return inheritance.getText();
	}

	public PictureReference getPictureReference() {
		return pictureReference;
	}

	public String getUserComments() {
		return userComments.getText();
	}

	public boolean isContactable() {
		return contactable.getValue();
	}

	public String getWebsiteUrl() {
		return websiteUrl.getHref();
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setCity(final String city) {
		this.city.setText(city);
	}

	public void setState(final String state) {
		this.state.setText(state);
	}

	public void setCountry(final String country) {
		this.country.setText(country);
	}

	public void setLatitude(final String latitude) {
		lat.setText(latitude);
	}

	public void setLongitude(final String longitude) {
		lng.setText(longitude);
	}

	public void setName(final String name) {
		this.name.setText(name);
	}

	public void setSurname(final String surname) {
		this.surname.setText(surname);
	}

	public void setOccupation(final String occupation) {
		this.occupation.setText(occupation);
	}

	public void setDob(final String dob) {
		this.dob.setText(dob);
	}

	public void setGender(final String gender) {
		this.gender.setText(gender);
	}

	public void setOperations(final String operations) {
		this.operations.setText(operations);
	}

	public void setInheritance(final String inheritance) {
		this.inheritance.setText(inheritance);
	}

	public void setPictureReference(final PictureReference picture) {
		pictureReference = picture;
		if( picture!=null ) {
			this.picture.setUrl(picture.getServingUrl(150));
			pictureDownload.setHref(picture.getServingUrl());
			pictureDownload.setVisible(true);
		} else {
			pictureDownload.setVisible(false);
		}

	}

	public void setUserComments(final String userComments) {
		this.userComments.setText(userComments);
	}

	public void setContactable(final boolean contactable) {
		this.contactable.setValue(contactable);
	}

	public void setWebsiteUrl(final String websiteUrl) {
		if( websiteUrl != null && websiteUrl.length() > 0 ) {
			this.websiteUrl.setHref(websiteUrl);
			this.websiteUrl.setVisible(true);
		} else {
			this.websiteUrl.setVisible(false);
		}
	}

	public void setFacebookId(final String facebookId) {
		this.facebookId = facebookId;
		if( facebookId != null && facebookId!=null) {
			facebookUrl.setHref("http://www.facebook.com/profile.php?id="+facebookId);
			facebookUrl.setVisible(true);
		} else {
			facebookUrl.setVisible(false);
		}
	}

	public void accept(final PersonVisitor visitor) {
		final HasPersonBase base = this;
		final HasPersonEmail email = this;
		final HasPersonId id = this;
		final HasPersonEnabled enabled = this;
		visitor.visit(base);
		visitor.visit(id);
		visitor.visit(email);
		visitor.visit(enabled);
	}

	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(this, this, this, this);
	}

}
