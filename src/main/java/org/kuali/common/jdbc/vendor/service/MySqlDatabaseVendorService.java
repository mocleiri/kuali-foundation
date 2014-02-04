package org.kuali.common.jdbc.vendor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.jdbc.vendor.model.VendorDefault;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.Liquibase;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.env.model.EnvironmentKeySuffix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlDatabaseVendorService extends DefaultDatabaseVendorService {

	private static final Logger logger = LoggerFactory.getLogger(MySqlDatabaseVendorService.class);

	private static final String USERNAME_KEY = "jdbc.username";

	public MySqlDatabaseVendorService(EnvironmentService env, VendorDefault vendor) {
		super(env, vendor);
	}

	@Override
	protected String getUrl(ConnectionContext dba) {
		String url = super.getUrl(dba) + "/" + getEnv().getString(USERNAME_KEY);
		logger.debug("mysql url: {}", url);
		return url;
	}

	@Override
	protected List<EnvironmentKeySuffix> getSqlKeySuffixes() {
		List<EnvironmentKeySuffix> keys = new ArrayList<EnvironmentKeySuffix>();
		keys.addAll(Admin.asList());
		keys.addAll(Liquibase.asList());
		return Collections.unmodifiableList(keys);
	}

}
