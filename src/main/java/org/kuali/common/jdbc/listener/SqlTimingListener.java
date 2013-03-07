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

import org.kuali.common.jdbc.context.JdbcContext;

/**
 * Print a dot to the console each time 1% of the SQL finishes executing
 */
public class SqlTimingListener implements SqlListener {

	long aggregateSqlTime;
	long wallTimeElapsed;
	long start;
	long stop;

	@Override
	public void beforeMetaData(JdbcContext context) {
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
	}

	@Override
	public void beforeExecuteSql(SqlEvent event) {
		this.start = System.currentTimeMillis();
	}

	@Override
	public synchronized void afterExecuteSql(SqlEvent event) {
		this.aggregateSqlTime += event.getStopTimeMillis() - event.getStartTimeMillis();
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		this.stop = System.currentTimeMillis();
		this.wallTimeElapsed = stop - start;
	}

	public long getAggregateSqlTime() {
		return aggregateSqlTime;
	}

	public long getWallTimeElapsed() {
		return wallTimeElapsed;
	}

}
