package org.kuali.common.jdbc.listener;

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.threads.SqlExecutionEvent;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SummaryListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(SummaryListener.class);

	long startMillis;
	long count;
	long size;

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
		logger.info("[SQL Count: {}  Sources: {}  Size: {}]", args);
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
		Object[] args = { count, sources, size, time, rate };
		logger.info("[SQL Count: {}  Sources: {}  Size: {}  Time: {}  Rate: {}]", args);
	}
}
