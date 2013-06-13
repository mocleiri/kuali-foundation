package org.kuali.common.impex.data;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.PercentCompleteInformer;

public class ExportTableBucket implements Comparable<ExportTableBucket> {

	ExportDataContext context;
	ExportDataService service;
	List<ExportTableContext> tables = new ArrayList<ExportTableContext>();
	PercentCompleteInformer progressTracker;
	List<ExportTableResult> results;
	long rowCount;
	long size;

	@Override
	public int compareTo(ExportTableBucket other) {
		Long one = rowCount;
		Long two = other.getRowCount();
		return one.compareTo(two);
	}

	public List<ExportTableContext> getTables() {
		return tables;
	}

	public void setTables(List<ExportTableContext> tables) {
		this.tables = tables;
	}

    public ExportDataContext getContext() {
        return context;
    }

    public void setContext(ExportDataContext context) {
        this.context = context;
    }

    public ExportDataService getService() {
        return service;
    }

    public void setService(ExportDataService service) {
        this.service = service;
    }

    public PercentCompleteInformer getProgressTracker() {
		return progressTracker;
	}

	public void setProgressTracker(PercentCompleteInformer progressTracker) {
		this.progressTracker = progressTracker;
	}

    public List<ExportTableResult> getResults() {
        return results;
    }

    public void setResults(List<ExportTableResult> results) {
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
