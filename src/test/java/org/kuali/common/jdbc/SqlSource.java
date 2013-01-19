package org.kuali.common.jdbc;

public class SqlSource {

	String location;
	SqlMetaData metaData;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public SqlMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(SqlMetaData metaData) {
		this.metaData = metaData;
	}

}
