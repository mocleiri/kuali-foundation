package org.kuali.core.db.torque.pojo;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.kuali.core.db.torque.service.ImpexService;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class TableBucketHandler implements ElementHandler<TableBucket> {

	@Override
	public void handleElement(ListIteratorContext<TableBucket> context, int index, TableBucket element) {
		List<TableContext> tables = element.getTables();
		ImpexService service = element.getService();
		DataSource dataSource = element.getContext().getDataSource();
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			element.getProgressTracker().progressOccurred();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (conn != null) {
				DataSourceUtils.releaseConnection(conn, dataSource);
			}
		}
	}

}
