package org.kuali.common.jdbc.model.sql;

import org.kuali.common.util.Assert;

public final class SqlContext {

	public static final int DEFAULT_THREADS = 5;
	public static final String DEFAULT_ENCODING = "UTF-8";

	public SqlContext(DbaSql dba, AdminSql admin, Credentials credentials, String schema) {
		this(dba, admin, credentials, schema, DEFAULT_ENCODING, DEFAULT_THREADS);
	}

	public SqlContext(DbaSql dba, AdminSql admin, Credentials credentials, String schema, String encoding) {
		this(dba, admin, credentials, schema, encoding, DEFAULT_THREADS);
	}

	public SqlContext(DbaSql dba, AdminSql admin, Credentials credentials, String schema, String encoding, int threads) {
		Assert.noNulls(dba, admin, credentials);
		Assert.noBlanks(schema, encoding);
		Assert.isTrue(threads > 0, "threads must be a positive integer");
		this.dba = dba;
		this.admin = admin;
		this.credentials = credentials;
		this.schema = schema;
		this.encoding = encoding;
		this.threads = threads;
	}

	private final DbaSql dba;
	private final AdminSql admin;
	private final Credentials credentials;
	private final String schema;
	private final String encoding;
	private final int threads;

	public DbaSql getDba() {
		return dba;
	}

	public AdminSql getAdmin() {
		return admin;
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

	public Credentials getCredentials() {
		return credentials;
	}

}
