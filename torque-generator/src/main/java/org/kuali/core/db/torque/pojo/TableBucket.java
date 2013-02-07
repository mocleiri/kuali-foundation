package org.kuali.core.db.torque.pojo;

import java.util.List;

import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.core.db.torque.service.ImpexContext;
import org.kuali.core.db.torque.service.ImpexService;

public class TableBucket {

	ImpexContext context;
	ImpexService service;
	List<TableContext> tables;
	PercentCompleteInformer progressTracker;
	List<DumpTableResult> results;

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

}
