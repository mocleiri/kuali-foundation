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
package org.kuali.common.util.service;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;

public class DefaultMySqlDumpServiceTest {

	@Test
	@Ignore
	public void test() {
		try {

			MySqlDumpContext context = new MySqlDumpContext();
			// The default ignorers strip out lines that cause 'noise' in the file
			// eg dump timestamps, server/client versions, etc
			// Anything that might change even though the data is the same
			context.setIgnorers(MySqlDumpUtils.getDefaultIgnorers());
			context.setUsername("JDBCTEST");
			context.setPassword("JDBCTEST");
			context.setHostname("localhost");
			context.setDatabase("JDBCTEST");
			context.setOutputFile(new File("/tmp/mysqldump/jtest.sql"));
			MySqlDumpService service = new DefaultMySqlDumpService();
			service.dump(context);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
