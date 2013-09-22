package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public enum VendorDefault {

	ORACLE(Vendors.Codes.ORACLE, Vendors.Oracle.DBA, Vendors.Oracle.DRIVER), //
	MYSQL(Vendors.Codes.MYSQL, Vendors.MySql.DBA, Vendors.MySql.DRIVER);

	private VendorDefault(String code, ConnectionContext dba, String driver) {
		Assert.noNulls(dba);
		Assert.noBlanks(code, driver);
		this.code = code;
		this.dba = dba;
		this.driver = driver;
	}

	private final String code;
	private final ConnectionContext dba;
	private final String driver;

	public String getCode() {
		return code;
	}

	public ConnectionContext getDba() {
		return dba;
	}

	public String getDriver() {
		return driver;
	}

}
