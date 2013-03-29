package org.kuali.common.jdbc.spring;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.spring.SpringUtils;

public class JdbcConfigUtils {

	public static JdbcContext getConcurrentJdbcContext(JdbcConfigContext jcc) {
		String threads = SpringUtils.getProperty(jcc.getEnv(), "sql.threads");

		JdbcContext ctx = getBaseJdbcContext(jcc);
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		return ctx;
	}

	public static JdbcContext getBaseJdbcContext(JdbcConfigContext jcc) {
		String skip = SpringUtils.getProperty(jcc.getEnv(), "sql." + jcc.getFragment() + ".skip", "false");
		DataSource dataSource = jcc.getDataSourceConfig().jdbcDataSource();
		String message = SpringUtils.getProperty(jcc.getEnv(), "sql." + jcc.getFragment() + ".concurrent.message");
		List<SqlSupplier> suppliers = jcc.getCommonConfig().getSqlSuppliers("sql." + jcc.getFragment() + ".concurrent");

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		return ctx;
	}

}
