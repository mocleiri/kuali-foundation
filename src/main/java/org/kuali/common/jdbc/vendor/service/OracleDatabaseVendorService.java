package org.kuali.common.jdbc.vendor.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.vendor.model.VendorDefault;
import org.kuali.common.jdbc.vendor.model.keys.Admin;
import org.kuali.common.jdbc.vendor.model.keys.Liquibase;
import org.kuali.common.jdbc.vendor.model.keys.Oracle;
import org.kuali.common.util.Assert;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.env.model.EnvironmentKeySuffix;

public class OracleDatabaseVendorService extends DefaultDatabaseVendorService {

	public OracleDatabaseVendorService(EnvironmentService env, VendorDefault vendor) {
		super(env, vendor);
	}

	@Override
	protected String getDbaAfter(String key, AdminSql adminSql, Properties sql) {
		String defaultValueKey = getVendorDefault().getCode() + "." + Oracle.SCHEMA_STATS.getValue();
		String defaultValue = sql.getProperty(defaultValueKey);
		Assert.noBlanks(defaultValue);
		return getEnv().getString(key, defaultValue);
	}

	@Override
	protected List<EnvironmentKeySuffix> getSqlKeySuffixes() {
		List<EnvironmentKeySuffix> keys = new ArrayList<EnvironmentKeySuffix>();
		keys.addAll(Admin.asList());
		keys.addAll(Liquibase.asList());
		keys.addAll(Oracle.asList());
		return Collections.unmodifiableList(keys);
	}

}
