package org.kuali.common.impex;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexGeneratorService;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class TableBucketHandler implements ElementHandler<TableBucket> {

	private static final Logger logger = LoggerFactory.getLogger(TableBucketHandler.class);

	@Override
	public void handleElement(ListIteratorContext<TableBucket> context, int index, TableBucket element) {
		DataSource dataSource = null;
		Connection conn = null;
		try {
			List<TableContext> tables = element.getTables();
			ImpexGeneratorService service = element.getService();
			ImpexContext impex = element.getContext();
			dataSource = impex.getDataSource();
			List<DumpTableResult> results = element.getResults();
			conn = DataSourceUtils.getConnection(dataSource);
			for (TableContext table : tables) {
				logger.debug("Dumping {}", table.getName());
				DumpTableResult result = service.dumpTable(impex, table, conn);
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
