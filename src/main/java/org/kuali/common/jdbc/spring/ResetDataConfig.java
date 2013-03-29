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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class })
public class ResetDataConfig {

	public static final String TYPE = "data";
	public static final String SKIP_KEY = "jdbc.data.skip";

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dbaConfig;

	@Bean
	public Executable jdbcDataConcurrentExecutable() {
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(JdbcConfigUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getConcurrentJdbcContext());
		return exec;
	}

	@Bean
	public Executable jdbcDataSequentialExecutable() {
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(JdbcConfigUtils.getBoolean(env, SKIP_KEY, false));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getSequentialJdbcContext());
		return exec;
	}

	protected JdbcContext getSequentialJdbcContext() {
		JdbcConfigContext jcc = new JdbcConfigContext(env, TYPE, SqlMode.SEQUENTIAL, commonConfig, dbaConfig);
		JdbcContext ctx = JdbcConfigUtils.getSequentialJdbcContext(jcc);
		ctx.setListener(JdbcConfigUtils.getSummaryAndProgressListener());
		return ctx;
	}

	protected JdbcContext getConcurrentJdbcContext() {
		JdbcConfigContext jcc = new JdbcConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dbaConfig);
		JdbcContext ctx = JdbcConfigUtils.getConcurrentJdbcContext(jcc);
		ctx.setListener(getConcurrentListener());
		return ctx;
	}

	protected SqlListener getConcurrentListener() {
		JdbcConfigContext jcc = new JdbcConfigContext(env, TYPE, SqlMode.CONCURRENT, commonConfig, dbaConfig);
		DataSummaryListener dsl = JdbcConfigUtils.getConcurrentDataSummaryListener(jcc);

		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new MetaDataListener());
		listeners.add(dsl);
		return new NotifyingListener(listeners);
	}

}
