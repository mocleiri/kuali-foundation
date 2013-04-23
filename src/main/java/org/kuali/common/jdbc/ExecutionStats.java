package org.kuali.common.jdbc;

public class ExecutionStats {

	long updateCount;
	long statementCount;

	public ExecutionStats() {
		this(0, 0);
	}

	public ExecutionStats(long updateCount, long statementCount) {
		super();
		this.updateCount = updateCount;
		this.statementCount = statementCount;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(long updateCount) {
		this.updateCount = updateCount;
	}

	public long getStatementCount() {
		return statementCount;
	}

	public void setStatementCount(long statementCount) {
		this.statementCount = statementCount;
	}

}
