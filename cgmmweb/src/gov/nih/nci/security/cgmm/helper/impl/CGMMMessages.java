package gov.nih.nci.security.cgmm.helper.impl;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CGMMMessages {
	private static final String BUNDLE_NAME = "gov.nih.nci.security.cgmm.helper.impl.messages"; 

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private CGMMMessages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
