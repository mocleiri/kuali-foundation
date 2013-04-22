/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.DefaultGroupedSqlConfigImpl;
import org.kuali.common.jdbc.GroupedSqlConfig;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.JdbcContextUtils;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SqlListener;
import org.springframework.core.env.Environment;

/**
 * Represents a set of GroupedSqlConfig objects with the same base configuration
 * 
 * @author andrewlubbers
 */
public class BatchGroupedSqlConfig {

	Environment env;

	JdbcCommonConfig commonConfig;

	JdbcDataSourceConfig dataSourceConfig;

	List<BatchEntry> batchEntries;

	public BatchGroupedSqlConfig(Environment env, JdbcCommonConfig commonConfig, JdbcDataSourceConfig dataSourceConfig) {
		this.env = env;
		this.commonConfig = commonConfig;
		this.dataSourceConfig = dataSourceConfig;
		this.batchEntries = new ArrayList<BatchEntry>();
	}

	public void addBatch(String groupKey, SqlMode sqlMode, SqlListener listener) {
		batchEntries.add(new BatchEntry(groupKey, sqlMode, listener));
	}

	public void clearBatches() {
		batchEntries.clear();
	}

	public List<JdbcContext> buildContexts() {
		List<JdbcContext> results = new ArrayList<JdbcContext>();

		for (BatchEntry entry : batchEntries) {
			GroupedSqlConfig groupedConfig = new DefaultGroupedSqlConfigImpl(entry.getGroupKey(), entry.getSqlMode(), env, commonConfig, dataSourceConfig, entry.getSqlListener());
			JdbcContext context = JdbcContextUtils.buildJdbcContextFromGroupedSql(groupedConfig);

			results.add(context);
		}

		return results;
	}
}
