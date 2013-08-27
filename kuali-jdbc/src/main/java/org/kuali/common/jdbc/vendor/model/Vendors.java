package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;

public final class Vendors {

	public static final class Oracle {
		public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		public static final String USERNAME = "system";
		public static final String PASSWORD = "manager";
		public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		public static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);
	}

	public static final class MySql {
		public static final String DRIVER = "com.mysql.jdbc.Driver";
		public static final String USERNAME = "root";
		public static final String PASSWORD = Credentials.NO_PASSWORD;
		public static final String URL = "jdbc:mysql://localhost";
		public static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);
	}

	// These are used by the Vendor enum
	public static final class Codes {
		public static final String ORACLE = "oracle";
		public static final String MYSQL = "mysql";
	}

	// These are used in Spring annotations
	public static final class Profiles {
		public static final String ORACLE = Codes.ORACLE;
		public static final String MYSQL = Codes.MYSQL;
	}
}
