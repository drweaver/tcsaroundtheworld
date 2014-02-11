package org.tcsaroundtheworld.submit.client;

import java.util.Arrays;
import java.util.List;

import org.tcsaroundtheworld.common.shared.HasPersonBase;
import org.tcsaroundtheworld.common.shared.HasPersonEmail;
import org.tcsaroundtheworld.common.shared.IsPerson;
import org.tcsaroundtheworld.common.shared.PersonCopyFromVisitor;
import org.tcsaroundtheworld.common.shared.PersonVisitor;
import org.tcsaroundtheworld.common.shared.PictureReference;
import org.tcsaroundtheworld.common.shared.verify.EmailValidator;
import org.tcsaroundtheworld.common.shared.verify.GenderValidator;
import org.tcsaroundtheworld.common.shared.verify.InheritanceValidator;
import org.tcsaroundtheworld.common.shared.verify.NameValidator;
import org.tcsaroundtheworld.common.shared.verify.OperationsValidator;
import org.tcsaroundtheworld.common.shared.verify.ScentenceValidator;
import org.tcsaroundtheworld.common.shared.verify.UrlValidator;
import org.tcsaroundtheworld.submit.client.map.AddressSearch;
import org.tcsaroundtheworld.submit.client.verify.AbstractFieldVerifier;
import org.tcsaroundtheworld.submit.client.verify.DobValidator;
import org.tcsaroundtheworld.submit.client.verify.FieldVerifier;
import org.tcsaroundtheworld.submit.client.verify.LabelFieldVerifier;
import org.tcsaroundtheworld.submit.client.verify.RadioFieldVerifier;
import org.tcsaroundtheworld.submit.client.verify.SubmissionVerifier;
import org.tcsaroundtheworld.submit.client.verify.TextFieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.gwtfb.client.Callback;
import com.gwtfb.client.UserJso;
import com.gwtfb.sdk.FBCore;
import com.gwtfb.sdk.FBEvent;
import com.gwtfb.sdk.FBXfbml;

public class PersonSubmissionWidget extends Composite implements IsPerson, HasPersonBase, HasPersonEmail {

	private static PersonSubmissionWidgetUiBinder uiBinder = GWT.create(PersonSubmissionWidgetUiBinder.class);

	interface PersonSubmissionWidgetUiBinder extends UiBinder<Widget,PersonSubmissionWidget> {
	}

	private final SubmissionHelp submissionHelp = GWT.create(SubmissionHelp.class);

	private final FacebookApiKeys facebookApiKeys = GWT.create(FacebookApiKeys.class);

	private final FBCore fbCore = GWT.create(FBCore.class);
	private final FBEvent fbEvent = GWT.create(FBEvent.class);
	private final FBXfbml fbXfbml = GWT.create(FBXfbml.class);

	private final boolean status = true;
	private final boolean xfbml = true;
	private final boolean cookie = true;

	@UiField Grid table;
	@UiField VerticalPanel fbLoginContainer;

	@UiField TextBox firstName;
	@UiField TextBox surname;
	@UiField TextBox email;
	@UiField TextBox occupation;
	@UiField TextBox dob;
	@UiField TextBox operations;
	@UiField TextBox userComments;
	@UiField TextBox websiteUrl;
	@UiField TextBox fbId;

	@UiField RadioButton genderMale;
	@UiField RadioButton genderFemale;
	@UiField RadioButton inheritanceInherited;
	@UiField RadioButton inheritanceSporadic;
	@UiField CheckBox contactable;
	Label city = new Label();
	Label state = new Label();
	Label country = new Label();
	Anchor findAddress = new Anchor("find address");
	Anchor clearAddress = new Anchor("(clear)");
	@UiField SimplePanel addressContainer;
	@UiField Label lat;
	@UiField Label lng;


	@UiField Label firstNameError;
	@UiField Label surnameError;
	@UiField Label emailError;
	@UiField Label occupationError;
	@UiField Label dobError;
	@UiField Label operationsError;
	@UiField Label genderError;
	@UiField Label inheritanceError;
	@UiField Label pictureError;
	@UiField Label addressError;
	@UiField Label userCommentsError;
	@UiField Label websiteUrlError;

	@UiField Anchor importDialogLink;

	@UiField Image firstNameInfo;

	private final AddressSearchDialog addressSearchDialog = new AddressSearchDialog();

	private final PopupPanel infoPopup = new DecoratedPopupPanel(true, false);
	private final SubmissionVerifier submissionVerifier = new SubmissionVerifier();

	private PictureReference pictureRef = null;
	@UiField VerticalPanel pictureUploadPanel;
	final PictureUploadingDialog pictureUploadingDialog = new PictureUploadingDialog();

	private final int COORDS_ROW = 11;
	private final int FBID_ROW = 12;
	private final int IMPORT_ROW = 14;
	private final int DUMMY_VALUES_ROW = 15;

	@SuppressWarnings("unchecked")
	public PersonSubmissionWidget(final int formNumber) {
		initWidget(uiBinder.createAndBindUi(this));
		addStyleName("new-person");

		firstName.setMaxLength(NameValidator.MAX_LENGTH);
		surname.setMaxLength(NameValidator.MAX_LENGTH);
		email.setMaxLength(EmailValidator.MAX_LENGTH);
		occupation.setMaxLength(ScentenceValidator.MAX_LENGTH);
		userComments.setMaxLength(ScentenceValidator.MAX_LENGTH);
		websiteUrl.setMaxLength(UrlValidator.MAX_LENGTH);

		submissionVerifier.add(new TextFieldVerifier("name", true, new NameValidator(), firstNameError, firstName) );
		submissionVerifier.add(new TextFieldVerifier("surname", true, new NameValidator(), surnameError, surname) );
		submissionVerifier.add(new TextFieldVerifier("email", true, new EmailValidator(), emailError, email) );
		submissionVerifier.add(new TextFieldVerifier("occupation", false, new ScentenceValidator(), occupationError, occupation) );
		submissionVerifier.add(new TextFieldVerifier("D.O.B", true, new DobValidator(), dobError, dob) );
		submissionVerifier.add(new TextFieldVerifier("number of operations", false, new OperationsValidator(), operationsError, operations) );
		submissionVerifier.add(new RadioFieldVerifier("gender", true, new GenderValidator(), genderError, genderMale, genderFemale) );
		submissionVerifier.add(new RadioFieldVerifier("inheritance", true, new InheritanceValidator(), inheritanceError, inheritanceSporadic, inheritanceInherited) );
		submissionVerifier.add(new LabelFieldVerifier("address", true, new NameValidator(), addressError, city) );
		submissionVerifier.add(new TextFieldVerifier("about me", false, new ScentenceValidator(), userCommentsError, userComments) );
		submissionVerifier.add(new TextFieldVerifier("website url", false, new UrlValidator(), websiteUrlError, websiteUrl));
		submissionVerifier.add(new PictureVerifier());

		final String genderGroup = "gender-"+formNumber;
		final String inhGroup = "inheritance-"+formNumber;
		genderMale.setName(genderGroup);
		genderFemale.setName(genderGroup);
		inheritanceInherited.setName(inhGroup);
		inheritanceSporadic.setName(inhGroup);

		table.getColumnFormatter().setWidth(2, "150px");

		if( !Submission.extendedMode() ) {
			table.getRowFormatter().setVisible(COORDS_ROW, false);
			table.getRowFormatter().setVisible(IMPORT_ROW, false);
			table.getRowFormatter().setVisible(FBID_ROW, false);
		}
		if( GWT.isProdMode() ) {
			table.getRowFormatter().setVisible(DUMMY_VALUES_ROW, false);
		}

		resetPictureUpload();
		resetAddress();

		final List<? extends FocusWidget> tabIndexList = Arrays.asList(firstName,surname,email,occupation,dob,operations,genderMale,genderFemale,inheritanceInherited,inheritanceSporadic,userComments,websiteUrl);
		int tabIndex = tabIndexList.size() * formNumber + 1;
		for( final Focusable w : tabIndexList) {
			w.setTabIndex(tabIndex++);
		}
		firstName.setFocus(true);
		contactable.setValue(true);

		importDialogLink.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				new OriginalSubmissionImportDialog().center();
			}
		});
		findAddress.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				addressSearchDialog.center();
				addressSearchDialog.search.focusQueryBox();
			}
		});
		clearAddress.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				resetAddress();
			}
		});

		if( formNumber == 0 && !Submission.extendedMode()) {
			initFbConnect();
		}

	}

	void showInfo( final String info, final MouseOverEvent event ) {
		infoPopup.clear();
		infoPopup.setAnimationEnabled(true);
		infoPopup.setWidth("200px");
		infoPopup.add( new Label(info) );
		infoPopup.setPopupPositionAndShow(new PositionCallback() {
			public void setPosition(final int offsetWidth, final int offsetHeight) {
				infoPopup.setPopupPosition(event.getClientX()+16, event.getClientY()+16);
			}
		});
	}

	@UiHandler("firstNameInfo")
	void firstNameInfo( final MouseOverEvent e ) { showInfo( submissionHelp.firstName(), e ); }
	@UiHandler("surnameInfo")
	void surnameInfo( final MouseOverEvent e ) { showInfo( submissionHelp.surname(), e ); }
	@UiHandler("emailInfo")
	void emailInfo( final MouseOverEvent e ) { showInfo( submissionHelp.email(), e ); }
	@UiHandler("inheritanceInfo")
	void inheritanceInfo( final MouseOverEvent e ) { showInfo( submissionHelp.inheritance(), e ); }
	@UiHandler("userCommentsInfo")
	void userCommentsInfo( final MouseOverEvent e ) { showInfo( submissionHelp.userComments(), e ); }
	@UiHandler("addressInfo")
	void addressInfo( final MouseOverEvent e ) { showInfo( submissionHelp.address(), e ); }
	@UiHandler("pictureInfo")
	void pictureInfo( final MouseOverEvent e ) { showInfo( submissionHelp.picture(), e ); }

	@UiHandler({"firstNameInfo","surnameInfo","emailInfo","inheritanceInfo","userCommentsInfo","addressInfo","pictureInfo"})
	void hideInfo(final MouseOutEvent event ) {
		infoPopup.hide();
	}

	private void initFbConnect() {
		//FIXME: FB connect isn't working, disable for now..
		/*
		final String apiKey = facebookApiKeys.keys().get(Location.getHost());
		if( apiKey == null ) {
			GWT.log("No valid FB api key available for host: " + Location.getHost());
			return;
		}
		fbCore.init(apiKey, status, cookie, xfbml);
		class SessionChangeCallback extends Callback<JavaScriptObject> {
			@Override
			public void onSuccess ( final JavaScriptObject response ) {
				if( fbCore.getSession() != null ) {
					populateFromFb();
				} else {
					resetFbPanel();
				}
			}
		}
		final SessionChangeCallback sessionChangeCallback = new SessionChangeCallback ();
		fbEvent.subscribe("auth.sessionChange",sessionChangeCallback);
		resetFbPanel();
		if( fbCore.getSession() != null ) {
			populateFromFb();
		}
        */
	}

	@SuppressWarnings("static-access")
	private void resetFbPanel() {
		fbLoginContainer.clear();
		fbLoginContainer.add ( new HTML ( "<fb:login-button autologoutlink='true' perms='email,user_about_me,user_birthday,user_website,user_hometown' /> " ) );
		fbLoginContainer.add( new Label("Connect to Facebook to fill in your details automatically."));
		fbXfbml.parse(fbLoginContainer);
	}

	private void populateFromFb() {
		class MeCallback extends Callback<JavaScriptObject> {
			@SuppressWarnings("static-access")
			@Override
			public void onSuccess(final JavaScriptObject result) {
				final UserJso user = result.cast();
				setName(user.getName());
				setSurname(user.getSurname());
				setEmail(user.getEmail());
				setDob(user.getDob());
				setUserComments(user.getUserComments());
				setGender(user.getGender());
				setWebsiteUrl(user.getWebsiteUrl());
				setFacebookId(user.getId());
				addressSearchDialog.search.getAddressQuery().setText(user.getCity());
				//Removed due to users think they don't need to upload a picture
				// fbLoginContainer.add( new HTML( "<fb:profile-pic uid='"+user.getId()+"' facebook-logo='true' linked='false' size='small'></fb:profile-pic>" ) );
				fbXfbml.parse(fbLoginContainer);
			}
		}
		fbCore.api("/me", new MeCallback() );
	}

	private void resetAddress() {
		addressContainer.clear();
		addressContainer.add( findAddress );
		city.setText("");
		state.setText("");
		country.setText("");
		lat.setText("");
		lng.setText("");
		addressError.setText("");
	}

	private void updateAddress( final String city, final String state, final String country, final String lat, final String lng ) {
		this.city.setText(city);
		this.state.setText(state);
		this.country.setText(country);
		this.lat.setText( lat );
		this.lng.setText( lng );
		addressContainer.clear();
		final HorizontalPanel panel = new HorizontalPanel();
		panel.add( this.city );
		panel.add( new HTML(",&nbsp;") );
		panel.add( this.state );
		panel.add( new HTML(",&nbsp;") );
		panel.add( this.country );
		panel.add( new HTML("&nbsp;") );
		panel.add( clearAddress );
		addressContainer.add( panel );
		addressError.setText("");
	}

	@UiHandler("websiteUrl")
	void cleanUrl(final ChangeEvent event) {
		String url = websiteUrl.getText().trim();
		if( url.length() == 0 ) {
			return;
		}
		if( url.endsWith("/") ) {
			url = url.substring(0, url.length()-1);
		}
		if( ! ( url.startsWith("http://") || url.startsWith("https://") ) ) {
			url = "http://" + url;
		}
		websiteUrl.setText(url);
	}

	@UiHandler("dummyValuesJoBloggs")
	void dummyValuesJoBloggsClicked(final ClickEvent event) {
		firstName.setText("Jo");
		surname.setText("Bloggs");
		email.setText("jo.bloggs@gmail.com");
		occupation.setText("Student");
		dob.setText("01/01/2000");
		operations.setText("10");
		genderMale.setValue(true);
		inheritanceSporadic.setValue(true);
		userComments.setText("A bit common");
		websiteUrl.setText("http://www.google.com");
		setFacebookId("826939744");
		updateAddress("Epping", "London", "GB", "52.0", "52.0");
	}

	private static class PictureUploadResponse  extends JavaScriptObject {
		protected PictureUploadResponse() {}
		public final native String getStatus()      /*-{ return this.status;      }-*/;
		public final native String getKey()         /*-{ return this.key;         }-*/;
		public static native PictureUploadResponse parseJson(String json) /*-{ return eval('(' + json + ')') }-*/;
	}

	private void resetPictureUpload() {
		pictureUploadPanel.clear();
		pictureRef = null;
		final FormPanel formPanelForPicture = new FormPanel();
		formPanelForPicture.setAction("/pictureupload");
		formPanelForPicture.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanelForPicture.setMethod(FormPanel.METHOD_POST);

		final FileUpload fileUpload = new FileUpload();
		fileUpload.setName("person-picture");
		fileUpload.setWidth("150px");
		formPanelForPicture.add( fileUpload );
		pictureUploadPanel.add( formPanelForPicture );
		fileUpload.addChangeHandler(new ChangeHandler() {
			public void onChange(final ChangeEvent event) {
				formPanelForPicture.submit();
			}
		});
		formPanelForPicture.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			public void onSubmitComplete(final SubmitCompleteEvent event) {
				pictureUploadingDialog.hide();
				//				pictureUploadPanel.clear();
				GWT.log( "Response from server: " + event.getResults() );
				final PictureUploadResponse res = PictureUploadResponse.parseJson( event.getResults() );
				GWT.log("Status="+res.getStatus());
				if( res.getStatus().equals("OK") ) {
					GWT.log("Key="+res.getKey());
					updatePicture(new PictureReference(res.getKey()));
				} else {
					pictureError.setText(res.getStatus());
					resetPictureUpload();
				}

			}
		});
		formPanelForPicture.addSubmitHandler(new SubmitHandler() {
			public void onSubmit(final SubmitEvent event) {
				pictureUploadingDialog.center();
			}
		});
	}

	private void updatePicture( final PictureReference pictureRef ) {
		if( pictureRef == null ) {
			resetPictureUpload();
		} else {
			pictureUploadPanel.clear();
			pictureError.setText("");
			final Anchor clearPicture = new Anchor("clear picture");
			clearPicture.addClickHandler(new ClickHandler() {
				public void onClick(final ClickEvent event) {
					resetPictureUpload();
				}
			});
			pictureUploadPanel.add( clearPicture );
			this.pictureRef = pictureRef;
			pictureUploadPanel.add( new Image( pictureRef.getServingUrl(80) ) );
		}
	}

	private static class PictureUploadingDialog extends Composite {
		DialogBox dialog = new DialogBox();
		public PictureUploadingDialog() {
			dialog.setGlassEnabled(true);
			dialog.setAutoHideEnabled(false);
			dialog.setAutoHideOnHistoryEventsEnabled(true);
			dialog.setText("Uploading...");
		}
		public void center() {
			dialog.center();
			dialog.clear();
			final HorizontalPanel uploadingMessage = new HorizontalPanel();
			uploadingMessage.setSpacing(6);
			uploadingMessage.add( new Image("img/loading.gif") );
			uploadingMessage.add( new Label("Your picture is being uploaded, please wait...") );
			dialog.add( uploadingMessage );
		}
		public void hide() {
			dialog.hide();
		}
	}

	public boolean isValid() {
		return submissionVerifier.isValid();
	}

	public void copyCommonInformation(final PersonSubmissionWidget existing) {
		surname.setText(existing.surname.getText());
		email.setText( existing.email.getText() );
		contactable.setValue( existing.contactable.getValue() );

		if( existing.lat.getText().length() > 0 && existing.lng.getText().length() > 0 ) {
			updateAddress(existing.getCity(), existing.getState(), existing.getCountry(), existing.getLatitude(), existing.getLongitude());
		}

	}

	private String getRadioButtonChoice( final List<RadioButton> options, final List<String> values ) {
		for( int i=0; i<options.size(); i++ ) {
			final RadioButton rb = options.get(i);
			if( rb.getValue() ) {
				return values.get(i);
			}
		}
		return null;
	}

	private class AddressSearchDialog extends DialogBox {
		final AddressSearch search = new AddressSearch();
		public AddressSearchDialog() {
			this(null);
		}
		public AddressSearchDialog(final String initialQuery) {
			setGlassEnabled(true);
			setText("Address Search");
			search.getAddressQuery().setText(initialQuery);
			add( search );
			search.getCancel().addClickHandler(new ClickHandler() {
				public void onClick(final ClickEvent event) {
					hide();
				}
			});
			search.getOk().addClickHandler(new ClickHandler() {
				public void onClick(final ClickEvent event) {
					hide();
					updateAddress(search.getCity(), search.getState(), search.getCountry(), search.getLatString(), search.getLngString());
				}
			});
		}
	}

	private class OriginalSubmissionImportDialog extends DialogBox {
		public OriginalSubmissionImportDialog() {
			setGlassEnabled(true);
			setText("Original Email Submission Import");
			final PersonSubmissionImport importWidget = new PersonSubmissionImport();
			add( importWidget );
			importWidget.cancelButton.addClickHandler(new ClickHandler() {
				public void onClick(final ClickEvent event) {
					hide();
				}
			});
			importWidget.importButton.addClickHandler(new ClickHandler() {
				public void onClick(final ClickEvent event) {
					if( importWidget.emailChoice.getValue() ) {
						hide();
						final PersonSubmissionEmailImport emailImport = new PersonSubmissionEmailImport( importWidget.content.getText() );
						PersonSubmissionWidget.this.accept( emailImport.createCopyVisitor() );
						if( emailImport.getAddressSearch() != null ) {
							addressSearchDialog.center();
							addressSearchDialog.search.getAddressQuery().setText(emailImport.getAddressSearch());
							addressSearchDialog.search.showAddress();
						}
					} else if( importWidget.jsonChoice.getValue() ) {
						hide();
						PersonSubmissionWidget.this.accept(PersonSubmissionJso.parseJson( importWidget.content.getText() ).createCopyVisitor());
						updateAddress(getCity(), getState(), getCountry(), getLatitude(), getLongitude());
					} else {
						Window.alert("Please select an import type!");
					}
				}

			});
		}
	}

	private class PictureVerifier implements FieldVerifier {
		public boolean checkField() {
			if( pictureRef == null ) {
				pictureError.setText(AbstractFieldVerifier.FIELD_IS_MANDATORY);
				return false;
			} else {
				pictureError.setText("");
				return true;
			}
		}
	}

	public String getEmail() {
		return email.getText().trim();
	}

	public void setEmail(final String email) {
		this.email.setText(email);
	}

	public String getCity() {
		return city.getText().trim();
	}

	public String getCountry() {
		return country.getText().trim();
	}

	public String getDob() {
		return dob.getText().trim();
	}

	public String getGender() {
		return getRadioButtonChoice(Arrays.asList(genderMale,genderFemale),Arrays.asList("Male","Female"));
	}

	public String getInheritance() {
		return getRadioButtonChoice(Arrays.asList(inheritanceInherited,inheritanceSporadic),Arrays.asList("Inherited","Sporadic"));
	}

	public String getLatitude() {
		return lat.getText().trim();
	}

	public String getLongitude() {
		return lng.getText().trim();
	}

	public String getName() {
		return firstName.getText().trim();
	}

	public String getOccupation() {
		return occupation.getText().trim();
	}

	public String getOperations() {
		return operations.getText().trim();
	}

	public PictureReference getPictureReference() {
		return pictureRef;
	}

	public String getState() {
		return state.getText().trim();
	}

	public String getSurname() {
		return surname.getText().trim();
	}

	public String getUserComments() {
		return userComments.getText().trim();
	}

	public boolean isContactable() {
		return contactable.getValue();
	}

	public void setCity(final String city) {
		this.city.setText(city);
	}

	public void setContactable(final boolean contactable) {
		this.contactable.setValue(contactable);
	}

	public void setCountry(final String country) {
		this.country.setText(country);
	}

	public void setDob(final String dob) {
		this.dob.setText(dob);
	}

	public void setGender(final String gender) {
		if( gender != null ) {
			if( gender.toLowerCase().equals("male") ) {
				genderMale.setValue(true);
			} else if ( gender.toLowerCase().equals("female") ) {
				genderFemale.setValue(true);
			}
		}
	}

	public void setInheritance(final String inheritance) {
		if( inheritance != null ) {
			if( inheritance.toLowerCase().equals("inherited") ) {
				inheritanceInherited.setValue(true);
			} else if ( inheritance.toLowerCase().equals("sporadic") ) {
				inheritanceSporadic.setValue(true);
			}
		}
	}

	public void setLatitude(final String latitude) {
		lat.setText(latitude);
	}

	public void setLongitude(final String longitude) {
		lng.setText(longitude);
	}

	public void setName(final String name) {
		firstName.setText(name);
	}

	public void setOccupation(final String occupation) {
		this.occupation.setText(occupation);
	}

	public void setOperations(final String operations) {
		this.operations.setText(operations);
	}

	public void setPictureReference(final PictureReference picture) {
		if( picture != null ) {
			updatePicture(picture);
		} else {
			resetPictureUpload();
		}
	}

	public void setState(final String state) {
		this.state.setText(state);
	}

	public void setSurname(final String surname) {
		this.surname.setText(surname);
	}

	public void setUserComments(final String userComments) {
		this.userComments.setText(userComments);
	}

	public String getWebsiteUrl() {
		return websiteUrl.getText().trim();
	}

	public void setWebsiteUrl(final String websiteUrl) {
		this.websiteUrl.setText(websiteUrl);
	}

	public String getFacebookId() {
		return fbId.getText().trim();
	}

	public void setFacebookId(final String facebookId) {
		fbId.setText(facebookId);
	}

	public void accept(final PersonVisitor visitor) {
		final HasPersonBase base = this;
		final HasPersonEmail email = this;
		visitor.visit(base);
		visitor.visit(email);
	}

	public PersonVisitor createCopyVisitor() {
		return new PersonCopyFromVisitor(this, null, this, null);
	}

}