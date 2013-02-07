package org.kuali.core.db.torque;

import static java.sql.Types.CLOB;
import static java.sql.Types.DATE;
import static java.sql.Types.TIMESTAMP;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.Table;
import org.apache.torque.engine.platform.Platform;
import org.apache.torque.engine.platform.PlatformFactory;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.util.XMLChar;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

/**
 * This task exports tables from a JDBC accessible database to XML
 */
public class KualiTorqueDataDumpTask extends DumpTask {

	private static final Logger logger = LoggerFactory.getLogger(KualiTorqueDataDumpTask.class);

	Utils utils = new Utils();
	private static final String FS = System.getProperty("file.separator");

	private File buildDirectory;

	/**
	 * The directory where XML files will be written
	 */
	private File dataXMLDir;

	/**
	 * The format to use for dates/timestamps
	 */
	private String dateFormat = "yyyyMMddHHmmss z";

	/**
	 * The formatter that will do the formatting of dates;
	 */
	private SimpleDateFormat dateFormatter;

	/**
	 * If true 2 text files are created in META-INF listing names of tables. First list is exported tables, second list is skipped tables.
	 */
	private boolean printMetaInfLists = true;

	@Override
	protected void showConfiguration() {
		super.showConfiguration();
		logger.info("Exporting to: " + getDataXMLDir().getAbsolutePath());
		logger.info("Date format: \"" + dateFormat + "\" - " + dateFormatter.format(new Date()));
	}

	protected void updateConfiguration(final Platform platform) {
		dateFormatter = new SimpleDateFormat(dateFormat);
	}

	/**
	 * Dump the data to XML files
	 */
	@Override
	public void execute() throws BuildException {

		try {
			Platform platform = PlatformFactory.getPlatformFor(targetDatabase);
			generateXML(platform);
		} catch (Exception e) {
			throw new BuildException(e);
		}
	}

	/**
	 * Generate XML from the data in the tables in the database
	 */
	protected void generateXML(final Platform platform) throws Exception {
		Connection connection = null;

		try {
			connection = dataSource.getConnection();

			// Get metadata about the database
			DatabaseMetaData dbMetaData = connection.getMetaData();

			// Get the correct platform (oracle, mysql etc)
			// Get ALL the table names
			Set<String> tableNames = getSet(getJDBCTableNames(dbMetaData));

			// Filter out tables as appropriate
			doFilter(tableNames, tableIncludes, tableExcludes, "tables");

			// Aggregate some context
			TableHelper helper = new TableHelper();
			helper.setConnection(connection);
			helper.setPlatform(platform);
			helper.setDbMetaData(dbMetaData);
			helper.setTableNames(tableNames);

			// Process the tables
			processTables(helper);
		} finally {
			// closeQuietly(connection);
		}
	}

	/**
	 * Generate a SQL statement that selects all data from the table
	 */
	protected String getDataSelectStatement(final TableHelper helper, final String tableName) throws SQLException {
		StringBuffer sb = new StringBuffer("SELECT * FROM ");
		sb.append(tableName);
		sb.append(" ORDER BY 'x'");
		List<String> pkFields = helper.getPlatform().getPrimaryKeys(helper.getDbMetaData(), getSchema(), tableName);
		for (String field : pkFields) {
			sb.append(", ").append(field);
		}
		return sb.toString();
	}

	/**
	 * Generate an array of Column objects from the result set metadata
	 */
	protected Column[] getColumns(final ResultSetMetaData md) throws SQLException {
		Column[] columns = new Column[md.getColumnCount() + 1];
		for (int i = 1; i <= md.getColumnCount(); i++) {
			Column column = new Column();
			column.setName(md.getColumnName(i));
			column.setJdbcType(md.getColumnType(i));
			columns[i] = column;
		}
		return columns;
	}

	/**
	 * Extract a column value from the result set, converting as needed
	 */
	protected Object getColumnValue(final ResultSet rs, final int index, final Column column, final int rowCount, final String tableName) {
		// Extract a raw object
		Object columnValue = null;
		try {
			columnValue = rs.getObject(index);

			// If it is null we're done
			if (columnValue == null) {
				return null;
			}
			// Handle special types
			switch (column.getJdbcType()) {
			case (CLOB):
				// Extract a CLOB
				return getClob((Clob) columnValue);
			case (DATE):
			case (TIMESTAMP):
				// Extract dates and timestamps
				return getDate(rs, index);
			default:
				// Otherwise return the raw object
				return columnValue;
			}
		} catch (Exception e) {
			// Don't let an issue extracting a value from one column in one row
			// stop the process
			// Log the row/column and continue
			logger.info("Problem reading row " + rowCount + " column " + column.getName() + " from " + tableName, Project.MSG_ERR);
			logger.info(e.getClass().getName() + " : " + e.getMessage(), Project.MSG_ERR);

		}
		return null;
	}

	/**
	 * Convert a JDBC Timestamp into a java.util.Date using the specified format
	 */
	protected String getDate(final ResultSet rs, final int index) throws SQLException {
		Timestamp date = rs.getTimestamp(index);
		return dateFormatter.format(date);
	}

	/**
	 * Convert a CLOB to a String
	 */
	protected String getClob(final Clob clob) throws SQLException {
		Reader r = null;
		StringBuffer sb = new StringBuffer();
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
	 * Convert a row from the result set into an Element
	 */
	protected Element getRow(final DocumentImpl doc, final String tableName, final ResultSetMetaData md, final ResultSet rs, final Column[] columns, final int rowCount)
	        throws SQLException {
		// Generate a row object
		Element row = doc.createElement(tableName);

		// Cycle through the columns
		for (int i = 1; i <= md.getColumnCount(); i++) {

			// Extract a column value
			Object columnValue = getColumnValue(rs, i, columns[i], rowCount, tableName);

			// Null values can be omitted from the XML
			if (columnValue == null) {
				continue;
			}

			// Otherwise, escape the String and add it to the row Element
			row.setAttribute(columns[i].getName(), xmlEscape(columnValue.toString()));
		}

		// Return an Element representing one row of data from the ResultSet
		return row;
	}

	/**
	 * Generate and return the dataset Element
	 */
	protected Element getDatasetNode(final TableHelper helper, final DocumentImpl document, final String tableName) throws SQLException {
		Element datasetNode = document.createElement("dataset");
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// This query selects everything from the table
			String query = getDataSelectStatement(helper, tableName);
			stmt = helper.getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
			ResultSetMetaData md = rs.getMetaData();
			Column[] columns = getColumns(md);
			int count = 0;
			// Process the ResultSet
			while (rs.next()) {
				count++;
				logger.debug("Processing row " + count + " of " + tableName);
				Element row = getRow(document, tableName, md, rs, columns, count);
				datasetNode.appendChild(row);
			}
			helper.setRowCount(count);
			// Keep track of how many rows we found
			if (count == 0) {
				logger.debug("No data found in table " + tableName);
				return null;
			}
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			// Clean up
			// closeQuietly(rs);
			// closeQuietly(stmt);
		}
		return datasetNode;
	}

	/**
	 * Return the systemId to use
	 */
	protected String getSystemId() {
		if (antCompatibilityMode) {
			return "data.dtd";
		} else {
			return getArtifactId() + ".dtd";
		}
	}

	/**
	 * Return the XML Document object that we will serialize to disk
	 */
	protected DocumentImpl getDocument(final TableHelper helper, final String tableName) throws SQLException {
		// Generate the document type
		DocumentTypeImpl docType = new DocumentTypeImpl(null, "dataset", null, getSystemId());
		// Generate an empty document
		DocumentImpl doc = new DocumentImpl(docType);
		// Append a comment
		doc.appendChild(doc.createComment(" " + getComment() + " "));
		// Extract the data from the table
		Element datasetNode = getDatasetNode(helper, doc, tableName);
		if (datasetNode == null) {
			// There was no data (zero rows), we are done
			return null;
		}
		// Add the dataset to the document
		doc.appendChild(datasetNode);
		// Return what we found
		return doc;
	}

	/**
	 * <code>
	 * Convert a List<Table> into a List<String> of table names
	 * </code>
	 */
	protected List<String> getTableNamesFromTableObjects(final List<?> list) {
		List<String> names = new ArrayList<String>();
		for (Object object : list) {
			Table table = (Table) object;
			names.add(table.getName());
		}
		return names;
	}

	/**
	 * Convert a List to a Set
	 *
	 * @param list
	 * @return
	 */
	protected Set<String> getSet(final List<String> list) {
		Set<String> set = new TreeSet<String>();
		set.addAll(list);
		return set;
	}

	/**
	 * Process the tables, keeping track of which tables had at least one row of data
	 */
	protected void processTables(final TableHelper helper) throws IOException, SQLException {
		long start = System.currentTimeMillis();
		int exportCount = 0;
		int skipCount = 0;
		List<String> skippedTables = new ArrayList<String>();
		List<String> exportedTables = new ArrayList<String>();
		for (String tableName : helper.getTableNames()) {
			boolean exported = processTable(helper, tableName);
			if (exported) {
				exportedTables.add(tableName);
				exportCount++;
			} else {
				skippedTables.add(tableName);
				skipCount++;
			}
		}
		long elapsed = System.currentTimeMillis() - start;
		logger.info(utils.pad("Processed " + helper.getTableNames().size() + " tables", elapsed));
		logger.info("Exported data from " + exportCount + " tables to XML");
		logger.info("Skipped " + skipCount + " tables that had zero rows");
		if (printMetaInfLists) {
			String base = buildDirectory.getCanonicalPath();
			String skipped = base + FS + "/META-INF/impex-tables-with-no-rows.txt";
			String exported = base + FS + "/META-INF/impex-exported-tables.txt";
			printTables("Skipped", skipped, skippedTables);
			printTables("Exported", exported, exportedTables);
		}
	}

	protected void printTables(String msg, String filename, List<String> tables) {
		if (tables.size() == 0) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (String table : tables) {
			sb.append(table + "\n");
		}
		OutputStream out = null;
		try {
			File file = new File(filename);
			logger.info(msg + " table list: " + file.getCanonicalPath());
			out = FileUtils.openOutputStream(file);
			out.write(sb.toString().getBytes());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}

	}

	/**
	 * Process one table. Only create an XML file if there is at least one row of data
	 */
	protected boolean processTable(final TableHelper helper, final String tableName) throws SQLException, IOException {
		logger.debug("Processing: " + tableName);
		long ts1 = System.currentTimeMillis();
		DocumentImpl doc = getDocument(helper, tableName);
		long ts2 = System.currentTimeMillis();
		logger.debug(utils.pad("Extracting: " + tableName + " ", ts2 - ts1));
		boolean exported = false;
		if (doc != null) {
			serialize(tableName, doc);
			exported = true;
		}
		long ts3 = System.currentTimeMillis();
		logger.debug(utils.pad("Serializing: " + tableName + " ", ts3 - ts2));
		if (!exported) {
			logger.info(utils.pad("Rows: " + StringUtils.leftPad(helper.getRowCount() + "", 6) + " " + tableName, (ts3 - ts1)));
		} else {
			logger.info(utils.pad("Rows: " + StringUtils.leftPad(helper.getRowCount() + "", 6) + " " + tableName, (ts3 - ts1)));
		}
		return exported;
	}

	/**
	 * This is where the XML will be written to
	 */
	protected Writer getWriter(final String tableName) throws IOException {
		String filename = getDataXMLDir() + FS + tableName + ".xml";
		logger.debug("filename:" + filename);
		return new PrintWriter(FileUtils.openOutputStream(new File(filename)));
	}

	/**
	 * This is the XMLSerializer responsible for outputting the XML document
	 */
	protected XMLSerializer getSerializer(final Writer out) {
		return new XMLSerializer(out, new OutputFormat(Method.XML, getEncoding(), true));
	}

	/**
	 * Serialize the document
	 */
	protected void serialize(final String tableName, final DocumentImpl doc) throws IOException {
		Writer out = null;
		try {
			out = getWriter(tableName);
			XMLSerializer serializer = getSerializer(out);
			serializer.serialize(doc);
			out.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(out);
		}
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
	 * Get the names of all the tables in our schema
	 */
	public List<String> getJDBCTableNames(final DatabaseMetaData dbMeta) throws SQLException {
		// these are the entity types we want from the database
		String[] types = { "TABLE" }; // JHK: removed views from list
		List<String> tables = new ArrayList<String>();
		ResultSet tableNames = null;
		try {
			// JHK: upper-cased schema name (required by Oracle)
			tableNames = dbMeta.getTables(null, getSchema().toUpperCase(), null, types);
			while (tableNames.next()) {
				String name = tableNames.getString(3);
				tables.add(name);
			}
		} finally {
			// closeQuietly(tableNames);
		}
		return tables;
	}

	public File getDataXMLDir() {
		return dataXMLDir;
	}

	public void setDataXMLDir(final File outputDirectory) {
		this.dataXMLDir = outputDirectory;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(final String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public File getBuildDirectory() {
		return buildDirectory;
	}

	public void setBuildDirectory(File buildDirectory) {
		this.buildDirectory = buildDirectory;
	}

	public boolean isPrintMetaInfLists() {
		return printMetaInfLists;
	}

	public void setPrintMetaInfLists(boolean printMetaInfLists) {
		this.printMetaInfLists = printMetaInfLists;
	}
}
