package org.kuali.common.jdbc;

public class SqlSource implements Comparable<SqlSource> {

	String location;
	String encoding;
	String sql;
	SqlMetaData metaData;

	@Override
    public int compareTo(SqlSource other) {
		Long size1 = metaData.getSize();
		Long size2 = other.getMetaData().getSize();
		return size1.compareTo(size2);
	}

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

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}
