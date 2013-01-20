package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.ExecutionMetaData;
import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlListener;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlSource;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSummaryListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(LogSummaryListener.class);

	ExecutionMetaData start;
	ExecutionMetaData finish;
	long startMillis;

	@Override
	public void beforeMetaData(ExecutionContext context) {
		this.startMillis = System.currentTimeMillis();
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		this.start = getStartMeta(event.getSources());
		String count = FormatUtils.getCount(start.getCount());
		String size = FormatUtils.getSize(start.getSize());
		logger.info("Executing {} SQL statements.  Total Size: {}", count, size);
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
		long bytes = start.getSize();
		String count = FormatUtils.getCount(start.getCount());
		String size = FormatUtils.getSize(start.getSize());
		String rate = FormatUtils.getRate(elapsed, bytes);
		Object[] args = { count, size, rate };
		logger.info("Executed {} SQL statements.  Total Size: {}  Rate: {}", args);
	}

	protected ExecutionMetaData getStartMeta(List<SqlSource> sources) {
		long count = 0;
		long size = 0;
		for (SqlSource source : sources) {
			SqlMetaData smd = source.getMetaData();
			count += smd.getCount();
			size += smd.getSize();
		}
		ExecutionMetaData meta = new ExecutionMetaData();
		meta.setCount(count);
		meta.setSize(size);
		return meta;
	}

}
