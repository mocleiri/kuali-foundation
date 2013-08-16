package org.kuali.common.jdbc.vendor.model;

import java.sql.Driver;
import java.util.Properties;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;

public final class DatabaseVendor {

	public DatabaseVendor(Vendor vendor, ConnectionContext dba, String url, Class<? extends Driver> driver, AdminSql adminSql, DbaSql dbaSql, Properties sql) {
		Assert.noNulls(vendor, dba, driver, adminSql, dbaSql, sql);
		Assert.noBlanks(url);
		this.dba = dba;
		this.url = url;
		this.vendor = vendor;
		this.driver = driver;
		this.adminSql = adminSql;
		this.dbaSql = dbaSql;
		this.sql = PropertyUtils.toImmutable(sql);
	}

	private final Vendor vendor;
	private final ConnectionContext dba;
	private final String url;
	private final Class<? extends Driver> driver;
	private final Properties sql;
	private final AdminSql adminSql;
	private final DbaSql dbaSql;

	public Properties getSql() {
		return sql;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public ConnectionContext getDba() {
		return dba;
	}

	public String getUrl() {
		return url;
	}

	public Class<? extends Driver> getDriver() {
		return driver;
	}

	public AdminSql getAdminSql() {
		return adminSql;
	}

	public DbaSql getDbaSql() {
		return dbaSql;
	}

}
