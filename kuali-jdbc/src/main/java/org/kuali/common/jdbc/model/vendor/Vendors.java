package org.kuali.common.jdbc.model.vendor;

import java.sql.Driver;

import oracle.jdbc.driver.OracleDriver;

import org.kuali.common.util.nullify.NullUtils;

public abstract class Vendors {

	public static class Oracle {

		public static final Class<? extends Driver> DRIVER = OracleDriver.class;
		public static final String URL = Oracle.Dba.URL;

		public static class Dba {
			public static final String USERNAME = "system";
			public static final String PASSWORD = "manager";
			public static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
		}
	}

	public static class MySql {

		public static final Class<? extends Driver> DRIVER = com.mysql.jdbc.Driver.class;
		public static final String URL = MySql.Dba.URL + "/${jdbc.username}";

		public static class Dba {
			public static final String USERNAME = "root";
			public static final String PASSWORD = NullUtils.NONE;
			public static final String URL = "jdbc:mysql://localhost";
		}
	}

}
