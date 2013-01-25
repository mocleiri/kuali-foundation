package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SummaryListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(SummaryListener.class);

	public SummaryListener() {
		this(true);
	}

	public SummaryListener(boolean showRate) {
		super();
		this.showRate = showRate;
	}

	long startMillis;
	long count;
	long size;
	LoggerLevel loggerLevel = LoggerLevel.INFO;
	boolean showRate = true;

	@Override
	public void beforeMetaData(ExecutionContext context) {
		this.startMillis = System.currentTimeMillis();
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		this.count = JdbcUtils.getSqlCount(event.getSources());
		this.size = JdbcUtils.getSqlSize(event.getSources());
		String count = FormatUtils.getCount(this.count);
		String sources = FormatUtils.getCount(event.getSources().size());
		String size = FormatUtils.getSize(this.size);
		Object[] args = { count, sources, size };
		LoggerUtils.logMsg("Executing - [SQL Count: {}  Sources: {}  Size: {}]", args, logger, loggerLevel);
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
	}

	@Override
	public void beforeExecuteSql(String sql) {
	}

	@Override
	public void afterExecuteSql(String sql) {
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		long elapsed = System.currentTimeMillis() - startMillis;
		String count = FormatUtils.getCount(this.count);
		String sources = FormatUtils.getCount(event.getSources().size());
		String size = FormatUtils.getSize(this.size);
		String time = FormatUtils.getTime(elapsed);
		String rate = FormatUtils.getRate(elapsed, this.size);
		if (showRate) {
			Object[] args = { count, sources, size, time, rate };
			LoggerUtils.logMsg("Completed - [SQL Count: {}  Sources: {}  Size: {}  Time: {}  Rate: {}]", args, logger, loggerLevel);
		} else {
			Object[] args = { count, sources, size, time, rate };
			LoggerUtils.logMsg("Completed - [SQL Count: {}  Sources: {}  Size: {}  Time: {}]", args, logger, loggerLevel);
		}
	}

	public boolean isShowRate() {
		return showRate;
	}

	public void setShowRate(boolean showRate) {
		this.showRate = showRate;
	}
}
