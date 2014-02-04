/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.jdbc.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.kuali.common.jdbc.context.SqlExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SqlConfigUtilsTest {

    private static final Logger logger = LoggerFactory.getLogger(SqlConfigUtilsTest.class);

    /**
     * All of these keys should fail to provide a valid SqlExecutionContext
     */
    protected static final List<String> INVALID_EXECUTION_CONTEXT_PROPERTY_KEYS = Arrays.asList("sql.foo.bar.nonconcurrent", "", "aSingleInvalidToken", "foo.bar.sequential");

    protected static final String SIMPLE_SCHEMA_KEY = "sql.schema";

    protected static final String SIMPLE_DATA_KEY = "sql.data";

    protected static final String COMPLEX_KEY = "sql.a.very.long.series.of.period.separated.words";

    protected static final List<String> EXPECTED_EXECUTION_CONTEXT_GROUP_NAMES = Arrays.asList(SIMPLE_SCHEMA_KEY, SIMPLE_DATA_KEY, COMPLEX_KEY);

    protected static final List<String> VALID_EXECUTION_CONTEXT_PROPERTY_KEYS = Arrays.asList(SIMPLE_SCHEMA_KEY + ".concurrent", SIMPLE_DATA_KEY + ".sequential", COMPLEX_KEY + ".concurrent");

    @Test
    public void testGetSqlExecutionContexts() {

        // ensure that invalid keys fail
        for (String invalidKey : INVALID_EXECUTION_CONTEXT_PROPERTY_KEYS) {
            try {
                List<SqlExecutionContext> contexts = SqlConfigUtils.getSqlExecutionContexts(Arrays.asList(invalidKey));
                fail("Invalid key: " + invalidKey + " did not throw expected exception, returned contexts: " + contexts.toString());
            }
            catch(Exception e) {
                // exception is expected here
            }
        }

        // ensure that valid keys work
        List<SqlExecutionContext> contexts = SqlConfigUtils.getSqlExecutionContexts(VALID_EXECUTION_CONTEXT_PROPERTY_KEYS);

        List<String> expectedEmptyList = new ArrayList<String>(EXPECTED_EXECUTION_CONTEXT_GROUP_NAMES);

        for (SqlExecutionContext context : contexts) {
            logger.info("Context with full property key " + context.getKey() + " has group of " + context.getGroup());
            expectedEmptyList.remove(context.getGroup());
        }

        assertTrue("Did not find expected group names in contexts: " + expectedEmptyList, expectedEmptyList.isEmpty());

    }

}
