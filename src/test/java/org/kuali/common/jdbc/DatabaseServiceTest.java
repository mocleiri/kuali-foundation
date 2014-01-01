/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.kuali.common.jdbc;

import java.util.List;
import java.util.Properties;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class DatabaseServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DatabaseServiceTest.class);

	@Autowired
	DatabaseResetContext context = null;

	@Autowired
	DatabaseService service = null;

	@Autowired
	Properties properties = null;

	@Test
	@Ignore
	public void resetDatabaseTest() {
		try {
			logger.info(properties + "");
			List<String> keys = PropertyUtils.getSortedKeys(properties);
			for (String key : keys) {
				String value = properties.getProperty(key);
				logger.info(key + "=" + Str.flatten(value));
			}
			service.reset(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
