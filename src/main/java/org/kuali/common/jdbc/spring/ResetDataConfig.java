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
package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.MetaDataListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetDataConfig extends ResetBaseConfig {

	public static final String TYPE = "data";
	public static final String SKIP_KEY = "jdbc.data.skip";

	@Bean
	public Executable jdbcDataConcurrentExecutable() {
		ResetConfigContext jcc = new ResetConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dataSourceConfig);
		JdbcContext ctx = ResetConfigUtils.getConcurrentJdbcContext(jcc);
		ctx.setListener(getConcurrentListener());

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(ctx);
		return exec;
	}

	@Bean
	public Executable jdbcDataSequentialExecutable() {
		ResetConfigContext jcc = new ResetConfigContext(env, TYPE, SqlMode.SEQUENTIAL, commonConfig, dataSourceConfig);
		JdbcContext ctx = ResetConfigUtils.getSequentialJdbcContext(jcc);
		ctx.setListener(ResetConfigUtils.getSummaryAndProgressListener(env));
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(ctx);
		return exec;
	}

	protected SqlListener getConcurrentListener() {
		ResetConfigContext rcc = new ResetConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dataSourceConfig);
		DataSummaryListener dsl = ResetConfigUtils.getConcurrentDataSummaryListener(rcc);

		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new MetaDataListener());
		listeners.add(dsl);
		listeners.add(ResetConfigUtils.getLogSqlListener(env));
		return new NotifyingListener(listeners);
	}

}
