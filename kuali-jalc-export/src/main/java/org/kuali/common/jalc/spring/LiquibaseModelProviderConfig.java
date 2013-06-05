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

package org.kuali.common.jalc.spring;

import java.sql.SQLException;
import java.util.logging.Logger;

import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.integration.commandline.CommandLineUtils;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.InvalidExampleException;
import liquibase.snapshot.SnapshotControl;
import liquibase.snapshot.SnapshotGeneratorFactory;
import org.kuali.common.jalc.liquibase.LiquibaseModelProvider;
import org.kuali.common.jalc.schema.OracleSequenceFinder;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcDataSourceConfig.class })
public class LiquibaseModelProviderConfig {

    private static Logger log = Logger.getLogger(LiquibaseModelProviderConfig.class.getSimpleName());

    @Autowired
    JdbcDataSourceConfig dataSourceConfig;

    @Bean
    public DatabaseSnapshot databaseSnapshot() throws DatabaseException, InvalidExampleException {

        long start = System.currentTimeMillis();

        DatabaseProcessContext context = dataSourceConfig.jdbcDatabaseProcessContext();

        log.info("Initializing liquibase snapshot of data source: url=" + context.getUrl() + ", username=" + context.getUsername());
        Database database = CommandLineUtils.createDatabaseObject(getClass().getClassLoader(), context.getUrl(), context.getUsername(), context.getPassword(), context.getDriver(), null, context.getUsername(), null, null);

        DatabaseSnapshot snapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl());

        log.info("liquibase snapshot initialized, elapsed time: " + (System.currentTimeMillis() - start)/1000l + " seconds");

        return snapshot;
    }

    @Bean
    public LiquibaseModelProvider liquibaseModelProvider() throws DatabaseException, InvalidExampleException, SQLException {

        DatabaseSnapshot snapshot = databaseSnapshot();

        log.info("Building LiquibaseModelProvider from snapshot...");
        long start = System.currentTimeMillis();

        LiquibaseModelProvider modelProvider = new LiquibaseModelProvider(snapshot, new OracleSequenceFinder(dataSourceConfig.jdbcDataSource().getConnection(), dataSourceConfig.jdbcDatabaseProcessContext().getUsername()));
        log.info("LiquibaseModelProvider constructed, elapsed time: " + (System.currentTimeMillis() - start)/1000l + " seconds");

        // By default, the liquibase DatabaseSnapshot keeps an open connection to the database
        // after building the snapshot
        // We want to close down that connection after getting the snapshot data
        databaseSnapshot().getDatabase().getConnection().close();

        return modelProvider;
    }
}
