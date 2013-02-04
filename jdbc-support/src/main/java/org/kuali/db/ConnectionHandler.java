/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.common.util.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);

	public static final String DRIVER_INFO_PROPERTIES_USER = "user";
	public static final String DRIVER_INFO_PROPERTIES_PASSWORD = "password";

	String url;
	boolean autocommit = true;
	boolean skipOnConnectionError = false;
	boolean connectionError = false;
	boolean enableAnonymousPassword = false;;
	boolean enableAnonymousUsername = false;;
	Credentials credentials;
	Properties driverProperties;
	String driver;
	boolean showPassword = false;

	protected void showConnectionInfo(Properties properties) {
		logger.info("------------------------------------------------------------------------");
		logger.info("JDBC Connection Information");
		logger.info("------------------------------------------------------------------------");
		logger.info("URL: " + getUrl());
		String username = properties.getProperty(DRIVER_INFO_PROPERTIES_USER);
		String password = properties.getProperty(DRIVER_INFO_PROPERTIES_PASSWORD);
		if (StringUtils.isEmpty(username)) {
			logger.info("Username: <no username was supplied>");
		} else {
			logger.info("Username: " + username);
		}
		if (isShowPassword()) {
			logger.info("Password: " + password);
		} else {
			logger.info("Password: {}", LoggerUtils.getPassword(username, password));
		}
		logger.info("Driver: " + getDriver());
		logger.info("------------------------------------------------------------------------");
	}

	protected Driver getDriverInstance() throws SQLException {
		try {
			Class<?> dc = Class.forName(getDriver());
			return (Driver) dc.newInstance();
		} catch (ClassNotFoundException e) {
			throw new SQLException("Driver class not found: " + getDriver(), e);
		} catch (Exception e) {
			throw new SQLException("Failure loading driver: " + getDriver(), e);
		}
	}

	protected Properties getInfo() throws SQLException {
		Properties info = new Properties();
		if (!enableAnonymousUsername) {
			info.put(DRIVER_INFO_PROPERTIES_USER, credentials.getUsername());
		}
		if (!enableAnonymousPassword) {
			info.put(DRIVER_INFO_PROPERTIES_PASSWORD, credentials.getPassword());
		}
		if (driverProperties != null) {
			info.putAll(getDriverProperties());
		}
		return info;
	}

	protected void validateConfiguration() throws SQLException {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		if (!enableAnonymousUsername && StringUtils.isBlank(username)) {
			throw new SQLException(
			        "\n\nNo username was supplied.\nYou can supply a username in the plugin configuration or provide it as a system property.\n\nFor example:\n-Dusername=myuser\n\n.");
		}
		if (!enableAnonymousPassword && StringUtils.isBlank(password)) {
			throw new SQLException(
			        "\n\nNo password was supplied.\nYou can supply a password in the plugin configuration or provide it as a system property.\n\nFor example:\n-Dpassword=mypassword\n\n.");
		}
		// Convert null to the empty string if needed
		if (StringUtils.isBlank(username)) {
			credentials.setUsername("");
		}
		if (StringUtils.isBlank(password)) {
			credentials.setPassword("");
		}
	}

	public Connection getConnection() throws SQLException {
		validateConfiguration();
		Properties info = getInfo();
		Connection conn = null;
		try {
			Driver driverInstance = getDriverInstance();
			showConnectionInfo(info);
			conn = driverInstance.connect(getUrl(), info);

			if (conn == null) {
				// Driver doesn't understand the URL
				throw new SQLException("No suitable Driver for " + getUrl());
			}

			conn.setAutoCommit(autocommit);
		} catch (SQLException e) {
			if (skipOnConnectionError) {
				// Error getting the connection but they have asked us not to throw an exception
				// Set our flag and return
				connectionError = true;
				return null;
			} else {
				// Otherwise, throw an exception
				throw new SQLException("Connection error: " + e.getMessage().toString(), e);
			}
		}
		return conn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isAutocommit() {
		return autocommit;
	}

	public void setAutocommit(boolean autocommit) {
		this.autocommit = autocommit;
	}

	public boolean isSkipOnConnectionError() {
		return skipOnConnectionError;
	}

	public void setSkipOnConnectionError(boolean skipOnConnectionError) {
		this.skipOnConnectionError = skipOnConnectionError;
	}

	public boolean isConnectionError() {
		return connectionError;
	}

	public void setConnectionError(boolean connectionError) {
		this.connectionError = connectionError;
	}

	public boolean isEnableAnonymousPassword() {
		return enableAnonymousPassword;
	}

	public void setEnableAnonymousPassword(boolean enableAnonymousPassword) {
		this.enableAnonymousPassword = enableAnonymousPassword;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Properties getDriverProperties() {
		return driverProperties;
	}

	public void setDriverProperties(Properties driverProperties) {
		this.driverProperties = driverProperties;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public boolean isShowPassword() {
		return showPassword;
	}

	public void setShowPassword(boolean showPassword) {
		this.showPassword = showPassword;
	}

	public boolean isEnableAnonymousUsername() {
		return enableAnonymousUsername;
	}

	public void setEnableAnonymousUsername(boolean enableAnonymousUsername) {
		this.enableAnonymousUsername = enableAnonymousUsername;
	}

}
