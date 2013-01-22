package org.kuali.common.jdbc.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.SqlSource;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.jdbc.threads.SqlBucket;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This listener will print statistics related to how the SQL is being divided up into different buckets for execution Only useful when
 * concurrent SQL execution is being performed.
 */
public class BucketListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(BucketListener.class);
	LoggerLevel level = LoggerLevel.INFO;

	@Override
	public void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
		List<SqlBucket> buckets = event.getBuckets();
		List<Object[]> argsList = new ArrayList<Object[]>();
		for (int i = 0; i < buckets.size(); i++) {
			SqlBucket bucket = buckets.get(i);
			List<SqlSource> sources = bucket.getSources();
			String count = FormatUtils.getCount(JdbcUtils.getSqlCount(sources));
			String srcs = FormatUtils.getCount(sources.size());
			String size = FormatUtils.getSize(JdbcUtils.getSqlSize(sources));
			Object[] args = { i + 1, count, srcs, size };
			argsList.add(args);
		}
		List<String> columns = Arrays.asList("Bucket", "SQL Count", "Sources", "Size");
		LoggerUtils.logTable(columns, argsList, LoggerLevel.INFO, logger);
	}

	@Override
	public void beforeExecuteSql(String sql) {
	}

	@Override
	public void afterExecuteSql(String sql) {
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

}
