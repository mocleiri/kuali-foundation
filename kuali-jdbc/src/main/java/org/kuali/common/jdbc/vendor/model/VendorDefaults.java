package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public enum VendorDefaults {

	ORACLE(new ConnectionContext("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager"), "oracle.jdbc.driver.OracleDriver"), //
	MYSQL(new ConnectionContext("jdbc:mysql://localhost", "root"), "com.mysql.jdbc.Driver");

	private VendorDefaults(ConnectionContext dba, String driver) {
		Assert.noNulls(dba);
		Assert.noBlanks(driver);
		this.dba = dba;
		this.driver = driver;
	}

	private final ConnectionContext dba;
	private final String driver;

	public ConnectionContext getDba() {
		return dba;
	}

	public String getDriver() {
		return driver;
	}

}
