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

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.schema.impl.oracle.OracleForeignKeySqlProducer;

import static junit.framework.Assert.assertEquals;

public class TestOracleForeignKeySqlProducer {

    public static final String EXPECTED_SQL = "ALTER TABLE FOO_T\n" +
            "\tADD CONSTRAINT FOO_FK_1\n" +
            "\tFOREIGN KEY (BAR_ID)\n" +
            "\tREFERENCES BAR_T (ID)\n" +
            "\tON DELETE CASCADE\n";

    @Test
    public void testGetForeignKeySql() {
        ForeignKey fk = MockDataUtil.buildSimpleForeignKey();

        OracleForeignKeySqlProducer producer = new OracleForeignKeySqlProducer();
        List<String> results = producer.getForeignKeySql(Collections.singletonList(fk));

        assertEquals(1, results.size());

        assertEquals(EXPECTED_SQL, results.iterator().next());
    }

}
