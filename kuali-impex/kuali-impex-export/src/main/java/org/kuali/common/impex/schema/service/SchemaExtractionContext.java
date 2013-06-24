/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.schema.service;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class SchemaExtractionContext {

    protected int threadCount;
    protected DatabaseMetaData databaseMetaData;
    private String schemaName;
    private List<String> tableNameIncludes = new ArrayList<String>();
    private List<String> tableNameExcludes = new ArrayList<String>();

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public DatabaseMetaData getDatabaseMetaData() {
        return databaseMetaData;
    }

    public void setDatabaseMetaData(DatabaseMetaData databaseMetaData) {
        this.databaseMetaData = databaseMetaData;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public List<String> getTableNameIncludes() {
        return tableNameIncludes;
    }

    public void setTableNameIncludes(List<String> tableNameIncludes) {
        this.tableNameIncludes = tableNameIncludes;
    }

    public List<String> getTableNameExcludes() {
        return tableNameExcludes;
    }

    public void setTableNameExcludes(List<String> tableNameExcludes) {
        this.tableNameExcludes = tableNameExcludes;
    }
}
