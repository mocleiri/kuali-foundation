package org.kuali.common.util;

public class Counter {
	int count;

	public Counter() {
		this(0);
	}

	public Counter(int count) {
		super();
		this.count = count;
	}

	public int increment() {
		if (count == Integer.MAX_VALUE) {
			throw new IllegalStateException("Maximum integer value exceeded");
		} else {
			return ++count;
		}
	}

	public int decrement() {
		if (count == Integer.MIN_VALUE) {
			throw new IllegalStateException("Minimum integer value exceeded");
		} else {
			return --count;
		}
	}
}
