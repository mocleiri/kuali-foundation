package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.nullify.NullUtils;

public enum Vendor {

	ORACLE(VendorConstants.ORACLE_CODE, "system", "manager", "jdbc:oracle:thin:@localhost:1521:XE"), //
	MYSQL(VendorConstants.MYSQL_CODE, "root", NullUtils.NONE, "jdbc:mysql://localhost");

	private Vendor(String code, String username, String password, String url) {
		this.code = code;
		this.dba = new ConnectionContext(url, username, password);
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
