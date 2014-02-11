package org.tcsaroundtheworld.common.shared;

import java.util.List;

public class InvalidPersonException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final List<String> errors;

	public InvalidPersonException(final List<String> errors) {
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

}
