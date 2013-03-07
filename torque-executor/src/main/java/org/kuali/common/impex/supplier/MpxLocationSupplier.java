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

package org.kuali.common.impex.supplier;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.impex.service.MpxMetaData;
import org.kuali.common.impex.service.MpxParser;
import org.kuali.common.impex.service.SqlProducer;
import org.kuali.common.jdbc.supplier.SqlSupplier;
import org.kuali.common.util.LocationUtils;

/**
 * This class provides an implementation of the SqlSupplier interface using an Mpx resource
 * as the data source
 *
 * @author andrewlubbers
 */
public class MpxLocationSupplier implements SqlSupplier {

    SqlProducer sqlProducer;
    MpxExecuteMetaData metaData;
    BufferedReader reader;
    String encoding;
    Table table;
    String location;

    @Override
    public void open() throws IOException {
        reader = LocationUtils.getBufferedReader(location, encoding);
    }

    @Override
    public String getSql() throws IOException {
        return sqlProducer.getSql(table, reader);
    }

    @Override
    public void close() {
        IOUtils.closeQuietly(reader);
    }

    @Override
    public void fillInMetaData() {
        try {
            metaData = getExecuteMetaData(sqlProducer, MpxParser.getMpxMetaData(location), encoding, table);
        } catch (IOException e) {
            throw new IllegalStateException("Exception thrown while trying to initialize meta data: " + e.getMessage(), e);
        }
    }

    protected MpxExecuteMetaData getExecuteMetaData(SqlProducer producer, MpxMetaData metaData, String encoding, Table table) {
        BufferedReader in = null;
        long count = 0;
        long size = 0;
        try {
            in = LocationUtils.getBufferedReader(metaData.getLocation(), encoding);
            String sql = producer.getSql(table, in);
            while (sql != null) {
                count++;
                size += sql.length();
                sql = producer.getSql(table, in);
            }
            MpxExecuteMetaData executeMeta = new MpxExecuteMetaData();
            executeMeta.setCount(count);
            executeMeta.setSize(size);
            executeMeta.setRawDataSize(metaData.getSize());
            executeMeta.setLocation(metaData.getLocation());
            executeMeta.setRowCount(metaData.getRowCount());
            return executeMeta;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public MpxExecuteMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MpxExecuteMetaData metaData) {
        this.metaData = metaData;
    }

    public SqlProducer getSqlProducer() {
        return sqlProducer;
    }

    public void setSqlProducer(SqlProducer sqlProducer) {
        this.sqlProducer = sqlProducer;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int compareTo(SqlSupplier o) {
        return metaData.compareTo(o.getMetaData());
    }
}
