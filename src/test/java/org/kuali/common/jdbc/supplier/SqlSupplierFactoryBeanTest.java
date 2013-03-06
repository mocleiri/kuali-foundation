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
package org.kuali.common.jdbc.supplier;

import org.junit.Test;
import org.kuali.common.util.service.DefaultSpringService;
import org.kuali.common.util.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlSupplierFactoryBeanTest {

	private static final Logger logger = LoggerFactory.getLogger(SqlSupplierFactoryBeanTest.class);

	@Test
	public void test() {
		try {
			logger.debug("");
			SpringService ss = new DefaultSpringService();
			ss.load("classpath:org/kuali/common/jdbc/supplier/sql-supplier-context.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
