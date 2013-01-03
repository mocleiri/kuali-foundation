/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.obscure.DefaultObscurer;
import org.kuali.common.util.obscure.Obscurer;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;

public class LoggerUtils {

	private static final Obscurer DEFAULT_OBSCURER = new DefaultObscurer();

	public static void logLines(String s, Logger logger, LoggerLevel level) {
		if (s == null) {
			return;
		}
		String[] lines = StringUtils.split(s, "\n");
		for (String line : lines) {
			LoggerUtils.logMsg(line, logger, level);
		}
	}

	public static final void logMsg(String msg, Logger logger, LoggerLevel level) {
		switch (level) {
		case DEBUG:
			logger.debug(msg);
			return;
		case TRACE:
			logger.trace(msg);
			return;
		case INFO:
			logger.info(msg);
			return;
		case WARN:
			logger.warn(msg);
			return;
		case ERROR:
			logger.error(msg);
			return;
		default:
			throw new IllegalArgumentException("Logger level " + level + " is unknown");
		}
	}

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
			// There is no password, return NONE
			return Constants.NONE;
		} else if (StringUtils.equals(username, password)) {
			// Not exactly high security, display the clear text value
			return password;
		} else {
			// Otherwise obscure it
			return obscurer.obscure(password);
		}
	}

}
