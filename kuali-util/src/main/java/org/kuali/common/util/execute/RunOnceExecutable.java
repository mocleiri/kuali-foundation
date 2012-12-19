package org.kuali.common.util.execute;

import java.io.File;
import java.util.Properties;

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
		ExecutionMode mode = getExecutionMode(properties, property);
		boolean runonce = ExecutionMode.RUNONCE.equals(mode);
		if (runonce) {
			logger.info("Running once - [{}={}]", property, mode);
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
			logger.info("Skipping execution - [{}={}]", property, mode);
		}
	}

	protected ExecutionMode getExecutionMode(Properties properties, String key) {
		String value = properties.getProperty(property);
		if (value == null) {
			return ExecutionMode.NEVER;
		} else {
			return ExecutionMode.valueOf(value);
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
