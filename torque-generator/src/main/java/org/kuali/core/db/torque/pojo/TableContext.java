package org.kuali.core.db.torque.pojo;

import java.util.List;
import java.util.Map;

public class TableContext implements Comparable<TableContext> {

	int sequence;
	Map<String, String> primaryKeys;
	List<Column> columns;
	Map<String, ForeignKey> foreignKeys;

	@Override
	public int compareTo(TableContext other) {
		Integer one = sequence;
		Integer two = other.getSequence();
		return one.compareTo(two);
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Map<String, String> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(Map<String, String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Map<String, ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(Map<String, ForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

}
