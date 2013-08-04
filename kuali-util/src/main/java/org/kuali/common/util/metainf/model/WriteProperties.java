package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.property.ImmutableProperties;

public final class WriteProperties {

	private final Properties properties;
	private final WriteRequest request;

	public WriteProperties(Properties properties, File outputFile, String encoding, File relativeDir) {
		this.request = new WriteRequest(outputFile, encoding, relativeDir);
		this.properties = new ImmutableProperties(properties);
	}

	public Properties getProperties() {
		return properties;
	}

	public WriteRequest getRequest() {
		return request;
	}

}
