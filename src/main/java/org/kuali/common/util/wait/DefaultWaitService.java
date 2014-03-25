package org.kuali.common.util.wait;

import static com.google.common.base.Preconditions.checkState;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.kuali.common.util.base.Threads.sleep;
import static org.kuali.common.util.log.Loggers.newLogger;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.condition.Condition;
import org.slf4j.Logger;

public class DefaultWaitService implements WaitService {

	private static final Logger logger = newLogger();

	@Override
	public WaitResult wait(WaitContext context, Condition condition) {
		long start = currentTimeMillis();
		long timeout = start + context.getTimeoutMillis();
		sleep(context.getInitialPauseMillis());
		while (!condition.isTrue()) {
			long now = currentTimeMillis();
			checkState(now <= timeout, "Timed out waiting");
			String elapsed = leftPad(FormatUtils.getTime(now - start), 7, " ");
			String timeoutString = leftPad(FormatUtils.getTime(timeout - now), 7, " ");
			logger.info("[elapsed: {}  timeout: {}]", elapsed, timeoutString);
			sleep(context.getSleepMillis());
		}
		return WaitResult.create(start, currentTimeMillis());
	}

}
