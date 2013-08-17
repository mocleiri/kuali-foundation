package org.kuali.common.jdbc.sql.spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.jdbc.model.context.JdbcContext;
import org.kuali.common.jdbc.model.context.SqlExecutionContext;
import org.kuali.common.jdbc.service.spring.DataSourceConfig;
import org.kuali.common.jdbc.sql.model.SqlContext;
import org.kuali.common.jdbc.supplier.ComplexStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DataSourceConfig.class, SqlContextConfig.class })
public class ResetExecConfig implements SqlExecConfig {

	@Autowired
	SqlContext sqlContext;

	@Autowired
	DataSourceConfig dataSourceConfig;

	@Override
	public List<SqlExecutionContext> sqlExecutionContexts() {
		SqlExecutionContext before = getContext("[dba:before]", sqlContext.getDba().getBefore());
		SqlExecutionContext after = getContext("[dba:after]", sqlContext.getDba().getBefore());
		return Collections.unmodifiableList(Arrays.asList(before, after));
	}

	protected SqlExecutionContext getContext(String message, String sql) {
		DataSource dataSource = dataSourceConfig.dbaDataSource();
		SqlSupplier supplier = new ComplexStringSupplier(sql);
		JdbcContext context = new JdbcContext(dataSource, supplier, message);
		return new SqlExecutionContext(message, context);
	}
}
