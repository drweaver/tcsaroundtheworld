package org.tcsaroundtheworld.submit.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReCaptchaPrivateKeys {

	private final static String PROPERTY_FILE="ReCaptchaPrivateKeys.properties";

	private final Properties properties = new Properties();
	private final Map<String,String> privateKeys = new HashMap<String, String>();

	private final static Logger log = Logger.getLogger(ReCaptchaPrivateKeys.class.getName());

	public ReCaptchaPrivateKeys() {
		try {
			properties.load( getClass().getResourceAsStream(PROPERTY_FILE) );
			for( Object o : properties.keySet() ) {
				final String host = (String)o;
				privateKeys.put( host, properties.getProperty(host) );
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Failed to load recatcha private keys from properties file " + PROPERTY_FILE+": "+e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	Map<String,String> privateKeys() {
		return privateKeys;
	}

}
