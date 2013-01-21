package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.ExecutionMetaData;
import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlSource;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProgressListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(ProgressListener.class);

	ExecutionMetaData start;
	ExecutionMetaData finish;
	int count = 0;

	@Override
	public synchronized void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public synchronized void beforeExecution(SqlExecutionEvent event) {
		this.start = getStartMeta(event.getSources());
		String count = FormatUtils.getCount(start.getCount());
		String size = FormatUtils.getSize(start.getSize());
		logger.info("Executing {} SQL statements.  Total Size: {}", count, size);
		System.out.print("[INFO] Progress: ");
	}

	@Override
	public synchronized void beforeExecuteSql(String sql) {
	}

	@Override
	public synchronized void afterExecuteSql(String sql) {
		count++;
		if (count % 100 == 0) {
			System.out.print(".");
		}
	}

	@Override
	public synchronized void afterExecution(SqlExecutionEvent event) {
		System.out.println();
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
