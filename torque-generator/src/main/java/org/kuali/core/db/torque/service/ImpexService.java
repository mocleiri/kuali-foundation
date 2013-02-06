package org.kuali.core.db.torque.service;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.TableContext;

public interface ImpexService {

	/**
	 * Get the list of table names, view names, and sequence names. This is usually pretty quick even for databases with hundreds of tables,
	 * since only names are being extracted. No relationships, indexes, primary keys, etc.
	 */
	DatabaseContext getDatabaseObjectLists(ImpexContext context) throws SQLException;

	/**
	 * Fill in the full set of schema metadata. The full set of metadata is acquired using multiple threads.
	 */
	void fillInMetaData(ImpexContext context, DatabaseContext database) throws SQLException;

	/**
	 * Fill in the full set of metadata for this table
	 */
	void fillInMetaData(ImpexContext context, TableContext table, DatabaseMetaData metaData) throws SQLException;
}
