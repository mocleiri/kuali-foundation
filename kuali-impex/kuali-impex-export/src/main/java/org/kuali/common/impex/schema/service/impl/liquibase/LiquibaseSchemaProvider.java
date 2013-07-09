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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.sql.DataSource;

import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.Database;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.JdbcDatabaseSnapshot;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.ForeignKeyConstraintType;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.Size;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.model.util.NamedElementComparator;
import org.kuali.common.impex.schema.SequenceFinder;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.util.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LiquibaseSchemaProvider {

	private static Logger log = LoggerFactory.getLogger(LiquibaseSchemaProvider.class.getSimpleName());

	/**
	 * Constants for result set keys from DatabaseMetaData.getImportedKeys
	 */
	protected static final String METADATA_FK_NAME = "FK_NAME";
	protected static final String METADATA_FK_COLUMN_NAME = "FKCOLUMN_NAME";
	protected static final String METADATA_PK_COLUMN_NAME = "PKCOLUMN_NAME";
	protected static final String METADATA_INDEX_NAME = "INDEX_NAME";
	protected static final String METADATA_INDEX_COLUMN_NAME = "COLUMN_NAME";

	protected static final String SINGLE_QUOTE = "'";

	protected static final String SYSGUID_KEYWORD_DEFAULT = "SYS_GUID()";
	protected static final String SYSDATE_KEYWORD_DEFAULT = "SYSDATE";
	protected static final String USERSESSION_KEYWORD_DEFAULT = "USERENV(\'SESSIONID\')";

	protected static final String[] RESERVED_DEFAULT_KEYWORDS = { SYSGUID_KEYWORD_DEFAULT, SYSDATE_KEYWORD_DEFAULT, USERSESSION_KEYWORD_DEFAULT };

	SequenceFinder sequenceFinder;

	/**
	 * Maps table names to outbound (i.e. having the same local table name as the key in the map) foreign keys
	 */
	Map<String, List<ForeignKey>> tableNameToForeignKeys;

	DataSource dataSource;

	StringFilter nameFilter;

	String schemaName;

	DatabaseSnapshot snapshot;

	public LiquibaseSchemaProvider(DatabaseSnapshot snapshot, SequenceFinder sequenceFinder, DataSource dataSource) throws SQLException {

		this.sequenceFinder = sequenceFinder;
		this.dataSource = dataSource;
		this.snapshot = snapshot;
	}

	public Schema buildSchema() throws SQLException {
		log.info("Building tables...");
		List<Table> tables = buildTables(snapshot);
		log.info("Table building complete.");

		log.info("Building views...");
		List<View> views = buildViews(snapshot);
		log.info("View building complete.");

		log.info("Building sequences...");
		List<Sequence> sequences = buildSequences(snapshot);
		log.info("Sequence building complete.");

		log.info("Building foreign keys...");
		List<ForeignKey> foreignKeys = buildForeignKeys(snapshot);
		log.info("Foreign key building complete.");

		// sort each of the schema elements
		Collections.sort(tables, NamedElementComparator.getInstance());
		Collections.sort(views, NamedElementComparator.getInstance());
		Collections.sort(sequences, NamedElementComparator.getInstance());
		Collections.sort(foreignKeys, NamedElementComparator.getInstance());

		Schema schema = new Schema();
		schema.setTables(tables);
		schema.setViews(views);
		schema.setSequences(sequences);
		schema.setForeignKeys(foreignKeys);

		return schema;
	}

	protected List<Table> buildTables(DatabaseSnapshot snapshot) {

		Set<liquibase.structure.core.Table> sourceTables = snapshot.get(liquibase.structure.core.Table.class);

		List<Table> results = new ArrayList<Table>(sourceTables.size());
		tableNameToForeignKeys = new HashMap<String, List<ForeignKey>>();

		for (liquibase.structure.core.Table sourceTable : sourceTables) {
			// skip any excluded names
			if (isExcludedName(sourceTable.getName())) {
				continue;
			}

			Table t = new Table(sourceTable.getName());
			t.setColumns(new ArrayList<Column>(sourceTable.getColumns().size()));

			List<String> primaryKeyColumnNames;
			if (sourceTable.getPrimaryKey() == null) {
				primaryKeyColumnNames = Collections.emptyList();
			} else {
				primaryKeyColumnNames = sourceTable.getPrimaryKey().getColumnNamesAsList();
			}

			boolean primaryKey;
			for (liquibase.structure.core.Column sourceColumn : sourceTable.getColumns()) {
				primaryKey = primaryKeyColumnNames.contains(sourceColumn.getName());
				Column c = buildColumn(sourceColumn, primaryKey, t);
				t.getColumns().add(c);
			}

			// sort the columns by name
			Collections.sort(t.getColumns(), NamedElementComparator.getInstance());

			// add all indexes found for this table and sort them
			t.getIndices().addAll(getIndices(sourceTable, snapshot));
			Collections.sort(t.getIndices(), NamedElementComparator.getInstance());

			// build unique constraints
			for (liquibase.structure.core.UniqueConstraint u : sourceTable.getUniqueConstraints()) {
				UniqueConstraint unique = new UniqueConstraint(u.getColumns(), u.getName());
				t.getUniqueConstraints().add(unique);
			}

			results.add(t);
		}

		Collections.sort(results, NamedElementComparator.getInstance());

		return results;
	}

	protected Collection<Index> getIndices(liquibase.structure.core.Table sourceTable, DatabaseSnapshot snapshot) {
		AbstractJdbcDatabase database = (AbstractJdbcDatabase) snapshot.getDatabase();
		String searchCatalog = database.getJdbcCatalogName(sourceTable.getSchema());
		String searchSchema = database.getJdbcSchemaName(sourceTable.getSchema());
		String searchTableName = database.correctObjectName(sourceTable.getName(), liquibase.structure.core.Table.class);

		// retrieve database meta data. The returned object is a string to string map
		// of the ResultSet returned by java.sql.DatabaseMetaInfo.getIndexInfo
		List<JdbcDatabaseSnapshot.CachedRow> rs;
		try {
			rs = ((JdbcDatabaseSnapshot) snapshot).getMetaData().getIndexInfo(searchCatalog, searchSchema, searchTableName, false, true);
		} catch (SQLException e) {
			throw new RuntimeException("Could not retrieve index information for table " + sourceTable.getName(), e);
		}

		// build a list of index names that should be ignored
		// specifically, the primary key does not need a separate index,
		// and any unique column constraints do not need a separately declared index
		List<String> ignoredIndexNames = new ArrayList<String>();
		if (sourceTable.getPrimaryKey() != null) {
			ignoredIndexNames.add(sourceTable.getPrimaryKey().getName());
		}
		for (liquibase.structure.core.UniqueConstraint u : sourceTable.getUniqueConstraints()) {
			ignoredIndexNames.add(u.getName());
		}

		// If an index references multiple columns, an entry in this result set will exist
		// for each column name
		Map<String, Index> results = new HashMap<String, Index>();
		for (JdbcDatabaseSnapshot.CachedRow row : rs) {
			String indexName = row.getString(METADATA_INDEX_NAME);
			// skip any entries with no index name or an ignored name
			if (StringUtils.isEmpty(indexName) || ignoredIndexNames.contains(indexName)) {
				continue;
			}

			// check to see if we already have created an index of the same name
			Index index = results.get(indexName);
			if (index == null) {
				index = new Index(new ArrayList<String>(), indexName);
				results.put(indexName, index);
			}

			// add the column name in this row to the column names for this index
			index.getColumnNames().add(row.getString(METADATA_INDEX_COLUMN_NAME));
		}

		return results.values();
	}

	protected Column buildColumn(liquibase.structure.core.Column sourceColumn, boolean primaryKey, Table modelTable) {
		Column col = new Column(sourceColumn.getName(), DataTypeUtils.getColumnDataType(sourceColumn));

		if (sourceColumn.getDefaultValue() != null) {
			String defaultValue = sourceColumn.getDefaultValue().toString().trim();

			// if the column is a string data type, check that it may need single quotes added
			if (col.getDataType() == DataType.STRING || col.getDataType() == DataType.CLOB) {
				// ignore adding single quotes if the default value contains a reseved keyword
				if (notReservedDefaultValue(defaultValue)) {
					if (!defaultValue.startsWith(SINGLE_QUOTE)) {
						defaultValue = SINGLE_QUOTE + defaultValue + SINGLE_QUOTE;
					}
				}
			}

			col.setDefaultValue(defaultValue);
		}
		col.setDescription(sourceColumn.getRemarks());
		col.setNullable(sourceColumn.isNullable());
		col.setPrimaryKey(primaryKey);

		if (sourceColumn.getType().getColumnSize() != null) {
			int size = sourceColumn.getType().getColumnSize();

			// if there are no decimal digits set, create a TypeSize with just a size
			Size ts;
			if (sourceColumn.getType().getDecimalDigits() == null) {
				ts = new Size(size);
			} else {
				int scale = sourceColumn.getType().getDecimalDigits();
				ts = new Size(size, scale);
			}

			col.setSize(ts);
		}

		return col;
	}

	/**
	 * Check that the given column default value is not a reseved keyword
	 * 
	 * @param defaultValue
	 *            the potential column default value
	 * @return true if the given value does not contain a reserved keyword, false otherwise
	 */
	protected boolean notReservedDefaultValue(String defaultValue) {

		for (String reserved : Arrays.asList(RESERVED_DEFAULT_KEYWORDS)) {
			if (defaultValue.contains(reserved)) {
				return false;
			}
		}

		return true;

	}

	protected List<View> buildViews(DatabaseSnapshot snapshot) {
		Set<liquibase.structure.core.View> sourceViews = snapshot.get(liquibase.structure.core.View.class);

		List<View> results = new ArrayList<View>(sourceViews.size());

		for (liquibase.structure.core.View sourceView : sourceViews) {
			if (isExcludedName(sourceView.getName())) {
				continue;
			}
			View v = new View(sourceView.getName(), sourceView.getDefinition());

			results.add(v);
		}

		Collections.sort(results, NamedElementComparator.getInstance());

		return results;
	}

	protected List<Sequence> buildSequences(DatabaseSnapshot snapshot) throws SQLException {
		// Current liquibase structure does not retrieve current value for sequences
		// SequenceFinder was created to work around that

		if (sequenceFinder != null) {
			Connection connection = null;

			try {
				connection = dataSource.getConnection();
				List<Sequence> results = sequenceFinder.findSequences(connection, schemaName, nameFilter);
				Collections.sort(results, NamedElementComparator.getInstance());
				return results;
			} finally {
				JdbcUtils.closeQuietly(dataSource, connection);
			}
		} else {
			log.warn("NO IMPLEMENTATION OF SequenceFinder FOUND, RETURNING EMPTY SEQUENCE LIST");
			return Collections.emptyList();
		}
	}

	protected List<ForeignKey> buildForeignKeys(DatabaseSnapshot snapshot) throws SQLException {
		Set<liquibase.structure.core.ForeignKey> sourceFks = snapshot.get(liquibase.structure.core.ForeignKey.class);

		List<ForeignKey> results = new ArrayList<ForeignKey>(sourceFks.size());

		for (liquibase.structure.core.ForeignKey sourceFk : sourceFks) {
			if (isExcludedName(sourceFk.getName())) {
				continue;
			}

			// In the liquibase model, Foreign keys are initialized with the "PrimaryKeyTable" as the table that is being pointed TO (i.e. the outside table)
			// and the "ForeignKeyTable" as the table that is pointed from (i.e. the source table)

			String localTableName = sourceFk.getForeignKeyTable().getName();
			String foreignTableName = sourceFk.getPrimaryKeyTable().getName();

			ForeignKey fk = new ForeignKey(sourceFk.getName(), localTableName, foreignTableName);

			setColumnNames(fk, sourceFk, snapshot);

			fk.setOnUpdate(translateForeignKeyConstraint(sourceFk.getUpdateRule()));
			fk.setOnDelete(translateForeignKeyConstraint(sourceFk.getDeleteRule()));

			List<ForeignKey> fkList = tableNameToForeignKeys.get(fk.getLocalTableName());
			if (fkList == null) {
				fkList = new ArrayList<ForeignKey>();
				tableNameToForeignKeys.put(fk.getLocalTableName(), fkList);
			}

			fkList.add(fk);

			results.add(fk);
		}

		Collections.sort(results, NamedElementComparator.getInstance());

		return results;
	}

	public ForeignKeyConstraintType translateForeignKeyConstraint(liquibase.structure.core.ForeignKeyConstraintType rule) {
		if (rule == null) {
			return null;
		} else {
			switch (rule) {
			case importedKeyCascade:
				return ForeignKeyConstraintType.CASCADE;
			case importedKeySetNull:
				return ForeignKeyConstraintType.SET_NULL;
			case importedKeySetDefault:
				return ForeignKeyConstraintType.SET_DEFAULT;
			case importedKeyRestrict:
				return ForeignKeyConstraintType.RESTRICT;
			case importedKeyNoAction:
				return ForeignKeyConstraintType.NO_ACTION;
			default:
				throw new IllegalArgumentException("Unknown ForeignKeyConstraintType value: " + rule.toString());
			}
		}
	}

	protected void setColumnNames(ForeignKey fk, liquibase.structure.core.ForeignKey liquibaseFk, DatabaseSnapshot snapshot) throws SQLException {
		Database database = snapshot.getDatabase();

		liquibase.structure.core.Table fkTable = liquibaseFk.getForeignKeyTable();
		String searchCatalog = ((AbstractJdbcDatabase) database).getJdbcCatalogName(fkTable.getSchema());
		String searchSchema = ((AbstractJdbcDatabase) database).getJdbcSchemaName(fkTable.getSchema());
		String searchTableName = database.correctObjectName(fkTable.getName(), liquibase.structure.core.Table.class);

		List<JdbcDatabaseSnapshot.CachedRow> importedKeyMetadataResultSet = ((JdbcDatabaseSnapshot) snapshot).getMetaData().getImportedKeys(searchCatalog, searchSchema,
				searchTableName);

		// find all foreign key metadata for the foreign key we are trying to populate
		// create a mapping of local column names to foreign column names,
		// sorted by the local name to keep generated schema consistent
		SortedMap<String, String> localToForeignColumnNames = new TreeMap<String, String>();
		for (JdbcDatabaseSnapshot.CachedRow row : importedKeyMetadataResultSet) {
			String fk_name = row.getString(METADATA_FK_NAME);

			if (fk_name.equals(fk.getName())) {

				// from the meta data, the foreign column comes from the local table,
				// and the primary column is the primary key from the outside table
				// See java.sql.DatabaseMetaData.getImportedKeys for more details
				localToForeignColumnNames.put(row.getString(METADATA_FK_COLUMN_NAME), row.getString(METADATA_PK_COLUMN_NAME));
			}
		}

		// Add the column names to the foreign key lists now that they are sorted
		for (String localColName : localToForeignColumnNames.keySet()) {
			fk.getLocalColumnNames().add(localColName);
			fk.getForeignColumnNames().add(localToForeignColumnNames.get(localColName));
		}

	}

	public StringFilter getNameFilter() {
		return nameFilter;
	}

	public void setNameFilter(StringFilter nameFilter) {
		this.nameFilter = nameFilter;
	}

	private boolean isExcludedName(String name) {
		if (nameFilter == null) {
			return false;
		} else {
			return !nameFilter.include(name);
		}
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

}
