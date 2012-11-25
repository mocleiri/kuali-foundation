package org.kuali.common.jdbc;

import java.util.ArrayList;

public class SqlMetaDataList extends ArrayList<SqlMetaData> {

	private static final long serialVersionUID = 7248787367263097240L;

	long count;
	long executionTime;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
}
