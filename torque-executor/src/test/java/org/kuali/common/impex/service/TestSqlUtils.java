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

package org.kuali.common.impex.service;

import org.kuali.common.jdbc.context.JdbcContext;
import org.kuali.common.jdbc.context.SqlExecutionContext;
import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.spring.SqlConfigContext;
import org.kuali.common.jdbc.spring.SqlConfigUtils;

/**
 * This class holds convenience methods used by several tests
 *
 * @author andrewlubbers
 */
public class TestSqlUtils {

    public static JdbcContext buildJdbcContext(TestSqlController controller, String group, SqlMode mode) {
        SqlExecutionContext context = new SqlExecutionContext();
        context.setGroup(group);
        context.setMode(mode);

        SqlConfigContext scc = new SqlConfigContext(controller.getEnv(), context, controller.getCommonConfig(), controller.getDataSourceConfig());
        return SqlConfigUtils.getJdbcContext(scc);
    }

}
