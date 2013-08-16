package org.kuali.common.jdbc.vendor.service;

import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.util.spring.env.EnvironmentService;

public class MySqlDatabaseVendorService extends DefaultDatabaseVendorService {

	public MySqlDatabaseVendorService(EnvironmentService env) {
		super(env);
	}

	@Override
	protected String getUrl(VendorBase base) {
		return super.getUrl(base) + "/" + getEnv().getString("jdbc.username");
	}

}
