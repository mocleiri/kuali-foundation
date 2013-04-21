package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import javax.sql.DataSource;

import org.kuali.common.jdbc.JdbcExecutable;
import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.listener.LogSqlListener;
import org.kuali.common.jdbc.listener.LogSqlMode;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.supplier.ComplexStringSupplier;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class OracleSchemaStats {

	@Autowired
	Environment env;

	@Bean(initMethod = "execute")
	public JdbcExecutable jdbcExecutable() {
		DataSource dataSource = getDataSource();
		String sql = SpringUtils.getProperty(env, "oracle.schemaStats");
		SqlSupplier supplier = new ComplexStringSupplier(sql);
		SqlListener listener = new LogSqlListener(LoggerLevel.INFO, LogSqlMode.BEFORE);

		JdbcContext context = new JdbcContext();
		context.setDataSource(dataSource);
		context.setSuppliers(Arrays.asList(supplier));
		context.setListener(listener);

		return new JdbcExecutable(context);
	}

	protected DataSource getDataSource() {
		DriverManagerDataSource dmds = new DriverManagerDataSource();
		dmds.setUrl(SpringUtils.getProperty(env, "jdbc.dba.url"));
		dmds.setDriverClassName(SpringUtils.getProperty(env, "jdbc.driver"));
		dmds.setUsername(SpringUtils.getProperty(env, "jdbc.dba.username"));
		dmds.setPassword(SpringUtils.getProperty(env, "jdbc.dba.password"));
		return dmds;
	}

}
