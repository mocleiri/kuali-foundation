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

import java.io.File;

import org.junit.Test;
import org.kuali.common.jdbc.convert.DirectoryContext;
import org.kuali.common.jdbc.convert.DirectoryConverter;
import org.kuali.common.jdbc.convert.MySqlConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDirectoryConverterTest {

	private static final Logger logger = LoggerFactory.getLogger(MySqlDirectoryConverterTest.class);

	@Test
	public void testConvert() {
		try {
			logger.info("");
			String base = "/Users/jeffcaddel/ws/spring-db-jc/ks-deployments/ks-cfg-dbs/ks-rice-db";
			String subdir = "src/main/resources/impex/sql/mysql";
			String dirname = base + "/" + subdir;
			File directory = new File(dirname);

			String include = "*.sql";
			String exclude = "ks-rice-db*.sql";
			DirectoryContext context = new DirectoryContext();
			context.setConverter(new MySqlConverter());
			context.setDirectory(directory);
			context.setInclude(include);
			context.setExclude(exclude);
			DirectoryConverter dc = new DirectoryConverter();
			dc.convert(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
