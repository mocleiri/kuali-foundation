package org.kuali.common.jdbc.service.spring;

import java.sql.Driver;

import org.kuali.common.jdbc.model.VendorName;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.model.vendor.Vendor;
import org.kuali.common.jdbc.model.vendor.Vendors;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class JdbcVendorConfig {

	@Autowired
	EnvironmentService env;

	@Bean
	public Vendor databaseVendorOracle() {
		VendorName name = VendorName.ORACLE;
		String dbaUsr = env.getString("oracle.dba.username", Vendors.Oracle.Dba.USERNAME);
		String dbaPwd = env.getString("oracle.dba.password", Vendors.Oracle.Dba.PASSWORD);
		String dbaUrl = env.getString("oracle.dba.url", Vendors.Oracle.Dba.URL);
		ConnectionContext dba = new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
		Class<? extends Driver> driver = Vendors.Oracle.DRIVER;
		String url = env.getString("oracle.url", Vendors.Oracle.URL);
		return new Vendor(name, dba, url, driver);
	}

	@Bean
	public Vendor databaseVendorMySql() {
		VendorName name = VendorName.MYSQL;
		String dbaUsr = env.getString("mysql.dba.username", Vendors.MySql.Dba.USERNAME);
		String dbaPwd = env.getString("mysql.dba.password", Vendors.MySql.Dba.PASSWORD);
		String dbaUrl = env.getString("mysql.dba.url", Vendors.MySql.Dba.URL);
		ConnectionContext dba = new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
		Class<? extends Driver> driver = com.mysql.jdbc.Driver.class;
		String url = env.getString("mysql.url", Vendors.MySql.URL);
		return new Vendor(name, dba, url, driver);
	}

}
