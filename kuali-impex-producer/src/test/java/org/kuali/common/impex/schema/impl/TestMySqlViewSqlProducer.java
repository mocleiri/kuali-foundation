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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.impl.mysql.MySqlViewSqlProducer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

public class TestMySqlViewSqlProducer {

    private static final String[] EXPECTED_SQL = {"DROP VIEW IF EXISTS TEST_V1\n",

            "CREATE VIEW TEST_V1 AS\n" +
            "SELECT ID, NAME FROM FOO_T\n" +
            "WHERE NAME LIKE 'TEST%'\n"};

    @Test
    public void testEmptyViewList() {
        List<View> empty = Collections.emptyList();

        MySqlViewSqlProducer sqlProducer = new MySqlViewSqlProducer();
        List<String> results = sqlProducer.getViewsSql(empty);

        assertTrue(results.isEmpty());
    }

    @Test
    public void testGetViewSql() {
        List<View> views = Collections.singletonList(MockDataUtil.buildSimpleView());

        MySqlViewSqlProducer sqlProducer = new MySqlViewSqlProducer();
        List<String> results = sqlProducer.getViewsSql(views);

        assertEquals(EXPECTED_SQL.length, results.size());

        List<String> expected = Arrays.asList(EXPECTED_SQL);

        List<String> foundExpected = new ArrayList<String>(expected);
        for (String e : expected) {
            if(results.contains(e)) {
                foundExpected.remove(e);
            }
            else {
                fail("Expected sql statment **" + e + "** not found in generated statements.\n Generated statements were: " + results.toString());
            }
        }

        if(!foundExpected.isEmpty()) {
            fail("Following expected sql statements not found in generated statements: \n" + foundExpected.toString());
        }
    }

}
