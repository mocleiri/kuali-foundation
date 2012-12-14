package org.kuali.common.util;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;

public class LoggerUtils {

	public static final void logUsername(Logger logger, String prefix, String username) {
		if (username == null) {
			logNullAsNone(logger, prefix);
		} else {
			logger.info("{} - {}", prefix, username);
		}
	}

	public static final void logPassword(Logger logger, String prefix, String username, String password) {
		if (password == null) {
			// There is no password, display NONE
			logNullAsNone(logger, prefix);
		} else if (StringUtils.equals(username, password)) {
			// Not exactly high security, display the clear text value
			logger.info("{} - {}", prefix, password);
		} else {
			// Otherwise obscure it
			logger.info("{} - {}", prefix, StringUtils.repeat("*", password.length()));
		}
	}

	/**
	 * Log <code>null</code> as <code>NONE</code>
	 */
	public static final void logNullAsNone(Logger logger, String prefix) {
		logger.info("{} - {}", prefix, Constants.NONE);
	}

}
