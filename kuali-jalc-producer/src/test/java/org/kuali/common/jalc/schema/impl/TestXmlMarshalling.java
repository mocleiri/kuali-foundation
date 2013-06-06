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

package org.kuali.common.jalc.schema.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.kuali.common.jalc.model.Schema;
import org.kuali.common.jalc.model.Sequence;
import org.kuali.common.jalc.model.Table;
import org.kuali.common.jalc.model.util.ModelUtils;
import org.kuali.common.util.CollectionUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestXmlMarshalling {

    @Test
    public void testMarshal() throws JAXBException {
        Schema schema = new Schema();
        schema.setTables(Collections.singletonList(MockDataUtil.buildSimpleTable()));
        schema.setForeignKeys(Collections.singletonList(MockDataUtil.buildSimpleForeignKey()));
        schema.setViews(Collections.singletonList(MockDataUtil.buildSimpleView()));
        schema.setSequences(Collections.singletonList(new Sequence("FOO_SEQ", "200")));

        JAXBContext context = JAXBContext.newInstance(Schema.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        marshaller.marshal(schema, sw);

        System.out.println(sw.toString());
    }

    @Test
    public void testUnmarshal() throws JAXBException {
        Schema schema = new Schema();
        schema.setTables(Collections.singletonList(MockDataUtil.buildSimpleTable()));
        schema.setForeignKeys(Collections.singletonList(MockDataUtil.buildSimpleForeignKey()));

        Table table = schema.getTables().iterator().next();

        JAXBContext context = JAXBContext.newInstance(Schema.class);
        Marshaller marshaller = context.createMarshaller();

        StringWriter sw = new StringWriter();
        marshaller.marshal(schema, sw);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object o = unmarshaller.unmarshal(new StringReader(sw.toString()));

        assertTrue(o instanceof Schema);

        Schema s = (Schema) o;

        assertEquals(1, s.getTables().size());
        assertEquals(1, s.getForeignKeys().size());

        Table loaded = s.getTables().iterator().next();

        assertEquals(table.getName(), loaded.getName());
        assertEquals(table.getDescription(), loaded.getDescription());
        assertEquals(table.getColumns().size(), loaded.getColumns().size());
        assertEquals(ModelUtils.getCsvPrimaryKeyColumnNames(table), ModelUtils.getCsvPrimaryKeyColumnNames(loaded));
        assertEquals(ModelUtils.getCsvColumnNames(table.getColumns()), ModelUtils.getCsvColumnNames(loaded.getColumns()));
        assertEquals("NAME", CollectionUtils.getCSV(loaded.getUniqueConstraints().get(0).getColumnNames()));
    }

}
