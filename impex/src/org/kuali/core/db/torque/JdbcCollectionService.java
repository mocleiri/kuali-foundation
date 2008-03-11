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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * General interface for collection generic JDBC information.
 * 
 */
public interface JdbcCollectionService {
    public String getDbUrl();

    public void setDbUrl(String dbUrl);

    public String getDbDriver();

    public void setDbDriver(String dbDriver);

    public String getDbUser();

    public void setDbUser(String dbUser);

    public String getDbPassword();

    public void setDbPassword(String dbPassword);

    public void setDbSchema(String dbSchema);
    
    public String getDbSchema();
   
    public String getTargetDatabase();

    public void setTargetDatabase(String targetDatabase);

    
    /**
     * Get the definition from the database metadata of the view by the given name
     * 
     * @param viewName
     * @return String definition
     */
    public String getViewDefinition(String viewName) throws SQLException;

    /**
     * Get the next val for a sequence with the given name
     * 
     * @param sequenceName
     * @return String next val
     * @throws SQLException
     */
    public String getSequenceNextVal(String sequenceName) throws SQLException;
  
    /**
     * Retrieves a list of foreign key columns for a given table.
     * 
     * @param tableName Table from which to retrieve FK information.
     * @return A <code>{@link Map}</code> of foreign key attributes keyed by name <code>tableName</code>.
     * @throws SQLException
     */
    public Map<String, Object[]> getForeignKeyMap(String tableName) throws SQLException;

    /**
     * 
     * This method...
     * 
     * @param tableName
     * @return
     * @throws SQLException
     */
    public List<TableIndex> getIndexes(String tableName) throws SQLException;

    public boolean isSequence(String sequenceName);

    /**
     * Retrieves all the column names and types for a given table from JDBC metadata. It returns a List of Lists. Each element of
     * the returned List is a List with:
     * 
     * element 0 => a String object for the column name. element 1 => an Integer object for the column type. element 2 => size of
     * the column. element 3 => null type.
     * 
     * @param tableName Table from which to retrieve column information.
     * @return The list of columns in <code>tableName</code>.
     * @throws SQLException
     */
    public List getColumns(String tableName) throws SQLException;

    /**
     * Retrieves a list of the columns composing the primary key for a given table.
     * 
     * @param dbMeta JDBC metadata.
     * @param tableName Table from which to retrieve PK information.
     * @return A list of the primary key parts for <code>tableName</code>.
     * @throws SQLException
     */
    public List getPrimaryKeys(String tableName) throws SQLException;

    /**
     * Get all the table names in the current database that are not system
     * tables.
     * 
     * @return The list of all the tables in a database.
     * @throws SQLException
     */
    public List getTableNames() throws SQLException;

    public static class TableIndex {
        public String name;
        public boolean unique;
        public List<String> columns = new ArrayList( 10 );
    }
    
    /**
     * Get a <code>{@link List}</code> containing the names of all views
     * 
     * @return List
     * @throws SQLException
     */
    public List<String> getViewNames() throws SQLException;

    /**
     * Get a <code>{@link List}</code> containing the names of all sequences
     * 
     * @return List
     * @throws SQLException
     */
    public List<String> getSequenceNames() throws SQLException;

    /**
     * Opens a database connection for collection jdbc data.
     * 
     * @throws SQLException
     */
    public void openConnection() throws SQLException;

    /**
     * Closes the database connection
     */
    public void closeConnection();
    
    /**
     * Iterates over all of the data in a table efficiently using a <code>{@link ResultSetClosure}</code>. The closure makes it so that all
     * the data in a <code>{@link ResultSet}</code> does not need to be read immediately. It can be read one record and a time and processed by
     * the closure
     * 
     * @param closure <code>{@link ResultSetClosure}</code> instance.
     * @throws SQLException duh
     */
    public void handleTableData(String tableName, ResultSetClosure closure) throws SQLException;
}
