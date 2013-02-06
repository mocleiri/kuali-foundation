package org.kuali.core.db.torque.pojo;

import java.util.List;

import org.apache.torque.engine.platform.Platform;

public class DatabaseContext {

	Platform platform;
	String schema;
	List<TableContext> tables;
	List<View> views;
	List<Sequence> sequences;

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

	public List<TableContext> getTables() {
		return tables;
	}

	public void setTables(List<TableContext> tables) {
		this.tables = tables;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

	public List<Sequence> getSequences() {
		return sequences;
	}

	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}

}
