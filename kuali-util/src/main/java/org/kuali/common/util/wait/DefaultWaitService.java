package org.kuali.common.util.wait;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.condition.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultWaitService implements WaitService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultWaitService.class);

	@Override
	public WaitResult wait(WaitContext context, Condition condition) {
		long start = System.currentTimeMillis();
		long timeout = start + context.getTimeoutMillis();
		ThreadUtils.sleep(context.getInitialPauseMillis());
		while (!condition.isTrue()) {
			long now = System.currentTimeMillis();
			Assert.isTrue(now <= timeout, "Timed out waiting");
			String elapsed = StringUtils.leftPad(FormatUtils.getTime(now - start), 7, " ");
			String timeoutString = StringUtils.leftPad(FormatUtils.getTime(timeout - now), 7, " ");
			logger.info("[elapsed: {}  timeout: {}]", elapsed, timeoutString);
			ThreadUtils.sleep(context.getSleepMillis());
		}
		return new WaitResult.Builder(start, System.currentTimeMillis()).build();
	}

}
