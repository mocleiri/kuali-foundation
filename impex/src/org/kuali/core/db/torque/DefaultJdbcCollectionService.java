/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.core.db.torque;

import static org.kuali.core.db.torque.LogManager.exception;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Element;

/**
 * 
 * This class...
 */
public class DefaultJdbcCollectionService implements JdbcCollectionService, Loggable {

    private static final void log(String logMessage) {
        org.apache.commons.logging.LogFactory.getLog(DefaultJdbcCollectionService.class).info(logMessage);
    }

    private static final void log(String logMessage, int level) {
        if (level == Project.MSG_WARN) {
            org.apache.commons.logging.LogFactory.getLog(DefaultJdbcCollectionService.class).warn(logMessage);

        }
        else {
            org.apache.commons.logging.LogFactory.getLog(DefaultJdbcCollectionService.class).debug(logMessage);
        }
    }

    /** JDBC URL. */
    protected String dbUrl;

    /** JDBC driver. */
    protected String dbDriver;

    /** JDBC user name. */
    protected String dbUser;

    /** JDBC password. */
    protected String dbPassword;

    /** DB schema to use. */
    protected String dbSchema;

    /** DOM document produced. */
    protected DocumentImpl doc;

    /** The document root element. */
    protected Element databaseNode;

    /** Hashtable of columns that have primary keys. */
    protected Hashtable primaryKeys;

    /** Hashtable to track what table a column belongs to. */
    protected Hashtable columnTableMap;

    protected boolean sameJavaName;

    private Connection connection;
    private Statement statement;
    private DatabaseMetaData dbMetaData;
    
    private String targetDatabase;

    public DatabaseMetaData getDbMetaData() {
        return dbMetaData;
    }

    public void setDbMetaData(DatabaseMetaData dbMetaData) {
        this.dbMetaData = dbMetaData;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }

    public void setDbUrl(String v) {
        dbUrl = v;
    }

    public void setDbDriver(String v) {
        dbDriver = v;
    }

    public void setDbUser(String v) {
        dbUser = v;
    }

    public void setDbPassword(String v) {
        dbPassword = v;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setSameJavaName(boolean v) {
        this.sameJavaName = v;
    }

    public boolean isSameJavaName() {
        return this.sameJavaName;
    }


    /**
     * Generates an XML database schema from JDBC metadata.
     * 
     * @throws Exception a generic exception.
     */

    /*
     * public void generateXML() throws Exception { // Load the Interbase Driver. Class.forName(dbDriver); log("DB driver sucessfuly
     * instantiated");
     * 
     * 
     * try { // The database map. List<String> tableList = getTableNames(dbMetaData);
     * 
     * databaseNode = doc.createElement("database"); databaseNode.setAttribute("name", dbUser); // Build a database-wide column ->
     * table map. columnTableMap = buildColumnTableMap(dbMetaData, tableList);
     * 
     * for (String curTable : tableList) { log("Processing table: " + curTable);
     * 
     * Element table = doc.createElement("table"); table.setAttribute("name", curTable); if (isSameJavaName()) {
     * table.setAttribute("javaName", curTable); } // Add Columns. // TableMap tblMap = dbMap.getTable(curTable);
     * 
     * populatePrimaryKeyTable(dbMetaData, curTable);
     * 
     * generateColumnXml(dbMetaData, curTable, table);
     * 
     * generateForeignKeyXml(dbMetaData, curTable, table);
     * 
     * generateIndexXml(dbMetaData, curTable, table);
     * 
     * databaseNode.appendChild(table); } doc.appendChild(databaseNode); } finally { if (con != null) { con.close(); con = null; } } }
     */

    /**
     * Retrieves a list of foreign key columns for a given table.
     * 
     * @param dbMeta JDBC metadata.
     * @param tableName Table from which to retrieve FK information.
     * @return A <code>{@link Map}</code> of foreign key attributes keyed by name <code>tableName</code>.
     * @throws SQLException
     */
    public Map<String, Object[]> getForeignKeyMap(String tableName) throws SQLException {
        HashMap<String, Object[]> fks = new HashMap<String, Object[]>();
        ResultSet foreignKeys = null;
        try {
            foreignKeys = getDbMetaData().getImportedKeys(null, dbSchema, tableName);
            while (foreignKeys.next()) {
                String refTableName = foreignKeys.getString(3);
                String fkName = foreignKeys.getString(12);
                int deleteRule = foreignKeys.getInt(11);
                String onDelete = "none";
                if (deleteRule == DatabaseMetaData.importedKeyCascade) {
                    onDelete = "cascade";
                }
                else if (deleteRule == DatabaseMetaData.importedKeyRestrict) {
                    onDelete = "restrict";
                }
                else if (deleteRule == DatabaseMetaData.importedKeySetNull) {
                    onDelete = "setnull";
                }
                // if FK has no name - make it up (use tablename instead)
                if (fkName == null) {
                    fkName = refTableName;
                }
                Object[] fk = (Object[]) fks.get(fkName);
                List refs;
                if (fk == null) {
                    fk = new Object[3];
                    fk[0] = refTableName; // referenced table name
                    refs = new ArrayList();
                    fk[1] = refs;
                    fks.put(fkName, fk);
                    fk[2] = onDelete;
                }
                else {
                    refs = (ArrayList) fk[1];
                }
                String[] ref = new String[2];
                ref[0] = foreignKeys.getString(8); // local column
                ref[1] = foreignKeys.getString(4); // foreign column
                refs.add(ref);
            }
        }
        catch (SQLException e) {
            // this seems to be happening in some db drivers (sybase)
            // when retrieving foreign keys from views.
            log("WARN: Could not read foreign keys for Table " + tableName + " : " + e.getMessage(), Project.MSG_WARN);
            throw e;
        }
        finally {
            if (foreignKeys != null) {
                foreignKeys.close();
            }
        }
        return fks;
    }

    /**
     * 
     * This method...
     * 
     * @param dbMeta
     * @param tableName
     * @return
     * @throws SQLException
     */
    public List<TableIndex> getIndexes(String tableName) throws SQLException {
        List<TableIndex> indexes = new ArrayList<TableIndex>();
        ResultSet pkInfo = null;
        String pkName = null;
        ResultSet indexInfo = null;
        try {
            indexInfo = getDbMetaData().getIndexInfo(null, dbSchema, tableName, false, false);
            // need to ensure that the PK is not returned as an index
            pkInfo = getDbMetaData().getPrimaryKeys(null, dbSchema, tableName);
            if (pkInfo.next()) {
                pkName = pkInfo.getString("PK_NAME");
            }

            TableIndex currIndex = null;
            while (indexInfo.next()) {
                if (indexInfo.getString("INDEX_NAME") == null)
                    continue;
                if (currIndex == null || !indexInfo.getString("INDEX_NAME").equals(currIndex.name)) {
                    currIndex = new TableIndex();
                    currIndex.name = indexInfo.getString("INDEX_NAME");
                    currIndex.unique = !indexInfo.getBoolean("NON_UNIQUE");
                    // if has the same name as the PK, skip adding it to the index list
                    if (pkName == null || !pkName.equals(currIndex.name)) {
                        indexes.add(currIndex);
                    }
                    else {
                    }
                }
                currIndex.columns.add(indexInfo.getString("COLUMN_NAME"));
            }

        }
        catch (SQLException e) {
            log("WARN: Could not read indexes for Table " + tableName + " : " + e.getMessage(), Project.MSG_WARN);
        }
        finally {
            if (indexInfo != null) {
                indexInfo.close();
            }
            if (pkInfo != null) {
                pkInfo.close();
            }
        }
        return indexes;
    }

    /**
     * 
     * This method...
     * 
     * @param dbMeta
     * @return
     * @throws SQLException
     */
    public List getViewNames() throws SQLException {
        log("Getting view list...");
        List tables = new ArrayList();
        ResultSet tableNames = null;
        // these are the entity types we want from the database
        String[] types = { "VIEW" }; // JHK: removed views from list
        try {
            tableNames = getDbMetaData().getTables(null, dbSchema, null, types);
            System.out.println();
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
        log("Found " + tables.size() + " views.");
        return tables;
    }
    
    /**
     * <view name="" viewdefinition="" />
     * 
     */
    public String getViewDefinition(String viewName) throws SQLException {
        String retval = "";          
        if (getTargetDatabase().equalsIgnoreCase("oracle")) {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT text FROM all_views WHERE owner = ? AND view_name = ?");
            ps.setString(1, dbSchema);
            ps.setString(2, viewName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                retval = rs.getString(1);
            }
            rs.close();
            ps.close();
        }
        else if (getTargetDatabase().equalsIgnoreCase("mysql")) {
            PreparedStatement ps = dbMetaData.getConnection().prepareStatement(
                    "SELECT view_definition FROM information_schema.views WHERE table_schema = ? AND table_name = ?");
            ps.setString(1, dbSchema);
            ps.setString(2, viewName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                retval = rs.getString(1);
            }
            rs.close();
            ps.close();
        }
        
        return retval;
    }

    public boolean isSequence(String sequenceName) {
        return sequenceName.toUpperCase().startsWith("SEQ_") || sequenceName.toUpperCase().startsWith("SEQUENCE_")
                || sequenceName.toUpperCase().endsWith("_SEQ") || sequenceName.toUpperCase().endsWith("_SEQUENCE")
                || sequenceName.toUpperCase().endsWith("_ID");
    }

    /**
     * 
     * This method...
     * 
     * @param dbMeta
     * @return
     * @throws SQLException
     */
    public List getSequenceNames() throws SQLException {
        log("Getting sequence list...");
        List tables = new ArrayList();
        ResultSet tableNames = null;
        // these are the entity types we want from the database
        String[] types = { "TABLE", "SEQUENCE" }; // JHK: removed views from list
        try {
            tableNames = getDbMetaData().getTables(null, dbSchema, null, types);
            while (tableNames.next()) {
                String name = tableNames.getString(3);
                if (isSequence(name)) {
                    tables.add(name);
                }
            }
        }
        finally {
            if (tableNames != null) {
                tableNames.close();
            }
        }
        log("Found " + tables.size() + " sequences.");
        return tables;
    }
    
    /**
     * <view name="" nextval="" />
     * 
     */
    public String getSequenceNextVal(String sequenceName) throws SQLException {
        String retval = "0";
        if (getTargetDatabase().equalsIgnoreCase("oracle")) {
            PreparedStatement ps = getConnection().prepareStatement(
                    "SELECT last_number FROM all_sequences WHERE sequence_owner = ? AND sequence_name = ?");
            ps.setString(1, dbSchema);
            ps.setString(2, sequenceName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                retval = rs.getString(1);
            }
            rs.close();
            ps.close();
        }
        else if (getTargetDatabase().equalsIgnoreCase("mysql")) {
            PreparedStatement ps = getConnection().prepareStatement("SELECT id FROM " + sequenceName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                retval = rs.getString(1);
            }
            rs.close();
            ps.close();
        }
        else {
            System.err.println("Unknown DB type: " + dbMetaData.getDatabaseProductName());
        }

        return retval;
    }

    /**
     * Retrieves all the column names and types for a given table from JDBC metadata. It returns a List of Lists. Each element of
     * the returned List is a List with:
     * 
     * element 0 => a String object for the column name. element 1 => an Integer object for the column type. element 2 => size of
     * the column. element 3 => null type.
     * 
     * @param dbMeta JDBC metadata.
     * @param tableName Table from which to retrieve column information.
     * @return The list of columns in <code>tableName</code>.
     * @throws SQLException
     */
    public List getColumns(String tableName) throws SQLException {
        List columns = new ArrayList();
        ResultSet columnSet = null;
        try {
            columnSet = getDbMetaData().getColumns(null, dbSchema, tableName, null);
            while (columnSet.next()) {
                String name = columnSet.getString(4);
                Integer sqlType = new Integer(columnSet.getString(5));
                Integer size = new Integer(columnSet.getInt(7));
                Integer decimalDigits = new Integer(columnSet.getInt(9));
                Integer nullType = new Integer(columnSet.getInt(11));
                String defValue = columnSet.getString(13);

                List col = new ArrayList(6);
                col.add(name);
                col.add(sqlType);
                col.add(size);
                col.add(nullType);
                col.add(defValue);
                col.add(decimalDigits);
                columns.add(col);
            }
        }
        finally {
            if (columnSet != null) {
                columnSet.close();
            }
        }
        return columns;
    }

    /**
     * Retrieves a list of the columns composing the primary key for a given table.
     * 
     * @param dbMeta JDBC metadata.
     * @param tableName Table from which to retrieve PK information.
     * @return A list of the primary key parts for <code>tableName</code>.
     * @throws SQLException
     */
    public List getPrimaryKeys(String tableName) throws SQLException {
        List pk = new ArrayList();
        ResultSet parts = null;
        try {
            parts = getDbMetaData().getPrimaryKeys(null, dbSchema, tableName);
            while (parts.next()) {
                pk.add(parts.getString(4));
            }
        }
        finally {
            if (parts != null) {
                parts.close();
            }
        }
        return pk;
    }

    /**
     * Get all the table names in the current database that are not system tables.
     * 
     * @param dbMeta JDBC database metadata.
     * @return The list of all the tables in a database.
     * @throws SQLException
     */
    public List getTableNames() throws SQLException {
        log("Getting table list ...");
        List tables = new ArrayList();
        ResultSet tableNames = null;
        // these are the entity types we want from the database
        String[] types = { "TABLE", "SEQUENCE" }; // JHK: removed views from list
        try {
            tableNames = getDbMetaData().getTables(null, dbSchema.toUpperCase(), null, types);
            while (tableNames.next()) {
                String name = tableNames.getString(3);
                if (!isSequence(name)) {
                    tables.add(name);
                }
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

    /**
     * 
     * @see org.kuali.core.db.torque.JdbcCollectionService#closeConnection()
     */
    public void closeConnection() {
        try {
            if (getConnection() != null && !getConnection().isClosed()) {
                getConnection().close();
            }
        }
        catch (Exception e) {
        }
    }

    /**
     * 
     * @see org.kuali.core.db.torque.JdbcCollectionService#openConnection()
     */
    public void openConnection() throws SQLException {
        try {
            Class.forName(getDbDriver());
        }
        catch (ClassNotFoundException cnfe) {
            exception(cnfe);
        }
        
        setConnection(DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPassword()));
        log("DB connection established");
        setDbMetaData(getConnection().getMetaData());
    }

    public String getLogMessage(Object... objs) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getStackOutputLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    /**
     * @see org.kuali.core.db.torque.JdbcCollectionService#handleTableData(java.lang.String, org.kuali.core.db.torque.ResultSetClosure)
     */
    public void handleTableData(String tableName, ResultSetClosure closure) throws SQLException {
        Statement stmt = getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

        closure.execute(rs);
        
        rs.close();
        stmt.close();
    }
}
