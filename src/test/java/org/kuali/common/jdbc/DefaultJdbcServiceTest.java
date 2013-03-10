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
package org.kuali.common.jdbc;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.supplier.SimpleStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LocationUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DefaultJdbcServiceTest {

	@Test
	public void test() {
		try {
			// List<String> strings = Arrays.asList("select sysdate from dual");
			List<String> strings = LocationUtils.readLines("classpath:org/kuali/common/jdbc/oracle-clob.sql");
			List<SqlSupplier> suppliers = Collections.singletonList((SqlSupplier) (new SimpleStringSupplier(strings)));
			DriverManagerDataSource dmds = new DriverManagerDataSource("jdbc:oracle:thin:@oracle.ks.kuali.org:1521:ORACLE", "JDBCTEST", "JDBCTEST");
			dmds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			JdbcContext context = new JdbcContext();
			context.setDataSource(dmds);
			context.setSuppliers(suppliers);
			context.setListener(new LogSqlListener());
			JdbcService service = new DefaultJdbcService();
			JdbcExecutable executable = new JdbcExecutable(service, context);
			executable.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
