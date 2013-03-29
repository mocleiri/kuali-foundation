package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.MetaDataListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
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

	@Autowired
	ConfigurableEnvironment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dbaConfig;

	@Bean
	public Executable jdbcDataConcurrentExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.data.skip", "false");
		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getConcurrentJdbcContext());
		return exec;
	}

	@Bean
	public Executable jdbcDataSequentialExecutable() {
		String skip = SpringUtils.getProperty(env, "jdbc.data.skip", "false");

		JdbcExecutable exec = new JdbcExecutable();
		exec.setSkip(new Boolean(skip));
		exec.setService(commonConfig.jdbcService());
		exec.setContext(getSequentialJdbcContext());
		return exec;
	}

	protected JdbcContext getSequentialJdbcContext() {
		JdbcContext ctx = getBaseJdbcContext("sql.data.sequential.message", "sql.data.sequential");
		ctx.setTrackProgressByUpdateCount(true);
		ctx.setListener(getSummaryAndProgressListener());
		return ctx;
	}

	protected SqlListener getSummaryAndProgressListener() {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener());
		list.add(new ProgressListener());
		return new NotifyingListener(list);
	}

	protected JdbcContext getBaseJdbcContext(String msgProp, String dataProp) {
		String skip = SpringUtils.getProperty(env, "sql.data.skip", "false");
		DataSource dataSource = dbaConfig.jdbcDataSource();
		String message = SpringUtils.getProperty(env, msgProp);
		List<SqlSupplier> suppliers = commonConfig.getSqlSuppliers(dataProp);

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		return ctx;
	}

	protected JdbcContext getConcurrentJdbcContext() {
		String threads = SpringUtils.getProperty(env, "sql.threads");
		String trackProgressByUpdateCount = SpringUtils.getProperty(env, "sql.data.concurrent.trackProgressByUpdateCount", "true");

		JdbcContext ctx = getBaseJdbcContext("sql.data.concurrent.message", "sql.data.concurrent");
		ctx.setTrackProgressByUpdateCount(new Boolean(trackProgressByUpdateCount));
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		ctx.setListener(getConcurrentListener());
		return ctx;
	}

	protected SqlListener getConcurrentListener() {
		String label = SpringUtils.getProperty(env, "sql.data.concurrent.progress.label", "Rows");
		String throughputLabel = SpringUtils.getProperty(env, "sql.data.concurrent.progress.label.throughput", "rows/s");
		DataSummaryListener dsl = new DataSummaryListener();
		dsl.setLabel(label);
		dsl.setThroughputLabel(throughputLabel);

		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new MetaDataListener());
		listeners.add(dsl);
		return new NotifyingListener(listeners);
	}

}
