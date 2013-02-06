package org.kuali.core.db.torque.pojo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.sql.DataSource;

import org.apache.torque.engine.platform.Platform;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.kuali.core.db.torque.service.ImpexContext;
import org.kuali.core.db.torque.service.ImpexService;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class JdbcRequestHandler implements ElementHandler<JdbcRequestBucket> {

	@Override
	public void handleElement(ListIteratorContext<JdbcRequestBucket> context, int index, JdbcRequestBucket element) {
		List<JdbcRequest> requests = element.getRequests();
		DataSource dataSource = element.getDataSource();
		ImpexContext impex = element.getImpexContext();
		Platform platform = impex.getPlatform();
		String schema = impex.getSchema();
		ImpexService service = element.getImpexService();
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			DatabaseMetaData metaData = conn.getMetaData();
			for (JdbcRequest request : requests) {
				if (request.getTable() != null) {
					service.fillInMetaData(request.getTable(), impex, metaData);
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
				element.getProgressTracker().progressOccurred();
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (conn != null) {
				DataSourceUtils.releaseConnection(conn, dataSource);
			}
		}
	}

}
