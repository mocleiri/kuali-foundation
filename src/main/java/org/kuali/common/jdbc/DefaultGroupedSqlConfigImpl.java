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

package org.kuali.common.jdbc;

import org.kuali.common.jdbc.context.SqlMode;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.spring.JdbcCommonConfig;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.springframework.core.env.Environment;

/**
 * @author andrewlubbers
 */
public class DefaultGroupedSqlConfigImpl implements GroupedSqlConfig {


    private String groupKey;
    private SqlMode sqlMode;
    private Environment environment;
    private JdbcCommonConfig jdbcCommonConfig;
    private JdbcDataSourceConfig dataSourceConfig;
    private SqlListener sqlListener;

    public DefaultGroupedSqlConfigImpl() {
        this(null, null, null, null, null, null);
    }

    public DefaultGroupedSqlConfigImpl(String groupKey, SqlMode sqlMode, Environment environment, JdbcCommonConfig jdbcCommonConfig, JdbcDataSourceConfig dataSourceConfig, SqlListener sqlListener) {
        this.groupKey = groupKey;
        this.sqlMode = sqlMode;
        this.environment = environment;
        this.jdbcCommonConfig = jdbcCommonConfig;
        this.dataSourceConfig = dataSourceConfig;
        this.sqlListener = sqlListener;
    }

    @Override
    public String getGroupKey() {
        return groupKey;
    }

    @Override
    public SqlMode getSqlMode() {
        return sqlMode;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public JdbcCommonConfig getJdbcCommonConfig() {
        return jdbcCommonConfig;
    }

    @Override
    public JdbcDataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(JdbcDataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public void setJdbcCommonConfig(JdbcCommonConfig jdbcCommonConfig) {
        this.jdbcCommonConfig = jdbcCommonConfig;
    }

    public void setSqlMode(SqlMode sqlMode) {
        this.sqlMode = sqlMode;
    }

    @Override
    public SqlListener getSqlListener() {
        return sqlListener;
    }

    public void setSqlListener(SqlListener sqlListener) {
        this.sqlListener = sqlListener;
    }
}
