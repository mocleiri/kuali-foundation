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

/**
 * Print a dot to the console each time we make at least 1% progress towards the total
 * 
 * @deprecated
 */
@Deprecated
public class PercentCompleteInformer extends AbstractProgressInformer {

	protected long progress;

	int percentageIncrement = 1;
	int percentCompletePrevious;
	long total;

	public PercentCompleteInformer() {
		this(0);
	}

	public PercentCompleteInformer(long total) {
		this(total, null);
	}

	public PercentCompleteInformer(long total, LogMsg startMessage) {
		super();
		this.total = total;
		this.startMessage = startMessage;
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

		// Have we made at least 1% progress since the last time we were informed about progress occurring?
		if (isEnoughProgress(percentComplete, percentCompletePrevious, percentageIncrement)) {
			// If so, print a dot to the console
			this.percentCompletePrevious = percentComplete;
			printStream.print(progressToken);
		}
	}

	protected boolean isEnoughProgress(int percentComplete, int percentCompletePrevious, int percentageIncrement) {
		int needed = percentCompletePrevious + percentageIncrement;
		return percentComplete >= needed;
	}

	public int getPercentageIncrement() {
		return percentageIncrement;
	}

	public void setPercentageIncrement(int percentageIncrement) {
		this.percentageIncrement = percentageIncrement;
	}

	public int getPercentCompletePrevious() {
		return percentCompletePrevious;
	}

	public void setPercentCompletePrevious(int percentCompletePrevious) {
		this.percentCompletePrevious = percentCompletePrevious;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
