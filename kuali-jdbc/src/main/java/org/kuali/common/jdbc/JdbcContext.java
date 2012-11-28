package org.kuali.common.jdbc;

import javax.sql.DataSource;

public class JdbcContext extends SqlContext {

	DataSource dataSource;
	CommitMode commitMode = CommitMode.PER_SOURCE;
	int showProgressMin = 50;
	int showProgressDivisor = 10;

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

	public int getShowProgressMin() {
		return showProgressMin;
	}

	public void setShowProgressMin(int showProgressMin) {
		this.showProgressMin = showProgressMin;
	}

	public int getShowProgressDivisor() {
		return showProgressDivisor;
	}

	public void setShowProgressDivisor(int showProgressDivisor) {
		this.showProgressDivisor = showProgressDivisor;
	}

}
