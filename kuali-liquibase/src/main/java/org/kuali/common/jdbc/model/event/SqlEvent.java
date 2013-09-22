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
package org.kuali.common.jdbc.model.event;

import org.kuali.common.util.Assert;

public final class SqlEvent {

	private static final int DEFAULT_UPDATE_COUNT = -1;

	private final String sql;
	private final long startTimeMillis;
	private final long stopTimeMillis;
	private final int updateCount;

	public SqlEvent(String sql, long startTimeMillis) {
		this(sql, startTimeMillis, -1);
	}

	public SqlEvent(String sql, long startTimeMillis, long stopTimeMillis) {
		this(sql, DEFAULT_UPDATE_COUNT, startTimeMillis, stopTimeMillis);
	}

	public SqlEvent(String sql, int updateCount, long startTimeMillis, long stopTimeMillis) {
		Assert.notNull(sql);
		this.sql = sql;
		this.updateCount = updateCount;
		this.startTimeMillis = startTimeMillis;
		this.stopTimeMillis = stopTimeMillis;
	}

	public String getSql() {
		return sql;
	}

	public long getStartTimeMillis() {
		return startTimeMillis;
	}

	public long getStopTimeMillis() {
		return stopTimeMillis;
	}

	public int getUpdateCount() {
		return updateCount;
	}

}
