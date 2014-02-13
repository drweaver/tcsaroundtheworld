package org.tcsaroundtheworld.common.server;

import java.io.IOException;
import java.util.Properties;

public class EmailProperties {
	

	/*
	 * This property file needs to contain the admin email address
	 */
	public static final String EMAIL_PROPERTIES_FILE = "email.properties";
	public static final String ADMIN_EMAIL_PROPERTY_KEY = "admin";
	
	private Properties p = new Properties();
	
	public EmailProperties() {
		this(EMAIL_PROPERTIES_FILE);
	}
	
	public EmailProperties(String filename) {
		try {
			p.load(getClass().getResourceAsStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("File not found: "+filename, e);
		}
		if( p.getProperty(ADMIN_EMAIL_PROPERTY_KEY) == null ) {
			throw new RuntimeException("Missing "+ADMIN_EMAIL_PROPERTY_KEY+ " in file: " +filename);
		}
	}
	
	public String getAdminAddress() {
		String property = p.getProperty(ADMIN_EMAIL_PROPERTY_KEY);
		if( property.trim().length() == 0 ) {
			throw new RuntimeException("Empty value for: " + ADMIN_EMAIL_PROPERTY_KEY);
		}
		return property ;
	}
}
