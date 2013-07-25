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
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.schema.impl.oracle.OracleSequenceSqlProducer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class TestOracleSequenceSqlProducer {

    public static final String[] EXPECTED_SQL = {"DECLARE temp NUMBER;\n" +
            "BEGIN\n" +
                    "\tSELECT COUNT(*) INTO temp FROM user_sequences WHERE sequence_name = 'FOO_SEQ';\n" +
                    "\tIF temp > 0 THEN EXECUTE IMMEDIATE 'DROP SEQUENCE FOO_SEQ'; END IF;\n" +
            "END;\n",
            "CREATE SEQUENCE FOO_SEQ INCREMENT BY 1 START WITH 500 NOMAXVALUE NOCYCLE NOCACHE ORDER\n"
    };

    @Test
    public void testGetSequencesSql() {
        Sequence sequence = new Sequence("FOO_SEQ", "500");

        OracleSequenceSqlProducer producer = new OracleSequenceSqlProducer();

        List<String> results = producer.getSequencesSql(Collections.singletonList(sequence));

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
