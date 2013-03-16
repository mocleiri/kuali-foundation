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

/**
 * Print a dot to the console each time we make at least 1% progress towards the total
 */
public class PercentCompleteInformer {

	protected long progress;

	PrintStream printStream = System.out;
	int percentageIncrement = 1;
	int percentCompletePrevious;
	String startToken = "[INFO] Progress: ";
	String progressToken = ".";
	String completeToken = "\n";
	long total;

	public PercentCompleteInformer() {
		this(0);
	}

	public PercentCompleteInformer(long total) {
		super();
		this.total = total;
	}

	/**
	 * Thread safe method exposing the current progress
	 */
	public synchronized long getProgress() {
		return progress;
	}

	/**
	 * Thread safe method for incrementing progress by one
	 */
	public synchronized void incrementProgress() {
		incrementProgress(1);
	}

	/**
	 * Thread safe method for incrementing progress by <code>amount</code>
	 */
	public synchronized void incrementProgress(long amount) {
		// Increment the progress indicator
		this.progress += amount;

		// Calculate how far along we are
		int percentComplete = (int) ((progress * 100) / total);

		// Print a dot to the console any time we make at least 1% progress
		if (isEnoughProgress(percentComplete, percentCompletePrevious, percentageIncrement)) {
			this.percentCompletePrevious = percentComplete;
			printStream.print(progressToken);
		}
	}

	protected boolean isEnoughProgress(int percentComplete, int percentCompletePrevious, int percentageIncrement) {
		int needed = percentCompletePrevious + percentageIncrement;
		return percentComplete >= needed;
	}

	/**
	 * Print the start token
	 */
	public void start() {

		Assert.notNull(printStream, "printStream is null");

		printStream.print(startToken);
	}

	/**
	 * Print the stop token
	 */
	public void stop() {
		printStream.print(completeToken);
	}

	public PrintStream getPrintStream() {
		return printStream;
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public int getPercentageIncrement() {
		return percentageIncrement;
	}

	public void setPercentageIncrement(int percentageIncrement) {
		this.percentageIncrement = percentageIncrement;
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
