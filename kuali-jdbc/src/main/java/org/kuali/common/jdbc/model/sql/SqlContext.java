package org.kuali.common.jdbc.model.sql;

import org.kuali.common.util.Assert;

public final class SqlContext {

	public static final int DEFAULT_THREADS = 5;

	public SqlContext(Dba dba, Admin admin, String username, String password, String schema, String encoding) {
		this(dba, admin, username, password, schema, encoding, DEFAULT_THREADS);
	}

	public SqlContext(Dba dba, Admin admin, String username, String password, String schema, String encoding, int threads) {
		Assert.noNulls(dba, admin);
		Assert.noBlanks(username, password, schema, encoding);
		Assert.isTrue(threads > 0, "threads must be a positive integer");
		this.dba = dba;
		this.admin = admin;
		this.username = username;
		this.password = password;
		this.schema = schema;
		this.encoding = encoding;
		this.threads = threads;
	}

	private final Dba dba;
	private final Admin admin;
	private final String username;
	private final String password;
	private final String schema;
	private final String encoding;
	private final int threads;

	public Dba getDba() {
		return dba;
	}

	public Admin getAdmin() {
		return admin;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSchema() {
		return schema;
	}

	public String getEncoding() {
		return encoding;
	}

	public int getThreads() {
		return threads;
	}

}
