package org.kuali.common.jdbc.sql.spring;

import javax.sql.DataSource;

import org.kuali.common.jdbc.listeners.LogSqlListener;
import org.kuali.common.jdbc.listeners.SqlListener;
import org.kuali.common.jdbc.model.LogSqlMode;
import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.reader.spring.SqlReaderConfig;
import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.kuali.common.jdbc.sql.model.SqlContext;
import org.kuali.common.jdbc.suppliers.ComplexStringSupplier;
import org.kuali.common.jdbc.suppliers.SqlSupplier;
import org.kuali.common.util.log.LoggerLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DataSourceConfig.class, SqlContextConfig.class, SqlReaderConfig.class })
public class DbaContextConfig {

	private static final String BEFORE = "[dba:before]";
	private static final String AFTER = "[dba:after]";

	@Autowired
	SqlContext context;

	@Autowired
	SqlReader reader;

	@Autowired
	DataSourceConfig dataSourceConfig;

	public JdbcContext dbaBeforeContext() {
		return getContext(BEFORE, context.getDba().getBefore());
	}

	public JdbcContext dbaAfterContext() {
		return getContext(AFTER, context.getDba().getAfter());
	}

	protected JdbcContext getContext(String message, String sql) {
		DataSource dataSource = dataSourceConfig.dbaDataSource();
		SqlSupplier supplier = new ComplexStringSupplier(sql, reader);
		SqlListener listener = new LogSqlListener(LoggerLevel.INFO, LogSqlMode.BEFORE);
		return new JdbcContext.Builder(dataSource, supplier).message(message).listener(listener).build();
	}
}
