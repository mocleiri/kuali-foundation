package org.kuali.common.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Very simple extension providing a token based method for explicitly setting the database password to <code>null</code>.
 */
public class DriverManagerDataSource extends org.springframework.jdbc.datasource.DriverManagerDataSource {

	private static final Logger logger = LoggerFactory.getLogger(DriverManagerDataSource.class);

	List<String> nullPasswordTokens = Arrays.asList(new String[] { Constants.NULL, Constants.NONE });

	public DriverManagerDataSource() {
		super();
	}

	public DriverManagerDataSource(String url, Properties conProps) {
		super(url, conProps);
	}

	public DriverManagerDataSource(String url, String username, String password) {
		super(url, username, password);
		nullifyPassword();
	}

	public DriverManagerDataSource(String url) {
		super(url);
	}

	@Override
	public Connection getConnection() throws SQLException {
		nullifyPassword();
		return super.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		if (isNullify(password)) {
			logger.debug("Database password for '{}' is '{}'  Proceeding with null password", username, password);
			return super.getConnection(username, null);
		} else {
			return super.getConnection(username, password);
		}
	}

	/**
	 * Return <code>true</code> if <code>password</code> is not null and is one of the strings in <code>getNullPasswordTokens()</code>,
	 * false otherwise.
	 */
	protected boolean isNullify(String password) {
		return password != null && CollectionUtils.toEmpty(nullPasswordTokens).contains(password);
	}

	/**
	 * Invoke <code>super.setPassword(null)</code> if <code>getPassword()</code> returns a non-null string contained in
	 * <code>getNullPasswordTokens()</code>
	 */
	public void nullifyPassword() {
		String pw = super.getPassword();
		if (isNullify(pw)) {
			logger.info("Database password for '{}' is '{}'  Setting to null.", super.getUsername(), pw);
			super.setPassword(null);
		}
	}

	public List<String> getNullPasswordTokens() {
		return nullPasswordTokens;
	}

	public void setNullPasswordTokens(List<String> nullPasswordTokens) {
		this.nullPasswordTokens = nullPasswordTokens;
	}

}
