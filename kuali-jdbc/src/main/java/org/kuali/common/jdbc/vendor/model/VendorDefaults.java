package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public enum VendorDefaults {

	ORACLE(Vendor.ORACLE, "jdbc:oracle:thin:@localhost:1521:XE", "system", "manager"), //
	MYSQL(Vendor.MYSQL, "jdbc:mysql://localhost", "root", Credentials.NO_PASSWORD);

	private VendorDefaults(Vendor vendor, String defaultDbaUrl, String defaultDbaUsername, String defaultDbaPassword) {
		Assert.noNulls(vendor);
		this.vendor = vendor;
		this.dba = new ConnectionContext(defaultDbaUrl, defaultDbaUsername, defaultDbaPassword);
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
