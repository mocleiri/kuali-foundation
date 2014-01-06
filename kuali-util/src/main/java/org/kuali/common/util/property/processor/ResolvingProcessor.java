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

import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;

public class ResolvingProcessor implements PropertyProcessor {

	private static final Logger logger = LoggerUtils.make();

	public static final boolean DEFAULT_IGNORE_UNRESOLVABLE = false;
	public static final String DEFAULT_RESOLVE_KEY = "properties.resolve";

	public ResolvingProcessor() {
		this(DEFAULT_IGNORE_UNRESOLVABLE, DEFAULT_RESOLVE_KEY);
	}

	public ResolvingProcessor(boolean ignoreUnresolvable, String resolveKey) {
		Assert.noBlanks(resolveKey);
		this.ignoreUnresolvable = ignoreUnresolvable;
		this.resolveKey = resolveKey;
		this.helper = new PropertyPlaceholderHelper("${", "}", ":", ignoreUnresolvable);
	}

	private final boolean ignoreUnresolvable;
	private final String resolveKey;
	private final PropertyPlaceholderHelper helper;

	@Override
	public void process(Properties properties) {
		boolean resolve = PropertyUtils.getBoolean(resolveKey, properties, true);
		if (resolve) {
			logger.info("Performing placeholder resolution for {} properties", properties.size());
			PropertyUtils.resolve(properties, helper);
		} else {
			logger.info("Skipping placeholder resolution for {} properties", properties.size());
		}
	}

	public boolean isIgnoreUnresolvable() {
		return ignoreUnresolvable;
	}

	public String getResolveKey() {
		return resolveKey;
	}

	public PropertyPlaceholderHelper getHelper() {
		return helper;
	}

}
