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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.jalc.model.Table;
import org.kuali.common.jalc.schema.impl.mysql.MySqlTableSqlProducer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TestMySqlTableSqlProducer {

    private final static String[] EXPECTED_SIMPLE_SQL = {"DROP TABLE IF EXISTS FOO_T\n",
            "CREATE TABLE FOO_T\n" +
            "(\n" +
            "\tID VARCHAR(36),\n" +
            "\tCREATETIME TIMESTAMP NOT NULL,\n" +
            "\tFOO_COUNT FLOAT(10),\n" +
            "\tNAME VARCHAR(255),\n" +
            "\tCONSTRAINT FOO_TP1 PRIMARY KEY(ID),\n" +
            "\tCONSTRAINT FOO_TP1 UNIQUE (NAME)\n" +
            ") ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin\n"
    };

    @Test
    public void simpleTableTest() {
        MySqlTableSqlProducer producer = new MySqlTableSqlProducer();

        producer.setMappingProvider(new NoOpProvider());

        Table table = MockDataUtil.buildSimpleTable();

        List<String> results = producer.getTablesSql(Collections.singletonList(table));

        assertEquals(EXPECTED_SIMPLE_SQL[1], results.get(1));

        List<String> expected = Arrays.asList(EXPECTED_SIMPLE_SQL);

        List<String> foundExpected = new ArrayList<String>(expected);
        for (String e : expected) {
            if (results.contains(e)) {
                foundExpected.remove(e);
            } else {
                fail("Expected sql statment **" + e + "** not found in generated statements.");
            }
        }

        if (!foundExpected.isEmpty()) {
            fail("Following expected sql statements not found in generated statements: \n" + foundExpected.toString());
        }
    }

}
