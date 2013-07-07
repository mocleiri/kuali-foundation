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

public class Counter {

	int count;

	public Counter() {
		this(0);
	}

	public Counter(int startValue) {
		super();
		this.count = startValue;
	}

	public synchronized int increment() {
		return increment(1);
	}

	public synchronized int increment(int amount) {
		Assert.isTrue(amount > 0, "amount must be > 0");
		Assert.isTrue(count <= Integer.MAX_VALUE - amount, "max value exceeded");
		return count += amount;
	}

	public synchronized int decrement() {
		return decrement(1);
	}

	public synchronized int decrement(int amount) {
		Assert.isTrue(amount > 0, "amount must be > 0");
		Assert.isTrue(count >= Integer.MIN_VALUE + amount, "min value exceeded");
		return count -= amount;
	}

	public synchronized int getValue() {
		return count;
	}
}
