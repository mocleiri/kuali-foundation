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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.integration.commandline.CommandLineUtils;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.InvalidExampleException;
import liquibase.snapshot.SnapshotControl;
import liquibase.snapshot.SnapshotGeneratorFactory;
import org.kuali.common.impex.liquibase.LiquibaseSchemaProvider;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.schema.MySqlSequenceFinder;
import org.kuali.common.impex.schema.OracleSequenceFinder;
import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.jdbc.context.DatabaseProcessContext;
import org.kuali.common.jdbc.spring.JdbcDataSourceConfig;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ JdbcDataSourceConfig.class })
public class LiquibaseSchemaConfig {

	private static final Logger log = LoggerFactory.getLogger(LiquibaseSchemaConfig.class);

    protected static final String DB_VENDOR_KEY = "db.vendor";

	@Autowired
	JdbcDataSourceConfig dataSourceConfig;

    @Autowired
    Environment env;

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

		log.info("Creating Liquibase snapshot [{}] - [{}]", url, username);

		// Use Liquibase to create a database object
		Database database = CommandLineUtils.createDatabaseObject(loader, url, username, password, driver, null, username, null, null);

		// Use Liquibase to snapshot the database
		SnapshotGeneratorFactory factory = SnapshotGeneratorFactory.getInstance();
		DatabaseSnapshot snapshot = factory.createSnapshot(database.getDefaultSchema(), database, new SnapshotControl());

		Object[] args = { url, username, FormatUtils.getTime(System.currentTimeMillis() - start) };
		log.info("Liquibase snapshot created  [{}] - [{}] - Time: {}", args);

		return snapshot;
	}

    @Bean
    public Map<String, SequenceFinder> sequenceFinderMap() throws SQLException {
        Map<String, SequenceFinder> result = new HashMap<String, SequenceFinder>();

        result.put(OracleSequenceFinder.SUPPORTED_VENDOR, oracleSequenceFinder());
        result.put(MySqlSequenceFinder.SUPPORTED_VENDOR, mysqlSequenceFinder());

        return result;
    }

	@Bean
	public LiquibaseSchemaProvider liquibaseModelProvider() throws DatabaseException, InvalidExampleException, SQLException {

		// Snapshot the db
		DatabaseSnapshot snapshot = databaseSnapshot();

		log.info("Creating LiquibaseModelProvider");

		// Preserve the start time
		long start = System.currentTimeMillis();

		// get the instance of the SequenceFinder
        String dbVendor = SpringUtils.getProperty(env, DB_VENDOR_KEY);
        SequenceFinder finder = sequenceFinderMap().get(dbVendor);

        if(finder == null) {
            log.warn("NO MATCHING IMPLENTATION FOR SequenceFinder INTERFACE FOUND FOR VENDOR " + dbVendor);
        }

		LiquibaseSchemaProvider modelProvider = new LiquibaseSchemaProvider(snapshot, finder);

		log.info("LiquibaseModelProvider created - Time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		// By default, the liquibase DatabaseSnapshot keeps an open connection to the database
		// after building the snapshot
		// We want to close down that connection after getting the snapshot data
		databaseSnapshot().getDatabase().getConnection().close();

		return modelProvider;
	}

    @Bean
    public Schema schema() throws SQLException, DatabaseException, InvalidExampleException {
        return liquibaseModelProvider().getSchema();
    }

    @Bean
    public OracleSequenceFinder oracleSequenceFinder() throws SQLException {
        DatabaseProcessContext context = dataSourceConfig.jdbcDatabaseProcessContext();
        Connection conn = dataSourceConfig.jdbcDataSource().getConnection();

        return new OracleSequenceFinder(conn, context.getUsername());
    }

    @Bean
    public MySqlSequenceFinder mysqlSequenceFinder() {
        return new MySqlSequenceFinder();
    }
}
