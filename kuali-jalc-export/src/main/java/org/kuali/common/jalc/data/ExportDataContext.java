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

package org.kuali.common.jalc.data;

import java.io.File;
import java.util.List;
import javax.sql.DataSource;

public class ExportDataContext {

    protected DataSource dataSource;
    protected int dataThreads;
    protected File workingDir;
    protected String encoding;
    protected List<ExportTableContext> tableContexts;

    protected int rowCountInterval;
    protected int dataSizeInterval;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getDataThreads() {
        return dataThreads;
    }

    public void setDataThreads(int dataThreads) {
        this.dataThreads = dataThreads;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getDataSizeInterval() {
        return dataSizeInterval;
    }

    public void setDataSizeInterval(int dataSizeInterval) {
        this.dataSizeInterval = dataSizeInterval;
    }

    public int getRowCountInterval() {
        return rowCountInterval;
    }

    public void setRowCountInterval(int rowCountInterval) {
        this.rowCountInterval = rowCountInterval;
    }

    public List<ExportTableContext> getTableContexts() {
        return tableContexts;
    }

    public void setTableContexts(List<ExportTableContext> tableContexts) {
        this.tableContexts = tableContexts;
    }

}
