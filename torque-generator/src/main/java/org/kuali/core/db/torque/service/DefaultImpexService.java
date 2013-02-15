package org.kuali.core.db.torque.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.Project;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.apache.torque.engine.database.model.TypeMap;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.apache.torque.task.TorqueDataModelTask;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.util.XMLChar;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleScanner;
import org.kuali.core.db.torque.ImpexDTDResolver;
import org.kuali.core.db.torque.KualiXmlToAppData;
import org.kuali.core.db.torque.StringFilter;
import org.kuali.core.db.torque.pojo.ColumnContext;
import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.DumpTableContext;
import org.kuali.core.db.torque.pojo.DumpTableResult;
import org.kuali.core.db.torque.pojo.ForeignKey;
import org.kuali.core.db.torque.pojo.Index;
import org.kuali.core.db.torque.pojo.Reference;
import org.kuali.core.db.torque.pojo.SchemaRequest;
import org.kuali.core.db.torque.pojo.SchemaRequestBucket;
import org.kuali.core.db.torque.pojo.SchemaRequestHandler;
import org.kuali.core.db.torque.pojo.Sequence;
import org.kuali.core.db.torque.pojo.TableBucket;
import org.kuali.core.db.torque.pojo.TableBucketHandler;
import org.kuali.core.db.torque.pojo.TableContext;
import org.kuali.core.db.torque.pojo.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.util.Assert;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DefaultImpexService implements ImpexService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultImpexService.class);
	private static final String FS = File.separator;

	@Override
	public void convertCsvToSql(ImpexContext context) {
		List<Table> tables = getTables(context);
		SimpleScanner scanner = new SimpleScanner();
		scanner.setBasedir(context.getWorkingDir());
		scanner.setIncludes(new String[] { "*.mpx" });
		List<File> files = scanner.getFiles();
		for (File file : files) {
			String filename = file.getName();
			logger.info("Converting " + filename);
			String tableName = StringUtils.substring(filename, 0, StringUtils.indexOf(filename, "."));
			Table table = getTableDefinition(tableName, tables);
			executeSql(context, table, LocationUtils.getCanonicalPath(file));
		}
	}

	@SuppressWarnings("unchecked")
	protected List<Column> getColumns(Table table) {
		return table.getColumns();
	}

	protected void executeSql(ImpexContext context, Table table, String location) {
		ImpexReader impexReader = context.getPlatform().getImpexReader();
		BufferedReader reader = null;
		OutputStream out = null;
		try {
			reader = LocationUtils.getBufferedReader(location, context.getEncoding());
			out = FileUtils.openOutputStream(new File(context.getWorkingDir() + "/" + table.getName() + ".sql"));
            String insertSql = impexReader.getInsertSql(table, reader, context);
            while (insertSql != null) {
                byte[] bytes = (insertSql + "\n/\n").getBytes(context.getEncoding());
                out.write(bytes);
                insertSql = impexReader.getInsertSql(table, reader, context);
            }
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(reader);
			IOUtils.closeQuietly(out);
		}
	}

	protected void trimQuotes(String[] tokens) {
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			int length = token.length();
			if (StringUtils.startsWith(token, "\"")) {
				token = StringUtils.substring(token, 1);
			}
			if (StringUtils.endsWith(token, "\"")) {
				token = StringUtils.substring(token, 0, length - 1);
			}
			tokens[i] = token;
		}
	}

	protected void unformat(String[] tokens) {
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = ImpexUtils.unformat(tokens[i]);
		}
	}

	protected Table getTableDefinition(String tableName, List<Table> tables) {
		for (Table table : tables) {
			boolean matches = StringUtils.equalsIgnoreCase(tableName, table.getName());
			if (matches) {
				return table;
			}
		}
		throw new IllegalArgumentException("Cannot locate table definition for [" + tableName + "]");
	}

	@SuppressWarnings("unchecked")
	protected List<Table> getTables(ImpexContext context) {
		try {
			// Get an xml parser for schema.xml
			KualiXmlToAppData xmlParser = new KualiXmlToAppData(context.getDatabaseVendor(), "");

			// Parse schema.xml into a database object
			String location = context.getWorkingDir() + "/ks-bundled-db/schema.xml";
			Database database = xmlParser.parseResource(location);

			return database.getTables();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Convert the data from the row into String form
	 */
	protected String[] getRowData(String dateFormat, String tableName, ResultSet rs, Column[] columns, long rowCount) throws SQLException {
		// Allocate some storage
		String[] data = new String[columns.length];

		// Cycle through the columns
		for (int i = 0; i < columns.length; i++) {

			// Extract the column we are working with
			Column column = columns[i];

			// ResultSet column indexes are one based not zero based
			int resultSetColumnIndex = i + 1;

			// Use JDBC to turn the value being held by the database into a Java string
			// The import side of Impex must be able to convert this string back into the native value without losing data
			// TODO Refactor things into a Converter API of some kind
			// TODO Need a richer API for dealing with the conversion of database values to Java strings
			// TODO This would allow for vastly superior handling of date/timestamp/timezone matters (among other things)
			data[i] = getColumnValueAsString(dateFormat, rs, resultSetColumnIndex, column, rowCount, tableName);
		}
		return data;
	}

	/**
	 * Escape characters that would cause issues for XML parsers
	 */
	protected String xmlEscape(final String st) {
		StringBuffer buff = new StringBuffer();
		char[] block = st.toCharArray();
		String stEntity = null;
		int i, last;

		for (i = 0, last = 0; i < block.length; i++) {
			if (XMLChar.isInvalid(block[i])) {
				stEntity = " ";
			}
			if (stEntity != null) {
				buff.append(block, last, i - last);
				buff.append(stEntity);
				stEntity = null;
				last = i + 1;
			}
		}
		if (last < block.length) {
			buff.append(block, last, i - last);
		}
		return buff.toString();
	}

	/**
	 * Convert a CLOB to a String
	 */
	protected String getClob(Clob clob) throws SQLException {
		Reader r = null;
		StringBuilder sb = new StringBuilder();
		try {
			r = clob.getCharacterStream();
			char[] buffer = new char[4096];
			int len;
			while ((len = r.read(buffer)) != -1) {
				sb.append(buffer, 0, len);
			}
		} catch (IOException e) {
			throw new SQLException(e);
		} finally {
			IOUtils.closeQuietly(r);
		}
		return sb.toString();
	}

	/**
	 * Use JDBC to extract the data held by the database into a <code>java.lang.String</code> suitable for dumping to disk. The String
	 * returned by this method must be completely disconnected from the ResultSet and database. Once this method returns, invoking a method
	 * on the underlying ResultSet or otherwise contacting the database to assist with processing the data held in this row/column is
	 * forbidden.
	 */
	protected String getColumnValueAsString(String dateFormat, ResultSet rs, int index, Column column, long rowCount, String tableName) {
		try {
			// Clob's and Date's need special handling
			switch (column.getJdbcType()) {
			case (Types.CLOB):
				Clob clob = rs.getClob(index);
				if (clob == null) {
					return null;
				} else {
					return getClob(clob);
				}
			case (Types.DATE):
			case (Types.TIMESTAMP):
				Timestamp date = rs.getTimestamp(index);
				if (date == null) {
					return null;
				} else {
					// New instance every single time because
					// 1 - SimpleDateFormat is not threadsafe AND
					// 2 - FasteDateFormat does not format UTC offset the same way SimpleDateFormat does
					// That combo of things makes it impossible to use a single pattern to express how dates should be both formatted
					// and parsed
					SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
					return sdf.format(date);
				}
			default:
				// Otherwise just invoke toString() on the method
				Object object = rs.getObject(index);
				if (object == null) {
					return null;
				} else {
					return object.toString();
				}
			}
		} catch (Exception e) {
			// Don't let an issue extracting one value from one column in one row stop the process
			// Log the table/row/column and continue
			logger.warn("Unexpected error reading row " + rowCount + " column " + column.getName() + " from " + tableName);
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public void generateSchemaSql(List<ImpexContext> contexts, List<String> databaseVendors) {
		ImpexUtils.prepareFileSystem(contexts, databaseVendors);
		Project antProject = getInitializedAntProject();
		for (ImpexContext context : contexts) {
			// doAntCompatibilityMode(context);
			for (String databaseVendor : databaseVendors) {
				logger.info("Generating {} schema + constraints SQL for {}", databaseVendor, context.getArtifactId());
				TorqueDataModelTask task = getGenerateSchemaSqlTask(context, antProject, databaseVendor);
				task.execute();
			}
		}
	}

	protected void doAntCompatibilityMode(ImpexContext context) {
		if (context.isAntCompatibilityMode()) {
			// The Ant task requires database.dtd to be on the file system in the same directory as schema.xml if schema.xml
			// was generated with antCompatibilityMode turned on
			File databaseDTD = new File(context.getWorkingDir() + "/database.dtd");
			logger.info("Creating [{}]", LocationUtils.getCanonicalPath(databaseDTD));
			LocationUtils.copyLocationToFile("classpath:database.dtd", databaseDTD);
		}
	}

	@Override
	public void generateDataDtds(List<ImpexContext> contexts) {
		// Generating the data.dtd is currently coupled to Ant because the logic is inside TorqueDataModelTask which extends TexenTask
		// That task has some very specific local file system requirements.
		// It requires the presence of 2 files in specific directories relative to where the data.dtd is being generated
		// Would be highly awesome to de-couple this stuff from Ant entirely
		// The source code of TexenTask doesn't look very complicated, few days work would probably do it
		// ImpexUtils.prepareFileSystem(contexts);
		Project antProject = getInitializedAntProject();
		for (ImpexContext context : contexts) {
			if (context.isAntCompatibilityMode()) {
				// The Ant task requires database.dtd to be on the file system in the same directory as schema.xml if schema.xml
				// was generated with antCompatibilityMode turned on
				File databaseDTD = new File(context.getWorkingDir() + "/database.dtd");
				logger.info("Creating [{}]", LocationUtils.getCanonicalPath(databaseDTD));
				LocationUtils.copyLocationToFile("classpath:database.dtd", databaseDTD);
			}
			TorqueDataModelTask task = getGenerateDtdTask(context, antProject);
			task.execute();
			File dtd = new File(context.getWorkingDir() + "/" + context.getArtifactId() + ".dtd");
			if (context.isAntCompatibilityMode()) {
				File newDtd = new File(context.getWorkingDir() + "/data.dtd");
				logger.info("Creating [{}]", LocationUtils.getCanonicalPath(newDtd));
				dtd.renameTo(newDtd);
			} else {
				logger.info("Creating [{}]", LocationUtils.getCanonicalPath(dtd));
			}
		}
	}

	protected Project getInitializedAntProject() {
		Project antProject = new Project();
		antProject.init();
		return antProject;
	}

	protected TorqueDataModelTask getGenerateSchemaSqlTask(ImpexContext context, Project project, String databaseVendor) {

		ReportFile rf = ImpexUtils.getReportFile(context, databaseVendor);
		File contextPropertiesFile = ImpexUtils.getContextPropertiesFile(context, databaseVendor);
		String contextPropertiesPath = LocationUtils.getCanonicalPath(contextPropertiesFile);
		File outputDirectory = new File(context.getWorkingDir() + FS + databaseVendor);

		TorqueDataModelTask task = new TorqueDataModelTask();
		task.setProject(project);
		task.setOutputDirectory(outputDirectory);
		task.setXmlFile(LocationUtils.getCanonicalPath(context.getSchemaXmlFile()));
		task.setTargetDatabase(databaseVendor);

		task.setContextProperties(contextPropertiesPath);
		task.setUseClasspath(true);
		task.setControlTemplate("sql/base/Control.vm");
		task.setOutputFile(rf.getRelativePath());
		return task;
	}

	protected TorqueDataModelTask getGenerateDtdTask(ImpexContext context, Project project) {
		TorqueDataModelTask task = new TorqueDataModelTask();
		task.setProject(project);
		task.setOutputDirectory(context.getWorkingDir());
		task.setXmlFile(LocationUtils.getCanonicalPath(context.getSchemaXmlFile()));
		task.setTargetDatabase(context.getDatabaseVendor());
		// task.setContextProperties(LocationUtils.getCanonicalPath(context.getContextProperties()));
		task.setUseClasspath(true);
		task.setControlTemplate(context.getControlTemplate());
		// task.setOutputFile(context.getReportFile());
		return task;
	}

	@Override
	public void serializeSchemas(List<ImpexContext> contexts, DatabaseContext database) {
		for (ImpexContext context : contexts) {
			Document document = getSchemaDocument(context, database);
			logger.info("Creating [{}]", LocationUtils.getCanonicalPath(context.getSchemaXmlFile()));
			serialize(document, context.getSchemaXmlFile(), context.getEncoding());
		}
	}

	@Override
	public void serialize(Document document, File file, String encoding) {
		Writer out = null;
		try {
			out = new PrintWriter(FileUtils.openOutputStream(file));
			OutputFormat format = new OutputFormat(Method.XML, encoding, true);
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(document);
		} catch (Exception e) {
			throw new IllegalStateException("Error serializing document to [" + file + "]", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 * Create the top level database node
	 */
	protected Element getDatabaseNode(ImpexContext context, Document document) {
		Assert.notNull(context.getArtifactId(), "artifact id is null");
		Element databaseNode = document.createElement("database");
		databaseNode.setAttribute("name", context.getArtifactId());
		databaseNode.setAttribute("defaultJavaNamingMethod", "nochange");
		return databaseNode;
	}

	protected String getSchemaSystemId(ImpexContext context) {
		if (context.isAntCompatibilityMode()) {
			return "database.dtd";
		} else {
			return ImpexDTDResolver.DTD_LOCATION;
		}
	}

	/**
	 * Return the systemId to use
	 */
	protected String getDatasetSystemId(ImpexContext context) {
		if (context.isAntCompatibilityMode()) {
			return "data.dtd";
		} else {
			return context.getArtifactId() + ".dtd";
		}
	}

	/**
	 * Create and return the top level schema document object
	 */
	protected Document getSchemaDocument(ImpexContext context) {
		DocumentTypeImpl docType = new DocumentTypeImpl(null, "database", null, getSchemaSystemId(context));
		Document document = new DocumentImpl(docType);
		if (!StringUtils.isBlank(context.getComment())) {
			Comment comment = document.createComment(" " + context.getComment() + " ");
			document.appendChild(comment);
		}
		return document;
	}

	@Override
	public Document getSchemaDocument(ImpexContext context, DatabaseContext database) {
		// Get the top level document object
		Document document = getSchemaDocument(context);

		// Use the document to help create the top level database node
		Element databaseNode = getDatabaseNode(context, document);

		// Populate the document with metadata about the tables
		if (context.isProcessTables()) {
			processTables(context, database.getTables(), document, databaseNode);
		}

		// Populate the document with metadata about the views
		if (context.isProcessViews()) {
			processViews(context, database.getViews(), document, databaseNode);
		}

		// Populate the document with metadata about the sequences
		if (context.isProcessSequences()) {
			processSequences(context, database.getSequences(), document, databaseNode);
		}

		// Append the database node to the document
		document.appendChild(databaseNode);

		// Return the fully constructed document object
		return document;
	}

	protected void processSequences(ImpexContext context, List<Sequence> sequences, Document document, Element databaseNode) {
		int excludedCount = 0;
		StringFilter filter = new StringFilter(context.getSequenceIncludes(), context.getSequenceExcludes());
		filter.compilePatterns();
		for (Sequence sequence : sequences) {
			String sequenceName = sequence.getName();
			boolean include = filter.isInclude(sequenceName);
			if (include) {
				Element sequenceElement = document.createElement("sequence");
				sequenceElement.setAttribute("name", sequence.getName());
				sequenceElement.setAttribute("nextval", sequence.getNextVal());
				databaseNode.appendChild(sequenceElement);
			} else {
				excludedCount++;
			}
		}
		logger.debug("Filtered out {} sequences", excludedCount);
	}

	protected void processViews(ImpexContext context, List<View> views, Document document, Element databaseNode) {
		int excludedCount = 0;
		StringFilter filter = new StringFilter(context.getViewIncludes(), context.getViewExcludes());
		filter.compilePatterns();
		for (View view : views) {
			String viewName = view.getName();
			boolean include = filter.isInclude(viewName);
			if (include) {
				Element viewElement = document.createElement("view");
				viewElement.setAttribute("name", view.getName());
				String definition = view.getDefinition().replaceAll("\0", "");
				viewElement.setAttribute("viewdefinition", definition);
				databaseNode.appendChild(viewElement);
			} else {
				excludedCount++;
			}
		}
		logger.debug("Filtered out {} views", excludedCount);
	}

	protected void processTables(ImpexContext context, List<TableContext> tables, Document document, Element databaseNode) {
		StringFilter filter = new StringFilter(context.getTableIncludes(), context.getTableExcludes());
		filter.compilePatterns();
		int excludedCount = 0;
		for (TableContext table : tables) {
			String tableName = table.getName();
			boolean include = filter.isInclude(tableName);
			if (include) {
				processTable(table, document, databaseNode);
			} else {
				excludedCount++;
			}
		}
		logger.debug("Filtered out {} table definitions", excludedCount);
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

	@Override
	public void fillInMetaData(ImpexContext context, TableContext table, DatabaseMetaData metaData) throws SQLException {
		List<String> primaryKeys = context.getPlatform().getPrimaryKeys(metaData, context.getSchema(), table.getName());
		Map<String, ForeignKey> foreignKeys = getForeignKeys(metaData, table.getName(), context.getSchema());
		List<Index> indexes = getIndexes(metaData, table.getName(), context.getSchema());
		List<ColumnContext> columns = getColumns(metaData, table.getName(), context.getSchema());
		String selectAllQuery = getSelectAllQuery(table.getName(), primaryKeys);

		table.setPrimaryKeys(primaryKeys);
		table.setColumns(columns);
		table.setIndexes(indexes);
		table.setForeignKeys(foreignKeys);
		table.setSelectAllQuery(selectAllQuery);
	}

	/**
	 * Generate a new dataset Document object
	 */
	protected Document getDatasetDocument(ImpexContext context, TableContext table) throws SQLException {
		// Generate the document type
		DocumentTypeImpl docType = new DocumentTypeImpl(null, "dataset", null, getDatasetSystemId(context));
		// Generate an empty document
		DocumentImpl document = new DocumentImpl(docType);
		// Append a comment
		document.appendChild(document.createComment(" " + context.getComment() + " "));
		// Return the document
		return document;
	}

	/**
	 * Dump tables as appropriate to disk.
	 */
	@Override
	public List<DumpTableResult> dumpTables(ImpexContext context, DatabaseContext database) {

		List<TableContext> tables = database.getTables();

		fillInTableMetaData(context, tables);

		List<DumpTableResult> results = new ArrayList<DumpTableResult>();

		// Print a dot any time we complete 1% of our requests
		PercentCompleteInformer progressTracker = new PercentCompleteInformer();
		progressTracker.setTotal(tables.size());

		// Each bucket holds a bunch of requests
		List<TableBucket> buckets = getTableBuckets(tables, context, results, progressTracker);

		// Create and invoke threads to fill in the metadata
		ExecutionStatistics stats = invokeThreads(buckets, new TableBucketHandler());
		String time = FormatUtils.getTime(stats.getExecutionTime());
		logger.info("Dump tables completed.  Time: {}", time);
		logger.info("Disconnecting from database.");
		return results;

	}

	protected List<TableBucket> getTableBuckets(List<TableContext> tables, ImpexContext context, List<DumpTableResult> results, PercentCompleteInformer progressTracker) {
		// number of buckets equals thread count, unless thread count > total number of sources
		int bucketCount = Math.min(context.getDataThreads(), tables.size());
		// Sort the sources by size
		Collections.sort(tables);
		// Largest to smallest instead of smallest to largest
		Collections.reverse(tables);
		// Allocate some buckets to hold the sql
		List<TableBucket> buckets = CollectionUtils.getNewList(TableBucket.class, bucketCount);
		// Distribute the sources into buckets as evenly as possible
		// "Evenly" in this case means each bucket should be roughly the same size
		for (TableContext table : tables) {
			// Sort the buckets by size
			Collections.sort(buckets);
			// First bucket in the list is the smallest
			TableBucket smallest = buckets.get(0);
			// Add this source to the bucket
			smallest.getTables().add(table);
			// Update the bucket metadata holding overall size
			smallest.setRowCount(smallest.getRowCount() + table.getRowCount());
			smallest.setSize(smallest.getSize() + table.getSize());
		}
		for (TableBucket bucket : buckets) {
			bucket.setProgressTracker(progressTracker);
			bucket.setContext(context);
			bucket.setService(this);
			bucket.setResults(results);
			// Randomize the order in which tables get dumped
			Collections.shuffle(bucket.getTables());
		}
		return buckets;
	}

	protected void fillInTableMetaData(ImpexContext context, List<TableContext> tables) {
		if (!LocationUtils.exists(context.getDatabaseTablePropertiesLocation())) {
			return;
		}
		Properties props = PropertyUtils.load(context.getDatabaseTablePropertiesLocation());
		for (TableContext table : tables) {
			String name = table.getName();
			String key = name.toLowerCase();
			String sizeKey = key + ".size";
			String rowsKey = key + ".rows";
			String sizeValue = props.getProperty(sizeKey);
			String rowsValue = props.getProperty(rowsKey);
			if (sizeValue != null) {
				table.setSize(new Long(sizeValue));
				table.setRowCount(new Long(rowsValue));
			}
		}
	}

	/**
	 * Dump the contents of the indicated table to disk
	 */
	@Override
	public DumpTableResult dumpTable(ImpexContext context, TableContext table, Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		long start = System.currentTimeMillis();
		try {
			String query = table.getSelectAllQuery();
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
			DumpTableResult result = dumpTable(context, table, rs);
			result.setFinish(System.currentTimeMillis());
			result.setStart(start);
			result.setElapsed(result.getFinish() - start);
			return result;
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			closeQuietly(rs);
			closeQuietly(stmt);
		}
	}

	protected DumpTableResult dumpTable(ImpexContext context, TableContext table, ResultSet rs) throws SQLException {
		OutputStream out = null;
		try {
			out = context.getDataHandler().openOutputStream(context.getWorkingDir(), table.getName());
			Column[] columns = getColumns(rs.getMetaData());
			long totalDataSize = 0;
			long totalRowCount = 0;
			long currentRowCount = 0;
			long currentDataSize = 0;
			List<String[]> data = new ArrayList<String[]>();
			DumpTableContext startContext = getDumpTableContext(out, columns, data, currentDataSize, context, currentRowCount, totalRowCount, table, totalDataSize);
			context.getDataHandler().startData(startContext);
			while (rs.next()) {
				currentRowCount++;
				totalRowCount++;
				String[] rowData = getRowData(context.getDateFormat(), table.getName(), rs, columns, totalRowCount);
				data.add(rowData);
				long rowSize = getSize(rowData);
				currentDataSize += rowSize;
				totalDataSize += rowSize;
				if (currentRowCount > context.getRowCountInterval() || currentDataSize > context.getDataSizeInterval()) {
					DumpTableContext doDataContext = getDumpTableContext(out, columns, data, currentDataSize, context, currentRowCount, totalRowCount, table, totalDataSize);
					context.getDataHandler().doData(doDataContext);
					currentDataSize = 0;
					currentRowCount = 0;
					data = new ArrayList<String[]>();
				}
			}
			DumpTableContext finishDataContext = getDumpTableContext(out, columns, data, currentDataSize, context, currentRowCount, totalRowCount, table, totalDataSize);
			context.getDataHandler().finishData(finishDataContext);
			DumpTableResult result = new DumpTableResult();
			result.setTable(table);
			result.setRows(totalRowCount);
			result.setSize(totalDataSize);
			return result;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected DumpTableContext getDumpTableContext(OutputStream out, Column[] columns, List<String[]> data, long cds, ImpexContext context, long crc, long trc, TableContext table,
	        long tds) {
		DumpTableContext dtc = new DumpTableContext();
		dtc.setOutputStream(out);
		dtc.setColumns(columns);
		dtc.setCurrentData(data);
		dtc.setCurrentDataSize(cds);
		dtc.setImpexContext(context);
		dtc.setCurrentRowCount(crc);
		dtc.setTotalRowCount(trc);
		dtc.setTableContext(table);
		dtc.setTotalDataSize(tds);
		return dtc;
	}

	protected long getSize(String[] data) {
		long size = 0;
		for (String s : data) {
			size += ((s == null) ? 0 : s.length());
		}
		return size;
	}

	protected void closeQuietly(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	/**
	 * Generate an array of Column objects from the result set metadata
	 */
	protected Column[] getColumns(ResultSetMetaData md) throws SQLException {
		Column[] columns = new Column[md.getColumnCount()];
		for (int i = 0; i < columns.length; i++) {
			Column column = new Column();
			column.setName(md.getColumnName(i + 1));
			column.setJdbcType(md.getColumnType(i + 1));
			columns[i] = column;
		}
		return columns;
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
			closeQuietly(columnSet);
		}
		return columns;
	}

	protected void closeQuietly(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	protected String getForeignKeyName(ResultSet foreignKeys, String refTableName) throws SQLException {
		String fkName = foreignKeys.getString(12);
		// if FK has no name - make it up (use tablename instead)
		if (fkName == null) {
			fkName = refTableName;
		}
		return fkName;
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

	protected ForeignKey getNewKualiForeignKey(String refTableName, String onDelete) {
		ForeignKey fk = new ForeignKey();
		fk.setRefTableName(refTableName); // referenced table name
		fk.setReferences(new ArrayList<Reference>());
		fk.setOnDelete(onDelete);
		return fk;
	}

	protected String getPrimaryKeyName(String tableName, String schema, DatabaseMetaData dbMeta) throws SQLException {
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

	protected List<String> getPrimaryKeys(Platform platform, DatabaseMetaData metaData, String table, String schema) throws SQLException {
		return platform.getPrimaryKeys(metaData, schema, table);
	}

	protected Index getTableIndex(ResultSet indexInfo, String pkName) throws SQLException {
		Index index = new Index();
		index.setName(indexInfo.getString("INDEX_NAME"));
		index.setUnique(!indexInfo.getBoolean("NON_UNIQUE"));
		return index;
	}

	public List<Index> getIndexes(DatabaseMetaData dbMeta, String tableName, String schema) throws SQLException {
		List<Index> indexes = new ArrayList<Index>();

		// need to ensure that the PK is not returned as an index
		String pkName = getPrimaryKeyName(tableName, schema, dbMeta);

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

	protected void addIndexIfNotPK(Index index, String pkName, List<Index> indexes) {
		// if has the same name as the PK, don't add it to the index list
		if (pkName == null || !pkName.equals(index.getName())) {
			indexes.add(index);
			logger.debug("Added " + index.getName() + " to index list");
		} else {
			logger.debug("Skipping PK: " + index.getName());
		}
	}

	/**
	 * Use JDBC calls to obtain the list of table names, view names, and sequence names for a given schema
	 */
	@Override
	public DatabaseContext getDatabaseObjectLists(ImpexContext context) throws SQLException {
		DatabaseContext database = new DatabaseContext();

		Platform platform = PlatformFactory.getPlatformFor(context.getDatabaseVendor());
		context.setPlatform(platform);

		// Add in tables and views
		long start = System.currentTimeMillis();
		fillInDatabaseObjectLists(database, context);
		logger.info("Database object lists created.  Time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		return database;
	}

	protected List<SchemaRequest> getSchemaRequests(DatabaseContext database) {
		List<SchemaRequest> requests = new ArrayList<SchemaRequest>();
		for (TableContext table : CollectionUtils.toEmptyList(database.getTables())) {
			SchemaRequest request = new SchemaRequest();
			request.setTable(table);
			requests.add(request);
		}
		for (View view : CollectionUtils.toEmptyList(database.getViews())) {
			SchemaRequest request = new SchemaRequest();
			request.setView(view);
			requests.add(request);
		}
		for (Sequence sequence : CollectionUtils.toEmptyList(database.getSequences())) {
			SchemaRequest request = new SchemaRequest();
			request.setSequence(sequence);
			requests.add(request);
		}
		return requests;
	}

	@Override
	public void fillInMetaData(ImpexContext context, DatabaseContext database) throws SQLException {

		// Aggregate into a single list all of the tables, views, and sequences we need to acquire info about
		List<SchemaRequest> requests = getSchemaRequests(database);

		logger.info("Acquiring metadata for {} database objects", requests.size());

		// Nothing to do
		if (CollectionUtils.isEmpty(requests)) {
			return;
		}

		// We want each thread to have approximately the same mix of tables/views/sequences to deal with
		Collections.shuffle(requests);

		// Divide the list up as evenly as possible
		List<List<SchemaRequest>> listOfLists = CollectionUtils.splitEvenly(requests, context.getMetaDataThreads());

		// Print a dot any time we complete 1% of our requests
		PercentCompleteInformer progressTracker = new PercentCompleteInformer();
		progressTracker.setTotal(requests.size());

		// Each bucket holds a bunch of requests
		List<SchemaRequestBucket> buckets = new ArrayList<SchemaRequestBucket>();
		for (List<SchemaRequest> list : listOfLists) {
			SchemaRequestBucket bucket = new SchemaRequestBucket();
			bucket.setProgressTracker(progressTracker);
			bucket.setDataSource(context.getDataSource());
			bucket.setRequests(list);
			bucket.setImpexContext(context);
			bucket.setImpexService(this);
			buckets.add(bucket);
		}

		// Create and invoke threads to fill in the metadata
		ExecutionStatistics stats = invokeThreads(buckets, new SchemaRequestHandler());
		String time = FormatUtils.getTime(stats.getExecutionTime());
		logger.info("Metadata acquired.  Time: {}", time);
		logger.info("Disconnecting from database.");
	}

	protected <T> ExecutionStatistics invokeThreads(List<T> buckets, ElementHandler<T> handler) {
		// Store some context for the thread handler
		ThreadHandlerContext<T> thc = new ThreadHandlerContext<T>();
		thc.setList(buckets);
		thc.setHandler(handler);
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);

		// Start threads to acquire table metadata concurrently
		ThreadInvoker invoker = new ThreadInvoker();
		return invoker.invokeThreads(thc);
	}

	protected void doFilter(Collection<String> elements, List<String> includes, List<String> excludes, String label) {
		int all = elements.size();
		StringFilter filterer = new StringFilter(includes, excludes);
		filterer.filter(elements.iterator());
		int remaining = elements.size();
		int diff = all - remaining;
		Object[] args = { StringUtils.rightPad(label, 12, " "), lpad(all), lpad(diff), lpad(remaining) };
		logger.info("{} - {}, filtered out - {}, remaining - {}", args);
	}

	// Left pad numbers with 5 digits or less
	protected String lpad(int smallNumber) {
		return StringUtils.leftPad(smallNumber + "", 5, " ");
	}

	/**
	 * Connect to a database and retrieve a list of all the tables for a given schema.
	 */
	protected List<TableContext> getTableList(DatabaseMetaData metaData, ImpexContext context) throws SQLException {
		List<String> tables = context.getPlatform().getTableNames(metaData, context.getSchema());
		doFilter(tables, context.getTableIncludes(), context.getTableExcludes(), "tables");
		return getTableContexts(tables);
	}

	/**
	 * Connect to a database and retrieve tables/views
	 */
	protected void fillInDatabaseObjectLists(DatabaseContext database, ImpexContext context) throws SQLException {
		logger.info("Opening database connection.");

		Connection conn = null;
		try {
			// Connect to the database
			conn = DataSourceUtils.getConnection(context.getDataSource());

			logger.info("Generating database object lists.");

			// Extract JDBC's metadata object
			DatabaseMetaData metaData = conn.getMetaData();

			// Convert JDBC metadata into a list of tables names
			if (context.isProcessTables()) {
				List<TableContext> tables = getTableList(metaData, context);
				database.setTables(tables);
			}

			// Convert JDBC metadata into a list of view names
			if (context.isProcessViews()) {
				List<String> names = getViewNames(metaData, context);
				List<View> views = getViews(names);
				database.setViews(views);
			}

			// Acquire a list of sequence names (invokes platform specific logic)
			if (context.isProcessSequences()) {
				List<String> names = getSequences(database, metaData, context);
				List<Sequence> sequences = getSequences(names);
				database.setSequences(sequences);
			}
		} finally {
			// Close the connection
			closeQuietly(conn, context.getDataSource());
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

	protected List<String> getSequences(DatabaseContext database, DatabaseMetaData metaData, ImpexContext context) throws SQLException {
		List<String> sequences = context.getPlatform().getSequenceNames(metaData, context.getSchema());
		doFilter(sequences, context.getSequenceIncludes(), context.getSequenceExcludes(), "sequences");
		return sequences;
	}

	public void closeQuietly(Connection conn, DataSource dataSource) {
		if (conn != null) {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	public List<String> getViewNames(DatabaseMetaData metaData, ImpexContext context) throws SQLException {
		List<String> views = new ArrayList<String>();
		ResultSet viewNames = null;
		String[] types = { "VIEW" };
		try {
			viewNames = metaData.getTables(null, context.getSchema(), null, types);
			while (viewNames.next()) {
				String name = viewNames.getString(3);
				views.add(name);
			}
		} finally {
			if (viewNames != null) {
				viewNames.close();
			}
		}
		doFilter(views, context.getViewIncludes(), context.getViewExcludes(), "views");
		return views;
	}

	protected List<TableContext> getTableContexts(List<String> tables) {
		List<TableContext> contexts = new ArrayList<TableContext>();
		for (int i = 0; i < tables.size(); i++) {
			TableContext context = new TableContext();
			context.setName(tables.get(i));
			contexts.add(context);
		}
		return contexts;
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

	/**
	 * Generate a SQL statement that selects all data from the table
	 */
	protected String getSelectAllQuery(String tableName, List<String> primaryKeys) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(tableName);
		sb.append(" ORDER BY 'x'");
		// Order by primary keys (if any) so the data always comes out in a deterministic order
		for (String primaryKey : primaryKeys) {
			sb.append(", ").append(primaryKey);
		}
		return sb.toString();
	}
}
