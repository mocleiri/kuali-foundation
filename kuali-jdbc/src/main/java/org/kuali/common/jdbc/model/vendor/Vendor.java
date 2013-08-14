package org.kuali.common.jdbc.model.vendor;

import java.sql.Driver;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public final class Vendor {

	public Vendor(Vendor name, ConnectionContext dba, String url, Driver driver) {
		Assert.noNulls(name, dba, driver);
		Assert.noBlanks(url);
		this.name = name;
		this.dba = dba;
		this.url = url;
		this.driver = driver;
	}

	private final Vendor name;
	private final ConnectionContext dba;
	private final String url;
	private final Driver driver;

	public Vendor getName() {
		return name;
	}

	public ConnectionContext getDba() {
		return dba;
	}

	public String getUrl() {
		return url;
	}

	public Driver getDriver() {
		return driver;
	}

}
