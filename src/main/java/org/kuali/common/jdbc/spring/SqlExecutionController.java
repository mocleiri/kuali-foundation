package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.JdbcContextUtils;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.MetaDataListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.execute.ExecutablesExecutable;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Default database reset controller class. It displays the JDBC configuration, then executes a series of SQL statements in order [dba->schema->data->constraints->other].
 */
@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class, ResetDbaConfig.class, ResetSchemaConfig.class, ResetConstraintsConfig.class, ResetOtherConfig.class })
public class SqlExecutionController {

	public static final String RESET_SKIP_KEY = "jdbc.reset.skip";
	public static final String RESET_TIMED_KEY = "jdbc.reset.timed";

	@Autowired
	Environment env;

	@Autowired
	JdbcCommonConfig commonConfig;

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Autowired
	ResetDbaConfig dbaConfig;

	private static final String SCHEMA_GROUP_KEY = "sql.schema";
	private static final String DATA_GROUP_KEY = "sql.data";
	private static final String CONSTRAINT_GROUP_KEY = "sql.constraints";
	private static final String OTHER_GROUP_KEY = "sql.other";

	@Bean(initMethod = "execute")
	public Executable jdbcResetExecutable() {

		// Build a concurrent listener for logging the data
		DataSummaryListener dsl = JdbcContextUtils.getConcurrentDataSummaryListener(env, DATA_GROUP_KEY, SqlMode.SEQUENTIAL);
		List<SqlListener> listeners = new ArrayList<SqlListener>();
		listeners.add(new MetaDataListener());
		listeners.add(dsl);
		listeners.add(ResetConfigUtils.getLogSqlListener(env));
		SqlListener concurrentDataListener = new NotifyingListener(listeners);

		BatchGroupedSqlConfig batchConfig = new BatchGroupedSqlConfig(env, commonConfig, dataSourceConfig);
		batchConfig.addBatch(SCHEMA_GROUP_KEY, SqlMode.CONCURRENT, JdbcContextUtils.buildSummaryListener(env));
		batchConfig.addBatch(DATA_GROUP_KEY, SqlMode.SEQUENTIAL, JdbcContextUtils.buildSummaryAndProgressListener(env));
		batchConfig.addBatch(DATA_GROUP_KEY, SqlMode.CONCURRENT, concurrentDataListener);
		batchConfig.addBatch(CONSTRAINT_GROUP_KEY, SqlMode.SEQUENTIAL, JdbcContextUtils.buildSummaryAndProgressListener(env));
		batchConfig.addBatch(CONSTRAINT_GROUP_KEY, SqlMode.CONCURRENT, JdbcContextUtils.buildSummaryListener(env));
		batchConfig.addBatch(OTHER_GROUP_KEY, SqlMode.SEQUENTIAL, JdbcContextUtils.buildSummaryAndProgressListener(env));
		batchConfig.addBatch(OTHER_GROUP_KEY, SqlMode.CONCURRENT, JdbcContextUtils.buildSummaryListener(env));

		List<JdbcContext> contexts = batchConfig.buildContexts();
		List<Executable> executables = new ArrayList<Executable>();
		executables.add(dataSourceConfig.jdbcShowConfigExecutable());
		executables.add(dbaConfig.jdbcDbaExecutable());

		for (JdbcContext context : contexts) {
			JdbcExecutable exec = new JdbcExecutable();
			exec.setService(commonConfig.jdbcService());
			exec.setContext(context);
			executables.add(exec);
		}

		ExecutablesExecutable exec = new ExecutablesExecutable();
		exec.setSkip(SpringUtils.getBoolean(env, RESET_SKIP_KEY, false));
		exec.setTimed(SpringUtils.getBoolean(env, RESET_TIMED_KEY, true));
		exec.setExecutables(executables);
		return exec;
	}

}
