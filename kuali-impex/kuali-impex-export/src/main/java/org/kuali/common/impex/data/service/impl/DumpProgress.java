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

package org.kuali.common.impex.data.service.impl;

import java.io.OutputStream;
import java.util.List;

import org.kuali.common.impex.data.service.DumpDataContext;
import org.kuali.common.impex.model.Column;

public class DumpProgress {

    List<List<String>> currentData;
    long currentDataSize;
    long totalDataSize;
    long currentRowCount;
    long totalRowCount;
    OutputStream outputStream;
    private List<Column> columns;
    private DumpDataContext context;
    private DumpTableContext tableContext;

    public List<List<String>> getCurrentData() {
        return currentData;
    }

    public void setCurrentData(List<List<String>> currentData) {
        this.currentData = currentData;
    }

    public long getCurrentDataSize() {
        return currentDataSize;
    }

    public void setCurrentDataSize(long currentDataSize) {
        this.currentDataSize = currentDataSize;
    }

    public long getCurrentRowCount() {
        return currentRowCount;
    }

    public void setCurrentRowCount(long currentRowCount) {
        this.currentRowCount = currentRowCount;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public long getTotalDataSize() {
        return totalDataSize;
    }

    public void setTotalDataSize(long totalDataSize) {
        this.totalDataSize = totalDataSize;
    }

    public long getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public DumpDataContext getContext() {
        return context;
    }

    public void setContext(DumpDataContext context) {
        this.context = context;
    }

    public DumpTableContext getTableContext() {
        return tableContext;
    }

    public void setTableContext(DumpTableContext tableContext) {
        this.tableContext = tableContext;
    }
}
