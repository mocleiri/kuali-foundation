package org.kuali.common.jdbc;

public class DatabaseProcessContext {

	DatabaseContext dba;
	DatabaseContext user;

	public DatabaseContext getDba() {
		return dba;
	}

	public void setDba(DatabaseContext dba) {
		this.dba = dba;
	}

	public DatabaseContext getUser() {
		return user;
	}

	public void setUser(DatabaseContext user) {
		this.user = user;
	}

}
