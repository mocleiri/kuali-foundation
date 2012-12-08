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
package org.kuali.common.util.property.processor;

import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrgProcessorTest {
	private static final Logger logger = LoggerFactory.getLogger(OrgProcessorTest.class);

	GroupCodeProcessor processor = getProcessor();

	protected static final String KUALI_GROUPID_KEY = "kuali.groupId";
	protected static final String KUALI_PROJECT_GROUPID_KEY = "project.groupId";

	protected static GroupCodeProcessor getProcessor() {
		return new GroupCodeProcessor(KUALI_GROUPID_KEY, KUALI_PROJECT_GROUPID_KEY);
	}

	protected static Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty(KUALI_GROUPID_KEY, "org.kuali");
		properties.setProperty(KUALI_PROJECT_GROUPID_KEY, "org.kuali.ole");
		return properties;
	}

	@Test
	public void testProcess() {
		Properties properties = getProperties();
		processor.process(properties);
		List<String> keys = PropertyUtils.getSortedKeys(properties);
		for (String key : keys) {
			String value = properties.getProperty(key);
			logger.info(key + "=" + value);
		}
	}

}
