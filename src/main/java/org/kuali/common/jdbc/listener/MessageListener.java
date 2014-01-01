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

import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

	public MessageListener() {
		this(null);
	}

	public MessageListener(String beforeMetaData) {
		super();
		this.beforeMetaData = beforeMetaData;
	}

	LoggerLevel level = LoggerLevel.INFO;
	String beforeMetaData;
	String beforeExecution;
	String bucketsCreated;
	String beforeExecuteSql;
	String afterExecuteSql;
	String afterExecution;

	@Override
	public void beforeMetaData(ExecutionContext context) {
		if (beforeMetaData != null) {
			LoggerUtils.logMsg(beforeMetaData, logger, level);
		}
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		if (beforeExecution != null) {
			LoggerUtils.logMsg(beforeExecution, logger, level);
		}
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
		if (bucketsCreated != null) {
			LoggerUtils.logMsg(bucketsCreated, logger, level);
		}
	}

	@Override
	public void beforeExecuteSql(SqlEvent event) {
		if (beforeExecuteSql != null) {
			LoggerUtils.logMsg(beforeExecuteSql, logger, level);
		}
	}

	@Override
	public void afterExecuteSql(SqlEvent event) {
		if (afterExecuteSql != null) {
			LoggerUtils.logMsg(afterExecuteSql, logger, level);
		}
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		if (afterExecution != null) {
			LoggerUtils.logMsg(afterExecution, logger, level);
		}
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

}
