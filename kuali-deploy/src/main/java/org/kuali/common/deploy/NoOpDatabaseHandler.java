package org.kuali.common.deploy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoOpDatabaseHandler implements DatabaseHandler {

	private static final Logger logger = LoggerFactory.getLogger(NoOpDatabaseHandler.class);

	@Override
	public void reset() {
		logger.info("Skip database reset");
	}

}
