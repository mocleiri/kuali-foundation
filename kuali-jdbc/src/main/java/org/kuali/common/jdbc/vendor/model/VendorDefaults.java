package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;

public abstract class VendorDefaults {

	public static final class Oracle {

		public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

		private static final String USERNAME = "system";
		private static final String PASSWORD = "manager";
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

		public static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);
	}

	public static final class MySql {

		public static final String DRIVER = "com.mysql.jdbc.Driver";

		private static final String USERNAME = "root";
		private static final String PASSWORD = Credentials.NO_PASSWORD;
		private static final String URL = "jdbc:mysql://localhost";

		public static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);

	}
}
