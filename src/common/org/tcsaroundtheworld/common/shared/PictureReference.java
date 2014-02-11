package org.tcsaroundtheworld.common.shared;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PictureReference implements IsSerializable, Serializable {

	private static final long serialVersionUID = 5830763156362135925L;
	protected String id;

	public PictureReference() {}

	public PictureReference(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getServingUrl(final int size) {
		return getServingUrl()+"&s="+size;
	}

	@Override
	public String toString() {
		return "PictureReference="+id;
	}

	public String getServingUrl() {
		return "/pictureserve?q="+id;
	}

}
