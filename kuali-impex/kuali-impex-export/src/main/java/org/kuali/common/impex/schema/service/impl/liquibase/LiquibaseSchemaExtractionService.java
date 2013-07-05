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

package org.kuali.common.impex.schema.service.impl.liquibase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.InvalidExampleException;
import liquibase.snapshot.SnapshotControl;
import liquibase.snapshot.SnapshotGeneratorFactory;

import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.schema.service.SchemaDumpContext;
import org.kuali.common.impex.schema.service.SchemaDumpService;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiquibaseSchemaExtractionService implements SchemaDumpService {

	private static final Logger log = LoggerFactory.getLogger(LiquibaseSchemaExtractionService.class);

	@Override
	public Schema getSchema(SchemaDumpContext context) {
		try {
			DatabaseSnapshot snapshot = getDatabaseSnapshot(context);
			LiquibaseSchemaProvider schemaProvider = getSchemaProvider(snapshot, context);

			return schemaProvider.buildSchema();
		} catch (Exception e) {
			log.error("Exception occurred when extracting database schema: " + e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	protected DatabaseSnapshot getDatabaseSnapshot(SchemaDumpContext context) throws DatabaseException, InvalidExampleException, SQLException {

		// Preserve the start time
		long start = System.currentTimeMillis();

		// get an instance of the database factory
		DatabaseFactory databaseFactory = DatabaseFactory.getInstance();

		Connection connection = null;
		DatabaseSnapshot snapshot = null;
		try {

			log.info("Creating Liquibase snapshot for schema {}", context.getSchemaName());

			connection = context.getDataSource().getConnection();

			// build an instance of the liquibase database object
			Database database = databaseFactory.findCorrectDatabaseImplementation(new JdbcConnection(connection));

			database.setDefaultCatalogName(null);
			database.setDefaultSchemaName(context.getSchemaName());

			// Use Liquibase to snapshot the database
			SnapshotGeneratorFactory factory = SnapshotGeneratorFactory.getInstance();
			snapshot = factory.createSnapshot(database.getDefaultSchema(), database, new SnapshotControl());

			log.info("Liquibase snapshot created - Time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		} finally {
			JdbcUtils.closeQuietly(context.getDataSource(), connection);
		}

		return snapshot;
	}

	protected LiquibaseSchemaProvider getSchemaProvider(DatabaseSnapshot snapshot, SchemaDumpContext context) throws SQLException {
		log.info("Creating LiquibaseModelProvider");

		// Preserve the start time
		long start = System.currentTimeMillis();

		LiquibaseSchemaProvider modelProvider = new LiquibaseSchemaProvider(snapshot, context.getSequenceFinder(), context.getDataSource());
		modelProvider.setSchemaName(context.getSchemaName());
		modelProvider.setNameFilter(context.getNameFilter());

		log.info("LiquibaseModelProvider created - Time: {}", FormatUtils.getTime(System.currentTimeMillis() - start));

		return modelProvider;
	}

	@Override
	public List<Table> extractTables(List<String> tableNames, SchemaDumpContext context) throws SQLException {
		Schema schema = getSchema(context);

		List<Table> result = new ArrayList<Table>(tableNames.size());
		for (Table table : schema.getTables()) {
			if (tableNames.contains(table.getName())) {
				result.add(table);
			}
		}

		return result;
	}

	@Override
	public List<View> extractViews(SchemaDumpContext context) throws SQLException {
		Schema schema = getSchema(context);

		return schema.getViews();
	}

	@Override
	public List<Sequence> extractSequences(SchemaDumpContext context) throws SQLException {
		Schema schema = getSchema(context);

		return schema.getSequences();
	}

	@Override
	public List<ForeignKey> extractForeignKeys(List<String> tableNames, SchemaDumpContext context) throws SQLException {
		Schema schema = getSchema(context);

		List<ForeignKey> result = new ArrayList<ForeignKey>(tableNames.size());
		for (ForeignKey foreignKey : schema.getForeignKeys()) {
			if (tableNames.contains(foreignKey.getLocalTableName())) {
				result.add(foreignKey);
			}
		}

		return result;
	}
}
