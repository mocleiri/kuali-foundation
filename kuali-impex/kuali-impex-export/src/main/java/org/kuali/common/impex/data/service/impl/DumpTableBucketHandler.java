package org.kuali.common.impex.data.service.impl;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.data.service.DumpDataService;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class DumpTableBucketHandler implements ElementHandler<DumpTableBucket> {

	private static final Logger logger = LoggerFactory.getLogger(DumpTableBucketHandler.class);

	@Override
	public void handleElement(ListIteratorContext<DumpTableBucket> context, int index, DumpTableBucket element) {
		DataSource dataSource = null;
		Connection conn = null;
		try {
			List<DumpTableContext> tables = element.getTables();
			DumpDataService service = element.getService();
			DumpDataContext impex = element.getContext();
			dataSource = impex.getDataSource();
			List<DumpTableResult> results = element.getResults();
			conn = DataSourceUtils.getConnection(dataSource);
			for (DumpTableContext tableContext : tables) {
				logger.debug("Dumping {}", tableContext.getTable().getName());
				DumpTableResult result = service.dumpTable(impex, tableContext, conn);
				synchronized (results) {
					results.add(result);
				}
				// element.getProgressTracker().progressOccurred();
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			JdbcUtils.closeQuietly(dataSource, conn);
		}
	}
}
