package org.kuali.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StateUtils {
	private static final Logger logger = LoggerFactory.getLogger(StateUtils.class);

	public static final void checkState(Mode mode, String msg) {
		checkState(mode, msg, msg);
	}

	public static final void checkState(Mode mode, String logMsg, String errMsg) {
		switch (mode) {
		case IGNORE:
			return;
		case DEBUG:
			logger.debug(logMsg);
			return;
		case INFORM:
			logger.info(logMsg);
			return;
		case WARN:
			logger.warn(logMsg);
			return;
		case ERROR:
			throw new IllegalStateException(errMsg);
		default:
			throw new IllegalArgumentException(mode + " is unknown");
		}
	}

}
