package org.tcsaroundtheworld.common.shared;

public abstract class AbstractOutputJson implements OutputsJson {

	public final String toJson() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{ \"").append(jsonName()).append("\": { ")
		.append(innerJson().replaceFirst(",\\s+$", "")).append(" } }\n");
		return sb.toString();
	}

	protected String jsonString(final String key, final String value) {
		return "\""+key+"\": \""+(value==null?"":value)+"\", ";
	}

	protected String jsonBoolean(final String key, boolean value) {
		return "\""+key+"\": "+(value?"true":"false")+", ";
	}

	protected abstract String jsonName();

	protected abstract String innerJson();

}
