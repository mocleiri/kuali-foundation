package org.kuali.common.jdbc.vendor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.Liquibase;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.env.model.EnvironmentKeySuffix;

public class MySqlDatabaseVendorService extends DefaultDatabaseVendorService {

	private static final String USERNAME_KEY = "jdbc.username";

	public MySqlDatabaseVendorService(EnvironmentService env, Vendor vendor) {
		super(env, vendor);
	}

	@Override
	protected String getUrl() {
		return super.getUrl() + "/" + getEnv().getString(USERNAME_KEY);
	}

	@Override
	protected List<EnvironmentKeySuffix> getSqlKeySuffixes() {
		List<EnvironmentKeySuffix> keys = new ArrayList<EnvironmentKeySuffix>();
		keys.addAll(Admin.asList());
		keys.addAll(Liquibase.asList());
		return Collections.unmodifiableList(keys);
	}

}
