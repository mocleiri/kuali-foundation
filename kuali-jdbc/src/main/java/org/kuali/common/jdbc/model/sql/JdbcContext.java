package org.kuali.common.jdbc.model.sql;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public final class JdbcContext {

	public JdbcContext(ConnectionContext normal, ConnectionContext dba) {
		Assert.noNulls(normal, dba);
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
