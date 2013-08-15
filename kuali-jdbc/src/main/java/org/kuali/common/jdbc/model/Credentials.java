package org.kuali.common.jdbc.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class Credentials {

	public Credentials() {
		this(NullUtils.NONE, NullUtils.NONE);
	}

	public Credentials(String username) {
		this(username, NullUtils.NONE);
	}

	public Credentials(String username, String password) {
		Assert.noBlanks(username, password);
		this.username = username;
		this.password = password;
	}

	private final String username;
	private final String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
