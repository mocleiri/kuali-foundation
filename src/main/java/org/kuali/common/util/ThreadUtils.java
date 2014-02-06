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

import java.util.List;

/**
 * @deprecated Use org.kuali.common.util.base.Threads instead
 */
@Deprecated
public class ThreadUtils {

	/**
	 * Sleep for <code>millis</code> throwing <code>IllegalStateException</code> if we get interrupted.
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	public static void start(List<Thread> threads) {
		for (Thread thread : threads) {
			thread.start();
		}
	}

	public static void join(List<Thread> threads) {
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
