package org.kuali.common.impex.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import org.kuali.common.impex.DatabaseContext;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.impex.MpxImportResult;
import org.kuali.common.impex.TableContext;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.w3c.dom.Document;

public interface ImpexGeneratorService {

	/**
	 *
	 */
	void generateSchemaSql(List<ImpexContext> contexts, List<String> databaseVendors);

	/**
	 * Get the list of table names, view names, and sequence names. This is usually pretty quick (a few seconds) even for databases with
	 * hundreds of tables. The only thing extracted from the database are the names. No detailed metadata like relationships, indexes,
	 * primary keys, etc.
	 */
	DatabaseContext getDatabaseObjectLists(ImpexContext context) throws SQLException;

	/**
	 * Fill in the full set of schema metadata. The full set of metadata can take a while and is acquired using multiple threads.
	 */
	void fillInMetaData(ImpexContext context, DatabaseContext database) throws SQLException;

	/**
	 * Fill in the full set of metadata for this table.
	 */
	void fillInMetaData(ImpexContext context, TableContext table, DatabaseMetaData metaData) throws SQLException;

	/**
	 * Populate a document object with the database metadata.
	 */
	Document getSchemaDocument(ImpexContext context, DatabaseContext database);

	/**
	 * Serialize a document object to disk.
	 */
	void serialize(Document document, File file, String encoding);

	/**
	 * Create schema documents for each context and serialize them to disk.
	 */
	void serializeSchemas(List<ImpexContext> contexts, DatabaseContext database);

	/**
	 * Parse schema xml files and produce corresponding data.dtd files
	 */
	void generateDataDtds(List<ImpexContext> contexts);

	/**
	 * Dump all of the data from this table to disk
	 */
	DumpTableResult dumpTable(ImpexContext context, TableContext table, Connection conn) throws SQLException;

	/**
	 * Dump one or more tables from a database to disk
	 */
	List<DumpTableResult> dumpTables(ImpexContext context, DatabaseContext database);

}
