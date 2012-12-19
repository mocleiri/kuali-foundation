package org.kuali.common.util.execute;

import org.kuali.common.util.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncrementCounterExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(IncrementCounterExecutable.class);

	Counter counter = new Counter();

	@Override
	public void execute() {
		logger.info("Count = {}", counter.increment());
	}

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}
}
