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

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.jalc.model.View;
import org.kuali.common.jalc.schema.impl.oracle.OracleViewSqlProducer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TestOracleViewSqlProducer {

    private static final String EXPECTED_VIEW_SQL = "CREATE OR REPLACE FORCE VIEW TEST_V1 AS \n" +
            "SELECT ID, NAME FROM FOO_T\n" +
            "WHERE NAME LIKE 'TEST%'\n";

    @Test
    public void testEmptyViewList() {
        List<View> empty = Collections.emptyList();

        OracleViewSqlProducer sqlProducer = new OracleViewSqlProducer();
        List<String> results = sqlProducer.getViewsSql(empty);

        assertTrue(results.isEmpty());
    }

    @Test
    public void testGetViewSql() {
        List<View> views = Collections.singletonList(MockDataUtil.buildSimpleView());

        OracleViewSqlProducer sqlProducer = new OracleViewSqlProducer();
        List<String> results = sqlProducer.getViewsSql(views);

        assertEquals(1, results.size());

        String sql = results.iterator().next();

        assertEquals(EXPECTED_VIEW_SQL, sql);
    }

}
