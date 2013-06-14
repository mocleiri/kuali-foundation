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

import java.sql.SQLException;

import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.integration.commandline.CommandLineUtils;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.InvalidExampleException;
import liquibase.snapshot.SnapshotControl;
import liquibase.snapshot.SnapshotGeneratorFactory;

import org.kuali.common.impex.liquibase.LiquibaseModelProvider;
import org.kuali.common.impex.schema.OracleSequenceFinder;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JdbcDataSourceConfig.class })
public class LiquibaseModelProviderConfig {

	private static final Logger log = LoggerFactory.getLogger(LiquibaseModelProviderConfig.class);

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

	@Bean
	public DatabaseSnapshot databaseSnapshot() throws DatabaseException, InvalidExampleException {

		// Preserve the start time
		long start = System.currentTimeMillis();

		// Extract the object holding some aggregated JDBC context information
		DatabaseProcessContext context = dataSourceConfig.jdbcDatabaseProcessContext();

		// Pull out the JDBC info
		ClassLoader loader = getClass().getClassLoader();
		String url = context.getUrl();
		String username = context.getUsername();
		String password = context.getPassword();
		String driver = context.getDriver();

		log.info("Creating Liquibase snapshot [{}] - [{}]", context.getUrl(), context.getUsername());

		// Use Liquibase to create a database object
		Database database = CommandLineUtils.createDatabaseObject(loader, url, username, password, driver, null, username, null, null);

		// Use Liquibase to snapshot the database
		DatabaseSnapshot snapshot = SnapshotGeneratorFactory.getInstance().createSnapshot(database.getDefaultSchema(), database, new SnapshotControl());

		log.info("Liquibase snapshot created. Elapsed time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		return snapshot;
	}

	@Bean
	public LiquibaseModelProvider liquibaseModelProvider() throws DatabaseException, InvalidExampleException, SQLException {

		DatabaseSnapshot snapshot = databaseSnapshot();

		log.info("Building LiquibaseModelProvider from snapshot...");
		long start = System.currentTimeMillis();

		LiquibaseModelProvider modelProvider = new LiquibaseModelProvider(snapshot, new OracleSequenceFinder(dataSourceConfig.jdbcDataSource().getConnection(), dataSourceConfig
				.jdbcDatabaseProcessContext().getUsername()));
		log.info("LiquibaseModelProvider constructed, elapsed time: " + (System.currentTimeMillis() - start) / 1000l + " seconds");

		// By default, the liquibase DatabaseSnapshot keeps an open connection to the database
		// after building the snapshot
		// We want to close down that connection after getting the snapshot data
		databaseSnapshot().getDatabase().getConnection().close();

		return modelProvider;
	}
}
