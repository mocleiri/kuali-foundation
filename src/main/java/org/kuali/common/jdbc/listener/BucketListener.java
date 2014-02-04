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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.jdbc.threads.SqlBucket;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This listener will print statistics related to how the SQL is being divided up into different buckets for execution. Only useful when concurrent SQL execution is being
 * performed.
 * 
 * @deprecated
 */
@Deprecated
public class BucketListener extends NoOpSqlListener implements SqlListener {

	private static final Logger logger = LoggerFactory.getLogger(BucketListener.class);
	LoggerLevel level = LoggerLevel.DEBUG;

	@Override
	public void bucketsCreated(BucketEvent event) {
		List<SqlBucket> buckets = event.getBuckets();
		List<Object[]> argsList = new ArrayList<Object[]>();
		for (int i = 0; i < buckets.size(); i++) {
			SqlBucket bucket = buckets.get(i);
			List<SqlSupplier> suppliers = bucket.getSuppliers();
			String count = FormatUtils.getCount(JdbcUtils.getSqlCount(suppliers));
			String srcs = FormatUtils.getCount(suppliers.size());
			String size = FormatUtils.getSize(JdbcUtils.getSqlSize(suppliers));
			Object[] args = { i + 1, count, srcs, size };
			argsList.add(args);
		}
		List<String> columns = Arrays.asList("Bucket", "SQL Count", "Sources", "Size");
		LoggerUtils.logTable(columns, argsList, level, logger);
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

}
