package org.tcsaroundtheworld.common.server;

import java.io.Serializable;

import org.tcsaroundtheworld.common.shared.PictureReference;


import com.google.gwt.user.client.rpc.IsSerializable;

public class Picture extends PictureReference implements IsSerializable, Serializable {

	private static final long serialVersionUID = -659581456108531126L;

	private final byte[] img;

	private final String contentType;

	public Picture(String id, byte[] img, String contentType) {
		super(id);
		this.img = img;
		this.contentType = contentType;
	}

	public byte[] getImg() {
		return img;
	}

	public String getContentType() {
		return contentType;
	}

}
