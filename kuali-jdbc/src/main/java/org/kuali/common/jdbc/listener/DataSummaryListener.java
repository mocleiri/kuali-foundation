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

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class DataSummaryListener extends NoOpSqlListener {

	private static final Logger logger = LoggerFactory.getLogger(DataSummaryListener.class);

	long count;
	long size;
	LoggerLevel loggerLevel = LoggerLevel.INFO;
	boolean showRate = true;
	String label = "Rows";
	String throughputLabel = "rows/s";

	public DataSummaryListener() {
		this(true);
	}

	public DataSummaryListener(boolean showRate) {
		super();
		this.showRate = showRate;
	}

	@Override
	public void afterMetaData(SqlMetaDataEvent event) {
		this.count = JdbcUtils.getSqlCount(event.getContext().getSuppliers());
		this.size = JdbcUtils.getSqlSize(event.getContext().getSuppliers());
		String count = FormatUtils.getCount(this.count);
		String sources = FormatUtils.getCount(event.getContext().getSuppliers().size());
		String size = FormatUtils.getSize(this.size);
		Object[] args = { label, count, sources, size };
		LoggerUtils.logMsg("Executing - [{}: {}  Sources: {}  Size: {}]", args, logger, loggerLevel);
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		long elapsed = event.getStopTimeMillis() - event.getStartTimeMillis();
		String count = FormatUtils.getCount(this.count);
		String sources = FormatUtils.getCount(event.getContext().getSuppliers().size());
		String size = FormatUtils.getSize(this.size);
		String time = FormatUtils.getTime(elapsed);
		String rate = FormatUtils.getRate(elapsed, this.size);
		String throughput = FormatUtils.getThroughputInSeconds(elapsed, this.count, throughputLabel);
		Object[] args = { label, count, sources, size, time, throughput, rate };
		if (showRate) {
			LoggerUtils.logMsg("Completed - [{}: {}  Sources: {}  Size: {}  Time: {}  Throughput: {}  Rate: {}]", args, logger, loggerLevel);
		} else {
			LoggerUtils.logMsg("Completed - [{}: {}  Sources: {}  Size: {}  Time: {}  Throughput: {}]", args, logger, loggerLevel);
		}
	}

	public boolean isShowRate() {
		return showRate;
	}

	public void setShowRate(boolean showRate) {
		this.showRate = showRate;
	}

	public LoggerLevel getLoggerLevel() {
		return loggerLevel;
	}

	public void setLoggerLevel(LoggerLevel loggerLevel) {
		this.loggerLevel = loggerLevel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getThroughputLabel() {
		return throughputLabel;
	}

	public void setThroughputLabel(String throughputLabel) {
		this.throughputLabel = throughputLabel;
	}

}
