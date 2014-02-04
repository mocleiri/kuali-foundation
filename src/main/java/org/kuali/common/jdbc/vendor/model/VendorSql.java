package org.kuali.common.jdbc.vendor.model;

import java.util.Properties;

import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;

public final class VendorSql {

	public VendorSql(AdminSql admin, DbaSql dba, Properties all) {
		Assert.noNulls(admin, dba, all);
		this.admin = admin;
		this.dba = dba;
		this.all = PropertyUtils.toImmutable(all);
	}

	private final AdminSql admin;
	private final DbaSql dba;
	private final Properties all;

	public AdminSql getAdmin() {
		return admin;
	}

	public DbaSql getDba() {
		return dba;
	}

	public Properties getAll() {
		return all;
	}

}
