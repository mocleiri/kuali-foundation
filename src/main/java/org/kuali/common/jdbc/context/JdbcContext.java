package org.kuali.common.jdbc.context;

import javax.sql.DataSource;

import org.kuali.common.jdbc.CommitMode;

public class JdbcContext extends SqlContext {

	DataSource dataSource;
	CommitMode commitMode = CommitMode.PER_SOURCE;
	boolean showProgress = true;
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

	public boolean isShowProgress() {
		return showProgress;
	}

	public void setShowProgress(boolean showProgress) {
		this.showProgress = showProgress;
	}

}
