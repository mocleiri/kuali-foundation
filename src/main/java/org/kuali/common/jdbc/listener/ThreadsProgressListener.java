package org.kuali.common.jdbc.listener;

import java.util.List;

import org.kuali.common.jdbc.ExecutionMetaData;
import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlSource;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.threads.listener.PercentCompleteListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadsProgressListener extends PercentCompleteListener<String> implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(ThreadsProgressListener.class);

	ExecutionMetaData start;
	ExecutionMetaData finish;
	long count = 0;
	long total = 0;

	@Override
	public synchronized void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public synchronized void beforeExecution(SqlExecutionEvent event) {
	}

	@Override
	public synchronized void beforeExecuteSql(String sql) {
	}

	@Override
	public synchronized void afterExecuteSql(String sql) {
		if (count == 0) {
			logger.debug("Progress started");
			progressStarted();
		}
		// TODO This breaks for clients trying to track execution progress of more than Integer.MAX_VALUE SQL statements
		progressOccurred((int) count++, (int) total, null);
		if (count == total) {
			logger.debug("Progress complete");
			progressCompleted();
		}
	}

	@Override
	public synchronized void afterExecution(SqlExecutionEvent event) {
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

	public ExecutionMetaData getStart() {
		return start;
	}

	public void setStart(ExecutionMetaData start) {
		this.start = start;
	}

	public ExecutionMetaData getFinish() {
		return finish;
	}

	public void setFinish(ExecutionMetaData finish) {
		this.finish = finish;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
