package org.kuali.common.jdbc.listener;

import java.io.PrintStream;

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.context.ExecutionContext;

public class ProgressListener implements SqlListener {

	PrintStream out = System.out;
	long count = 0;
	long total = 0;
	int percentageIncrement = 1;
	int percentCompletePrevious;
	String startToken = "[INFO] Progress: ";
	String progressToken = ".";
	String completeToken = "\n";

	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public synchronized void beforeExecution(SqlExecutionEvent event) {
		// The total number of SQL statements being executed
		this.total = JdbcUtils.getSqlCount(event.getSources());
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
	}

	@Override
	public void beforeExecuteSql(String sql) {
	}

	@Override
	public synchronized void afterExecuteSql(String sql) {
		// The first SQL statement was just executed
		if (count == 0) {
			out.print(startToken);
		}

		// Increment the counter
		this.count++;

		// Print a dot anytime we make 1% progress
		int percentComplete = (int) ((count * 100) / total);
		if (enoughProgress(percentComplete)) {
			this.percentCompletePrevious = percentComplete;
			out.print(progressToken);
		}

		// The last SQL statement was just executed
		if (count == total) {
			out.print(completeToken);
		}
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
	}

	protected boolean enoughProgress(int percentComplete) {
		int needed = percentCompletePrevious + percentageIncrement;
		return percentComplete >= needed;
	}

	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getPercentageIncrement() {
		return percentageIncrement;
	}

	public void setPercentageIncrement(int percentageIncrement) {
		this.percentageIncrement = percentageIncrement;
	}

	public String getStartToken() {
		return startToken;
	}

	public void setStartToken(String startToken) {
		this.startToken = startToken;
	}

	public String getCompleteToken() {
		return completeToken;
	}

	public void setCompleteToken(String completeToken) {
		this.completeToken = completeToken;
	}

	public String getProgressToken() {
		return progressToken;
	}

	public void setProgressToken(String progressToken) {
		this.progressToken = progressToken;
	}

	public long getTotal() {
		return total;
	}

}
