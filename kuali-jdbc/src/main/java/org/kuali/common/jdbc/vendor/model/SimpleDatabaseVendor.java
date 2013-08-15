package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public final class SimpleDatabaseVendor {

	public SimpleDatabaseVendor(String name, ConnectionContext dba, String url, String driver) {
		Assert.noNulls(dba);
		Assert.noBlanks(name, url, driver);
		this.name = name;
		this.dba = dba;
		this.url = url;
		this.driver = driver;
	}

	private final String name;
	private final ConnectionContext dba;
	private final String url;
	private final String driver;

	public String getName() {
		return name;
	}

	public ConnectionContext getDba() {
		return dba;
	}

	public String getUrl() {
		return url;
	}

	public String getDriver() {
		return driver;
	}

}
