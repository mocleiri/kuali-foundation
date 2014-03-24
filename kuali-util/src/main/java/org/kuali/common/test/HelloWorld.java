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
package org.kuali.common.test;

import static org.kuali.common.test.Exceptions.illegalState;
import static org.kuali.common.util.FormatUtils.getMillis;

import java.util.List;

public class HelloWorld {

	/**
	 * Sleep for {@code time} where time is 15ms, 15s, 15m, 15h, 15d, 15y for 15 millis, seconds, minutes, hours, days, and years, respectively
	 * 
	 * @throws <code>IllegalStateException</code> if interrupted.
	 */
	public static void sleep(String time) {
		sleep(getMillis(time));
	}

	/**
	 * Sleep for <code>millis</code>
	 * 
	 * @throws <code>IllegalStateException</code> if interrupted.
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw illegalState(e);
		}
	}

	/**
	 * Start each thread
	 */
	public static void start(List<Thread> threads) {
		for (Thread thread : threads) {
			thread.start();
		}
	}

	/**
	 * Invoke join() on each thread
	 * 
	 * @throws <code>IllegalStateException</code> if any thread gets interrupted.
	 */
	public static void join(List<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw illegalState(e, "unexpected interruption [thread id:%s] [thread name:%s]", thread.getId(), thread.getName());
			}
		}
	}
}
