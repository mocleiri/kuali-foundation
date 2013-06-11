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

package org.kuali.common.impex.schema.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBException;

import org.kuali.common.impex.ProducerUtils;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.SchemaSqlProducer;
import org.kuali.common.jdbc.SqlMetaData;
import org.kuali.common.jdbc.supplier.AbstractSupplier;
import org.kuali.common.jdbc.supplier.LocationSupplier;

public class SchemaXmlLocationSupplier extends AbstractSupplier implements LocationSupplier {

    SchemaSqlProducer producer;
    String location;
    Schema schema;

    protected List<String> schemaSql;

    protected Iterator<String> readingIterator;

    public SchemaXmlLocationSupplier() {
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void open() throws IOException {
        try {
            schema = ProducerUtils.unmarshalSchema(location);
        } catch (JAXBException e) {
            throw new IOException("Could not load schema from location " + location + " due to JAXBException", e);
        }

        readingIterator = getSchemaSql().iterator();
    }

    public Schema getSchema() throws IOException {
        if(schema == null) {
            open();
        }

        return schema;
    }

    @Override
    public List<String> getSql() throws IOException {
        if (readingIterator.hasNext()) {
            return Collections.singletonList(readingIterator.next());
        }
        else {
            return null;
        }
    }

    @Override
    public void close() {
    }

    public List<String> getSchemaSql() throws IOException {

        if(schemaSql == null) {

            // do not attempt to load the schema sql if location is not yet set
            if(location != null) {
                schemaSql = new ArrayList<String>();

                Schema theSchema = getSchema();

                schemaSql.addAll(producer.getTablesSql(theSchema.getTables()));
                schemaSql.addAll(producer.getViewsSql(theSchema.getViews()));
                schemaSql.addAll(producer.getSequencesSql(theSchema.getSequences()));
                schemaSql.addAll(producer.getForeignKeySql(theSchema.getForeignKeys()));
            }
        }

        return schemaSql;
    }

    public void setSchemaSql(List<String> schemaSql) {
        this.schemaSql = schemaSql;
    }

    @Override
    public void fillInMetaData() {
        List<String> sqlStrings;
        try {
            sqlStrings = getSchemaSql();
        } catch (IOException e) {
            throw new RuntimeException("Unable to build metadata for schema at location " + location + " due to IOException", e);
        }

        long characterCount = 0l;
        for (String s : sqlStrings) {
            characterCount += s.length();
        }

        this.metaData = new SqlMetaData(sqlStrings.size(), characterCount);
    }

    public SchemaSqlProducer getProducer() {
        return producer;
    }

    public void setProducer(SchemaSqlProducer producer) {
        this.producer = producer;
    }
}
