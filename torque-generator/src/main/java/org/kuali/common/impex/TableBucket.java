package org.kuali.common.impex;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexService;
import org.kuali.common.util.PercentCompleteInformer;

public class TableBucket implements Comparable<TableBucket> {

	ImpexContext context;
	ImpexService service;
	List<TableContext> tables = new ArrayList<TableContext>();
	PercentCompleteInformer progressTracker;
	List<DumpTableResult> results;
	long rowCount;
	long size;

	@Override
	public int compareTo(TableBucket other) {
		Long one = new Long(rowCount);
		Long two = new Long(other.getRowCount());
		return one.compareTo(two);
	}

	public List<TableContext> getTables() {
		return tables;
	}

	public void setTables(List<TableContext> tables) {
		this.tables = tables;
	}

	public ImpexContext getContext() {
		return context;
	}

	public void setContext(ImpexContext context) {
		this.context = context;
	}

	public ImpexService getService() {
		return service;
	}

	public void setService(ImpexService service) {
		this.service = service;
	}

	public PercentCompleteInformer getProgressTracker() {
		return progressTracker;
	}

	public void setProgressTracker(PercentCompleteInformer progressTracker) {
		this.progressTracker = progressTracker;
	}

	public List<DumpTableResult> getResults() {
		return results;
	}

	public void setResults(List<DumpTableResult> results) {
		this.results = results;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.rowCount = rowCount;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
