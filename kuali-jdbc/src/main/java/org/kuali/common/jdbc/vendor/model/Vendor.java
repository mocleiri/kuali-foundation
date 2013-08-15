package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public enum Vendor {

	ORACLE(VendorConstants.ORACLE_CODE, "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager"), //
	MYSQL(VendorConstants.MYSQL_CODE, "jdbc:mysql://localhost", "root", Credentials.NO_PASSWORD);

	private Vendor(String code, String defaultDbaUrl, String defaultDbaUsername, String defaultDbaPassword) {
		Assert.noBlanks(code);
		this.code = code;
		this.dba = new ConnectionContext(defaultDbaUrl, defaultDbaUsername, defaultDbaPassword);
	}

	private final String code;
	private final ConnectionContext dba;

	public String getCode() {
		return code;
	}

	public ConnectionContext getDba() {
		return dba;
	}

}
