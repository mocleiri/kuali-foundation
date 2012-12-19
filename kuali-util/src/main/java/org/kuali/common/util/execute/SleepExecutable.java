package org.kuali.common.util.execute;

import org.kuali.common.util.SimpleFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SleepExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SleepExecutable.class);

	SimpleFormatter formatter = new SimpleFormatter();
	long millis;

	@Override
	public void execute() {
		logger.info("Sleeping for {}", formatter.getTime(millis));
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	public long getMillis() {
		return millis;
	}

	public void setMillis(long millis) {
		this.millis = millis;
	}

	public SimpleFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(SimpleFormatter formatter) {
		this.formatter = formatter;
	}

}
