package org.kuali.common.impex.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.data.service.DumpDataService;

public class ExportTableBucket implements Comparable<ExportTableBucket> {

	DumpDataContext context;
	DumpDataService service;
	List<ExportTableContext> tables = new ArrayList<ExportTableContext>();
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

    public DumpDataContext getContext() {
        return context;
    }

    public void setContext(DumpDataContext context) {
        this.context = context;
    }

    public DumpDataService getService() {
        return service;
    }

    public void setService(DumpDataService service) {
        this.service = service;
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
