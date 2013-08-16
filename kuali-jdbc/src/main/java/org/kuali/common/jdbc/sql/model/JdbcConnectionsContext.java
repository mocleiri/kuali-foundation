package org.kuali.common.jdbc.sql.model;

import java.sql.Driver;

import org.kuali.common.util.Assert;

public class JdbcConnectionsContext {

	public JdbcConnectionsContext(Class<? extends Driver> driver, JdbcConnections connections) {
		Assert.noNulls(driver, connections);
		this.driver = driver;
		this.connections = connections;
	}

	private final Class<? extends Driver> driver;
	private final JdbcConnections connections;

	public Class<? extends Driver> getDriver() {
		return driver;
	}

	public JdbcConnections getConnections() {
		return connections;
	}

}
