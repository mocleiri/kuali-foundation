package org.kuali.common.jdbc.vendor.service;

import java.sql.Driver;
import java.util.List;
import java.util.Properties;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.VendorDefault;
import org.kuali.common.jdbc.vendor.model.VendorSql;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.Basic;
import org.kuali.common.jdbc.vendor.model.keys.Dba;
import org.kuali.common.util.Assert;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.env.model.EnvironmentKeySuffix;

public class DefaultDatabaseVendorService implements DatabaseVendorService {

	public DefaultDatabaseVendorService(EnvironmentService env, VendorDefault vendorDefault) {
		Assert.noNulls(env, vendorDefault);
		this.env = env;
		this.vendorDefault = vendorDefault;
	}

	private final EnvironmentService env;
	private final VendorDefault vendorDefault;

	@Override
	public DatabaseVendor getDatabaseVendor() {
		ConnectionContext dba = getDba();
		Class<? extends Driver> driver = getDriver();
		Properties sql = getSql();
		AdminSql adminSql = getAdminSql(sql);
		DbaSql dbaSql = getDbaSql(adminSql, sql);
		VendorSql vendorSql = new VendorSql(adminSql, dbaSql, sql);
		String url = getUrl(dba);
		return new DatabaseVendor(vendorDefault, dba, url, driver, vendorSql);
	}

	protected String getUrl(ConnectionContext dba) {
		String key = vendorDefault.getCode() + "." + Basic.URL.getValue();
		String defaultValue = dba.getUrl();
		String actualValue = env.getString(key, defaultValue);
		return actualValue;
	}

	protected DbaSql getDbaSql(AdminSql adminSql, Properties sql) {
		String before = getDbaBefore(vendorDefault.getCode() + "." + Dba.BEFORE.getValue(), adminSql, sql);
		String after = getDbaAfter(vendorDefault.getCode() + "." + Dba.AFTER.getValue(), adminSql, sql);
		return new DbaSql(before, after);
	}

	protected String getDbaBefore(String key, AdminSql adminSql, Properties sql) {
		return env.getString(key, adminSql.getValidate() + adminSql.getDrop() + adminSql.getCreate());
	}

	protected String getDbaAfter(String key, AdminSql adminSql, Properties sql) {
		return env.getString(key, adminSql.getValidate());
	}

	protected AdminSql getAdminSql(Properties sql) {
		String validate = sql.getProperty(vendorDefault.getCode() + "." + Admin.VALIDATE.getValue());
		String create = sql.getProperty(vendorDefault.getCode() + "." + Admin.CREATE.getValue());
		String drop = sql.getProperty(vendorDefault.getCode() + "." + Admin.DROP.getValue());
		return new AdminSql(validate, create, drop);
	}

	protected String getSql(String key, Properties sql) {
		String defaultValue = sql.getProperty(key);
		return env.getString(key, defaultValue);
	}

	protected Properties getSql() {
		List<EnvironmentKeySuffix> suffixes = getSqlKeySuffixes();
		Properties properties = new Properties();
		for (EnvironmentKeySuffix suffix : suffixes) {
			String key = vendorDefault.getCode() + "." + suffix.getValue();
			String sql = env.getString(key);
			properties.setProperty(key, sql);
		}
		return properties;
	}

	protected Class<? extends Driver> getDriver() {
		String driver = env.getString(vendorDefault.getCode() + "." + Basic.DRIVER.getValue(), vendorDefault.getDriver());
		return ReflectionUtils.getTypedClass(driver);
	}

	protected ConnectionContext getDba() {
		String dbaUrl = env.getString(vendorDefault.getCode() + "." + Dba.URL.getValue(), vendorDefault.getDba().getUrl());
		String dbaUsr = env.getString(vendorDefault.getCode() + "." + Dba.USERNAME.getValue(), vendorDefault.getDba().getCredentials().getUsername());
		String dbaPwd = env.getString(vendorDefault.getCode() + "." + Dba.PASSWORD.getValue(), vendorDefault.getDba().getCredentials().getPassword());
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

	protected List<EnvironmentKeySuffix> getSqlKeySuffixes() {
		return Admin.asList();
	}

	public EnvironmentService getEnv() {
		return env;
	}

	public VendorDefault getVendorDefault() {
		return vendorDefault;
	}

}
