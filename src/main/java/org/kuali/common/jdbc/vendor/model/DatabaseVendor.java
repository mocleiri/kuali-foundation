package org.kuali.common.jdbc.vendor.model;

import java.sql.Driver;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public final class DatabaseVendor {

	public DatabaseVendor(VendorDefault vendor, ConnectionContext dba, String url, Class<? extends Driver> driver, VendorSql sql) {
		Assert.noNulls(vendor, dba, driver, sql);
		Assert.noBlanks(url);
		this.dba = dba;
		this.url = url;
		this.code = vendor.getCode();
		this.driver = driver;
		this.sql = sql;
	}

	private final String code;
	private final ConnectionContext dba;
	private final String url;
	private final Class<? extends Driver> driver;
	private final VendorSql sql;

	public String getCode() {
		return code;
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

	public VendorSql getSql() {
		return sql;
	}

}
