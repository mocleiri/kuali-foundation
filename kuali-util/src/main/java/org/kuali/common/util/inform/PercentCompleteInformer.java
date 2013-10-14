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

/**
 * Print a dot to the console each time we make at least 1% progress towards a total
 */
public final class PercentCompleteInformer {

	public static final int DEFAULT_PERCENTAGE_INCREMENT = 1;
	public static final long UNINITIALIZED_PROGRESS_INDICATOR = -1;
	public static final int UNINITIALIZED_PERCENT_COMPLETE_INDICATOR = -1;

	private final int percentageIncrement;
	private final long total;
	private final Inform inform;
	private final StartStopInformer informer;

	public PercentCompleteInformer(long total) {
		this(total, DEFAULT_PERCENTAGE_INCREMENT);
	}

	public PercentCompleteInformer(long total, int percentageIncrement) {
		this(total, DEFAULT_PERCENTAGE_INCREMENT, Inform.DEFAULT_INFORM);
	}

	public PercentCompleteInformer(long total, int percentageIncrement, Inform inform) {
		Assert.isTrue(total >= 0, "total is negative");
		Assert.isTrue(percentageIncrement > 0, "percentage increment must be a positive integer");
		Assert.noNulls(inform);
		this.total = total;
		this.inform = inform;
		this.percentageIncrement = percentageIncrement;
		this.informer = new StartStopInformer(inform);
	}

	private int percentComplete = UNINITIALIZED_PERCENT_COMPLETE_INDICATOR;
	private volatile long progress = UNINITIALIZED_PROGRESS_INDICATOR;
	private boolean started = false;

	/**
	 * Indicates if we are in the "started" state or not
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * Indicates how far along we are
	 */
	public long getProgress() {
		return progress;
	}

	/**
	 * Indicates how far along we are as a percentage
	 */
	public int getPercentComplete() {
		return percentComplete;
	}

	/**
	 * Thread safe method to indicate progress has begun
	 */
	public synchronized void start() {
		Assert.isFalse(started, "Already started");
		this.started = true;
		this.percentComplete = 0;
		this.progress = 0;
		informer.start();
	}

	/**
	 * Thread safe method for incrementing progress by one
	 */
	public void incrementProgress() {
		incrementProgress(1);
	}

	/**
	 * Thread safe method for incrementing progress by <code>amount</code>
	 */
	public synchronized void incrementProgress(long amount) {
		// Make sure were are in the started state
		Assert.isTrue(started, "Not started");

		// Increment the progress indicator
		this.progress += amount;

		// Calculate how far along we are
		int newPercentCompleted = (int) ((progress * 100) / total);

		// Have we made at least 1% progress?
		if (isEnoughProgress(newPercentCompleted, percentComplete, percentageIncrement)) {
			// If so, update the field holding the percent complete
			this.percentComplete = newPercentCompleted;
			// and print a dot to the console
			inform.getPrintStream().print(inform.getProgressToken());
		}
	}

	/**
	 * Thread safe method to indicate progress has stopped
	 */
	public synchronized void stop() {
		Assert.isTrue(started, "Not started");
		this.started = false;
		this.percentComplete = UNINITIALIZED_PERCENT_COMPLETE_INDICATOR;
		this.progress = UNINITIALIZED_PROGRESS_INDICATOR;
		informer.stop();
	}

	protected boolean isEnoughProgress(int newPercentCompleted, int percentComplete, int percentageIncrement) {
		int needed = percentComplete + percentageIncrement;
		return newPercentCompleted >= needed;
	}

	public int getPercentageIncrement() {
		return percentageIncrement;
	}

	public long getTotal() {
		return total;
	}

	public Inform getInform() {
		return inform;
	}

	public StartStopInformer getInformer() {
		return informer;
	}

}
