package org.kuali.common.jdbc.vendor.service;

import java.sql.Driver;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorSql;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.KeySuffix;
import org.kuali.common.util.Assert;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

public class DefaultDatabaseVendorService implements DatabaseVendorService {

	public DefaultDatabaseVendorService(EnvironmentService env, Vendor vendor) {
		Assert.noNulls(env, vendor);
		this.env = env;
		this.vendor = vendor;
	}

	private final EnvironmentService env;
	private final Vendor vendor;

	protected String getUrl() {
		String prefix = vendor.getCode();
		String key = prefix + ".url";
		return env.getString(key, vendor.getDba().getUrl());
	}

	@Override
	public DatabaseVendor getDatabaseVendor() {
		ConnectionContext dba = getDba(vendor);
		Class<? extends Driver> driver = getDriver();
		Properties sql = getSql();
		AdminSql adminSql = getAdminSql(sql);
		DbaSql dbaSql = getDbaSql(adminSql, sql);
		VendorSql vendorSql = new VendorSql(adminSql, dbaSql, sql);
		String url = getUrl();
		return new DatabaseVendor(vendor, dba, url, driver, vendorSql);
	}

	protected DbaSql getDbaSql(AdminSql adminSql, Properties sql) {
		String prefix = vendor.getCode();
		String before = getDbaBefore(prefix + ".dba.before", adminSql, sql);
		String after = getDbaAfter(prefix + ".dba.after", adminSql, sql);
		return new DbaSql(before, after);
	}

	protected String getDbaBefore(String key, AdminSql adminSql, Properties sql) {
		return env.getString(key, adminSql.getValidate() + adminSql.getDrop() + adminSql.getCreate());
	}

	protected String getDbaAfter(String key, AdminSql adminSql, Properties sql) {
		return env.getString(key, adminSql.getValidate());
	}

	protected AdminSql getAdminSql(Properties sql) {
		String prefix = vendor.getCode();
		String validate = sql.getProperty(prefix + "." + Admin.VALIDATE.getValue());
		String create = sql.getProperty(prefix + "." + Admin.CREATE.getValue());
		String drop = sql.getProperty(prefix + "." + Admin.DROP.getValue());
		return new AdminSql(validate, create, drop);
	}

	protected String getSql(String key, Properties sql) {
		String defaultValue = sql.getProperty(key);
		return env.getString(key, defaultValue);
	}

	protected Properties getSql() {
		List<KeySuffix> suffixes = getSqlKeys();
		Properties properties = new Properties();
		for (KeySuffix suffix : suffixes) {
			String key = vendor.getCode() + "." + suffix.getValue();
			String sql = env.getString(key);
			properties.setProperty(key, sql);
		}
		return properties;
	}

	protected Class<? extends Driver> getDriver() {
		String prefix = vendor.getCode();
		String driver = env.getString(prefix + ".driver", vendor.getDriver());
		return ReflectionUtils.getTypedClass(driver);
	}

	protected ConnectionContext getDba(Vendor vendor) {
		String prefix = vendor.getCode();
		String dbaUrl = env.getString(prefix + ".dba.url", vendor.getDba().getUrl());
		String dbaUsr = env.getString(prefix + ".dba.username", vendor.getDba().getCredentials().getUsername());
		String dbaPwd = env.getString(prefix + ".dba.password", vendor.getDba().getCredentials().getPassword());
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

	protected List<KeySuffix> getSqlKeys() {
		return Collections.unmodifiableList(Admin.asList());
	}

	public EnvironmentService getEnv() {
		return env;
	}

	public Vendor getVendor() {
		return vendor;
	}

}
