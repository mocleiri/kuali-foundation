package org.kuali.common.jdbc.vendor.model;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;

public final class Vendors {

	public static final Map<Vendor, DatabaseVendorBase> DEFAULTS = getSimpleVendors();

	public static final Map<Vendor, DatabaseVendorBase> getSimpleVendors() {
		DatabaseVendorBase oracle = new DatabaseVendorBase(Codes.ORACLE, Oracle.DBA, Oracle.DRIVER);
		DatabaseVendorBase mysql = new DatabaseVendorBase(Codes.MYSQL, MySql.DBA, MySql.DRIVER);
		Map<Vendor, DatabaseVendorBase> map = new HashMap<Vendor, DatabaseVendorBase>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return map;
	}

	public static final class Oracle {

		public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		public static final ConnectionContext DBA = new ConnectionContext(Dba.URL, Dba.USERNAME, Dba.PASSWORD);

		private static final class Dba {
			private static final String USERNAME = "system";
			private static final String PASSWORD = "manager";
			private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		}
	}

	public static final class MySql {

		public static final String DRIVER = "com.mysql.jdbc.Driver";
		public static final ConnectionContext DBA = new ConnectionContext(Dba.URL, Dba.USERNAME, Dba.PASSWORD);

		private static final class Dba {
			private static final String USERNAME = "root";
			private static final String PASSWORD = Credentials.NO_PASSWORD;
			private static final String URL = "jdbc:mysql://localhost";
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
