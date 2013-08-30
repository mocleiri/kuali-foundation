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
package org.kuali.common.util.runonce;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunOnceExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(RunOnceExecutable.class);

	public RunOnceExecutable(Executable executable, File propertiesFile, String property, String encoding, boolean skip) {
		Assert.noNulls(executable, propertiesFile);
		Assert.noBlanks(property, encoding);
		this.executable = executable;
		this.propertiesFile = new CanonicalFile(propertiesFile);
		this.property = property;
		this.encoding = encoding;
		this.skip = skip;
	}

	private final Executable executable;
	private final File propertiesFile;
	private final String property;
	private final String encoding;
	private final boolean skip;

	@Override
	public void execute() {

		// Skip has been explicitly configured
		if (skip) {
			return;
		}

		// If the properties file does not exist, don't do anything
		if (!propertiesFile.exists()) {
			logger.info("Skipping execution. File does not exist - [{}]", propertiesFile);
			return;
		}

		// Log a message indicating we found the properties file and are going to inspect its contents
		logger.info("Examining run once property [{}] in [{}]", property, propertiesFile);

		// Load the properties
		Properties properties = PropertyUtils.load(propertiesFile, encoding);

		// Extract the property we are interested in
		String value = properties.getProperty(property);

		// The property must be present and equal to "true"
		boolean runonce = StringUtils.equalsIgnoreCase(Boolean.TRUE.toString(), value);

		// The property was not present or was not set to "true"
		if (!runonce) {
			logger.info("Skipping execution - [{}={}]", property, value);
			return;
		}

		// Update the property in the properties file to INPROGRESS
		updatePersistentState(properties, RunOnceState.INPROGRESS);

		try {
			// Invoke execute now that we have successfully transitioned things to INPROGRESS
			executable.execute();

			// Update the property in the properties file to COMPLETED
			updatePersistentState(properties, RunOnceState.COMPLETED);
		} catch (Exception e) {
			// Update the property in the properties file to FAILED
			updatePersistentState(properties, RunOnceState.FAILED);
			throw new IllegalStateException("Unexpected execution error", e);
		}
	}

	protected void updatePersistentState(Properties properties, RunOnceState state) {
		properties.setProperty(property, state.name());
		PropertyUtils.store(properties, propertiesFile, encoding);
	}

	public static Logger getLogger() {
		return logger;
	}

	public Executable getExecutable() {
		return executable;
	}

	public File getPropertiesFile() {
		return propertiesFile;
	}

	public String getProperty() {
		return property;
	}

	public String getEncoding() {
		return encoding;
	}

	public boolean isSkip() {
		return skip;
	}

}
