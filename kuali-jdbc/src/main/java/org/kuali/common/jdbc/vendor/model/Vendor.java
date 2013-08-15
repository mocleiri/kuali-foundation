package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;

public enum Vendor {

	ORACLE(VendorConstants.ORACLE_CODE, "system", "manager", "jdbc:oracle:thin:@localhost:1521:XE"), //
	MYSQL(VendorConstants.MYSQL_CODE, "root", Credentials.NO_PASSWORD, "jdbc:mysql://localhost");

	private Vendor(String code, String defaultDbaUsername, String defaultDbaPassword, String url) {
		this.code = code;
		this.dba = new ConnectionContext(url, defaultDbaUsername, defaultDbaPassword);
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
