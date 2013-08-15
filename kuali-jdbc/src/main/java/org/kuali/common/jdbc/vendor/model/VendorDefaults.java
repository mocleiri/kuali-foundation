package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public enum VendorDefaults {

	ORACLE(Vendor.ORACLE, new ConnectionContext("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager"), "oracle.jdbc.driver.OracleDriver"), //
	MYSQL(Vendor.MYSQL, new ConnectionContext("jdbc:mysql://localhost", "root"), "com.mysql.jdbc.Driver");

	private VendorDefaults(Vendor vendor, ConnectionContext dba, String driver) {
		Assert.noNulls(vendor, dba);
		Assert.noBlanks(driver);
		this.vendor = vendor;
		this.dba = dba;
		this.driver = driver;
	}

	private final Vendor vendor;
	private final ConnectionContext dba;
	private final String driver;

	public Vendor getVendor() {
		return vendor;
	}

	public ConnectionContext getDba() {
		return dba;
	}

	public String getDriver() {
		return driver;
	}

}
