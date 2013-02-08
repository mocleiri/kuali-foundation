package org.kuali.core.db.torque;

import java.io.File;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.torque.engine.database.model.TypeMap;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.core.db.torque.pojo.ColumnContext;
import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.ForeignKey;
import org.kuali.core.db.torque.pojo.Index;
import org.kuali.core.db.torque.pojo.Reference;
import org.kuali.core.db.torque.pojo.SchemaRequest;
import org.kuali.core.db.torque.pojo.SchemaRequestBucket;
import org.kuali.core.db.torque.pojo.SchemaRequestHandler;
import org.kuali.core.db.torque.pojo.Sequence;
import org.kuali.core.db.torque.pojo.TableContext;
import org.kuali.core.db.torque.pojo.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class KualiTorqueSchemaDumpTask extends DumpTask {

	private static final Logger logger = LoggerFactory.getLogger(KualiTorqueSchemaDumpTask.class);

	boolean processTables = true;
	boolean processViews = true;
	boolean processSequences = true;
	int threads = 15;
	File schemaXMLFile;

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
			// Oracle, mysql, etc
			Platform platform = PlatformFactory.getPlatformFor(targetDatabase);

			// Connect to the database and get a list of tables/views to process
			DatabaseContext database = getInitialMetaData(platform, this);

			// Use multiple threads to pull in metadata concurrently
			fillInMetaData(database, dataSource, threads);

			// Create a document object from the metadata
			Document document = getDocument(database, this);

			// Serialize the document object as XML to the file system
			logger.info("Creating [{}]", LocationUtils.getCanonicalPath(schemaXMLFile));
			serialize(document, schemaXMLFile, encoding);
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

	protected Document getDocument(DatabaseContext database, KualiTorqueSchemaDumpTask task) {
		// Get the top level document object
		Document document = getDocument();

		// Use the document to help create the top level database node
		Element databaseNode = getDatabaseNode(document);

		// Populate the document with metadata about the tables
		if (task.isProcessTables()) {
			processTables(database.getTables(), document, databaseNode);
		}

		// and views
		if (task.isProcessViews()) {
			processViews(database.getViews(), document, databaseNode);
		}

		// and sequences
		if (task.isProcessSequences()) {
			processSequences(database.getSequences(), document, databaseNode);
		}

		// Append the database node to the document
		document.appendChild(databaseNode);

		// Return the fully constructed document object
		return document;
	}

	protected void processSequences(List<Sequence> sequences, Document document, Element databaseNode) {
		for (Sequence sequence : sequences) {
			Element sequenceElement = document.createElement("sequence");
			sequenceElement.setAttribute("name", sequence.getName());
			sequenceElement.setAttribute("nextval", sequence.getNextVal());
			databaseNode.appendChild(sequenceElement);
		}
	}

	protected void processViews(List<View> views, Document document, Element databaseNode) {
		for (View view : views) {
			Element viewElement = document.createElement("view");
			viewElement.setAttribute("name", view.getName());
			String definition = view.getDefinition().replaceAll("\0", "");
			viewElement.setAttribute("viewdefinition", definition);
			databaseNode.appendChild(viewElement);
		}
	}

	protected DatabaseContext getInitialMetaData(Platform platform, KualiTorqueSchemaDumpTask task) throws SQLException {
		DatabaseContext context = new DatabaseContext();

		// Add in tables and views
		long start = System.currentTimeMillis();
		fillInContext(context, task);
		logger.info("Database object lists created.  Time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		return context;
	}

	protected void processTables(List<TableContext> tables, Document document, Element databaseNode) {
		for (TableContext table : tables) {
			processTable(table, document, databaseNode);
		}
	}

	protected void processTable(TableContext table, Document document, Element databaseNode) {
		Element tableElement = document.createElement("table");
		tableElement.setAttribute("name", table.getName());
		processColumns(table, document, tableElement);
		processForeignKeys(table, document, tableElement);
		processIndexes(table, document, tableElement);
		databaseNode.appendChild(tableElement);
	}

	protected void processColumns(TableContext context, Document document, Element tableElement) {
		for (ColumnContext column : context.getColumns()) {
			Element columnElement = getColumnElement(column, context, document);
			tableElement.appendChild(columnElement);
		}
	}

	protected Element getColumnElement(ColumnContext col, TableContext context, Document document) {
		String name = col.getName();
		Integer type = col.getSqlType();
		int size = col.getSize();
		int scale = col.getDecimalDigits();

		Integer nullType = col.getNullType();
		String defValue = col.getDefValue();

		Element column = document.createElement("column");
		column.setAttribute("name", name);

		column.setAttribute("type", TypeMap.getTorqueType(type).getName());

		if (size > 0
		        && (type.intValue() == Types.CHAR || type.intValue() == Types.VARCHAR || type.intValue() == Types.LONGVARCHAR || type.intValue() == Types.DECIMAL || type
		                .intValue() == Types.NUMERIC)) {
			column.setAttribute("size", String.valueOf(size));
		}

		if (scale > 0 && (type.intValue() == Types.DECIMAL || type.intValue() == Types.NUMERIC)) {
			column.setAttribute("scale", String.valueOf(scale));
		}

		if (context.getPrimaryKeys().contains(name)) {
			column.setAttribute("primaryKey", "true");
			// JHK: protect MySQL from excessively long column in the PK
			// System.out.println( curTable + "." + name + " / " + size );
			if (column.getAttribute("size") != null && size > 765) {
				logger.debug("updating column " + context.getName() + "." + name + " length from " + size + " to 255");
				column.setAttribute("size", "255");
			}
		} else {
			if (nullType.intValue() == DatabaseMetaData.columnNoNulls) {
				column.setAttribute("required", "true");
			}
		}

		if (StringUtils.isNotBlank(defValue)) {
			defValue = getDefaultValue(defValue);
			column.setAttribute("default", defValue);
		}
		return column;
	}

	protected void processForeignKeys(TableContext context, Document document, Element tableElement) {
		for (String fkName : context.getForeignKeys().keySet()) {
			Element fk = getForeignKeyElement(fkName, context.getForeignKeys(), document);
			tableElement.appendChild(fk);
		}
	}

	protected void processIndexes(TableContext context, Document document, Element tableElement) {
		for (Index idx : context.getIndexes()) {
			String tagName = idx.isUnique() ? "unique" : "index";
			Element index = document.createElement(tagName);
			index.setAttribute("name", idx.getName());
			for (String colName : idx.getColumns()) {
				Element col = document.createElement(tagName + "-column");
				col.setAttribute("name", colName);
				index.appendChild(col);
			}
			tableElement.appendChild(index);
		}
	}

	protected Element getForeignKeyElement(String fkName, Map<String, ForeignKey> foreignKeys, Document document) {
		Element fk = document.createElement("foreign-key");
		fk.setAttribute("name", fkName);
		ForeignKey forKey = foreignKeys.get(fkName);
		String foreignKeyTable = forKey.getRefTableName();
		List<Reference> refs = forKey.getReferences();
		fk.setAttribute("foreignTable", foreignKeyTable);
		String onDelete = forKey.getOnDelete();
		// gmcgrego - just adding onDelete if it's cascade so as not to affect kfs behavior
		if (onDelete == "cascade") {
			fk.setAttribute("onDelete", onDelete);
		}
		for (Reference refData : refs) {
			Element ref = document.createElement("reference");
			ref.setAttribute("local", refData.getLocalColumn());
			ref.setAttribute("foreign", refData.getForeignColumn());
			fk.appendChild(ref);
		}
		return fk;
	}

	protected String getDefaultValue(String defValue) {
		if (StringUtils.isBlank(defValue)) {
			return null;
		}
		defValue = defValue.trim();
		// trim out parens & quotes out of def value.
		// makes sense for MSSQL. not sure about others.
		if (defValue.startsWith("(") && defValue.endsWith(")")) {
			defValue = defValue.substring(1, defValue.length() - 1);
		}

		if (defValue.startsWith("'") && defValue.endsWith("'")) {
			defValue = defValue.substring(1, defValue.length() - 1);
		}
		if (defValue.equals("NULL")) {
			defValue = "";
		}
		return defValue;
	}

	/**
	 * Create the top level database node
	 */
	protected Element getDatabaseNode(Document document) {
		Element databaseNode = document.createElement("database");
		databaseNode.setAttribute("name", getName());
		databaseNode.setAttribute("defaultJavaNamingMethod", "nochange");
		return databaseNode;
	}

	protected String getSystemId() {
		if (antCompatibilityMode) {
			return "database.dtd";
		} else {
			return ImpexDTDResolver.DTD_LOCATION;
		}
	}

	/**
	 * Create and return the top level Document object
	 */
	protected Document getDocument() {
		DocumentTypeImpl docType = new DocumentTypeImpl(null, "database", null, getSystemId());
		Document document = new DocumentImpl(docType);
		Comment comment = document.createComment(" " + getComment() + " ");
		document.appendChild(comment);
		return document;
	}

	protected void serialize(Document document, File file, String encoding) {
		Writer out = null;
		try {
			out = new PrintWriter(FileUtils.openOutputStream(file));
			OutputFormat format = new OutputFormat(Method.XML, encoding, true);
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);
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

	protected void fillInMetaData(DatabaseContext database, DataSource dataSource, int threads) throws SQLException {

		// Aggregate into a single list all of the tables, views, and sequences we need to acquire info about
		List<SchemaRequest> requests = getJdbcContexts(database);

		logger.info("Acquiring metadata for {} database objects", requests.size());

		// Nothing to do
		if (CollectionUtils.isEmpty(requests)) {
			return;
		}

		// We want each thread to have approximately the same mix of tables/views/sequences to deal with
		Collections.shuffle(requests);

		// Divide the list up as evenly as possible
		List<List<SchemaRequest>> listOfLists = CollectionUtils.splitEvenly(requests, threads);

		// Print a dot any time we complete 1% of our requests
		PercentCompleteInformer progressTracker = new PercentCompleteInformer();
		progressTracker.setTotal(requests.size());

		// Each bucket holds a bunch of requests
		List<SchemaRequestBucket> buckets = new ArrayList<SchemaRequestBucket>();
		for (List<SchemaRequest> list : listOfLists) {
			SchemaRequestBucket bucket = new SchemaRequestBucket();
			bucket.setProgressTracker(progressTracker);
			bucket.setDataSource(dataSource);
			bucket.setRequests(list);
			buckets.add(bucket);
		}

		// Create and invoke threads to fill in the metadata
		invokeThreads(buckets);
	}

	protected List<SchemaRequest> getJdbcContexts(DatabaseContext database) {
		List<SchemaRequest> contexts = new ArrayList<SchemaRequest>();
		for (TableContext table : CollectionUtils.toEmptyList(database.getTables())) {
			SchemaRequest jc = new SchemaRequest();
			jc.setTable(table);
			contexts.add(jc);
		}
		for (View view : CollectionUtils.toEmptyList(database.getViews())) {
			SchemaRequest jc = new SchemaRequest();
			jc.setView(view);
			contexts.add(jc);
		}
		for (Sequence sequence : CollectionUtils.toEmptyList(database.getSequences())) {
			SchemaRequest jc = new SchemaRequest();
			jc.setSequence(sequence);
			contexts.add(jc);
		}
		return contexts;
	}

	protected void invokeThreads(List<SchemaRequestBucket> buckets) {
		// Store some context for the thread handler
		ThreadHandlerContext<SchemaRequestBucket> thc = new ThreadHandlerContext<SchemaRequestBucket>();
		thc.setList(buckets);
		thc.setHandler(new SchemaRequestHandler());
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);

		// Start threads to acquire table metadata concurrently
		ThreadInvoker invoker = new ThreadInvoker();
		ExecutionStatistics stats = invoker.invokeThreads(thc);
		String time = FormatUtils.getTime(stats.getExecutionTime());
		logger.info("Metadata acquired.  Time: {}", time);
		logger.info("Disconnecting from database.");
	}

	public void fillInMetaData(TableContext table, DatabaseContext db, DatabaseMetaData metaData) throws SQLException {
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

	/**
	 * Connect to a database and retrieve tables/views
	 */
	protected void fillInContext(DatabaseContext context, KualiTorqueSchemaDumpTask task) throws SQLException {
		logger.info("Opening database connection.");

		Connection conn = null;
		try {
			// Connect to the database
			conn = DataSourceUtils.getConnection(dataSource);

			logger.info("Generating database object lists.");

			// Extract JDBC's metadata object
			DatabaseMetaData metaData = conn.getMetaData();

			// Convert JDBC metadata into a list of tables names
			if (task.isProcessTables()) {
			}

			// Convert JDBC metadata into a list of view names
			if (task.isProcessViews()) {
				List<String> names = getViewNames(metaData);
				List<View> views = getViews(names);
				context.setViews(views);
			}

			// Acquire a list of sequence names (invokes platform specific logic)
			if (task.isProcessSequences()) {
				List<String> names = getSequences(context, metaData, task);
				List<Sequence> sequences = getSequences(names);
				context.setSequences(sequences);
			}
		} finally {
			// Close the connection
			closeQuietly(conn, dataSource);
		}
	}

	protected List<Sequence> getSequences(List<String> names) {
		List<Sequence> sequences = new ArrayList<Sequence>();
		for (String name : names) {
			Sequence sequence = new Sequence();
			sequence.setName(name);
			sequences.add(sequence);
		}
		return sequences;
	}

	protected List<View> getViews(List<String> names) {
		List<View> views = new ArrayList<View>();
		for (String name : names) {
			View view = new View();
			view.setName(name);
			views.add(view);
		}
		return views;
	}

	protected List<String> getSequences(DatabaseContext context, DatabaseMetaData metaData, KualiTorqueSchemaDumpTask task) throws SQLException {
		return null;
	}

	/**
	 * Connect to a database and retrieve a list of all the tables for a given schema.
	 */
	protected List<TableContext> getTableList(DatabaseMetaData metaData, Platform platform, DumpTask task) throws SQLException {
		List<String> tables = platform.getTableNames(metaData, task.getSchema());
		doFilter(tables, task.getTableIncludes(), task.getTableExcludes(), "tables");
		return getTableContexts(tables);
	}

	public void closeQuietly(Connection conn, DataSource dataSource) {
		if (conn != null) {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	protected void generateXML(Document document, Element databaseNode, Platform platform, List<TableContext> tables) throws Exception {

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
	protected List<ColumnContext> getColumns(DatabaseMetaData dbMeta, String tableName, String schema) throws SQLException {
		List<ColumnContext> columns = new ArrayList<ColumnContext>();
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

				ColumnContext col = new ColumnContext();
				col.setName(name);
				col.setSqlType(sqlType);
				col.setSize(size);
				col.setNullType(nullType);
				col.setDefValue(defValue);
				col.setDecimalDigits(decimalDigits);
				columns.add(col);
			}
		} finally {
			// JDBCUtils.closeQuietly(columnSet);
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
			// JDBCUtils.closeQuietly(foreignKeys);
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
			// JDBCUtils.closeQuietly(pkInfo);
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
			// JDBCUtils.closeQuietly(indexInfo);
		}
		return indexes;
	}

	protected String getName() {
		return artifactId;
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

	public int getThreads() {
		return threads;
	}

	public void setThreads(int threads) {
		this.threads = threads;
	}
}
