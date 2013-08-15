package org.kuali.common.jdbc.vendor.model;

import org.kuali.common.jdbc.model.Credentials;

public final class Vendors {

	public static final class Defaults {

		public static final class Oracle {

			public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

			public static final class Dba {
				public static final String USERNAME = "system";
				public static final String PASSWORD = "manager";
				public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
			}

		}

		public static final class MySql {

			public static final String DRIVER = "com.mysql.jdbc.Driver";

			public static final class Dba {
				public static final String USERNAME = "root";
				public static final String PASSWORD = Credentials.NO_PASSWORD;
				public static final String URL = "jdbc:mysql://localhost";
			}
		}
	}

	public enum Code {

		ORACLE(Codes.ORACLE), MYSQL(Codes.MYSQL);

		private Code(String value) {
			this.value = value;
		}

		private final String value;

		public String getValue() {
			return value;
		}
	}

	public static final class Codes {

		public static final String ORACLE = "oracle";
		public static final String MYSQL = "mysql";

	}

	public static final class Profiles {

		public static final String ORACLE = Codes.ORACLE;
		public static final String MYSQL = Codes.MYSQL;

	}
}
