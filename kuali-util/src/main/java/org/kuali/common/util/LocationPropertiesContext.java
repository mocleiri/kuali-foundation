package org.kuali.common.util;

import java.util.Properties;

public class LocationPropertiesContext {

	Properties properties;
	String encoding = "UTF-8";
	String keySuffix = ".list";
	String locationPropertiesSuffix = ".properties";

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getKeySuffix() {
		return keySuffix;
	}

	public void setKeySuffix(String keySuffix) {
		this.keySuffix = keySuffix;
	}

	public String getLocationPropertiesSuffix() {
		return locationPropertiesSuffix;
	}

	public void setLocationPropertiesSuffix(String locationPropertiesSuffix) {
		this.locationPropertiesSuffix = locationPropertiesSuffix;
	}

}
