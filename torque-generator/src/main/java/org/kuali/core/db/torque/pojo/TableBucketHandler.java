package org.kuali.core.db.torque.pojo;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.kuali.core.db.torque.service.ImpexContext;
import org.kuali.core.db.torque.service.ImpexService;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class TableBucketHandler implements ElementHandler<TableBucket> {

	@Override
	public void handleElement(ListIteratorContext<TableBucket> context, int index, TableBucket element) {
		List<TableContext> tables = element.getTables();
		ImpexService service = element.getService();
		ImpexContext impex = element.getContext();
		DataSource dataSource = element.getContext().getDataSource();
		List<DumpTableResult> results = element.getResults();
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			for (TableContext table : tables) {
				DumpTableResult result = service.dumpTable(impex, table, conn);
				synchronized (results) {
					results.add(result);
				}
				System.out.println("Rows - " + result.getRows() + ", Size - " + result.getSize());
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
