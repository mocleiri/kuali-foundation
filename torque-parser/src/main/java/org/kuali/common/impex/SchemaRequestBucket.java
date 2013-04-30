package org.kuali.common.impex;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexGeneratorService;
import org.kuali.common.util.PercentCompleteInformer;

public class SchemaRequestBucket {

	List<SchemaRequest> requests;
	DataSource dataSource;
	ImpexContext impexContext;
	PercentCompleteInformer progressTracker;
	ImpexGeneratorService impexService;

	public List<SchemaRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<SchemaRequest> requests) {
		this.requests = requests;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ImpexContext getImpexContext() {
		return impexContext;
	}

	public void setImpexContext(ImpexContext impexContext) {
		this.impexContext = impexContext;
	}

	public PercentCompleteInformer getProgressTracker() {
		return progressTracker;
	}

	public void setProgressTracker(PercentCompleteInformer progressTracker) {
		this.progressTracker = progressTracker;
	}

	public ImpexGeneratorService getImpexService() {
		return impexService;
	}

	public void setImpexService(ImpexGeneratorService impexService) {
		this.impexService = impexService;
	}

}
