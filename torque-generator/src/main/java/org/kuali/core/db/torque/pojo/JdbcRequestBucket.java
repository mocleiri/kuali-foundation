package org.kuali.core.db.torque.pojo;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.core.db.torque.service.ImpexContext;
import org.kuali.core.db.torque.service.ImpexService;

public class JdbcRequestBucket {

	List<JdbcRequest> requests;
	DataSource dataSource;
	ImpexContext impexContext;
	PercentCompleteInformer progressTracker;
	ImpexService impexService;

	public List<JdbcRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<JdbcRequest> requests) {
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

	public ImpexService getImpexService() {
		return impexService;
	}

	public void setImpexService(ImpexService impexService) {
		this.impexService = impexService;
	}

}
