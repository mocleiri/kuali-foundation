package org.kuali.common.jdbc.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

public final class Credentials {

	public static final Credentials EMPTY = new Credentials();
	public static final String NO_USERNAME = NullUtils.NONE;
	public static final String NO_PASSWORD = NullUtils.NONE;

	public Credentials() {
		this(NO_USERNAME, NO_PASSWORD);
	}

	public Credentials(String username) {
		this(username, NO_PASSWORD);
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
