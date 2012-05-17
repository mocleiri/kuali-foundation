/**
 * Copyright 2004-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.maven.mojo.s3;

/**
 * Helper pojo for marking up the html for a column in an html table
 */
public class ColumnDecorator {

    String tableDataClass;
    String spanClass;
    String columnTitle;

    public ColumnDecorator() {
        this(null, null, null);
    }

    public ColumnDecorator(final String tableDataClass, final String spanClass,
            final String columnTitle) {
        super();
        this.tableDataClass = tableDataClass;
        this.spanClass = spanClass;
        this.columnTitle = columnTitle;
    }

    public String getTableDataClass() {
        return tableDataClass;
    }

    public void setTableDataClass(final String tableDataClass) {
        this.tableDataClass = tableDataClass;
    }

    public String getSpanClass() {
        return spanClass;
    }

    public void setSpanClass(final String spanClass) {
        this.spanClass = spanClass;
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public void setColumnTitle(final String columnTitle) {
        this.columnTitle = columnTitle;
    }
}
