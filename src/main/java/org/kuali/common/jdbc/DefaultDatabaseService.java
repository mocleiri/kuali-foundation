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

import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.context.DatabaseResetContext;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultDatabaseService implements DatabaseService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultDatabaseService.class);

	@Override
	public void reset(DatabaseResetContext context) {
		DatabaseProcessContext dpc = context.getDatabaseProcessContext();
		logger.info("---------------- Reset Database ----------------");
		logger.info("Vendor - {}", context.getDatabaseProcessContext().getVendor());
		logger.info("URL - {}", context.getDatabaseProcessContext().getUrl());
		logger.info("User - {}", LoggerUtils.getUsername(dpc.getUsername()));
		logger.info("Password - {}", LoggerUtils.getPassword(dpc.getUsername(), dpc.getPassword()));
		logger.info("DBA URL - {}", context.getDatabaseProcessContext().getDbaUrl());
		logger.info("DBA User - {}", LoggerUtils.getUsername(dpc.getDbaUsername()));
		logger.info("DBA Password - {}", LoggerUtils.getPassword(dpc.getDbaUsername(), dpc.getDbaPassword()));
		JdbcMetaData metadata = context.getService().getJdbcMetaData(context.getDbaJdbcContext().getDataSource());
		logger.info("Product Name - {}", metadata.getDatabaseProductName());
		logger.info("Product Version - {}", metadata.getDatabaseProductVersion());
		logger.info("Driver - {}", context.getDatabaseProcessContext().getDriver());
		logger.info("Driver Name - {}", metadata.getDriverName());
		logger.info("Driver Version - {}", metadata.getDriverVersion());
		logger.info("SQL Encoding - {}", context.getEncoding());
		logger.info("------------------------------------------------");
	}

}
