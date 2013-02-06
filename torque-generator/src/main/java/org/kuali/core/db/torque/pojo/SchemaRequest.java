package org.kuali.core.db.torque.pojo;

public class SchemaRequest {

	TableContext table;
	View view;
	Sequence sequence;

	public TableContext getTable() {
		return table;
	}

	public void setTable(TableContext table) {
		this.table = table;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

}
