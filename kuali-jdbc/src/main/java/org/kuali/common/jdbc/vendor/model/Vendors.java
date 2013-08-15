package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;

public final class Vendors {

	public static final class Oracle {

		public static final String CODE = "oracle";
		public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		public static final ConnectionContext DBA = new ConnectionContext(Dba.URL, Dba.USERNAME, Dba.PASSWORD);

		private static class Dba {
			public static final String USERNAME = "system";
			public static final String PASSWORD = "manager";
			public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		}

		// Prevent instantiation
		private Oracle() {
		}
	}

	public static final class MySql {

		public static final String CODE = "mysql";
		public static final String DRIVER = "com.mysql.jdbc.Driver";
		public static final ConnectionContext DBA = new ConnectionContext(Dba.URL, Dba.USERNAME, Dba.PASSWORD);

		private static class Dba {
			public static final String USERNAME = "root";
			public static final String PASSWORD = Credentials.NO_PASSWORD;
			public static final String URL = "jdbc:mysql://localhost";
		}

		// Prevent instantiation
		private MySql() {
		}
	}

	// Prevent instantiation
	private Vendors() {
	}

}
