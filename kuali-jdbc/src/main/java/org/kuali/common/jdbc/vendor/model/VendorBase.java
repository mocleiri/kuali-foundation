package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public final class VendorBase {

	public VendorBase(Vendor vendor, ConnectionContext dba, String driver) {
		Assert.noNulls(dba, vendor);
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
