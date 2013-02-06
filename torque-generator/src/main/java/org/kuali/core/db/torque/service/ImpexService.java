package org.kuali.core.db.torque.service;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.TableContext;

public interface ImpexService {

	/**
	 * Get the list of table names, view names, and sequence names.
	 */
	DatabaseContext getDatabaseObjectLists(ImpexContext context) throws SQLException;

	/**
	 * Fill in the full set of schema metadata
	 */
	void fillInMetaData(DatabaseContext database, ImpexContext context) throws SQLException;

	/**
	 * Fill in the full set of metadata for this table
	 */
	void fillInMetaData(TableContext table, ImpexContext context, DatabaseMetaData metaData) throws SQLException;
}
