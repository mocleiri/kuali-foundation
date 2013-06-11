package org.kuali.common.impex.data;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;

import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class ExportTableBucketHandler implements ElementHandler<ExportTableBucket> {

	private static final Logger logger = LoggerFactory.getLogger(ExportTableBucketHandler.class);

	@Override
	public void handleElement(ListIteratorContext<ExportTableBucket> context, int index, ExportTableBucket element) {
		DataSource dataSource = null;
		Connection conn = null;
		try {
			List<ExportTableContext> tables = element.getTables();
			ExportDataService service = element.getService();
			ExportDataContext impex = element.getContext();
			dataSource = impex.getDataSource();
			List<ExportTableResult> results = element.getResults();
			conn = DataSourceUtils.getConnection(dataSource);
			for (ExportTableContext tableContext : tables) {
				logger.debug("Dumping {}", tableContext.getTable().getName());
				ExportTableResult result = service.exportTable(impex, tableContext, conn);
				synchronized (results) {
					results.add(result);
				}
				// element.getProgressTracker().progressOccurred();
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
