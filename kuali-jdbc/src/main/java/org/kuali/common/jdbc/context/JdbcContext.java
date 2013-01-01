/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
