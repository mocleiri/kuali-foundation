package org.kuali.common.jdbc.vendor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.common.jdbc.model.Credentials;
import org.kuali.common.jdbc.model.context.ConnectionContext;

public final class Vendors {

	public static final Map<Vendor, VendorBase> DEFAULTS = getDefaults();

	public static final List<String> DEFAULT_SQL_KEYS = getDefaultSqlKeys();

	private static List<String> getDefaultSqlKeys() {
		List<String> keys = new ArrayList<String>();
		keys.add("validate");
		keys.add("create");
		keys.add("drop");
		keys.add("liquibase.drop");
		keys.add("liquibase.create");
		return Collections.unmodifiableList(keys);
	}

	private static Map<Vendor, VendorBase> getDefaults() {
		VendorBase oracle = new VendorBase(Vendor.ORACLE, Oracle.DBA, Oracle.DRIVER);
		VendorBase mysql = new VendorBase(Vendor.MYSQL, MySql.DBA, MySql.DRIVER);
		Map<Vendor, VendorBase> map = new HashMap<Vendor, VendorBase>();
		map.put(Vendor.ORACLE, oracle);
		map.put(Vendor.MYSQL, mysql);
		return Collections.unmodifiableMap(map);
	}

	private static class Oracle {
		private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		private static final String USERNAME = "system";
		private static final String PASSWORD = "manager";
		private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		private static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);
	}

	private static class MySql {
		private static final String DRIVER = "com.mysql.jdbc.Driver";
		private static final String USERNAME = "root";
		private static final String PASSWORD = Credentials.NO_PASSWORD;
		private static final String URL = "jdbc:mysql://localhost";
		private static final ConnectionContext DBA = new ConnectionContext(URL, USERNAME, PASSWORD);
	}

	// This is used by the Vendor enum
	public static class Codes {
		public static final String ORACLE = "oracle";
		public static final String MYSQL = "mysql";
	}

	// This is used when working with Spring annotations.
	public static class Profiles {
		public static final String ORACLE = Codes.ORACLE;
		public static final String MYSQL = Codes.MYSQL;
	}
}
