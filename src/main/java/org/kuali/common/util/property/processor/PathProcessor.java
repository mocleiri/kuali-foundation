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

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;

public class PathProcessor implements PropertyProcessor {

	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;
	String suffix = Constants.DEFAULT_PATH_SUFFIX;
	List<String> includes;
	List<String> excludes;

	public PathProcessor() {
		this(Collections.<String> emptyList());
	}

	public PathProcessor(String include) {
		this(Collections.singletonList(include));
	}

	public PathProcessor(List<String> includes) {
		super();
		this.includes = includes;
	}

	@Override
	public void process(Properties properties) {
		List<String> keys = PropertyUtils.getSortedKeys(properties, includes, excludes);
		for (String key : keys) {
			String oldValue = properties.getProperty(key);
			String newValue = Str.getPath(oldValue);
			String newKey = key + "." + suffix;
			PropertyUtils.addOrOverrideProperty(properties, newKey, newValue, propertyOverwriteMode);
		}
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
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

	public Mode getPropertyOverwriteMode() {
		return propertyOverwriteMode;
	}

	public void setPropertyOverwriteMode(Mode propertyOverwriteMode) {
		this.propertyOverwriteMode = propertyOverwriteMode;
	}

}
