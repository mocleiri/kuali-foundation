package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.MetaDataListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
@Import({ JdbcCommonConfig.class, ResetDataSourceConfig.class })
public class ResetDataConfig {

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDbaConfig dbaConfig;

	@Bean
	public Executable jdbcConcurrentDataExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.data.skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getConcurrentJdbcContext());
		return exec;
	}

	protected JdbcContext getConcurrentJdbcContext() {
		String skip = SpringUtils.getProperty(env, "sql.data.skip", "false");
		String threads = SpringUtils.getProperty(env, "sql.threads");
		String message = SpringUtils.getProperty(env, "sql.data.concurrent.message");
		List<SqlSupplier> suppliers = commonConfig.getSqlSuppliers("sql.data.concurrent");
		DataSource dataSource = dbaConfig.jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setTrackProgressByUpdateCount(true);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		ctx.setListener(getConcurrentListener());
		return ctx;
	}

	protected SqlListener getConcurrentListener() {
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new MetaDataListener());
		listeners.add(new DataSummaryListener());
		return new NotifyingListener(listeners);
	}

}
