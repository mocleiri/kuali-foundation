package org.kuali.common.jdbc.spring;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.core.env.Environment;

public class JdbcConfigUtils {

	public static JdbcContext getConcurrentJdbcContext(Environment env, String fragment, JdbcCommonConfig jcc, JdbcDataSourceConfig jdsc) {
		String skip = SpringUtils.getProperty(env, "sql." + fragment + ".skip", "false");
		String threads = SpringUtils.getProperty(env, "sql.threads");
		String message = SpringUtils.getProperty(env, "sql." + fragment + ".concurrent.message");
		List<SqlSupplier> suppliers = jcc.getSqlSuppliers("sql." + fragment + ".concurrent");
		DataSource dataSource = jdsc.jdbcDataSource();

		JdbcContext ctx = new JdbcContext();
		ctx.setMultithreaded(true);
		ctx.setThreads(new Integer(threads));
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		return ctx;
	}

}
