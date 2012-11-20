package org.kuali.common.util;

import org.kuali.common.util.property.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModeUtils {
	private static final Logger logger = LoggerFactory.getLogger(ModeUtils.class);

	public static final void check(Mode mode, String msg) {
		check(mode, msg, msg);
	}

	public static final void check(Mode mode, String logMsg, String errMsg) {
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
