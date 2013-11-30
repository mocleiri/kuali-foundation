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

import org.junit.Assert;
import org.junit.Test;

public class CounterTest {

	@Test
	public void testMax() {
		try {
			Counter counter = new Counter(Integer.MAX_VALUE);
			counter.increment();
			Assert.fail("Should have failed");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test
	public void testMin() {
		try {
			Counter counter = new Counter(Integer.MIN_VALUE);
			counter.decrement();
			Assert.fail("Should have failed");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test
	public void testMaxLong() {
		try {
			LongCounter counter = new LongCounter(Long.MAX_VALUE);
			counter.increment();
			Assert.fail("Should have failed");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}

	@Test
	public void testMinLong() {
		try {
			LongCounter counter = new LongCounter(Long.MIN_VALUE);
			counter.decrement();
			Assert.fail("Should have failed");
		} catch (IllegalArgumentException e) {
			// expected
		}
	}
}
