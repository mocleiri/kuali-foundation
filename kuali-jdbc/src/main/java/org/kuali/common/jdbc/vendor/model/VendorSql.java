package org.kuali.common.jdbc.vendor.model;

import java.util.Properties;

import org.kuali.common.jdbc.sql.model.AdminSql;
import org.kuali.common.jdbc.sql.model.DbaSql;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PropertyUtils;

public final class VendorSql {

	public VendorSql(AdminSql admin, DbaSql dba, Properties sql) {
		Assert.noNulls(admin, dba, sql);
		this.admin = admin;
		this.dba = dba;
		this.sql = PropertyUtils.toImmutable(sql);
	}

	private final AdminSql admin;
	private final DbaSql dba;
	private final Properties sql;

	public AdminSql getAdmin() {
		return admin;
	}

	public DbaSql getDba() {
		return dba;
	}

	public Properties getSql() {
		return sql;
	}

}
