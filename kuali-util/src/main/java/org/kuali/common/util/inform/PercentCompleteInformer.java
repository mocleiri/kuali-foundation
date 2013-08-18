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

import org.kuali.common.util.Assert;
import org.kuali.common.util.log.LogMsg;

/**
 * Print a dot to the console each time we make at least 1% progress towards the total
 */
public class PercentCompleteInformer {

	public static final int DEFAULT_PERCENTAGE_INCREMENT = 1;
	public static final LogMsg DEFAULT_START_MESSAGE = LogMsg.NOOP;
	public static final long UNINITIALIZED_PROGRESS_INDICATOR = -1;

	private final int percentageIncrement;
	private final long total;
	private final StartStopProgressInformer informer;

	public PercentCompleteInformer(long total) {
		this(total, DEFAULT_PERCENTAGE_INCREMENT);
	}

	public PercentCompleteInformer(long total, int percentageIncrement) {
		this(total, DEFAULT_PERCENTAGE_INCREMENT, DEFAULT_START_MESSAGE);
	}

	public PercentCompleteInformer(long total, int percentageIncrement, LogMsg startMessage) {
		this(total, percentageIncrement, new StartStopProgressInformer(startMessage));
	}

	public PercentCompleteInformer(long total, int percentageIncrement, StartStopProgressInformer informer) {
		Assert.isTrue(total >= 0, "total is negative");
		Assert.isTrue(percentageIncrement > 0, "percentage increment must be > 0");
		Assert.noNulls(informer);
		this.total = total;
		this.percentageIncrement = percentageIncrement;
		this.informer = informer;
	}

	int percentCompletePrevious;
	boolean started = false;
	long progress = UNINITIALIZED_PROGRESS_INDICATOR;

	public synchronized void start() {
		Assert.isFalse(started, "Already started");
		informer.start();
		this.percentCompletePrevious = 0;
		this.progress = 0;
	}

	/**
	 * Thread safe method for incrementing progress by one
	 */
	public synchronized void incrementProgress() {
		Assert.isTrue(started, "Not started");
		incrementProgress(1);
	}

	/**
	 * Thread safe method for incrementing progress by <code>amount</code>
	 */
	public synchronized void incrementProgress(long amount) {
		Assert.isTrue(started, "Not started");
		// Increment the progress indicator
		this.progress = +amount;

		// Calculate how far along we are
		int percentComplete = (int) ((progress * 100) / total);

		// Have we made at least 1% progress since the last time we were informed about progress occurring?
		if (isEnoughProgress(percentComplete, percentCompletePrevious, percentageIncrement)) {
			// If so, print a dot to the console
			this.percentCompletePrevious = percentComplete;
			informer.getPrintStream().print(informer.getProgressToken());
		}
	}

	protected boolean isEnoughProgress(int percentComplete, int percentCompletePrevious, int percentageIncrement) {
		int needed = percentCompletePrevious + percentageIncrement;
		return percentComplete >= needed;
	}

	public int getPercentageIncrement() {
		return percentageIncrement;
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

}
