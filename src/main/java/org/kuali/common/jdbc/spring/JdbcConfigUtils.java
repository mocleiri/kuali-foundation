package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.Environment;

public class JdbcConfigUtils {

	public static Boolean getBoolean(Environment env, String key, boolean defaultValue) {
		String value = SpringUtils.getProperty(env, key, defaultValue + "");
		return new Boolean(value);
	}

	public static DataSummaryListener getConcurrentDataSummaryListener(JdbcConfigContext jcc) {
		String propertyPrefix = getPropertyPrefix(jcc);
		String label = SpringUtils.getProperty(jcc.getEnv(), propertyPrefix + ".progress.label", "Rows");
		String throughputLabel = SpringUtils.getProperty(jcc.getEnv(), propertyPrefix + ".progress.label.throughput", "rows/s");
		DataSummaryListener dsl = new DataSummaryListener();
		dsl.setLabel(label);
		dsl.setThroughputLabel(throughputLabel);
		return dsl;
	}

	public static JdbcContext getConcurrentJdbcContext(JdbcConfigContext jcc) {
		String threads = SpringUtils.getProperty(jcc.getEnv(), "sql.threads");
		JdbcContext ctx = getBaseJdbcContext(jcc);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		return ctx;
	}

	public static JdbcContext getSequentialJdbcContext(JdbcConfigContext jcc) {
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
	public static String getPropertyPrefix(JdbcConfigContext jcc) {
		String mode = jcc.getMode().name().toLowerCase();

		StringBuilder sb = new StringBuilder();
		sb.append("sql");
		sb.append(".");
		sb.append(jcc.getType());
		sb.append(".");
		sb.append(mode);
		return sb.toString();
	}

	public static JdbcContext getBaseJdbcContext(JdbcConfigContext jcc) {
		// dba, schema, data, constraints, other
		String fragment = jcc.getType();
		String propertyPrefix = getPropertyPrefix(jcc);
		String message = SpringUtils.getProperty(jcc.getEnv(), propertyPrefix + ".message");
		String skip = SpringUtils.getProperty(jcc.getEnv(), "sql." + fragment + ".skip", "false");
		String trackProgressByUpdateCount = SpringUtils.getProperty(jcc.getEnv(), propertyPrefix + ".trackProgressByUpdateCount", "false");
		List<SqlSupplier> suppliers = jcc.getCommonConfig().getSqlSuppliers(propertyPrefix);
		DataSource dataSource = jcc.getDataSourceConfig().jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setTrackProgressByUpdateCount(new Boolean(trackProgressByUpdateCount));
		ctx.setSuppliers(suppliers);
		return ctx;
	}

	public static SqlListener getSummaryAndProgressListener() {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener());
		list.add(new ProgressListener());
		return new NotifyingListener(list);
	}

}
