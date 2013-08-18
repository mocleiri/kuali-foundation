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
import org.kuali.common.util.log.LogMsg;
import org.kuali.common.util.log.LoggerUtils;
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
	public static final LogMsg DEFAULT_START_MESSAGE = LogMsg.NOOP;
	public static final LogMsg DEFAULT_STOP_MESSAGE = LogMsg.NOOP;

	public static final long UNINITIALIZED_PROGRESS_INDICATOR = -1;

	public AbstractProgressInformer() {
		this(DEFAULT_PRINT_STREAM, DEFAULT_START_TOKEN, DEFAULT_PROGRESS_TOKEN, DEFAULT_COMPLETE_TOKEN, DEFAULT_START_MESSAGE, DEFAULT_STOP_MESSAGE);
	}

	public AbstractProgressInformer(LogMsg startMessage, LogMsg stopMessage) {
		this(DEFAULT_PRINT_STREAM, DEFAULT_START_TOKEN, DEFAULT_PROGRESS_TOKEN, DEFAULT_COMPLETE_TOKEN, startMessage, stopMessage);
	}

	public AbstractProgressInformer(PrintStream printStream, String startToken, String progressToken, String completeToken, LogMsg startMessage, LogMsg stopMessage) {
		Assert.noNulls(printStream, startMessage, stopMessage);
		Assert.noBlanks(startToken, progressToken, completeToken);
		this.printStream = printStream;
		this.startToken = startToken;
		this.progressToken = progressToken;
		this.completeToken = completeToken;
		this.startMessage = startMessage;
		this.stopMessage = stopMessage;
	}

	long progress = UNINITIALIZED_PROGRESS_INDICATOR;
	boolean started = false;

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
	 * Make sure we haven't already been started. Set the started indicator to true. Reset progress to zero. Log the start message. Print the start token.
	 */
	public synchronized void start() {
		Assert.isFalse(started, "Already started");
		this.started = true;
		this.progress = 0;
		LoggerUtils.log(startMessage, logger);
		printStream.print(startToken);
	}

	/**
	 * Make sure we haven't already been stopped. Set the started indicator to false. Reset progress to -1. Log the stop message. Print the stop token.
	 */
	public synchronized void stop() {
		Assert.isTrue(started, "Not started");
		this.started = false;
		this.progress = UNINITIALIZED_PROGRESS_INDICATOR;
		printStream.print(completeToken);
		LoggerUtils.log(stopMessage, logger);
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
