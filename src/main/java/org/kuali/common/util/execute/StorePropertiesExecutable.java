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
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.springframework.util.Assert;

public class StorePropertiesExecutable implements Executable {

	String encoding = "UTF-8";
	boolean skip;
	boolean skipIfExists = true;
	boolean skipIfEqual = true;
	Properties properties;
	File outputFile;
	List<String> includes;
	List<String> excludes;

	@Override
	public void execute() {
		// Nothing to do in this case
		if (skip) {
			return;
		}

		// Make sure we have an output file
		Assert.notNull(outputFile, "outputFile is null");

		// May not need to go any further
		if (LocationUtils.exists(outputFile) && skipIfExists) {
			return;
		}

		// Make sure we have some properties to work with
		Assert.notNull(properties, "properties is null");

		// Clone the properties they passed us
		Properties duplicate = PropertyUtils.duplicate(properties);

		// Trim out unwanted properties
		PropertyUtils.trim(duplicate, includes, excludes);

		// Might not need to store them
		if (!isStoreProperties(outputFile, skipIfEqual, duplicate)) {
			return;
		}

		// Persist them to the file system
		store(duplicate, outputFile, encoding);
	}

	protected boolean isStoreProperties(File outputFile, boolean skipIfEqual, Properties properties) {
		// Always return true if the file does not exist
		if (!LocationUtils.exists(outputFile)) {
			return true;
		}

		// The file might exist and contain the exact same properties, but it doesn't matter
		if (!skipIfEqual) {
			return true;
		}

		// Load the existing properties
		Properties loaded = PropertyUtils.loadSilently(outputFile);

		// Compare the loaded properties with the properties we have
		boolean equal = PropertyUtils.equals(loaded, properties);

		// If they are not equal to each other, we need to store them
		boolean storeProperties = !equal;

		// Return our value
		return storeProperties;
	}

	protected void store(Properties properties, File outputFile, String encoding) {
		PropertyUtils.store(properties, outputFile, encoding, null, true);
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
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

	public boolean isSkipIfExists() {
		return skipIfExists;
	}

	public void setSkipIfExists(boolean skipIfExists) {
		this.skipIfExists = skipIfExists;
	}

	public boolean isSkipIfEqual() {
		return skipIfEqual;
	}

	public void setSkipIfEqual(boolean skipIfEqual) {
		this.skipIfEqual = skipIfEqual;
	}

}
