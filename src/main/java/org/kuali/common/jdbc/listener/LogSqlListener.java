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

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSqlListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(LogSqlListener.class);
	LoggerLevel level = LoggerLevel.DEBUG;
	boolean flatten = true;

	@Override
	public void beforeMetaData(ExecutionContext context) {
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
	}

	@Override
	public void beforeExecuteSql(SqlEvent event) {
	}

	@Override
	public void afterExecuteSql(SqlEvent event) {
		String sql = getSql(event.getSql(), flatten);
		long millis = event.getStopTimeMillis() - event.getStartTimeMillis();
		String time = StringUtils.leftPad(FormatUtils.getTime(millis), 8, " ");
		Object[] args = { time };
		String msg = "Elapsed: {} " + sql;
		LoggerUtils.logMsg(msg, args, logger, level);
	}

	protected String getSql(String sql, boolean flatten) {
		if (flatten) {
			return "[" + Str.flatten(sql) + "]";
		} else {
			return sql;
		}
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

	public boolean isFlatten() {
		return flatten;
	}

	public void setFlatten(boolean flatten) {
		this.flatten = flatten;
	}

}
