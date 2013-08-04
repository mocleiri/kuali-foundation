package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.property.ImmutableProperties;

public final class WritePropertiesRequest extends WriteRequest {

	private final Properties properties;

	public WritePropertiesRequest(Properties properties, File outputFile, String encoding) {
		super(outputFile, encoding);
		this.properties = new ImmutableProperties(properties);
	}

	public Properties getProperties() {
		return properties;
	}

}
