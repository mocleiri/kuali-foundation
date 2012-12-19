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
		Properties properties = PropertyUtils.load(propertiesFile);
		String value = properties.getProperty(property);
		logger.info("[{}={}]", property, value);
		boolean runonce = StringUtils.equals(ExecutionMode.RUNONCE.name(), value);
		if (runonce) {
			// Make sure we have the ability to successfully store updated properties back to the file
			logger.info("Updating state to {}", ExecutionMode.INPROGRESS);
			setState(properties, property, ExecutionMode.INPROGRESS);
			try {
				// Invoke execute now that we have successfully transitioned things to INPROGRESS
				logger.info("Executing task");
				executable.execute();
				logger.info("Updating state to {}", ExecutionMode.COMPLETED);
				setState(properties, property, ExecutionMode.COMPLETED);
			} catch (Exception e) {
				setState(properties, property, ExecutionMode.FAILED);
				throw new IllegalStateException("Unexpected execution error", e);
			}
		}
	}

	protected void setState(Properties properties, String key, ExecutionMode mode) {
		properties.setProperty(property, mode.name());
		PropertyUtils.store(properties, propertiesFile, encoding);
	}

	protected boolean isState(Properties properties, String key, ExecutionMode mode) {
		String value = properties.getProperty(key);
		return StringUtils.equals(mode.name(), value);
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
