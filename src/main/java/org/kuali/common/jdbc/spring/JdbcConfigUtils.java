package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
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
		String trackProgressByUpdateCount = SpringUtils.getProperty(jcc.getEnv(), "sql." + jcc.getFragment() + ".concurrent.trackProgressByUpdateCount", "false");

		JdbcContext ctx = new JdbcContext();
		ctx.setMessage(message);
		ctx.setSkip(new Boolean(skip));
		ctx.setDataSource(dataSource);
		ctx.setSuppliers(suppliers);
		ctx.setTrackProgressByUpdateCount(new Boolean(trackProgressByUpdateCount));
		return ctx;
	}

	public static SqlListener getSummaryAndProgressListener() {
		List<SqlListener> list = new ArrayList<SqlListener>();
		list.add(new SummaryListener());
		list.add(new ProgressListener());
		return new NotifyingListener(list);
	}

}
