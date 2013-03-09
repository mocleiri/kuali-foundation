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
package org.kuali.common.jdbc.listener;

/**
 * Track the total amount of time spent executing SQL. This must be threadsafe.
 */
public class MultiThreadedExecutionListener extends NoOpSqlListener {

	long aggregateTime;
	long aggregateUpdateCount;

	@Override
	public synchronized void afterExecution(SqlExecutionEvent event) {
		long elapsed = event.getStopTimeMillis() - event.getStartTimeMillis();
		this.aggregateTime += elapsed;
	}

	@Override
	public synchronized void afterExecuteSql(SqlEvent event) {
		int updateCount = event.getUpdateCount();
		if (updateCount != -1) {
			this.aggregateUpdateCount += updateCount;
		}
	}

	public long getAggregateTime() {
		return aggregateTime;
	}

	public long getAggregateUpdateCount() {
		return aggregateUpdateCount;
	}

}
