package org.kuali.core.db.torque.service;

import java.sql.SQLException;

import org.kuali.common.util.FormatUtils;
import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetaDataService {

	private static final Logger logger = LoggerFactory.getLogger(MetaDataService.class);

	protected DatabaseContext getDatabaseObjectLists(MetaDataContext context) throws SQLException {
		DatabaseContext database = new DatabaseContext();
		context.setPlatform(context.getPlatform());
		context.setSchema(context.getSchema());

		// Add in tables and views
		long start = System.currentTimeMillis();
		// fillInDatabaseMetaData(database, context);
		logger.info("Database object lists created.  Time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		return database;
	}

}
