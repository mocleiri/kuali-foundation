package org.kuali.common.jdbc.vendor.service;

import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.util.spring.env.EnvironmentService;

public class MySqlDatabaseVendorService extends DefaultDatabaseVendorService {

	private static final String USERNAME_KEY = "jdbc.username";

	public MySqlDatabaseVendorService(EnvironmentService env, Vendor vendor) {
		super(env, vendor);
	}

	@Override
	protected String getUrl() {
		return super.getUrl() + "/" + getEnv().getString(USERNAME_KEY);
	}

}
