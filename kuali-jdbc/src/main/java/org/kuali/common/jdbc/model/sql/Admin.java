package org.kuali.common.jdbc.model.sql;

import org.kuali.common.util.Assert;

public final class Admin {

	public Admin(String validate, String create, String drop, Dba dba) {
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
	private final Dba dba;

	public String getValidate() {
		return validate;
	}

	public String getCreate() {
		return create;
	}

	public String getDrop() {
		return drop;
	}

	public Dba getDba() {
		return dba;
	}

}
