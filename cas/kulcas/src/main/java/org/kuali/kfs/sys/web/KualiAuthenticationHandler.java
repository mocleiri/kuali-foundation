package org.kuali.kfs.sys.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.AuthenticationHandler;
import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

public class KualiAuthenticationHandler implements AuthenticationHandler {

	private DataSource dataSource;
	private boolean validatePassword;
	
	public boolean authenticate(Credentials credentials) throws AuthenticationException {
		UsernamePasswordCredentials upCredentials = (UsernamePasswordCredentials) credentials;

		boolean authentic = false;
		String username = upCredentials.getUsername();
		String password = upCredentials.getPassword();

		// TODO: May want to replace this JDBC with Spring JDBC
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("select PRNCPL_ID, PRNCPL_PSWD from KRIM_PRNCPL_T where PRNCPL_NM = '" + username + "'");
			rs = ps.executeQuery();			
			authentic = rs.next();
			if (authentic && isValidatePassword()) {
				if (password == null || password.trim().length() == 0) {
					authentic = false;
				} else {
					// TODO: Need to hash the comparison but don't have access to Kuali if this is a deployed war
					authentic = password.equals(rs.getString("PRNCPL_PSWD"));
				}
			}
		} catch (SQLException e) {
			// TODO: Determine an appropriate way to log errors since this can be deployed as a standalone war
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return authentic;
	}

	public boolean supports(Credentials credentials) {
		return credentials instanceof UsernamePasswordCredentials;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the validatePassword
	 */
	public boolean isValidatePassword() {
		return validatePassword;
	}

	/**
	 * @param validatePassword the validatePassword to set
	 */
	public void setValidatePassword(boolean validatePassword) {
		this.validatePassword = validatePassword;
	}	
	
}
