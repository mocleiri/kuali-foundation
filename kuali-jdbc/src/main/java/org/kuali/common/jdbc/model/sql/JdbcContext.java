package org.kuali.common.jdbc.model.sql;

import org.kuali.common.jdbc.model.context.ConnectionContext;

public final class JdbcContext {

	private JdbcContext(ConnectionContext normal, ConnectionContext dba) {
		this.normal = normal;
		this.dba = dba;
	}

	private final ConnectionContext normal;
	private final ConnectionContext dba;

	public ConnectionContext getNormal() {
		return normal;
	}

	public ConnectionContext getDba() {
		return dba;
	}

}
