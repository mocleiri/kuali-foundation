package org.kuali.common.jdbc.vendor.model;

import java.sql.Driver;
import java.util.Properties;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;

public final class DatabaseVendor {

	public DatabaseVendor(Vendor vendor, ConnectionContext dba, String url, Class<? extends Driver> driver, Properties sql) {
		Assert.noNulls(dba, sql, driver, vendor);
		Assert.noBlanks(url);
		this.dba = dba;
		this.url = url;
		this.vendor = vendor;
		this.driver = driver;
		this.sql = PropertyUtils.toImmutable(sql);
	}

	private final Vendor vendor;
	private final ConnectionContext dba;
	private final String url;
	private final Class<? extends Driver> driver;
	private final Properties sql;

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

}
