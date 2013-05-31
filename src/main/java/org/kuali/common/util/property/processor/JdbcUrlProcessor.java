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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.util.Mode;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class JdbcUrlProcessor implements PropertyProcessor {
	private static final Logger logger = LoggerFactory.getLogger(JdbcUrlProcessor.class);

	String jdbcUrlProperty = "jdbc.url";
	String dbVendorProperty = "db.vendor";
	Map<String, String> jdbcUrlFragments = getJdbcUrlFragments();
	Mode propertyOverwriteMode = Constants.DEFAULT_PROPERTY_OVERWRITE_MODE;

	@Override
	public void process(Properties properties) {
		Assert.notNull(jdbcUrlProperty);
		Assert.notNull(dbVendorProperty);
		String jdbcUrl = properties.getProperty(jdbcUrlProperty);
		if (jdbcUrl == null) {
			logger.info(jdbcUrlProperty + " is not set");
			return;
		}
		String databaseVendor = getMatch(jdbcUrl, jdbcUrlFragments);
		if (databaseVendor != null) {
			PropertyUtils.addOrOverrideProperty(properties, dbVendorProperty, databaseVendor, propertyOverwriteMode);
		} else {
			logger.info("Could not identify a database vendor from url - [{}]", jdbcUrl);
		}
	}

	/**
	 * Loop through <code>fragments.keySet()</code> to see if <code>string</code> contains any of the <code>fragments</code>. If there is a
	 * match with a <code>fragment</code>, return the corresponding value for the <code>fragment</code> from the map.
	 */
	protected String getMatch(String string, Map<String, String> fragments) {
		for (String fragment : fragments.keySet()) {
			if (string.contains(fragment)) {
				return fragments.get(fragment);
			}
		}
		return null;
	}

	protected Map<String, String> getJdbcUrlFragments() {
		Map<String, String> m = new HashMap<String, String>();
		m.put(":hsqldb:", "hsql");
		m.put(":derby:", "derby");
		m.put(":postgresql:", "postgresql");
		m.put(":db2:", "db2");
		m.put(":mysql:", "mysql");
		m.put(":oracle:", "oracle");
		m.put(":h2:", "h2");
		return m;
	}

}
