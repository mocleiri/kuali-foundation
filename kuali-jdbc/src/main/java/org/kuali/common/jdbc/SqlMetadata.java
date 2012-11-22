package org.kuali.common.jdbc;

import java.util.List;

public class SqlMetadata {

	long count;
	long executionTime;

	List<SqlSourceMetadata> sourceMetadata;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<SqlSourceMetadata> getSourceMetadata() {
		return sourceMetadata;
	}

	public void setSourceMetadata(List<SqlSourceMetadata> sourceMetadata) {
		this.sourceMetadata = sourceMetadata;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

}
