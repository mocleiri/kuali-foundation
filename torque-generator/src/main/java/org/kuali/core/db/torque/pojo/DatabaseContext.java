package org.kuali.core.db.torque.pojo;

import java.sql.DatabaseMetaData;

import org.apache.torque.engine.platform.Platform;

public class DatabaseContext {

	Platform platform;
	String schema;
	DatabaseMetaData metaData;

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public DatabaseMetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(DatabaseMetaData metaData) {
		this.metaData = metaData;
	}

}
