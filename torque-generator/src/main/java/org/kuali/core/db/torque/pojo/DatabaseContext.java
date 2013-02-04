package org.kuali.core.db.torque.pojo;

import org.apache.torque.engine.platform.Platform;

public class DatabaseContext {

	Platform platform;
	String schema;

	public DatabaseContext() {
		this(null, null);
	}

	public DatabaseContext(Platform platform, String schema) {
		super();
		this.platform = platform;
		this.schema = schema;
	}

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

}
