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
package org.kuali.common.jdbc;

/**
 * @deprecated
 */
@Deprecated
public class ExecutionResult {

	long updateCount;
	long startTimeMillis;
	long stopTimeMillis;
	long elapsed;
	long statementsExecuted;

	public ExecutionResult() {
		this(0, 0, 0, 0);
	}

	public ExecutionResult(long updateCount, long startTimeMillis, long stopTimeMillis, long statementsExecuted) {
		super();
		this.updateCount = updateCount;
		this.startTimeMillis = startTimeMillis;
		this.stopTimeMillis = stopTimeMillis;
		this.statementsExecuted = statementsExecuted;
		this.elapsed = stopTimeMillis - startTimeMillis;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(long updateCount) {
		this.updateCount = updateCount;
	}

	public long getStartTimeMillis() {
		return startTimeMillis;
	}

	public void setStartTimeMillis(long startTimeMillis) {
		this.startTimeMillis = startTimeMillis;
	}

	public long getStopTimeMillis() {
		return stopTimeMillis;
	}

	public void setStopTimeMillis(long stopTimeMillis) {
		this.stopTimeMillis = stopTimeMillis;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

	public long getStatementsExecuted() {
		return statementsExecuted;
	}

	public void setStatementsExecuted(long statementsExecuted) {
		this.statementsExecuted = statementsExecuted;
	}

}
