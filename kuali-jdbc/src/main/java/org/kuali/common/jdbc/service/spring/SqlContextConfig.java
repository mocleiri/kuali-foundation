package org.kuali.common.jdbc.service.spring;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.Connections;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.jdbc.sql.model.SqlContext;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class, DatabaseVendorsConfig.class, JdbcContextConfig.class })
public class SqlContextConfig {

	@Autowired
	DatabaseVendor vendor;

	@Autowired
	Connections connections;

	@Autowired
	EnvironmentService env;

	@Bean
	public SqlContext sqlContext() {
		Credentials auth = connections.getNormal().getCredentials();

		int threads = env.getInteger("sql.threads", SqlContext.DEFAULT_THREADS);
		String encoding = env.getString("sql.encoding"); // No default value. Force them to explicitly supply this.
		String schema = env.getString("sql.schema", auth.getUsername());
		String username = env.getString("sql.username", auth.getUsername());
		String password = env.getString("sql.password", auth.getPassword());
		String validate = env.getString("sql.validate", vendor.getAdminSql().getValidate());
		String create = env.getString("sql.create", vendor.getAdminSql().getCreate());
		String drop = env.getString("sql.drop", vendor.getAdminSql().getDrop());
		String dbaBefore = env.getString("sql.dba.before", validate + create + drop);
		String dbaAfter = env.getString("sql.dba.after", vendor.getDbaAfter());

		DbaSql dba = new DbaSql(dbaBefore, dbaAfter);
		AdminSql admin = new AdminSql(validate, create, drop);
		Credentials creds = new Credentials(username, password);

		return new SqlContext(creds, dba, admin, schema, encoding, threads);
	}
}
