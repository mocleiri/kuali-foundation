/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util.execute;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @deprecated
 */
@Deprecated
public class RunOnceExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(RunOnceExecutable.class);

	Executable executable;
	File propertiesFile;
	String property;
	String encoding;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution");
			return;
		}
		Assert.notNull(propertiesFile);
		Assert.notNull(property);
		Assert.notNull(executable);

		if (!propertiesFile.exists()) {
			logger.info("Skipping execution. File does not exist - [{}]", LocationUtils.getCanonicalPath(propertiesFile));
			return;
		}

		logger.info("Examining run once property [{}] in [{}]", property, LocationUtils.getCanonicalPath(propertiesFile));

		// Load the properties
		Properties properties = PropertyUtils.load(propertiesFile, encoding);

		// Translate the property value into an execution mode
		ExecutionMode mode = getExecutionMode(properties, property);

		// Are we going to run once?
		if (!isRunOnce(mode)) {
			logger.info("Skipping execution - [{}={}]", property, mode);
			return;
		}

		// Show the execution mode we are in
		logger.info("{}={}", property, mode);

		// Make sure we have the ability to successfully store updated properties back to the file
		if (!isAlways(mode)) {
			setState(properties, property, ExecutionMode.INPROGRESS);
		}

		try {
			// Invoke execute now that we have successfully transitioned things to INPROGRESS
			executable.execute();
			// There is always a chance that the executable finishes correctly and we encounter some kind of
			// issue just storing the properties back to the file. This should be pretty rare considering
			// we were able to successfully store the properties just prior to the executable commencing.
			// In any event, the executable won't run again in the normal use case because we can only get to this point
			// if the original execution mode was "TRUE" and we were able to successfully change it to "INPROGRESS"
			if (!isAlways(mode)) {
				setState(properties, property, ExecutionMode.COMPLETED);
			}
		} catch (Exception e) {
			if (!isAlways(mode)) {
				setState(properties, property, ExecutionMode.FAILED);
			}
			throw new IllegalStateException("Unexpected execution error", e);
		}
	}

	protected boolean isAlways(ExecutionMode mode) {
		return ExecutionMode.ALWAYS.equals(mode);
	}

	protected boolean isRunOnce(ExecutionMode mode) {
		if (ExecutionMode.RUNONCE.equals(mode)) {
			return true;
		}
		if (isAlways(mode)) {
			return true;
		}
		return ExecutionMode.TRUE.equals(mode);
	}

	protected ExecutionMode getExecutionMode(Properties properties, String key) {
		String value = properties.getProperty(property);
		if (value == null) {
			return ExecutionMode.NULL;
		} else {
			return ExecutionMode.valueOf(value.toUpperCase());
		}

	}

	protected void setState(Properties properties, String key, ExecutionMode mode) {
		logger.info("{}={}", key, mode);
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

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
