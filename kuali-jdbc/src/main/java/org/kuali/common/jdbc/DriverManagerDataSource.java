package org.kuali.common.jdbc;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.property.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Very simple extension providing a token based method for explicitly setting the database password to <code>null</code>. If
 * <code>password</code> equals <code>nullPasswordToken</code>, the <code>init()</code> method sets
 * <code>password<code> to <code>null</code>.
 */
public class DriverManagerDataSource extends org.springframework.jdbc.datasource.DriverManagerDataSource {

	private static final Logger logger = LoggerFactory.getLogger(DriverManagerDataSource.class);

	String nullPasswordToken = Constants.NONE;

	public void init() {
		boolean noPassword = StringUtils.equals(nullPasswordToken, super.getPassword());
		if (noPassword) {
			logger.info("Password is '" + nullPasswordToken + "'.  Setting to null.");
			super.setPassword(null);
		}
	}

	public String getNullPasswordToken() {
		return nullPasswordToken;
	}

	public void setNullPasswordToken(String nullPasswordToken) {
		this.nullPasswordToken = nullPasswordToken;
	}

}
