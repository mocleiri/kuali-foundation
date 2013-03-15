/**
 * Copyright 2010-2012 The Kuali Foundation
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

import org.kuali.common.util.PropertyUtils;
import org.springframework.util.Assert;

public class StorePropertiesExecutable implements Executable {

	String encoding = "UTF-8";
	Properties properties;
	File outputFile;
	List<String> includes;
	List<String> excludes;

	@Override
	public void execute() {
		Assert.notNull(properties, "properties is null");
		Assert.notNull(outputFile, "outputFile is null");
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		Properties outputProperties = new Properties();
		for (String key : keys) {
			String value = properties.getProperty(key);
			outputProperties.setProperty(key, value);
		}
		PropertyUtils.store(outputProperties, outputFile, encoding);
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

}
