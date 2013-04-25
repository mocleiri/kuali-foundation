package org.kuali.common.deploy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppDynamics implements MonitorTooling {

	private static final Logger logger = LoggerFactory.getLogger(AppDynamics.class);

	@Override
	public void stop() {
		logger.info("Shutting down AppDynamics");
	}

	@Override
	public void prepare() {
		logger.info("Preparing AppDynamics");
	}

	@Override
	public void start() {
		logger.info("Starting AppDynamics");
	}

}
