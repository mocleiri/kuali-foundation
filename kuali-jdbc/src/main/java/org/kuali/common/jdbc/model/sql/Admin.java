package org.kuali.common.jdbc.model.sql;

import org.kuali.common.util.Assert;

public final class Admin {

	public Admin(String validate, String create, String drop) {
		Assert.noBlanks(validate, create, drop);
		this.validate = validate;
		this.create = create;
		this.drop = drop;
	}

	private final String validate;
	private final String create;
	private final String drop;

	public String getValidate() {
		return validate;
	}

	public String getCreate() {
		return create;
	}

	public String getDrop() {
		return drop;
	}

}
