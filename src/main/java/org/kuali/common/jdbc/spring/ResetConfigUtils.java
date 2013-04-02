package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.LogSqlMode;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.Environment;

public class ResetConfigUtils {

	public static DataSummaryListener getConcurrentDataSummaryListener(ResetConfigContext jcc) {
		String propertyPrefix = getPropertyPrefix(jcc);
		String label = SpringUtils.getProperty(jcc.getEnv(), propertyPrefix + ".progress.label", "Rows");
		String throughputLabel = SpringUtils.getProperty(jcc.getEnv(), propertyPrefix + ".progress.label.throughput", "rows/s");
		DataSummaryListener dsl = new DataSummaryListener();
		dsl.setLabel(label);
		dsl.setThroughputLabel(throughputLabel);
		return dsl;
	}

	public static JdbcContext getConcurrentJdbcContext(ResetConfigContext jcc) {
		String threads = SpringUtils.getProperty(jcc.getEnv(), "sql.threads");
		JdbcContext ctx = getBaseJdbcContext(jcc);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		return ctx;
	}

	public static JdbcContext getSequentialJdbcContext(ResetConfigContext jcc) {
		JdbcContext ctx = getBaseJdbcContext(jcc);
		ctx.setMultithreaded(false);
		ctx.setThreads(1);
		return ctx;
	}

	/**
	 * <pre>
	 *   sql.dba.concurrent
	 *   sql.dba.sequential
	 *   sql.schema.concurrent
	 *   sql.schema.sequential
	 *   sql.constraints.concurrent
	 *   sql.constraints.sequential
	 *   sql.other.concurrent
	 *   sql.other.sequential
	 * </pre>
	 */
	public static String getPropertyPrefix(ResetConfigContext jcc) {
		String mode = jcc.getMode().name().toLowerCase();

		StringBuilder sb = new StringBuilder();
		sb.append("sql");
		sb.append(".");
		sb.append(jcc.getType());
		sb.append(".");
		sb.append(mode);
		return sb.toString();
	}

	public static LogSqlListener getLogSqlListener(Environment env) {
		String level = SpringUtils.getProperty(env, "sql.log.level", LogSqlListener.DEFAULT_LOGGER_LEVEL.name());
		String mode = SpringUtils.getProperty(env, "sql.log.mode", LogSqlListener.DEFAULT_MODE.name());

		LogSqlListener lsl = new LogSqlListener();
		lsl.setLevel(LoggerLevel.valueOf(level));
		lsl.setMode(LogSqlMode.valueOf(mode));
		return lsl;
	}

	public static SqlListener getOtherListener() {

		NotifyingListener nl = getSummaryAndProgressListener();
		nl.getListeners().add(new LogSqlListener(LoggerLevel.INFO, LogSqlMode.AFTER));
		return nl;
	}

	protected static JdbcContext getBaseJdbcContext(ResetConfigContext jcc) {
		// dba, schema, data, constraints, other
		String type = jcc.getType();
		// sql.dba.concurrent, sql.dba.sequential, sql.schema.concurrent, sql.schema.sequential, etc
		String propertyPrefix = getPropertyPrefix(jcc);
		String message = SpringUtils.getProperty(jcc.getEnv(), propertyPrefix + ".message");
		boolean skip = SpringUtils.getBoolean(jcc.getEnv(), "sql." + type + ".skip", false);
		boolean trackProgressByUpdateCount = SpringUtils.getBoolean(jcc.getEnv(), propertyPrefix + ".trackProgressByUpdateCount", false);
		List<SqlSupplier> suppliers = jcc.getCommonConfig().getSqlSuppliers(propertyPrefix);
		DataSource dataSource = jcc.getDataSourceConfig().jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(skip);
		ctx.setDataSource(dataSource);
		ctx.setTrackProgressByUpdateCount(trackProgressByUpdateCount);
		ctx.setSuppliers(suppliers);
		return ctx;
	}

	public static NotifyingListener getSummaryAndProgressListener() {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener());
		list.add(new ProgressListener());
		return new NotifyingListener(list);
	}

}
