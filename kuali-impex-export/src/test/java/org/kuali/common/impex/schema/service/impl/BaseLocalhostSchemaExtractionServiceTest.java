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

import javax.sql.DataSource;

import junit.framework.Assert;

import org.junit.Test;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.MySqlSequenceFinder;
import org.kuali.common.impex.schema.MySqlViewFinder;
import org.kuali.common.impex.schema.OracleSequenceFinder;
import org.kuali.common.impex.schema.OracleViewFinder;
import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.impex.schema.ViewFinder;
import org.kuali.common.impex.schema.service.ExtractSchemaContext;
import org.kuali.common.impex.schema.service.ExtractSchemaService;
import org.kuali.common.jdbc.spring.SqlControllerExecutableConfig;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.config.service.DefaultConfigService;
import org.kuali.common.util.config.supplier.ConfigPropertiesSupplier;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public abstract class BaseLocalhostSchemaExtractionServiceTest {

	protected abstract String getDatabaseVendor();

	private static final int EXPECTED_TABLE_COUNT = 2;

	@Test
	public void testGetSchema() {

		try {
			// Set a system property to establish the database vendor
			System.setProperty("db.vendor", getDatabaseVendor());

			String propertiesLocation = "classpath:org/kuali/common/kuali-impex-export/localhost.properties";
			Properties overrides = PropertyUtils.load(propertiesLocation);
			ConfigPropertiesSupplier supplier = new ConfigPropertiesSupplier(null, new DefaultConfigService(), overrides);
			Executable executable = SpringUtils.getSpringExecutable(supplier, SqlControllerExecutableConfig.class);
			executable.execute();

			// extract the schema
			Properties props = PropertyUtils.load(propertiesLocation);

			String username = props.getProperty("jdbc.username");
			String url = props.getProperty("jdbc.url");

			// This property points to the MockImpl, uncomment the DefaultImpl once it is ready
			ExtractSchemaService service = ReflectionUtils.newInstance(props.getProperty("impex.export.service"));

			ViewFinder viewFinder = null;
			SequenceFinder sequenceFinder = null;

			if (getDatabaseVendor().equals(OracleViewFinder.SUPPORTED_VENDOR)) {
				OracleViewFinder oViewFinder = new OracleViewFinder();
				OracleSequenceFinder oSequenceFinder = new OracleSequenceFinder();
				viewFinder = oViewFinder;
				sequenceFinder = oSequenceFinder;
			} else if (getDatabaseVendor().equals(MySqlViewFinder.SUPPORTED_VENDOR)) {
				MySqlViewFinder mViewFinder = new MySqlViewFinder();
				MySqlSequenceFinder mSequenceFinder = new MySqlSequenceFinder();
				viewFinder = mViewFinder;
				sequenceFinder = mSequenceFinder;
			}

			ExtractSchemaContext context = new ExtractSchemaContext();
			context.setSchemaName(username);
			context.setThreadCount(8);
			context.setSequenceFinder(sequenceFinder);
			context.setViewFinder(viewFinder);

			DataSource dataSource = new DriverManagerDataSource(url, username, username);

			context.setDataSource(dataSource);

			Schema s = service.getSchema(context);

			Assert.assertEquals(EXPECTED_TABLE_COUNT, s.getTables().size());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception: " + e.getMessage());
		}
	}

}
