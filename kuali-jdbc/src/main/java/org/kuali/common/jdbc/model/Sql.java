package org.kuali.common.jdbc.model;

import org.kuali.common.util.Assert;

public final class Sql {

	public Sql(String validate, String create, String drop, DbaSql dba) {
		Assert.notNull(dba);
		Assert.noBlanks(validate, create, drop);
		this.validate = validate;
		this.create = create;
		this.drop = drop;
		this.dba = dba;
	}

	private final String validate;
	private final String create;
	private final String drop;
	private final DbaSql dba;

	public String getValidate() {
		return validate;
	}

	public String getCreate() {
		return create;
	}

	public String getDrop() {
		return drop;
	}

	public DbaSql getDba() {
		return dba;
	}

}
