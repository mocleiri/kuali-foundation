package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public enum VendorDefaults {

	ORACLE(Vendor.ORACLE, new ConnectionContext("jdbc:oracle:thin:@localhost:1521:XE", "system", "manager"), ""), //
	MYSQL(Vendor.MYSQL, new ConnectionContext("jdbc:mysql://localhost", "root"), "");

	private VendorDefaults(Vendor vendor, ConnectionContext dba, String defaultDriver) {
		Assert.noNulls(vendor);
		this.vendor = vendor;
		this.dba = dba;
	}

	private final Vendor vendor;
	private final ConnectionContext dba;

	public Vendor getVendor() {
		return vendor;
	}

	public ConnectionContext getDba() {
		return dba;
	}

}
