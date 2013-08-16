package org.kuali.common.jdbc.vendor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.jdbc.vendor.model.Vendor;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.KeySuffix;
import org.kuali.common.jdbc.vendor.model.keys.Liquibase;
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

	@Override
	protected List<KeySuffix> getSqlKeys() {
		List<KeySuffix> keys = new ArrayList<KeySuffix>();
		keys.addAll(Admin.asList());
		keys.addAll(Liquibase.asList());
		return Collections.unmodifiableList(keys);
	}

}
