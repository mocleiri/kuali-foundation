package org.apache.torque.util;

import java.util.List;

public class CloverETLTable {

	String name;
	List<String> columns;
	List<String[]> rows;
	List<CloverETLColumn> etlColumns;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<String[]> getRows() {
		return rows;
	}

	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}

	public List<CloverETLColumn> getEtlColumns() {
		return etlColumns;
	}

	public void setEtlColumns(List<CloverETLColumn> etlColumns) {
		this.etlColumns = etlColumns;
	}

}
