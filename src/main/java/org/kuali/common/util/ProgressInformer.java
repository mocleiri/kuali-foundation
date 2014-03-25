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
 * Print a dot to the console each time we make enough progress
 * 
 * @deprecated
 */
@Deprecated
public class ProgressInformer extends AbstractProgressInformer {

	long interval;

	public ProgressInformer() {
		this(0);
	}

	public ProgressInformer(long interval) {
		super();
		this.interval = interval;
	}

	/**
	 * Thread safe method for incrementing progress
	 */
	public synchronized void incrementProgress() {
		if (this.progress++ % interval == 0) {
			printStream.print(progressToken);
		}
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

}
