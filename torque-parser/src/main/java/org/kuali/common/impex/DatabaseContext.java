package org.kuali.common.impex;

import java.util.List;

public class DatabaseContext {

	List<TableContext> tables;
	List<View> views;
	List<Sequence> sequences;

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
