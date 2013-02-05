package org.kuali.core.db.torque;

import static org.kuali.db.JDBCUtils.closeQuietly;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tools.ant.BuildException;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.core.db.torque.pojo.Column;
import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.ForeignKey;
import org.kuali.core.db.torque.pojo.Index;
import org.kuali.core.db.torque.pojo.Reference;
import org.kuali.core.db.torque.pojo.TableBucket;
import org.kuali.core.db.torque.pojo.TableBucketHandler;
import org.kuali.core.db.torque.pojo.TableContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.w3c.dom.Element;

public class KualiTorqueSchemaDumpTask extends DumpTask {

	private static final Logger logger = LoggerFactory.getLogger(KualiTorqueSchemaDumpTask.class);

	boolean processTables = true;
	boolean processViews = true;
	boolean processSequences = true;
	int threads = 15;
	File schemaXMLFile;
	DataSource dataSource;

	/**
	 * Execute the task
	 */
	@Override
	public void execute() throws BuildException {
		Assert.notNull(targetDatabase, "targetDatabase is null");
		Assert.notNull(dataSource, "dataSource is null");
		Assert.notBlank(schema, "schema is null");
		Assert.notNull(schemaXMLFile, "schemaXMLFile is null");
		try {
			Platform platform = PlatformFactory.getPlatformFor(targetDatabase);
			List<TableContext> tables = getTableList(platform, dataSource, schema, tableIncludes, tableExcludes);
			DatabaseContext db = new DatabaseContext(platform, schema);
			fillInMetaData(tables, dataSource, db, threads);
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

	protected String getSystemId() {
		if (antCompatibilityMode) {
			return "database.dtd";
		} else {
			return ImpexDTDResolver.DTD_LOCATION;
		}
	}

	protected DocumentImpl getDocumentImpl() {
		DocumentTypeImpl docType = new DocumentTypeImpl(null, "database", null, getSystemId());
		DocumentImpl doc = new DocumentImpl(docType);
		doc.appendChild(doc.createComment(" " + getComment() + " "));
		return doc;
	}

	protected void serialize(DocumentImpl document, File file, String encoding) {
		Writer out = null;
		try {
			out = new PrintWriter(FileUtils.openOutputStream(file));
			OutputFormat format = new OutputFormat(Method.XML, encoding, true);
			XMLSerializer xmlSerializer = new XMLSerializer(out, format);
			xmlSerializer.serialize(document);
		} catch (Exception e) {
			throw new IllegalStateException("Error serializing", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected Map<String, String> getPrimaryKeys(Platform platform, DatabaseMetaData metaData, String table, String schema) throws SQLException {
		List<String> primKeys = platform.getPrimaryKeys(metaData, schema, table);

		// Set the primary keys.
		Map<String, String> primaryKeys = new HashMap<String, String>();
		for (int k = 0; k < primKeys.size(); k++) {
			String curPrimaryKey = (String) primKeys.get(k);
			primaryKeys.put(curPrimaryKey, curPrimaryKey);
		}
		return primaryKeys;
	}

	protected void fillInMetaData(List<TableContext> contexts, DataSource dataSource, DatabaseContext db, int threads) throws SQLException {
		List<List<TableContext>> listOfLists = CollectionUtils.splitEvenly(contexts, threads);
		PercentCompleteInformer progressTracker = new PercentCompleteInformer();
		progressTracker.setTotal(contexts.size());
		List<TableBucket> buckets = new ArrayList<TableBucket>();
		for (List<TableContext> list : listOfLists) {
			TableBucket bucket = new TableBucket();
			bucket.setProgressTracker(progressTracker);
			bucket.setDataSource(dataSource);
			bucket.setTables(list);
			bucket.setTask(this);
			bucket.setDatabaseContext(db);
			buckets.add(bucket);
		}

		// Store some context for the thread handler
		ThreadHandlerContext<TableBucket> thc = new ThreadHandlerContext<TableBucket>();
		thc.setList(buckets);
		thc.setHandler(new TableBucketHandler());
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);

		// Start threads to acquire table metadata concurrently
		ThreadInvoker invoker = new ThreadInvoker();
		invoker.invokeThreads(thc);
	}

	public void fillInMetaData(TableContext table, DatabaseContext db, DatabaseMetaData metaData) throws SQLException {
		// Get the primary keys.
		Map<String, String> primaryKeys = getPrimaryKeys(db.getPlatform(), metaData, table.getName(), db.getSchema());
		Map<String, ForeignKey> foreignKeys = getForeignKeys(metaData, table.getName(), db.getSchema());
		List<Index> indexes = getIndexes(metaData, table.getName(), db.getSchema());
		List<Column> columns = getColumns(metaData, table.getName(), db.getSchema());

		table.setPrimaryKeys(primaryKeys);
		table.setColumns(columns);
		table.setIndexes(indexes);
		table.setForeignKeys(foreignKeys);
	}

	protected List<TableContext> getTableContexts(List<String> tables) {
		List<TableContext> contexts = new ArrayList<TableContext>();
		for (int i = 0; i < tables.size(); i++) {
			TableContext context = new TableContext();
			context.setSequence(i + 1);
			context.setName(tables.get(i));
			contexts.add(context);
		}
		return contexts;
	}

	protected String getName() {
		return artifactId;
	}

	/**
	 * Connect to a database and retrieve a list of all the tables for a given schema.
	 */
	protected List<TableContext> getTableList(Platform platform, DataSource dataSource, String schema, List<String> includes, List<String> excludes) throws Exception {
		Connection conn = null;
		try {
			conn = DataSourceUtils.doGetConnection(dataSource);
			DatabaseMetaData metaData = conn.getMetaData();
			List<String> tables = platform.getTableNames(metaData, schema);
			doFilter(tables, includes, excludes, "tables");
			return getTableContexts(tables);
		} finally {
			DataSourceUtils.doCloseConnection(conn, dataSource);
		}
	}

	protected void generateXML(DocumentImpl document, Element databaseNode, Platform platform, List<TableContext> tables) throws Exception {

		databaseNode = document.createElement("database");
		databaseNode.setAttribute("name", getName());
		// JHK added naming method
		databaseNode.setAttribute("defaultJavaNamingMethod", "nochange");

		// processTables(platform, dbMetaData);
		// processViews(platform, dbMetaData);
		// processSequences(platform, dbMetaData);

	}

	public List<String> getViewNames(DatabaseMetaData dbMeta) throws SQLException {
		List<String> views = new ArrayList<String>();
		ResultSet tableNames = null;
		// these are the entity types we want from the database
		String[] types = { "VIEW" }; // JHK: removed views from list
		try {
			tableNames = dbMeta.getTables(null, schema, null, types);
			while (tableNames.next()) {
				String name = tableNames.getString(3);
				views.add(name);
			}
		} finally {
			if (tableNames != null) {
				tableNames.close();
			}
		}
		doFilter(views, viewIncludes, viewExcludes, "views");
		return views;
	}

	/**
	 * Retrieves all the column names and types for a given table from JDBC metadata. It returns a List of Lists. Each element of the
	 * returned List is a List with:
	 *
	 * element 0 => a String object for the column name. element 1 => an Integer object for the column type. element 2 => size of the
	 * column. element 3 => null type.
	 *
	 * @param dbMeta
	 *            JDBC metadata.
	 * @param tableName
	 *            Table from which to retrieve column information.
	 * @return The list of columns in <code>tableName</code>.
	 * @throws SQLException
	 */
	protected List<Column> getColumns(DatabaseMetaData dbMeta, String tableName, String schema) throws SQLException {
		List<Column> columns = new ArrayList<Column>();
		ResultSet columnSet = null;
		try {
			columnSet = dbMeta.getColumns(null, schema, tableName, null);
			while (columnSet.next()) {
				String name = columnSet.getString(4);
				Integer sqlType = new Integer(columnSet.getString(5));
				Integer size = new Integer(columnSet.getInt(7));
				Integer decimalDigits = new Integer(columnSet.getInt(9));
				Integer nullType = new Integer(columnSet.getInt(11));
				String defValue = columnSet.getString(13);

				Column col = new Column();
				col.setName(name);
				col.setSqlType(sqlType);
				col.setSize(size);
				col.setNullType(nullType);
				col.setDefValue(defValue);
				col.setDecimalDigits(decimalDigits);
				columns.add(col);
			}
		} finally {
			closeQuietly(columnSet);
		}
		return columns;
	}

	protected String getOnDelete(ResultSet foreignKeys) throws SQLException {
		int deleteRule = foreignKeys.getInt(11);
		String onDelete = "none";
		if (deleteRule == DatabaseMetaData.importedKeyCascade) {
			onDelete = "cascade";
		} else if (deleteRule == DatabaseMetaData.importedKeyRestrict) {
			onDelete = "restrict";
		} else if (deleteRule == DatabaseMetaData.importedKeySetNull) {
			onDelete = "setnull";
		}
		return onDelete;
	}

	protected String getForeignKeyName(ResultSet foreignKeys, String refTableName) throws SQLException {
		String fkName = foreignKeys.getString(12);
		// if FK has no name - make it up (use tablename instead)
		if (fkName == null) {
			fkName = refTableName;
		}
		return fkName;
	}

	protected ForeignKey getNewKualiForeignKey(String refTableName, String onDelete) {
		ForeignKey fk = new ForeignKey();
		fk.setRefTableName(refTableName); // referenced table name
		fk.setReferences(new ArrayList<Reference>());
		fk.setOnDelete(onDelete);
		return fk;
	}

	protected void addForeignKey(Map<String, ForeignKey> fks, String fkName, String refTableName, String onDelete, ResultSet foreignKeys) throws SQLException {
		ForeignKey fk = (ForeignKey) fks.get(fkName);
		if (fk == null) {
			fk = getNewKualiForeignKey(refTableName, onDelete);
			fks.put(fkName, fk);
		}
		List<Reference> references = fk.getReferences();
		Reference reference = new Reference();
		reference.setLocalColumn(foreignKeys.getString(8)); // local column
		reference.setForeignColumn(foreignKeys.getString(4)); // foreign column
		references.add(reference);
	}

	/**
	 * Retrieves a list of foreign key columns for a given table.
	 *
	 * @param dbMeta
	 *            JDBC metadata.
	 * @param tableName
	 *            Table from which to retrieve FK information.
	 * @return A list of foreign keys in <code>tableName</code>.
	 * @throws SQLException
	 */
	protected Map<String, ForeignKey> getForeignKeys(DatabaseMetaData dbMeta, String tableName, String schema) throws SQLException {
		Map<String, ForeignKey> fks = new HashMap<String, ForeignKey>();
		ResultSet foreignKeys = null;
		try {
			foreignKeys = dbMeta.getImportedKeys(null, schema, tableName);
			while (foreignKeys.next()) {
				String refTableName = foreignKeys.getString(3);
				String fkName = getForeignKeyName(foreignKeys, refTableName);
				String onDelete = getOnDelete(foreignKeys);
				addForeignKey(fks, fkName, refTableName, onDelete, foreignKeys);
			}
		} catch (SQLException e) {
			// this seems to be happening in some db drivers (sybase)
			// when retrieving foreign keys from views.
			logger.warn("Could not read foreign keys for Table " + tableName + " : " + e.getMessage());
		} finally {
			closeQuietly(foreignKeys);
		}
		return fks;
	}

	protected String getPrimaryKeyName(String tableName, DatabaseMetaData dbMeta) throws SQLException {
		ResultSet pkInfo = null;
		try {
			pkInfo = dbMeta.getPrimaryKeys(null, schema, tableName);
			if (pkInfo.next()) {
				return pkInfo.getString("PK_NAME");
			}
		} catch (SQLException e) {
			logger.warn("Could not locate primary key info for " + tableName + " : " + e.getMessage());
		} finally {
			closeQuietly(pkInfo);
		}
		return null;
	}

	protected Index getTableIndex(ResultSet indexInfo, String pkName) throws SQLException {
		Index index = new Index();
		index.setName(indexInfo.getString("INDEX_NAME"));
		index.setUnique(!indexInfo.getBoolean("NON_UNIQUE"));
		return index;
	}

	protected void addIndexIfNotPK(Index index, String pkName, List<Index> indexes) {
		// if has the same name as the PK, don't add it to the index list
		if (pkName == null || !pkName.equals(index.getName())) {
			indexes.add(index);
			logger.debug("Added " + index.getName() + " to index list");
		} else {
			logger.debug("Skipping PK: " + index.getName());
		}
	}

	public List<Index> getIndexes(DatabaseMetaData dbMeta, String tableName, String schema) throws SQLException {
		List<Index> indexes = new ArrayList<Index>();

		// need to ensure that the PK is not returned as an index
		String pkName = getPrimaryKeyName(tableName, dbMeta);

		ResultSet indexInfo = null;
		try {
			indexInfo = dbMeta.getIndexInfo(null, schema, tableName, false, true);
			Index currIndex = null;
			while (indexInfo.next()) {

				// Extract the name of the index
				String name = indexInfo.getString("INDEX_NAME");
				if (name == null) {
					// If there is no name, we are done
					continue;
				}

				// If this is the first time we are assigning a value to currIndex, OR
				// we have scrolled to the next row in the result set and are now on a
				// new index, we need to add to our list of indexes
				if (currIndex == null || !name.equals(currIndex.getName())) {
					// Get a new TableIndex object
					currIndex = getTableIndex(indexInfo, pkName);
					// Add this index to the list if it is not the primary key index
					// The PK is handled elsewhere
					addIndexIfNotPK(currIndex, pkName, indexes);
				}

				// Add column information to the current index
				currIndex.getColumns().add(indexInfo.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			logger.warn("Could not read indexes for Table " + tableName + " : " + e.getMessage());
		} finally {
			closeQuietly(indexInfo);
		}
		return indexes;
	}

	public boolean isProcessTables() {
		return processTables;
	}

	public boolean isProcessViews() {
		return processViews;
	}

	public boolean isProcessSequences() {
		return processSequences;
	}

	public File getSchemaXMLFile() {
		return schemaXMLFile;
	}

	public void setSchemaXMLFile(File schemaXMLFile) {
		this.schemaXMLFile = schemaXMLFile;
	}

	public void setProcessTables(boolean processTables) {
		this.processTables = processTables;
	}

	public void setProcessViews(boolean processViews) {
		this.processViews = processViews;
	}

	public void setProcessSequences(boolean processSequences) {
		this.processSequences = processSequences;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}
}
