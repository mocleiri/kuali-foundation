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
 * Print a dot to the console each time we make 1% progress towards the total count
 */
public class PercentCompleteInformer {

	PrintStream out = System.out;
	long count = 0;
	long total = 0;
	int percentageIncrement = 1;
	int percentCompletePrevious;
	String startToken = "[INFO] Progress: ";
	String progressToken = ".";
	String completeToken = "\n";

	public synchronized void start() {
		out.print(startToken);
	}

	public synchronized void update(long progress) {
		// Increment the counter
		this.count += progress;

		// Print a dot anytime we make at least 1% progress
		int percentComplete = (int) ((count * 100) / total);
		if (enoughProgress(percentComplete)) {
			this.percentCompletePrevious = percentComplete;
			out.print(progressToken);
		}
	}

	public synchronized void stop() {
		out.print(completeToken);
	}

	protected boolean enoughProgress(int percentComplete) {
		int needed = percentCompletePrevious + percentageIncrement;
		return percentComplete >= needed;
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
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
