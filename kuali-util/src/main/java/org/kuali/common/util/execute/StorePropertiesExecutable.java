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
	Properties properties;
	File outputFile;
	List<String> includes;
	List<String> excludes;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		Assert.notNull(outputFile, "outputFile is null");
		boolean exists = LocationUtils.exists(outputFile);
		if (exists && skipIfExists) {
			return;
		}
		Assert.notNull(properties, "properties is null");
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		Properties outputProperties = new Properties();
		for (String key : keys) {
			String value = properties.getProperty(key);
			outputProperties.setProperty(key, value);
		}
		store(outputProperties, outputFile, encoding);
	}

	protected void store(Properties properties, File outputFile, String encoding) {
		PropertyUtils.store(properties, outputFile, encoding);
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

}
