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
package org.kuali.common.jdbc.spring;

import java.util.List;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.PropertySourceContext;
import org.kuali.common.util.service.SpringContext;
import org.kuali.common.util.service.SpringService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.PropertySource;

public class OracleSchemaStatsTest {

	@Test
	public void test() {
		try {
			System.setProperty("jdbc.username", "KSENV2");
			System.setProperty("db.vendor", "oracle");
			System.setProperty("oracle.dba.url", "jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE");
			System.setProperty("oracle.dba.username", "master");
			System.setProperty("oracle.dba.password", "gw570229");

			// Default Spring service will do what we need
			SpringService ss = new DefaultSpringService();

			List<PropertySource<?>> sources = SpringUtils.getPropertySources(OracleSchemaStatsPropertySourceConfig.class);

			PropertySourceContext psc = new PropertySourceContext(sources);

			// Setup a Spring context
			SpringContext context = new SpringContext();
			context.setPropertySourceContext(psc);

			// Reset the db using annotated config
			context.setAnnotatedClasses(CollectionUtils.asList(OracleSchemaStats.class));

			// Execute Spring
			ss.load(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
