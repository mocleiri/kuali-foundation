/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.schema.service.impl;

import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.service.SchemaExtractionContext;
import org.kuali.common.impex.schema.service.SchemaExtractionService;
import org.kuali.common.jdbc.JdbcProjectContext;
import org.kuali.common.jdbc.spring.SqlControllerConfig;
import org.kuali.common.util.ProjectContext;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;

public abstract class BaseLocalhostSchemaExtractionServiceTest {

	protected abstract String getDatabaseVendor();

	private static final int EXPECTED_TABLE_COUNT = 2;

	@Test
	public void testGetSchema() {

		try {
			// Set a system property to establish the database vendor
			System.setProperty("db.vendor", getDatabaseVendor());

			// load the schema
			ProjectContext project = new JdbcProjectContext();
			String propertiesLocation = "classpath:org/kuali/common/kuali-impex-export/localhost.properties";
			Executable executable = SpringUtils.getSpringExecutable(project, propertiesLocation, SqlControllerConfig.class);
			executable.execute();

			// extract the schema
			Properties props = PropertyUtils.load(propertiesLocation);

			// This property points to the MockImpl, uncomment the DefaultImpl once it is ready
			SchemaExtractionService service = ReflectionUtils.newInstance(props.getProperty("impex.export.service"));
			SchemaExtractionContext context = new SchemaExtractionContext();
			context.setSchemaName(props.getProperty("jdbc.username"));
			context.setThreadCount(3);
			Schema s = service.getSchema(context);

			Assert.assertEquals(EXPECTED_TABLE_COUNT, s.getTables().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
