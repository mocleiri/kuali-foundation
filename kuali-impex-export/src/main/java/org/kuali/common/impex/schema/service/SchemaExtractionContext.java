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

import javax.sql.DataSource;

import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.impex.schema.ViewFinder;
import org.kuali.common.util.StringFilter;

public class SchemaExtractionContext {

    protected int threadCount;
    protected DataSource dataSource;
    protected StringFilter nameFilter;
    protected String schemaName;
    protected ViewFinder viewFinder;
    protected SequenceFinder sequenceFinder;

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public StringFilter getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(StringFilter nameFilter) {
        this.nameFilter = nameFilter;
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
