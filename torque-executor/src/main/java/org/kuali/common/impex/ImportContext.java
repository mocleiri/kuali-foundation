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

package org.kuali.common.impex;

import java.util.List;

/**
 * Properties for the mpx execution
 *
 * @author andrewlubbers
 */
public class ImportContext {

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final int DEFAULT_BATCH_ROW_COUNT = 50;
    public static final int DEFAULT_BATCH_DATA_SIZE = 50 * 1024;
    public static final int DEFAULT_MAX_THREAD_COUNT = 8;

    List<String> dataLocations;
    String schemaXmlLocation;
    String databaseVendor;


    int batchDataSize = DEFAULT_BATCH_DATA_SIZE;
    int batchRowCount = DEFAULT_BATCH_ROW_COUNT;
    String encoding = DEFAULT_ENCODING;
    int maxThreadCount = DEFAULT_MAX_THREAD_COUNT;

    public List<String> getDataLocations() {
        return dataLocations;
    }

    public void setDataLocations(List<String> dataLocations) {
        this.dataLocations = dataLocations;
    }

    public String getDatabaseVendor() {
        return databaseVendor;
    }

    public void setDatabaseVendor(String databaseVendor) {
        this.databaseVendor = databaseVendor;
    }

    public String getSchemaXmlLocation() {
        return schemaXmlLocation;
    }

    public void setSchemaXmlLocation(String schemaXmlLocation) {
        this.schemaXmlLocation = schemaXmlLocation;
    }

    public int getBatchDataSize() {
        return batchDataSize;
    }

    public void setBatchDataSize(int batchDataSize) {
        this.batchDataSize = batchDataSize;
    }

    public int getBatchRowCount() {
        return batchRowCount;
    }

    public void setBatchRowCount(int batchRowCount) {
        this.batchRowCount = batchRowCount;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getMaxThreadCount() {
        return maxThreadCount;
    }

    public void setMaxThreadCount(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
    }
}
