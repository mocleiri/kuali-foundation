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

import java.util.Properties;

import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.kuali.common.util.property.GlobalPropertiesMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * @deprecated
 */
@Deprecated
public class ResolvePlaceholdersProcessor implements PropertyProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ResolvePlaceholdersProcessor.class);

	PropertyPlaceholderHelper helper = Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER;
	GlobalPropertiesMode globalPropertiesMode = Constants.DEFAULT_GLOBAL_PROPERTIES_MODE;

	public ResolvePlaceholdersProcessor() {
		this(Constants.DEFAULT_PROPERTY_PLACEHOLDER_HELPER);
	}

	public ResolvePlaceholdersProcessor(PropertyPlaceholderHelper helper) {
		this(helper, Constants.DEFAULT_GLOBAL_PROPERTIES_MODE);
	}

	public ResolvePlaceholdersProcessor(PropertyPlaceholderHelper helper, GlobalPropertiesMode globalPropertiesMode) {
		super();
		this.helper = helper;
		this.globalPropertiesMode = globalPropertiesMode;
	}

	@Override
	public void process(Properties properties) {
		Properties resolvedProperties = PropertyUtils.getResolvedProperties(properties, helper, globalPropertiesMode);
		if (resolvedProperties.size() > 0) {
			logger.debug("Resolved {} property values", resolvedProperties.size());
			properties.putAll(resolvedProperties);
		}
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

	public void setHelper(PropertyPlaceholderHelper helper) {
		this.helper = helper;
	}

	public GlobalPropertiesMode getGlobalPropertiesMode() {
		return globalPropertiesMode;
	}

	public void setGlobalPropertiesMode(GlobalPropertiesMode globalPropertiesMode) {
		this.globalPropertiesMode = globalPropertiesMode;
	}
}
