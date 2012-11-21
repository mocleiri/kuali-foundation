package org.kuali.common.jdbc;

import javax.sql.DataSource;

public class JdbcContext extends SqlContext {

	DataSource dataSource;
	CommitMode commitMode = CommitMode.PER_SOURCE;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public CommitMode getCommitMode() {
		return commitMode;
	}

	public void setCommitMode(CommitMode commitMode) {
		this.commitMode = commitMode;
	}

}
