package org.kuali.common.util;

import junit.framework.Assert;

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
