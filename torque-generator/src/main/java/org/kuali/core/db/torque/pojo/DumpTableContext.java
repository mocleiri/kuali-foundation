package org.kuali.core.db.torque.pojo;

import java.util.List;

import org.apache.torque.engine.database.model.Column;
import org.kuali.core.db.torque.service.ImpexContext;

public class DumpTableContext {

	ImpexContext impexContext;
	List<String[]> currentData;
	long currentDataSize;
	TableContext tableContext;
	long rowCount;
	long totalDataSize;
	Column[] columns;

	public ImpexContext getImpexContext() {
		return impexContext;
	}

	public void setImpexContext(ImpexContext impexContext) {
		this.impexContext = impexContext;
	}

	public List<String[]> getCurrentData() {
		return currentData;
	}

	public void setCurrentData(List<String[]> currentData) {
		this.currentData = currentData;
	}

	public long getCurrentDataSize() {
		return currentDataSize;
	}

	public void setCurrentDataSize(long currentDataSize) {
		this.currentDataSize = currentDataSize;
	}

	public TableContext getTableContext() {
		return tableContext;
	}

	public void setTableContext(TableContext tableContext) {
		this.tableContext = tableContext;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public long getTotalDataSize() {
		return totalDataSize;
	}

	public void setTotalDataSize(long totalDataSize) {
		this.totalDataSize = totalDataSize;
	}

	public Column[] getColumns() {
		return columns;
	}

	public void setColumns(Column[] columns) {
		this.columns = columns;
	}
}
