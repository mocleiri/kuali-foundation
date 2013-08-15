package org.kuali.common.jdbc.sql.model;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.util.Assert;

public final class SqlContext {

	public static final int DEFAULT_THREADS = 5;

	public SqlContext(DbaSql dba, AdminSql admin, Credentials credentials, String schema, String encoding, int threads) {
		Assert.noNulls(dba, credentials, admin);
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

	public AdminSql getAdmin() {
		return admin;
	}

}
