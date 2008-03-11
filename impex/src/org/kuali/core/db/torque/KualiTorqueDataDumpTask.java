package org.kuali.core.db.torque;

import static org.kuali.core.db.torque.LogManager.exception;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xerces.util.XMLChar;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class KualiTorqueDataDumpTask extends Task {
    
    private String jdbcCollectionServiceName;

    /** Database URL used for JDBC connection. */
    private String databaseUrl;

    /** Database driver used for JDBC connection. */
    private String databaseDriver;

    /** Database user used for JDBC connection. */
    private String databaseUser;

    /** Database user used for JDBC connection. */
    private String databaseSchema;

    /** Database password used for JDBC connection. */
    private String databasePassword;

    /** The database connection used to retrieve the data to dump. */
    private Connection conn;

    private String outputDirectory;

    public String getJdbcCollectionServiceName() {
        return jdbcCollectionServiceName;
    }

    public void setJdbcCollectionServiceName(String jdbcCollectionServiceName) {
        this.jdbcCollectionServiceName = jdbcCollectionServiceName;
    }

    /**
     * Get the database url
     * 
     * @return The DatabaseUrl value
     */
    public String getDatabaseUrl() {
        return databaseUrl;
    }

    /**
     * Set the database url
     * 
     * @param v The new DatabaseUrl value
     */
    public void setDatabaseUrl(String v) {
        databaseUrl = v;
    }

    /**
     * Get the database driver name
     * 
     * @return String database driver name
     */
    public String getDatabaseDriver() {
        return databaseDriver;
    }

    /**
     * Set the database driver name
     * 
     * @param v The new DatabaseDriver value
     */
    public void setDatabaseDriver(String v) {
        databaseDriver = v;
    }

    /**
     * Get the database user
     * 
     * @return String database user
     */
    public String getDatabaseUser() {
        return databaseUser;
    }

    /**
     * Set the database user
     * 
     * @param v The new DatabaseUser value
     */
    public void setDatabaseUser(String v) {
        databaseUser = v;
    }

    /**
     * Get the database password
     * 
     * @return String database password
     */
    public String getDatabasePassword() {
        return databasePassword;
    }

    /**
     * Set the database password
     * 
     * @param v The new DatabasePassword value
     */
    public void setDatabasePassword(String v) {
        databasePassword = v;
    }

    /**
     * Adds database information on top of normal project properties. Clones the <code>{@link Hashtable}</code> containing the
     * project properties, so that they actual properties class is not modified.
     * 
     * @return Hashtable for properties
     */
    private Hashtable<String, String> customizedProjectProperties() {
        Hashtable<String, String> retval = (Hashtable<String, String>) getProject().getProperties().clone();

        retval.put("dbDriver", databaseDriver);
        retval.put("dbUrl", databaseUrl);
        retval.put("dbUser", databaseUser);
        retval.put("dbPassword", databasePassword);
        retval.put("dbSchema", databaseSchema);
        retval.put("targetDatabase", getProject().getProperty("torque.database"));

        return retval;
    }

    /**
     * Initializes initial context
     * 
     * @return the context
     * @throws Exception generic exception
     */
    public void execute() throws BuildException {

        log("Torque - KualiTorqueDataDump starting");
        log("Your DB settings are:");
        log("driver: " + databaseDriver);
        log("URL: " + databaseUrl);
        log("user: " + databaseUser);
        // log("password: " + databasePassword);


        JdbcCollectionServiceFactory collectionServiceFactory = JdbcCollectionServiceFactory
                .getInstance(customizedProjectProperties());
        JdbcCollectionService jdbcCollections = collectionServiceFactory.getCollectionService(getJdbcCollectionServiceName());

        try {
            jdbcCollections.openConnection();
            generateXML(jdbcCollections);
        }
        catch (Exception e) {
            jdbcCollections.closeConnection();
            throw new BuildException(e);
        }
    }

    private void generateXML(JdbcCollectionService collectionService) throws Exception {

        List<String> tableList = collectionService.getTableNames();
        for (String tableName : tableList) {
            // if ( !tableName.startsWith( "EN_DOC" ) ) continue;
            log("Processing: " + tableName);
            DocumentTypeImpl docType = new DocumentTypeImpl(null, "dataset", null, "data.dtd");
            Document doc = new DocumentImpl(docType);
            Element datasetNode = doc.createElement("dataset");

            collectionService.handleTableData(tableName, new DataDumpClosure(datasetNode, tableName));
            
            doc.appendChild(datasetNode);
            XMLSerializer xmlSerializer = new XMLSerializer(new PrintWriter(new FileOutputStream(outputDirectory + "/" + tableName
                    + ".xml")), new OutputFormat(Method.XML, null, true));
            xmlSerializer.serialize(doc);
        }
    }

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    /**
     * Get all the table names in the current database that are not system tables.
     * 
     * @param dbMeta JDBC database metadata.
     * @return The list of all the tables in a database.
     * @throws SQLException
     */
    public List getTableNames(DatabaseMetaData dbMeta) throws SQLException {
        log("Getting table list...");
        List tables = new ArrayList();
        ResultSet tableNames = null;
        // these are the entity types we want from the database
        String[] types = { "TABLE" }; // JHK: removed views from list
        try {
            tableNames = dbMeta.getTables(null, databaseSchema.toUpperCase(), null, types); // JHK: upper-cased schema name
                                                                                            // (required by Oracle)
            while (tableNames.next()) {
                String name = tableNames.getString(3);
                tables.add(name);
            }
        }
        finally {
            if (tableNames != null) {
                tableNames.close();
            }
        }
        log("Found " + tables.size() + " tables.");
        return tables;
    }

    public String getDatabaseSchema() {
        return databaseSchema;
    }

    public void setDatabaseSchema(String databaseSchema) {
        this.databaseSchema = databaseSchema;
    }
}

class DataDumpClosure implements ResultSetClosure {
    private static Map<String, String> invalidSubStringMap = new HashMap<String, String>();
    static {
        invalidSubStringMap.put("$", "_DOLLAR_SIGN_");
    }

    private Document document;
    private Element datasetNode;
    private String tableName;
    
    public DataDumpClosure(Element datasetNode, String tableName) {
        this.document = datasetNode.getOwnerDocument();
        this.datasetNode = datasetNode;
        this.tableName = tableName;
    }

    /**
     * @see org.kuali.core.db.torque.ResultSetClosure#execute(java.sql.ResultSet)
     */
    public void execute(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int[] columnTypes = new int[md.getColumnCount() + 1];
        String[] columnNames = new String[md.getColumnCount() + 1];
        for (int i = 1; i <= md.getColumnCount(); i++) {
            columnNames[i] = md.getColumnName(i);
            columnTypes[i] = md.getColumnType(i);
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        while (rs.next()) {
            Element row = getDocument().createElement(xmlEscape(tableNameEscape(getTableName())));
            int colCount = md.getColumnCount();
            for (int i = 1; i <= colCount; i++) {

                Object columnValue = rs.getObject(i);
                if (columnValue != null) {
                    if (columnTypes[i] == java.sql.Types.CLOB) {
                        java.sql.Clob clob = (java.sql.Clob) columnValue;
                        Reader r = clob.getCharacterStream();
                        StringBuffer sb = new StringBuffer();
                        char[] buffer = new char[2000];
                        try {
                            int len;
                            while ((len = r.read(buffer)) != -1) {
                                sb.append(buffer, 0, len);
                            }
                        }
                        catch (IOException ex) {
                            System.err.println("IO exception processing CLOB");
                            exception(ex);
                        }
                        columnValue = sb;
                    }
                    else if (columnTypes[i] == java.sql.Types.DATE || columnTypes[i] == java.sql.Types.TIMESTAMP) {
                        columnValue = rs.getTimestamp(i);
                        // System.out.println( columnValue );
                        columnValue = df.format((java.util.Date) columnValue);
                    }

                    row.setAttribute(columnNames[i], xmlEscape(columnValue.toString()));
                }
            }
            datasetNode.appendChild(row);
        }
    }

    /**
     * Oracle and other RDBMS are much more flexible than XML with naming. This means that there may be table names that don't directly translate
     * to XML element names because XML elements have a small set of allowable characters. This relies on a <code>{@link Map}</code> to compare 
     * naming to make the table name more valid for XML
     * 
     * @param st table name
     * @return valid XML name that can be translated back to the original table name
     */
    private String tableNameEscape(String st) {
        if (XMLChar.isValidName(st)) {
            return st;
        }
                
        StringBuffer buff = new StringBuffer();
        for (char xmlChar : st.toCharArray()) {
            if (!XMLChar.isName(xmlChar)) {
                String replacement = invalidSubStringMap.get(new String(new char[] { xmlChar }));
                buff.append(replacement);
            }
            else {
                buff.append(xmlChar);
            }
        }

        return buff.toString();
    }

    private String xmlEscape(String st) {
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

    public Document getDocument() {
        return document;
    }

    public Element getDatasetNode() {
        return datasetNode;
    }

    public void setDatasetNode(Element datasetNode) {
        this.datasetNode = datasetNode;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
