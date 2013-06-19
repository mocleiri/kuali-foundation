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

public class LongCounter {

	long count;

	public LongCounter() {
		this(0);
	}

	public LongCounter(long startValue) {
		super();
		this.count = startValue;
	}

	public synchronized long increment() {
		Assert.isFalse(count == Long.MAX_VALUE, "max counter value exceeded");
		return count++;
	}

	public synchronized long decrement() {
		Assert.isFalse(count == Long.MIN_VALUE, "min counter value exceeded");
		return count--;
	}

	public synchronized long getValue() {
		return count;
	}
}
