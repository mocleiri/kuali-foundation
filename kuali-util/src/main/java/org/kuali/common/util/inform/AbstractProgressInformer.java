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
package org.kuali.common.util.inform;

import java.io.PrintStream;

import org.kuali.common.util.Assert;
import org.kuali.common.util.LogMsg;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Print a dot to the console each time we make progress
 */
public abstract class AbstractProgressInformer {

	private static final Logger logger = LoggerFactory.getLogger(AbstractProgressInformer.class);

	public static final PrintStream DEFAULT_PRINT_STREAM = System.out;
	public static final String DEFAULT_START_TOKEN = "[INFO] Progress: ";
	public static final String DEFAULT_PROGRESS_TOKEN = ".";
	public static final String DEFAULT_COMPLETE_TOKEN = "\n";

	protected long progress;

	private final PrintStream printStream;
	private final String startToken;
	private final String progressToken;
	private final String completeToken;
	private final LogMsg startMessage;
	private final LogMsg stopMessage;

	/**
	 * Thread safe method exposing the current progress
	 */
	public synchronized long getProgress() {
		return progress;
	}

	/**
	 * Print the start token
	 */
	public void start() {
		if (startMessage != null) {
			LoggerUtils.log(startMessage, logger);
		}

		Assert.notNull(printStream, "printStream is null");
		this.progress = 0;

		printStream.print(startToken);
	}

	/**
	 * Print the stop token
	 */
	public void stop() {
		printStream.print(completeToken);

		if (stopMessage != null) {
			LoggerUtils.log(stopMessage, logger);
		}
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public String getStartToken() {
		return startToken;
	}

	public String getProgressToken() {
		return progressToken;
	}

	public String getCompleteToken() {
		return completeToken;
	}

	public LogMsg getStartMessage() {
		return startMessage;
	}

	public LogMsg getStopMessage() {
		return stopMessage;
	}

}
