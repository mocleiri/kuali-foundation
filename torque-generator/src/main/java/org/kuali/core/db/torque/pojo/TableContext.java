package org.kuali.core.db.torque.pojo;

import java.util.List;
import java.util.Map;

import org.kuali.common.util.Weighted;

public class TableContext implements Comparable<TableContext>, Weighted {

	String name;
	List<String> primaryKeys;
	List<ColumnContext> columns;
	Map<String, ForeignKey> foreignKeys;
	List<Index> indexes;
	String selectAllQuery;
	long rowCount;
	long size;

	@Override
	public double getWeight() {
		return new Double(rowCount);
	}

	@Override
	public int compareTo(TableContext other) {
		Long one = new Long(rowCount);
		Long two = new Long(other.getRowCount());
		return one.compareTo(two);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<String> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

	public List<ColumnContext> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnContext> columns) {
		this.columns = columns;
	}

	public Map<String, ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(Map<String, ForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public List<Index> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<Index> indexes) {
		this.indexes = indexes;
	}

	public Long getRowCount() {
		return rowCount;
	}

	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}

	public String getSelectAllQuery() {
		return selectAllQuery;
	}

	public void setSelectAllQuery(String selectQuery) {
		this.selectAllQuery = selectQuery;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}
