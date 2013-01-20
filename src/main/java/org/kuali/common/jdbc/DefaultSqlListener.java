package org.kuali.common.jdbc;

import java.util.List;

import org.kuali.common.jdbc.context.ExecutionContext;

public class DefaultSqlListener implements SqlListener {

	ExecutionMetaData start;
	ExecutionMetaData finish;
	int count = 0;

	@Override
	public synchronized void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public synchronized void beforeExecution(SqlExecutionEvent event) {
		this.start = getStartMeta(event.getSources());
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
			count += smd.getSize();
		}
		ExecutionMetaData meta = new ExecutionMetaData();
		meta.setCount(count);
		meta.setSize(size);
		return meta;
	}

}
