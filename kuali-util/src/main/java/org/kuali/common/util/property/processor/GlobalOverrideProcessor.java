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
import org.kuali.common.util.property.GlobalPropertiesMode;

public class GlobalOverrideProcessor implements PropertyProcessor {

	GlobalPropertiesMode globalPropertiesMode;
	Mode propertyOverwriteMode;
	List<String> includes;
	List<String> excludes;

	public GlobalOverrideProcessor() {
		this(Constants.DEFAULT_GLOBAL_PROPERTIES_MODE, Constants.DEFAULT_PROPERTY_OVERWRITE_MODE);
	}

	public GlobalOverrideProcessor(GlobalPropertiesMode globalPropertiesMode) {
		this(globalPropertiesMode, Constants.DEFAULT_PROPERTY_OVERWRITE_MODE);
	}

	public GlobalOverrideProcessor(GlobalPropertiesMode globalPropertiesMode, Mode propertyOverwriteMode) {
		super();
		this.globalPropertiesMode = globalPropertiesMode;
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

	@Override
	public void process(Properties properties) {
		Properties global = PropertyUtils.getProperties(properties, globalPropertiesMode);
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String newValue = global.getProperty(key);
			PropertyUtils.addOrOverrideProperty(properties, key, newValue, propertyOverwriteMode);
		}
	}

	public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
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
