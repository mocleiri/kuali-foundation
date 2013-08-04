package org.kuali.common.util.metainf.model;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.property.ImmutableProperties;

public final class PropertiesWriteRequest {

	private final Properties properties;
	private final File outputFile;
	private final String encoding;

	public PropertiesWriteRequest(Properties properties, File outputFile, String encoding) {
		super();
		this.properties = new ImmutableProperties(properties);
		this.outputFile = outputFile;
		this.encoding = encoding;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public String getEncoding() {
		return encoding;
	}

	public Properties getProperties() {
		return properties;
	}

}
