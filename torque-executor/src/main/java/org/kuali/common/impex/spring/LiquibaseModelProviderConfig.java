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

package org.kuali.common.impex.spring;

import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.integration.commandline.CommandLineUtils;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.InvalidExampleException;
import liquibase.snapshot.SnapshotControl;
import liquibase.snapshot.SnapshotGeneratorFactory;
import org.kuali.common.impex.model.impl.liquibase.LiquibaseModelProvider;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcDataSourceConfig.class })
public class LiquibaseModelProviderConfig {

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    @Bean
    public DatabaseSnapshot databaseSnapshot() throws DatabaseException, InvalidExampleException {
        DatabaseProcessContext context = dataSourceConfig.jdbcDatabaseProcessContext();

        Database database = CommandLineUtils.createDatabaseObject(MpxSupplierConfig.class.getClassLoader(), context.getUrl(), context.getUsername(), context.getPassword(), context.getDriver(), null, context.getUsername(), null, null);

        return SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl());
    }

    @Bean
    public LiquibaseModelProvider liquibaseModelProvider() throws DatabaseException, InvalidExampleException {

        LiquibaseModelProvider modelProvider = new LiquibaseModelProvider(databaseSnapshot());

        // By default, the liquibase DatabaseSnapshot keeps an open connection to the database
        // after building the snapshot
        // We want to close down that connection after getting the snapshot data
        databaseSnapshot().getDatabase().getConnection().close();

        return modelProvider;
    }

}
