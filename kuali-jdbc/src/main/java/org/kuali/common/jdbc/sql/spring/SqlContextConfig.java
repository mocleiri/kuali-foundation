package org.kuali.common.jdbc.sql.spring;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.JdbcConnections;
import org.kuali.common.jdbc.service.spring.JdbcConnectionsConfig;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.jdbc.sql.model.SqlContext;
import org.kuali.common.jdbc.sql.model.SqlKeys;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.spring.DatabaseVendorConfig;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DatabaseVendorConfig.class, JdbcConnectionsConfig.class })
public class SqlContextConfig {

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	JdbcConnections connections;

	@Autowired
	EnvironmentService env;

	@Bean
	public SqlContext sqlContext() {
		Credentials auth = connections.getNormal().getCredentials();

		int threads = env.getInteger(SqlKeys.THREADS.getValue(), SqlContext.DEFAULT_THREADS);
		String encoding = env.getString(SqlKeys.ENCODING.getValue()); // No default value, force them to supply this
		String schema = env.getString(SqlKeys.SCHEMA.getValue(), auth.getUsername());
		String username = env.getString(SqlKeys.USERNAME.getValue(), auth.getUsername());
		String password = env.getString(SqlKeys.PASSWORD.getValue(), auth.getPassword());
		String validate = env.getString(SqlKeys.VALIDATE.getValue(), vendor.getSql().getAdmin().getValidate());
		String create = env.getString(SqlKeys.CREATE.getValue(), vendor.getSql().getAdmin().getCreate());
		String drop = env.getString(SqlKeys.DROP.getValue(), vendor.getSql().getAdmin().getDrop());
		String dbaBefore = env.getString(SqlKeys.DBA_BEFORE.getValue(), vendor.getSql().getDba().getBefore());
		String dbaAfter = env.getString(SqlKeys.DBA_AFTER.getValue(), vendor.getSql().getDba().getAfter());

		DbaSql dba = new DbaSql(dbaBefore, dbaAfter);
		AdminSql admin = new AdminSql(validate, create, drop);
		Credentials creds = new Credentials(username, password);

		return new SqlContext(creds, dba, admin, schema, encoding, threads);
	}
}
