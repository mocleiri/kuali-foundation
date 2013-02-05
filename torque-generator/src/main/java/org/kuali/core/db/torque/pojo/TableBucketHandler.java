package org.kuali.core.db.torque.pojo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.kuali.core.db.torque.KualiTorqueSchemaDumpTask;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class TableBucketHandler implements ElementHandler<TableBucket> {

	@Override
	public void handleElement(ListIteratorContext<TableBucket> context, int index, TableBucket element) {
		List<TableContext> tables = element.getTables();
		DataSource dataSource = element.getDataSource();
		KualiTorqueSchemaDumpTask task = element.getTask();
		DatabaseContext db = element.getDatabaseContext();
		Connection conn = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			DatabaseMetaData metaData = conn.getMetaData();
			for (TableContext table : tables) {
				task.fillInMetaData(table, db, metaData);
				element.getProgressTracker().progressOccurred();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

}
