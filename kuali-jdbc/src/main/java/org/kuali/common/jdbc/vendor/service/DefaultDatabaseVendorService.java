package org.kuali.common.jdbc.vendor.service;

import java.sql.Driver;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.jdbc.vendor.model.DatabaseVendor;
import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.VendorSql;
import org.kuali.common.jdbc.vendor.model.Vendors;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.KeySuffix;
import org.kuali.common.jdbc.vendor.model.keys.Liquibase;
import org.kuali.common.jdbc.vendor.model.keys.Oracle;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ReflectionUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

public class DefaultDatabaseVendorService implements DatabaseVendorService {

	public DefaultDatabaseVendorService(EnvironmentService env, VendorBase base) {
		Assert.noNulls(env, base);
		this.env = env;
		this.base = base;
	}

	private final EnvironmentService env;
	private final VendorBase base;

	protected String getUrl() {
		String prefix = base.getVendor().getCode();
		String key = prefix + ".url";
		return env.getString(key, base.getDba().getUrl());
	}

	@Override
	public DatabaseVendor getDatabaseVendor() {
		ConnectionContext dba = getDba(base);
		Class<? extends Driver> driver = getDriver();
		Properties sql = getSql();
		AdminSql adminSql = getAdminSql(sql);
		DbaSql dbaSql = getDbaSql(adminSql, sql);
		VendorSql vendorSql = new VendorSql(adminSql, dbaSql, sql);
		String url = getUrl();
		return new DatabaseVendor(base.getVendor(), dba, url, driver, vendorSql);
	}

	protected DbaSql getDbaSql(AdminSql adminSql, Properties sql) {
		String prefix = base.getVendor().getCode();
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
		String prefix = base.getVendor().getCode();
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
		Vendor vendor = base.getVendor();
		List<KeySuffix> suffixes = getVendorSqlKeysMap().get(vendor);
		Properties properties = new Properties();
		for (KeySuffix suffix : suffixes) {
			String key = vendor.getCode() + "." + suffix.getValue();
			String sql = env.getString(key);
			properties.setProperty(key, sql);
		}
		return properties;
	}

	protected Class<? extends Driver> getDriver() {
		String prefix = base.getVendor().getCode();
		String driver = env.getString(prefix + ".driver", base.getDriver());
		return ReflectionUtils.getTypedClass(driver);
	}

	protected ConnectionContext getDba(VendorBase base) {
		String prefix = base.getVendor().getCode();
		String dbaUrl = env.getString(prefix + ".dba.url", base.getDba().getUrl());
		String dbaUsr = env.getString(prefix + ".dba.username", base.getDba().getCredentials().getUsername());
		String dbaPwd = env.getString(prefix + ".dba.password", base.getDba().getCredentials().getPassword());
		return new ConnectionContext(dbaUrl, dbaUsr, dbaPwd);
	}

	protected Map<Vendor, VendorBase> getVendorBaseMap() {
		VendorBase oracle = new VendorBase(Vendor.ORACLE, Vendors.Oracle.DBA, Vendors.Oracle.DRIVER);
		VendorBase mysql = new VendorBase(Vendor.MYSQL, Vendors.MySql.DBA, Vendors.MySql.DRIVER);
		Map<Vendor, VendorBase> map = new HashMap<Vendor, VendorBase>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return Collections.unmodifiableMap(map);
	}

	protected Map<Vendor, List<KeySuffix>> getVendorSqlKeysMap() {
		List<KeySuffix> oracle = CollectionUtils.combine(Admin.asList(), Liquibase.asList(), Oracle.asList());
		List<KeySuffix> mysql = CollectionUtils.combine(Admin.asList(), Liquibase.asList());
		Map<Vendor, List<KeySuffix>> map = new HashMap<Vendor, List<KeySuffix>>();
		map.put(Vendor.ORACLE, Collections.unmodifiableList(oracle));
		map.put(Vendor.MYSQL, Collections.unmodifiableList(mysql));
		return Collections.unmodifiableMap(map);
	}

	public EnvironmentService getEnv() {
		return env;
	}

	public VendorBase getBase() {
		return base;
	}

}
