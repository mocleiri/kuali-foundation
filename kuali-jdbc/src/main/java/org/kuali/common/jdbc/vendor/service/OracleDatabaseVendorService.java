package org.kuali.common.jdbc.vendor.service;

import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.util.spring.env.EnvironmentService;

public class OracleDatabaseVendorService extends DefaultDatabaseVendorService {

	public OracleDatabaseVendorService(EnvironmentService env) {
		super(env);
	}

	@Override
	protected String getUrl(VendorBase base) {
		EnvironmentService env = getEnv();
		String prefix = base.getVendor().getCode();
		String key = prefix + ".url";
		return env.getString(key, base.getDba().getUrl());
	}

}
