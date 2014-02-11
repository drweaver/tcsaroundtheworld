package org.tcsaroundtheworld.submit.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ReCaptchaFields implements IsSerializable {

	private String challenge;
	private String response;
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}



}
