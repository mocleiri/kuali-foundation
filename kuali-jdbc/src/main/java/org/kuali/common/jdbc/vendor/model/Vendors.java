package org.kuali.common.jdbc.vendor.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;

public final class Vendors {

	public static final Map<Vendor, VendorBase> DEFAULTS = getDefaults();

	public static final Map<Vendor, VendorBase> getDefaults() {
		VendorBase oracle = new VendorBase(Vendor.ORACLE, Oracle.DBA, Oracle.DRIVER);
		VendorBase mysql = new VendorBase(Vendor.MYSQL, MySql.DBA, MySql.DRIVER);
		Map<Vendor, VendorBase> map = new HashMap<Vendor, VendorBase>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return Collections.unmodifiableMap(map);
	}

	private static final class Oracle {
		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		private static final String USERNAME = "system";
		private static final String PASSWORD = "manager";
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		private static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);
	}

	private static final class MySql {
		private static final String DRIVER = "com.mysql.jdbc.Driver";
		private static final String USERNAME = "root";
		private static final String PASSWORD = Credentials.NO_PASSWORD;
		private static final String URL = "jdbc:mysql://localhost";
		private static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);
	}

	// This is used by the Vendor enum
	public static final class Codes {
		public static final String ORACLE = "oracle";
		public static final String MYSQL = "mysql";
	}

	// This is used when working with Spring annotations.
	public static final class Profiles {
		public static final String ORACLE = Codes.ORACLE;
		public static final String MYSQL = Codes.MYSQL;
	}
}
