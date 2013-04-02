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

	public static DataSummaryListener getConcurrentDataSummaryListener(ResetConfigContext rcc) {
		String propertyPrefix = getPropertyPrefix(rcc);
		String label = SpringUtils.getProperty(rcc.getEnv(), propertyPrefix + ".progress.label", "Rows");
		String throughputLabel = SpringUtils.getProperty(rcc.getEnv(), propertyPrefix + ".progress.label.throughput", "rows/s");
		DataSummaryListener dsl = new DataSummaryListener();
		dsl.setLabel(label);
		dsl.setThroughputLabel(throughputLabel);
		return dsl;
	}

	public static JdbcContext getConcurrentJdbcContext(ResetConfigContext rcc) {
		String threads = SpringUtils.getProperty(rcc.getEnv(), "sql.threads");
		JdbcContext ctx = getBaseJdbcContext(rcc);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		return ctx;
	}

	public static JdbcContext getSequentialJdbcContext(ResetConfigContext rcc) {
		JdbcContext ctx = getBaseJdbcContext(rcc);
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
	public static String getPropertyPrefix(ResetConfigContext rcc) {
		String mode = rcc.getMode().name().toLowerCase();

		StringBuilder sb = new StringBuilder();
		sb.append("sql");
		sb.append(".");
		sb.append(rcc.getType());
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

	public static NotifyingListener getOtherListener(Environment env) {
		NotifyingListener nl = getSummaryAndProgressListener();
		nl.getListeners().add(getLogSqlListener(env));
		return nl;
	}

	protected static JdbcContext getBaseJdbcContext(ResetConfigContext rcc) {
		// dba, schema, data, constraints, other
		String type = rcc.getType();
		// sql.dba.concurrent, sql.dba.sequential, sql.schema.concurrent, sql.schema.sequential, etc
		String propertyPrefix = getPropertyPrefix(rcc);
		String message = SpringUtils.getProperty(rcc.getEnv(), propertyPrefix + ".message");
		boolean skip = SpringUtils.getBoolean(rcc.getEnv(), "sql." + type + ".skip", false);
		boolean trackProgressByUpdateCount = SpringUtils.getBoolean(rcc.getEnv(), propertyPrefix + ".trackProgressByUpdateCount", false);
		List<SqlSupplier> suppliers = rcc.getCommonConfig().getSqlSuppliers(propertyPrefix);
		DataSource dataSource = rcc.getDataSourceConfig().jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(skip);
		ctx.setDataSource(dataSource);
		ctx.setTrackProgressByUpdateCount(trackProgressByUpdateCount);
		ctx.setSuppliers(suppliers);
		return ctx;
	}

	public static NotifyingListener getSchemaListener(Environment env) {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener(false));
		list.add(getLogSqlListener(env));
		return new NotifyingListener(list);
	}

	public static NotifyingListener getSummaryAndProgressListener() {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener());
		list.add(new ProgressListener());
		return new NotifyingListener(list);
	}

}
