package org.kuali.core.db.torque.pojo;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.core.db.torque.KualiTorqueSchemaDumpTask;

public class TableBucket {

	List<TableContext> tables;
	DataSource dataSource;
	KualiTorqueSchemaDumpTask task;
	DatabaseContext databaseContext;
	PercentCompleteInformer progressTracker;

	public List<TableContext> getTables() {
		return tables;
	}

	public void setTables(List<TableContext> tables) {
		this.tables = tables;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public KualiTorqueSchemaDumpTask getTask() {
		return task;
	}

	public void setTask(KualiTorqueSchemaDumpTask task) {
		this.task = task;
	}

	public DatabaseContext getDatabaseContext() {
		return databaseContext;
	}

	public void setDatabaseContext(DatabaseContext databaseContext) {
		this.databaseContext = databaseContext;
	}

	public PercentCompleteInformer getProgressTracker() {
		return progressTracker;
	}

	public void setProgressTracker(PercentCompleteInformer progressTracker) {
		this.progressTracker = progressTracker;
	}

}
