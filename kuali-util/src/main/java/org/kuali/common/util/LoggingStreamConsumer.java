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

import org.codehaus.plexus.util.cli.StreamConsumer;
import org.slf4j.Logger;

public class LoggingStreamConsumer implements StreamConsumer {

	Logger logger;
	LoggerLevel level;

	public LoggingStreamConsumer() {
		this(null);
	}

	public LoggingStreamConsumer(Logger logger) {
		this(logger, LoggerLevel.INFO);
	}

	public LoggingStreamConsumer(Logger logger, LoggerLevel level) {
		super();
		this.logger = logger;
		this.level = level;
	}

	@Override
	public void consumeLine(String line) {
		switch (level) {
		case TRACE:
			logger.trace(line);
			return;
		case DEBUG:
			logger.debug(line);
			return;
		case INFO:
			logger.info(line);
			return;
		case WARN:
			logger.warn(line);
			return;
		case ERROR:
			logger.error(line);
			return;
		default:
			throw new IllegalStateException("Logger level " + level + " is unknown");
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
