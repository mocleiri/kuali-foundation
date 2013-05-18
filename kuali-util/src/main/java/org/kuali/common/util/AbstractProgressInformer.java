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
package org.kuali.common.util;

import java.io.PrintStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Print a dot to the console each time we make progress
 */
public abstract class AbstractProgressInformer {

	private static final Logger logger = LoggerFactory.getLogger(AbstractProgressInformer.class);

	protected long progress;

	PrintStream printStream = System.out;
	String startToken = "[INFO] Progress: ";
	String progressToken = ".";
	String completeToken = "\n";
	String startMessage;
	Object[] startMessageArgs;
	String stopMessage;
	Object[] stopMessageArgs;

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
		if (!StringUtils.isBlank(startMessage)) {
			logger.info(startMessage, startMessageArgs);
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

		if (!StringUtils.isBlank(stopMessage)) {
			logger.info(stopMessage, stopMessageArgs);
		}
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public String getStartToken() {
		return startToken;
	}

	public void setStartToken(String startToken) {
		this.startToken = startToken;
	}

	public String getCompleteToken() {
		return completeToken;
	}

	public void setCompleteToken(String completeToken) {
		this.completeToken = completeToken;
	}

	public String getProgressToken() {
		return progressToken;
	}

	public void setProgressToken(String progressToken) {
		this.progressToken = progressToken;
	}

	public String getStartMessage() {
		return startMessage;
	}

	public void setStartMessage(String startMessage) {
		this.startMessage = startMessage;
	}

	public String getStopMessage() {
		return stopMessage;
	}

	public void setStopMessage(String stopMessage) {
		this.stopMessage = stopMessage;
	}

	public Object[] getStartMessageArgs() {
		return startMessageArgs;
	}

	public void setStartMessageArgs(Object[] startMessageArgs) {
		this.startMessageArgs = startMessageArgs;
	}

	public Object[] getStopMessageArgs() {
		return stopMessageArgs;
	}

	public void setStopMessageArgs(Object[] stopMessageArgs) {
		this.stopMessageArgs = stopMessageArgs;
	}

}
