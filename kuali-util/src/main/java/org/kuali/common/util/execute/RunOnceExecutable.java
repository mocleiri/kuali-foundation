package org.kuali.common.util.execute;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;

public class RunOnceExecutable implements Executable {

	Executable executable;
	File propertiesFile;
	String property;
	String encoding;

	@Override
	public void execute() {
		String location = LocationUtils.getCanonicalPath(propertiesFile);
		Properties properties = PropertyUtils.load(location);
		String value = properties.getProperty(property);
		boolean runonce = StringUtils.equalsIgnoreCase("RUNONCE", value);
		if (runonce) {
			executable.execute();
			properties.setProperty(property, "COMPLETED");
			PropertyUtils.store(properties, propertiesFile, encoding);
		}
	}

	public Executable getExecutable() {
		return executable;
	}

	public void setExecutable(Executable executable) {
		this.executable = executable;
	}

	public File getPropertiesFile() {
		return propertiesFile;
	}

	public void setPropertiesFile(File propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
