package org.kuali.common.jdbc.vendor.service;

import java.util.Properties;

import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.jdbc.vendor.model.VendorBase;
import org.kuali.common.jdbc.vendor.model.keys.Oracle;
import org.kuali.common.util.spring.env.EnvironmentService;

public class OracleDatabaseVendorService extends DefaultDatabaseVendorService {

	public OracleDatabaseVendorService(EnvironmentService env, VendorBase base) {
		super(env, base);
	}

	@Override
	protected DbaSql getDbaSql(AdminSql adminSql, Properties sql) {
		String prefix = getBase().getVendor().getCode();
		String afterKey = prefix + ".dba.after";
		String defaultAfter = sql.getProperty(Oracle.SCHEMA_STATS.getValue());
		String after = getEnv().getString(afterKey, defaultAfter);
		DbaSql original = super.getDbaSql(adminSql, sql);
		return new DbaSql(original.getBefore(), after);
	}

}
