/**
 * Copyright 2010-2014 The Kuali Foundation
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

public class SqlEvent {

	String sql;
	long startTimeMillis;
	long stopTimeMillis;

	public SqlEvent() {
		this(null, 0, 0);
	}

	public SqlEvent(String sql, long startTimeMillis, long stopTimeMillis) {
		super();
		this.sql = sql;
		this.startTimeMillis = startTimeMillis;
		this.stopTimeMillis = stopTimeMillis;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
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

}
