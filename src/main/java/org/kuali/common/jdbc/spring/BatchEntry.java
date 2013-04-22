package org.kuali.common.jdbc.spring;

import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SqlListener;

public class BatchEntry {

	String groupKey;
	SqlMode sqlMode;
	SqlListener sqlListener;

	public BatchEntry() {
		this(null, null, null);
	}

	public BatchEntry(String groupKey, SqlMode sqlMode, SqlListener sqlListener) {
		this.groupKey = groupKey;
		this.sqlMode = sqlMode;
		this.sqlListener = sqlListener;
	}

	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}

	public SqlMode getSqlMode() {
		return sqlMode;
	}

	public void setSqlMode(SqlMode sqlMode) {
		this.sqlMode = sqlMode;
	}

	public SqlListener getSqlListener() {
		return sqlListener;
	}

	public void setSqlListener(SqlListener sqlListener) {
		this.sqlListener = sqlListener;
	}
}
