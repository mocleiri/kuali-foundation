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

import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSqlListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(LogSqlListener.class);
	LoggerLevel level = LoggerLevel.DEBUG;
	boolean flatten = true;
	boolean showTiming = false;

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
		String msg = event.getSql();
		if (flatten) {
			msg = "[" + Str.flatten(event.getSql()) + "]";
		}
		LoggerUtils.logMsg(msg, logger, level);
	}

	@Override
	public void afterExecuteSql(SqlEvent event) {
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
