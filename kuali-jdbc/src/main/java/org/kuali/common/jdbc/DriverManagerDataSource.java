package org.kuali.common.jdbc;

import java.util.Arrays;
import java.util.List;

import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Very simple extension providing a token based method for explicitly setting the database password to <code>null</code>. If
 * <code>password</code> equals any of the strings in <code>nullPasswordTokens</code>, the <code>init()</code> method sets
 * <code>password<code> to <code>null</code>.
 */
public class DriverManagerDataSource extends org.springframework.jdbc.datasource.DriverManagerDataSource {

	private static final Logger logger = LoggerFactory.getLogger(DriverManagerDataSource.class);

	List<String> nullPasswordTokens = Arrays.asList(new String[] { Constants.NULL, Constants.NONE });

	public void init() {
		String pw = super.getPassword();
		List<String> tokens = CollectionUtils.toEmpty(nullPasswordTokens);
		// Null it out if it matches one of the tokens
		boolean nullifyPassword = (pw != null) && tokens.contains(pw);
		if (nullifyPassword) {
			logger.info("Datbase password for '{}' is '{}'  Setting to null.", super.getUsername(), pw);
			super.setPassword(null);
		}
	}

}
