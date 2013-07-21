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
package org.kuali.common.util.spring.profile;

import org.junit.Test;
import org.kuali.common.util.spring.service.DefaultSpringService;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;

public class DatabaseConfigTest {

	@Test
	public void test() {
		try {
			SpringContext context = new SpringContext(ShowDatabaseExecutableConfig.class);
			// context.setActiveProfiles(Arrays.asList(DatabaseConstants.ORACLE_SPRING_PROFILE_NAME));
			// context.setActiveProfiles(Arrays.asList(DatabaseConstants.MYSQL_SPRING_PROFILE_NAME));
			SpringService ss = new DefaultSpringService();
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
