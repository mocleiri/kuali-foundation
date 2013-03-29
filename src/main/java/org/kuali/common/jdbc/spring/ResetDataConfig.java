package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.MetaDataListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class })
public class ResetDataConfig {

	public static final String TYPE = "data";

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dbaConfig;

	@Bean
	public Executable jdbcDataConcurrentExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc." + TYPE + ".skip", "false");
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getConcurrentJdbcContext());
		return exec;
	}

	@Bean
	public Executable jdbcDataSequentialExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc." + TYPE + ".skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getSequentialJdbcContext());
		return exec;
	}

	protected JdbcContext getSequentialJdbcContext() {
		SqlContext sc = new SqlContext(TYPE, SqlMode.SEQUENTIAL);
		JdbcConfigContext jcc = new JdbcConfigContext(env, sc, commonConfig, dbaConfig);
		JdbcContext ctx = JdbcConfigUtils.getSequentialJdbcContext(jcc);
		ctx.setTrackProgressByUpdateCount(true);
		ctx.setListener(JdbcConfigUtils.getSummaryAndProgressListener());
		return ctx;
	}

	protected JdbcContext getConcurrentJdbcContext() {
		SqlContext sc = new SqlContext(TYPE, SqlMode.CONCURRENT);
		JdbcConfigContext jcc = new JdbcConfigContext(env, sc, commonConfig, dbaConfig);
		JdbcContext ctx = JdbcConfigUtils.getConcurrentJdbcContext(jcc);
		String trackProgressByUpdateCount = SpringUtils.getProperty(env, "sql.data.concurrent.trackProgressByUpdateCount", "true");
		ctx.setTrackProgressByUpdateCount(new Boolean(trackProgressByUpdateCount));
		ctx.setListener(getConcurrentListener());
		return ctx;
	}

	protected SqlListener getConcurrentListener() {
		SqlContext sc = new SqlContext(TYPE, SqlMode.CONCURRENT);
		JdbcConfigContext jcc = new JdbcConfigContext(env, sc, commonConfig, dbaConfig);
		DataSummaryListener dsl = JdbcConfigUtils.getConcurrentDataSummaryListener(jcc);

		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new MetaDataListener());
		listeners.add(dsl);
		return new NotifyingListener(listeners);
	}

}
