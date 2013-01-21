package org.kuali.common.jdbc.listener;

import java.io.PrintStream;
import java.util.List;

import org.kuali.common.jdbc.ExecutionMetaData;
import org.kuali.common.jdbc.SqlExecutionEvent;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.SqlSource;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProgressListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(ProgressListener.class);

	ExecutionMetaData start;
	ExecutionMetaData finish;
	long count = 0;
	long total = 0;
	int percentageIncrement = 2;
	int percentCompletePrevious;
	PrintStream out = System.out;
	String startToken = ".";
	String completeToken = ".";
	String progressToken = ".";

	@Override
	public synchronized void beforeMetaData(ExecutionContext context) {
		logger.trace("before metadata");
	}

	@Override
	public synchronized void beforeExecution(SqlExecutionEvent event) {
		this.start = getStartMeta(event.getSources());
		this.total = start.getCount();
		out.print("[INFO] Progress: ");
	}

	@Override
	public synchronized void beforeExecuteSql(String sql) {
	}

	@Override
	public synchronized void afterExecuteSql(String sql) {
		count++;
		int percentComplete = (int) ((count * 100) / total);
		if (enoughProgress(percentComplete)) {
			percentCompletePrevious = percentComplete;
			out.print(progressToken);
		}
	}

	protected boolean enoughProgress(int percentComplete) {
		int needed = percentCompletePrevious + percentageIncrement;
		return percentComplete >= needed;
	}

	@Override
	public synchronized void afterExecution(SqlExecutionEvent event) {
		out.println();
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
