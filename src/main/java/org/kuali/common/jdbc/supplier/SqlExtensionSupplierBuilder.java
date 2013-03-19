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

package org.kuali.common.jdbc.supplier;

import org.kuali.common.jdbc.SqlReader;

/**
 * Simple builder that creates a SqlLocationSupplier for a location
 *
 * @author andrewlubbers
 */
public class SqlExtensionSupplierBuilder implements LocationExtensionSupplierBuilder {

    private final static String DEFAULT_EXTENSION = "sql";

    String extension = DEFAULT_EXTENSION;
    String encoding;
    SqlReader sqlReader;

    @Override
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public LocationSupplier buildSupplier(String location) {
        SqlLocationSupplier supplier = new SqlLocationSupplier(location);

        // set optional properties.  These have well-defined defaults in SqlLocationSupplier, but may be overridden
        if(encoding != null) {
            supplier.setEncoding(encoding);
        }

        if(sqlReader != null) {
            supplier.setReader(sqlReader);
        }

        return supplier;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public SqlReader getSqlReader() {
        return sqlReader;
    }

    public void setSqlReader(SqlReader sqlReader) {
        this.sqlReader = sqlReader;
    }
}
