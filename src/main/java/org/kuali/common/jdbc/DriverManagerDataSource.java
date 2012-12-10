package org.kuali.common.jdbc;

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

	/**
	 * If <code>super.getPassword()</code> equals any of the strings in <code>getNullPasswordTokens()</code>, invoke
	 * <code>super.setPassword(null)</code>
	 */
	public void nullifyPassword() {
		String pw = super.getPassword();
		List<String> tokens = CollectionUtils.toEmpty(nullPasswordTokens);
		// Null it out if it matches one of the tokens
		boolean nullifyPassword = (pw != null) && tokens.contains(pw);
		if (nullifyPassword) {
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
