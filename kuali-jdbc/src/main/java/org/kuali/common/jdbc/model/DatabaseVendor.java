package org.kuali.common.jdbc.model;

import java.sql.Driver;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.util.Assert;

public final class DatabaseVendor {

	public DatabaseVendor(Vendor name, ConnectionContext dba, String url, Class<? extends Driver> driver, AdminSql adminSql, String dbaAfter) {
		Assert.noNulls(name, dba, driver);
		Assert.noBlanks(url, dbaAfter);
		this.name = name;
		this.dba = dba;
		this.url = url;
		this.driver = driver;
		this.adminSql = adminSql;
		this.dbaAfter = dbaAfter;
	}

	private final Vendor name;
	private final ConnectionContext dba;
	private final String url;
	private final Class<? extends Driver> driver;
	private final AdminSql adminSql;
	private final String dbaAfter;

	public Vendor getName() {
		return name;
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

	public String getDbaAfter() {
		return dbaAfter;
	}

}
