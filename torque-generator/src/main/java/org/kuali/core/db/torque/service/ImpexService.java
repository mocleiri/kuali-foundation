package org.kuali.core.db.torque.service;

import java.io.File;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.TableContext;
import org.w3c.dom.Document;

public interface ImpexService {

	/**
	 * Get the list of table names, view names, and sequence names. This is usually pretty quick even for databases with hundreds of tables.
	 * The only thing extracted from the database are the names. No detailed metadata like relationships, indexes, primary keys, etc.
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

	/**
	 * Populate a document object with the database metadata
	 */
	Document getSchemaDocument(ImpexContext context, DatabaseContext database);

	/**
	 * Serialize the document object to disk
	 */
	void serialize(Document document, File file, String encoding);

}
