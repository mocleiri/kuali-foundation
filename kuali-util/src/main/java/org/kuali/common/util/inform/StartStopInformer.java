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
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public final class StartStopInformer {

	private static final Logger logger = LoggerFactory.getLogger(StartStopInformer.class);

	private final Inform inform;

	public StartStopInformer() {
		this(Inform.DEFAULT_INFORM);
	}

	public StartStopInformer(Inform inform) {
		Assert.noNulls(inform);
		this.inform = inform;
	}

	private boolean started = false;

	/**
	 * Indicates if we are in the "started" state
	 */
	public boolean isStarted() {
		return started;
	}

	/**
	 * Make sure we haven't already been started. Set the started indicator to true. Log the start message. Print the start token.
	 */
	public synchronized void start() {
		Assert.isFalse(started, "Already started");
		this.started = true;
		LoggerUtils.log(inform.getStartMessage(), logger);
		inform.getPrintStream().print(inform.getStartToken());
	}

	/**
	 * Make sure we haven't already been stopped. Set the started indicator to false. Print the stop token. Log the stop message.
	 */
	public synchronized void stop() {
		Assert.isTrue(started, "Not started");
		this.started = false;
		inform.getPrintStream().print(inform.getCompleteToken());
		LoggerUtils.log(inform.getStopMessage(), logger);
	}

	public Inform getInform() {
		return inform;
	}

}
