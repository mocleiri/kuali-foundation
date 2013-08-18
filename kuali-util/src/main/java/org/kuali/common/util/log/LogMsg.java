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
package org.kuali.common.util.log;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class LogMsg {

	public static final LoggerLevel DEFAULT_LOGGER_LEVEL = LoggerLevel.INFO;
	public static final String NO_MSG = NullUtils.NONE;
	public static final Object[] NO_ARGS = new Object[] {};
	public static final LogMsg NOOP = new LogMsg(NO_MSG, NO_ARGS);

	private final LoggerLevel level;
	private final String message;
	private final Object[] args;

	public LogMsg(String message, Object[] args) {
		this(message, args, DEFAULT_LOGGER_LEVEL);
	}

	public LogMsg(String message) {
		this(message, NO_ARGS, DEFAULT_LOGGER_LEVEL);
	}

	public LogMsg(String message, Object[] args, LoggerLevel level) {
		Assert.noBlanks(message);
		Assert.noNulls(args, level);
		this.message = message;
		this.args = args;
		this.level = level;
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public String getMessage() {
		return message;
	}

	public Object[] getArgs() {
		return args;
	}

}
