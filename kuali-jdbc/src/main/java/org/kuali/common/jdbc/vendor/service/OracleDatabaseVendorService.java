package org.kuali.common.jdbc.vendor.service;

import java.util.Properties;

import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.keys.Oracle;
import org.kuali.common.util.spring.env.EnvironmentService;

public class OracleDatabaseVendorService extends DefaultDatabaseVendorService {

	public OracleDatabaseVendorService(EnvironmentService env, VendorBase base) {
		super(env, base);
	}

	@Override
	protected String getDbaAfter(String key, AdminSql adminSql, Properties sql) {
		String prefix = getBase().getVendor().getCode();
		String defaultAfter = sql.getProperty(prefix + "." + Oracle.SCHEMA_STATS.getValue());
		return getEnv().getString(key, defaultAfter);
	}

}
