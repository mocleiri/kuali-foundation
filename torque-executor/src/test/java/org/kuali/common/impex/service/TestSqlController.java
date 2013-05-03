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

import org.kuali.common.impex.spring.GeneratorPropertiesConfig;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.jdbc.spring.JdbcPropertiesConfig;
import org.kuali.common.jdbc.spring.SqlDbaBeforeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

/**
 * Created with IntelliJ IDEA.
 * User: andy
 * Date: 5/3/13
 * Time: 9:23 AM
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@Import({ JdbcCommonConfig.class, JdbcDataSourceConfig.class, SqlDbaBeforeConfig.class, JdbcPropertiesConfig.class, GeneratorPropertiesConfig.class })
public class TestSqlController {

    @Autowired
    Environment env;

    @Autowired
    JdbcPropertiesConfig jdbcProperties;

    @Autowired
    GeneratorPropertiesConfig generatorProperties;

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    @Autowired
    JdbcCommonConfig commonConfig;

    @Autowired
    SqlDbaBeforeConfig dbaBeforeConfig;

    public JdbcCommonConfig getCommonConfig() {
        return commonConfig;
    }

    public JdbcDataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public SqlDbaBeforeConfig getDbaBeforeConfig() {
        return dbaBeforeConfig;
    }

    public Environment getEnv() {
        return env;
    }

}
