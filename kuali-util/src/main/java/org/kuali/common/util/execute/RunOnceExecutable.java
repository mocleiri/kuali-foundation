package org.kuali.common.util.execute;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class RunOnceExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(RunOnceExecutable.class);

	Executable executable;
	File propertiesFile;
	String property;
	String encoding;

	@Override
	public void execute() {
		Assert.notNull(propertiesFile);
		Assert.notNull(property);
		Assert.notNull(executable);

		if (!propertiesFile.exists()) {
			logger.info("Skipping execution. File does not exist - [{}]", LocationUtils.getCanonicalPath(propertiesFile));
			return;
		}

		logger.info("Loading run once properties from - [{}]", LocationUtils.getCanonicalPath(propertiesFile));
		Properties properties = PropertyUtils.load(propertiesFile, encoding);
		ExecutionMode mode = getExecutionMode(properties, property);
		boolean runonce = ExecutionMode.RUNONCE.equals(mode);
		if (runonce) {
			logger.info("Running once - [{}={}]", property, mode);
			// Make sure we have the ability to successfully store updated properties back to the file
			setState(properties, property, ExecutionMode.INPROGRESS);
			try {
				// Invoke execute now that we have successfully transitioned things to INPROGRESS
				executable.execute();
				// There is always a chance that the executable finishes correctly and we encounter some kind of
				// issue just storing the properties back to the file. This should be pretty rare considering
				// we were able to successfully store the properties just prior to the executable commencing
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
		logger.info("Updating state [{}={}]", key, mode);
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
