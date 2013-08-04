package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.property.ImmutableProperties;

public final class WriteProperties extends WriteRequest {

	private final Properties properties;

	public WriteProperties(Properties properties, File outputFile, String encoding, File relativeDir) {
		super(outputFile, encoding, relativeDir);
		this.properties = new ImmutableProperties(properties);
	}

	public Properties getProperties() {
		return properties;
	}

}
