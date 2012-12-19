package org.kuali.common.util.execute;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunOnceExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(RunOnceExecutable.class);

	Executable executable;
	File propertiesFile;
	String property;
	String encoding;

	@Override
	public void execute() {
		Properties properties = PropertyUtils.load(propertiesFile, encoding);
		String runOnceValue = properties.getProperty(property);
		boolean runonce = StringUtils.equals(ExecutionMode.RUNONCE.name(), runOnceValue);
		if (runonce) {
			logger.info("Running once - [{}={}]", property, runOnceValue);
			// Make sure we have the ability to successfully store updated properties back to the file
			setState(properties, property, ExecutionMode.INPROGRESS);
			try {
				// Invoke execute now that we have successfully transitioned things to INPROGRESS
				logger.info("Executing task");
				executable.execute();
				setState(properties, property, ExecutionMode.COMPLETED);
			} catch (Exception e) {
				setState(properties, property, ExecutionMode.FAILED);
				throw new IllegalStateException("Unexpected execution error", e);
			}
		} else {
			logger.info("Skipping execution - [{}={}]", property, runOnceValue);
		}
	}

	protected void setState(Properties properties, String key, ExecutionMode mode) {
		logger.info("Updating state to {}", ExecutionMode.INPROGRESS);
		properties.setProperty(property, mode.name());
		PropertyUtils.store(properties, propertiesFile, encoding);
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
