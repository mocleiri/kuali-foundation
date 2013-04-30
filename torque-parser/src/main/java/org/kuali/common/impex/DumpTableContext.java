package org.kuali.common.impex;

import java.io.OutputStream;
import java.util.List;

import org.apache.torque.engine.database.model.Column;
import org.kuali.common.impex.service.ImpexContext;

public class DumpTableContext {

	ImpexContext impexContext;
	TableContext tableContext;
	Column[] columns;
	List<String[]> currentData;
	long currentDataSize;
	long totalDataSize;
	long currentRowCount;
	long totalRowCount;
	OutputStream outputStream;

	public ImpexContext getImpexContext() {
		return impexContext;
	}

	public void setImpexContext(ImpexContext impexContext) {
		this.impexContext = impexContext;
	}

	public TableContext getTableContext() {
		return tableContext;
	}

	public void setTableContext(TableContext tableContext) {
		this.tableContext = tableContext;
	}

	public Column[] getColumns() {
		return columns;
	}

	public void setColumns(Column[] columns) {
		this.columns = columns;
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

	public long getTotalDataSize() {
		return totalDataSize;
	}

	public void setTotalDataSize(long totalDataSize) {
		this.totalDataSize = totalDataSize;
	}

	public long getCurrentRowCount() {
		return currentRowCount;
	}

	public void setCurrentRowCount(long currentRowCount) {
		this.currentRowCount = currentRowCount;
	}

	public long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
}
