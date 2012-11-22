package org.kuali.common.jdbc;

public class DatabaseProcessContext {

	String driver;

	DatabaseContext dba;
	DatabaseContext normal;

	public DatabaseContext getDba() {
		return dba;
	}

	public void setDba(DatabaseContext dba) {
		this.dba = dba;
	}

	public DatabaseContext getNormal() {
		return normal;
	}

	public void setNormal(DatabaseContext user) {
		this.normal = user;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

}
