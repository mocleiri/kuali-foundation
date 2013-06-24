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

import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.impex.schema.ViewFinder;

public class SchemaExtractionContext {

    protected int threadCount;
    protected DatabaseMetaData databaseMetaData;
    protected String schemaName;
    protected List<String> elementNameIncludes = new ArrayList<String>();
    protected List<String> elementNameExcludes = new ArrayList<String>();
    protected ViewFinder viewFinder;
    protected SequenceFinder sequenceFinder;

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

    public List<String> getElementNameIncludes() {
        return elementNameIncludes;
    }

    public void setElementNameIncludes(List<String> elementNameIncludes) {
        this.elementNameIncludes = elementNameIncludes;
    }

    public List<String> getElementNameExcludes() {
        return elementNameExcludes;
    }

    public void setElementNameExcludes(List<String> elementNameExcludes) {
        this.elementNameExcludes = elementNameExcludes;
    }

    public ViewFinder getViewFinder() {
        return viewFinder;
    }

    public void setViewFinder(ViewFinder viewFinder) {
        this.viewFinder = viewFinder;
    }

    public SequenceFinder getSequenceFinder() {
        return sequenceFinder;
    }

    public void setSequenceFinder(SequenceFinder sequenceFinder) {
        this.sequenceFinder = sequenceFinder;
    }
}
