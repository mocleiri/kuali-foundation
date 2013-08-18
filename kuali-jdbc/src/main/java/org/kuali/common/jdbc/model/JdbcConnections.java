package org.kuali.common.jdbc.model;

import java.sql.Driver;

import org.kuali.common.jdbc.model.context.ConnectionContext;
import org.kuali.common.util.Assert;

public final class JdbcConnections {

	public JdbcConnections(ConnectionContext normal, ConnectionContext dba, Class<? extends Driver> driver) {
		Assert.noNulls(normal, dba, driver);
		this.normal = normal;
		this.dba = dba;
		this.driver = driver;
	}

	private final Class<? extends Driver> driver;
	private final ConnectionContext normal;
	private final ConnectionContext dba;

	public ConnectionContext getNormal() {
		return normal;
	}

	public ConnectionContext getDba() {
		return dba;
	}

	public Class<? extends Driver> getDriver() {
		return driver;
	}

}
