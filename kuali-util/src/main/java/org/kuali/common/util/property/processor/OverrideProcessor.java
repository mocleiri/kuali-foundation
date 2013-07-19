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
package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;

public class OverrideProcessor implements PropertyProcessor {

	Mode propertyOverwriteMode;
	Properties overrideProperties;
	List<String> includes;
	List<String> excludes;
	int indent;

	public OverrideProcessor() {
		this(Constants.DEFAULT_PROPERTY_OVERWRITE_MODE);
	}

	public OverrideProcessor(Mode propertyOverwriteMode) {
		this(Constants.DEFAULT_PROPERTY_OVERWRITE_MODE, null, 0);
	}

	public OverrideProcessor(Mode propertyOverwriteMode, Properties overrideProperties) {
		this(Constants.DEFAULT_PROPERTY_OVERWRITE_MODE, overrideProperties, 0);
	}

	public OverrideProcessor(Mode propertyOverwriteMode, Properties overrideProperties, int indent) {
		super();
		this.propertyOverwriteMode = propertyOverwriteMode;
		this.overrideProperties = overrideProperties;
		this.indent = indent;
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(overrideProperties, includes, excludes);
		for (String key : keys) {
			String newValue = overrideProperties.getProperty(key);
			PropertyUtils.addOrOverrideProperty(properties, key, newValue, propertyOverwriteMode, indent);
		}
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	public Properties getOverrideProperties() {
		return overrideProperties;
	}

	public void setOverrideProperties(Properties overrideProperties) {
		this.overrideProperties = overrideProperties;
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

}
