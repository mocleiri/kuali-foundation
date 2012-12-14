package org.kuali.common.util;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.obscure.DefaultObscurer;
import org.kuali.common.util.obscure.Obscurer;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;

public class LoggerUtils {

	private static final Obscurer DEFAULT_OBSCURER = new DefaultObscurer();

	public static final String getUsername(String username) {
		return getNullAsNone(username);
	}

	public static final String getNullAsNone(String string) {
		if (string == null) {
			return Constants.NONE;
		} else {
			return string;
		}
	}

	public static final String getPassword(String username, String password) {
		return getPassword(username, password, DEFAULT_OBSCURER);
	}

	public static final String getPassword(String username, String password, Obscurer obscurer) {
		if (password == null) {
			return Constants.NONE;
		} else if (StringUtils.equals(username, password)) {
			return password;
		} else {
			return obscurer.obscure(password);
		}
	}

	public static final void logUsername(Logger logger, String prefix, String username) {
		if (username == null) {
			logNullAsNone(logger, prefix);
		} else {
			logger.info("{} - {}", prefix, username);
		}
	}

	public static final void logPassword(Logger logger, String prefix, String username, String password) {
		logPassword(logger, prefix, username, password, DEFAULT_OBSCURER);
	}

	public static final void logPassword(Logger logger, String prefix, String username, String password, Obscurer obscurer) {
		if (password == null) {
			// There is no password, display NONE
			logNullAsNone(logger, prefix);
		} else if (StringUtils.equals(username, password)) {
			// Not exactly high security, display the clear text value
			logger.info("{} - {}", prefix, password);
		} else {
			// Otherwise obscure it
			logger.info("{} - {}", prefix, obscurer.obscure(password));
		}
	}

	/**
	 * Log <code>null</code> as <code>NONE</code>
	 */
	public static final void logNullAsNone(Logger logger, String prefix) {
		logger.info("{} - {}", prefix, Constants.NONE);
	}

}
