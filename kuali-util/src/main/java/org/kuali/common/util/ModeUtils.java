package org.kuali.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModeUtils {
	private static final Logger logger = LoggerFactory.getLogger(ModeUtils.class);

	public static final void validate(Mode mode, String msg) {
		validate(mode, msg, msg);
	}

	public static final void validate(Mode mode, String msg, String errMsg) {
		validate(mode, msg, null, errMsg);
	}

	public static final void validate(Mode mode, String msg, Object arg, String errMsg) {
		validate(mode, msg, arg, null, errMsg);
	}

	public static final void validate(Mode mode, String msg, Object arg1, Object arg2, String errMsg) {
		validate(mode, msg, new Object[] { arg1, arg2 }, errMsg);
	}

	public static final void validate(Mode mode, String msg, Object[] args, String errMsg) {
		switch (mode) {
		case IGNORE:
			return;
		case DEBUG:
			logger.debug(msg, args);
			return;
		case INFORM:
			logger.info(msg, args);
			return;
		case WARN:
			logger.warn(msg, args);
			return;
		case ERROR:
			logger.error(msg, args);
			throw new IllegalStateException(errMsg);
		default:
			throw new IllegalArgumentException(mode + " is unknown");
		}
	}

}
