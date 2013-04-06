package org.kuali.common.impex;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.sql.DataSource;

import org.apache.torque.engine.platform.Platform;
import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexGeneratorService;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.kuali.common.util.PercentCompleteInformer;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class SchemaRequestHandler implements ElementHandler<SchemaRequestBucket> {

	PercentCompleteInformer informer;
	boolean startInformer = true;

	public SchemaRequestHandler() {
		this(null);
	}

	public SchemaRequestHandler(PercentCompleteInformer informer) {
		super();
		this.informer = informer;
	}

	@Override
	public void handleElement(ListIteratorContext<SchemaRequestBucket> context, int index, SchemaRequestBucket element) {
		informerCheck();
		List<SchemaRequest> requests = element.getRequests();
		DataSource dataSource = element.getDataSource();
		ImpexContext impex = element.getImpexContext();
		Platform platform = impex.getPlatform();
		String schema = impex.getSchema();
		ImpexGeneratorService service = element.getImpexService();
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			DatabaseMetaData metaData = conn.getMetaData();
			for (SchemaRequest request : requests) {
				if (request.getTable() != null) {
					service.fillInMetaData(impex, request.getTable(), metaData);
				}
				if (request.getView() != null) {
					View view = request.getView();
					String definition = platform.getViewDefinition(metaData.getConnection(), schema, view.getName());
					view.setDefinition(definition);
				}
				if (request.getSequence() != null) {
					Sequence sequence = request.getSequence();
					Long nextVal = platform.getSequenceNextVal(metaData.getConnection(), schema, sequence.getName());
					sequence.setNextVal(nextVal.toString());
				}
				element.getProgressTracker().incrementProgress(1);
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (conn != null) {
				DataSourceUtils.releaseConnection(conn, dataSource);
			}
		}
	}

	protected synchronized void informerCheck() {
		if (startInformer && informer != null) {
			informer.start();
		}
		startInformer = false;
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}

}
