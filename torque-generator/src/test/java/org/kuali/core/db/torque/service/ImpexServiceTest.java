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
package org.kuali.core.db.torque.service;

import java.io.File;
import java.util.Date;

import org.apache.torque.engine.platform.PlatformFactory;
import org.junit.Test;
import org.kuali.common.impex.service.DefaultImpexService;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexService;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImpexServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ImpexServiceTest.class);

	@Test
	// @Ignore
	public void test1() {
		try {
			logger.info(FormatUtils.getDate(new Date()));
			ImpexContext context = new ImpexContext();
			context.setDatabaseVendor("mysql");
			context.setEncoding("UTF-8");
			context.setWorkingDir(new File("/Users/jeffcaddel/ws/impex-2.0/torque-generator/target/impex"));
			context.setPlatform(PlatformFactory.getPlatformFor(context.getDatabaseVendor()));
			long start = System.currentTimeMillis();
			ImpexService service = new DefaultImpexService();
			service.convertCsvToSql(context);
			String time = FormatUtils.getTime(System.currentTimeMillis() - start);
			logger.info("Total time: {}", time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
