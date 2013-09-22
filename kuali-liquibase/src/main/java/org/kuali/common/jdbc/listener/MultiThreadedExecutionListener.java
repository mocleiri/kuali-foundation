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

import org.kuali.common.util.PercentCompleteInformer;

/**
 * Thread safe tracking of SQL execution related statistics
 * 
 * @deprecated
 */
@Deprecated
public class MultiThreadedExecutionListener extends NoOpSqlListener {

	long aggregateTime;
	long aggregateUpdateCount;
	long aggregateSqlCount;
	long aggregateSqlSize;
	PercentCompleteInformer informer;
	boolean trackProgressByUpdateCount;

	@Override
	public synchronized void afterExecution(SqlExecutionEvent event) {
		this.aggregateTime += event.getStopTimeMillis() - event.getStartTimeMillis();
	}

	@Override
	public synchronized void afterExecuteSql(SqlEvent event) {
		this.aggregateUpdateCount += event.getUpdateCount();
		this.aggregateSqlCount++;
		this.aggregateSqlSize += event.getSql().length();
		if (trackProgressByUpdateCount) {
			informer.incrementProgress(event.getUpdateCount());
		} else {
			informer.incrementProgress();
		}
	}

	public long getAggregateTime() {
		return aggregateTime;
	}

	public long getAggregateUpdateCount() {
		return aggregateUpdateCount;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}

	public boolean isTrackProgressByUpdateCount() {
		return trackProgressByUpdateCount;
	}

	public void setTrackProgressByUpdateCount(boolean trackProgressByUpdateCount) {
		this.trackProgressByUpdateCount = trackProgressByUpdateCount;
	}

	public long getAggregateSqlCount() {
		return aggregateSqlCount;
	}

	public long getAggregateSqlSize() {
		return aggregateSqlSize;
	}
}
