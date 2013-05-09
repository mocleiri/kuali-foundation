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

package org.kuali.common.impex.service.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.service.schema.impl.NoOpProvider;
import org.kuali.common.impex.service.schema.impl.oracle.OracleTableSqlProducer;

import static junit.framework.Assert.fail;

public class TestOracleTableSqlProducer {

    private final static String[] EXPECTED_SIMPLE_SQL = {"DECLARE temp NUMBER;\n" +
            "BEGIN\n" +
            "\tSELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'FOO_T';\n" +
            "\tIF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE FOO_T CASCADE CONSTRAINTS PURGE'; END IF;\n" +
            "END;\n" +
            "/",

            "CREATE TABLE FOO_T\n" +
            "(\n" +
            "\tID VARCHAR2(36),\n" +
            "\tCREATETIME TIMESTAMP NOT NULL,\n" +
            "\tNAME VARCHAR2(255),\n" +
            "\tCONSTRAINT FOO_U1_NAME UNIQUE (NAME)\n" +
            ")\n" +
            "/",

            "ALTER TABLE FOO_T\n" +
            "\tADD CONSTRAINT FOO_TP1\n" +
            "PRIMARY KEY (ID)\n" +
            "/"};



    @Test
    public void simpleTableTest() {

        OracleTableSqlProducer producer = new OracleTableSqlProducer();

        producer.setMappingProvider(new NoOpProvider());

        Table table = buildSimpleTable();

        List<String> results = producer.getTablesSql(Collections.singletonList(table));

        List<String> expected = Arrays.asList(EXPECTED_SIMPLE_SQL);

        List<String> foundExpected = new ArrayList<String>(expected);
        for (String e : expected) {
            if(results.contains(e)) {
                foundExpected.remove(e);
            }
            else {
                fail("Expected sql statment **" + e + "** not found in generated statements.");
            }
        }

        if(!foundExpected.isEmpty()) {
            fail("Following expected sql statements not found in generated statements: \n" + foundExpected.toString());
        }

    }

    private Table buildSimpleTable() {
        MockTable result = new MockTable();

        result.setName("FOO_T");

        // id column
        MockColumn idCol = new MockColumn();
        idCol.setName("ID");
        idCol.setColumnDataType(DataType.STRING);
        idCol.setTypeSize(new TypeSize(36));
        idCol.setTextType(true);
        idCol.setPrimaryKey(true);

        result.getColumns().add(idCol);

        // createtime column
        MockColumn timeCol = new MockColumn();
        timeCol.setName("CREATETIME");
        timeCol.setColumnDataType(DataType.TIMESTAMP);
        timeCol.setNullable(false);

        result.getColumns().add(timeCol);

        // name column
        MockColumn nameCol = new MockColumn();
        nameCol.setName("NAME");
        nameCol.setColumnDataType(DataType.STRING);
        nameCol.setTextType(true);
        nameCol.setTypeSize(new TypeSize(255));

        result.getColumns().add(nameCol);

        // unique constraint on name
        UniqueConstraint nameIndex = new UniqueConstraint(Collections.singletonList((Column)nameCol), "FOO_U1_NAME");

        result.getUniqueConstraints().add(nameIndex);

        return result;
    }

}
