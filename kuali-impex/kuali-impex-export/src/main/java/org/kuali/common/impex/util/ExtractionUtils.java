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

package org.kuali.common.impex.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.ForeignKeyConstraintType;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.impex.model.util.NamedElementComparator;
import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.util.PercentCompleteInformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The following class contains methods used for schema extraction with java.sql.DatabaseMetaData
 */
public class ExtractionUtils {

	private static Logger log = LoggerFactory.getLogger(ExtractionUtils.class);

	protected static final String TABLE_META_DATA_TYPE = "TABLE";
	protected static final int TABLE_NAME_INDEX = 3;
	protected static final int TABLE_COMMENT_INDEX = 5;

	protected static final int COLUMN_NAME_INDEX = 4;
	protected static final int COLUMN_TYPE_INDEX = 5;
	protected static final int COLUMN_TYPE_SIZE_INDEX = 7;
	protected static final int COLUMN_TYPE_SCALE_INDEX = 9;
	protected static final int COLUMN_REMARKS_INDEX = 12;
	protected static final int COLUMN_DEFAULT_INDEX = 13;
	protected static final int COLUMN_NULLABLE_INDEX = 18;

	protected static final String COLUMN_RESULT_SET_NULLABLE_VALUE = "YES";

	protected static final int PRIMARY_KEY_COLUMN_NAME_INDEX = 4;
	protected static final int PRIMARY_KEY_NAME_INDEX = 6;
	protected static final String PRIMARY_KEY_UNNAMED_KEY = "UNNAMED";

	protected static final int INDEX_NON_UNIQUE_INDEX = 4;
	protected static final int INDEX_NAME_INDEX = 6;
	protected static final int INDEX_COLUMN_NAME_INDEX = 9;

	protected static final int FOREIGN_KEY_IMPORTED_TABLE_NAME_INDEX = 3;
	protected static final int FOREIGN_KEY_IMPORTED_COLUMN_NAME_INDEX = 4;
	protected static final int FOREIGN_KEY_LOCAL_COLUMN_NAME_INDEX = 8;
	protected static final int FOREIGN_KEY_ON_UPDATE_INDEX = 10;
	protected static final int FOREIGN_KEY_ON_DELETE_INDEX = 11;
	protected static final int FOREIGN_KEY_NAME_INDEX = 12;

	protected static final Map<Integer, DataType> SQL_TYPE_TO_DATA_TYPE = getDataTypeMapping();

	protected static Map<Integer, DataType> getDataTypeMapping() {
		HashMap<Integer, DataType> typeMap = new HashMap<Integer, DataType>();

		typeMap.put(Types.CLOB, DataType.CLOB);
		typeMap.put(Types.LONGNVARCHAR, DataType.CLOB);
		typeMap.put(Types.LONGVARCHAR, DataType.CLOB);
		typeMap.put(Types.VARCHAR, DataType.STRING);
		typeMap.put(Types.CHAR, DataType.CHAR);
		typeMap.put(Types.DATE, DataType.DATE);
		typeMap.put(Types.TIMESTAMP, DataType.TIMESTAMP);
		typeMap.put(Types.NUMERIC, DataType.FLOAT);
		typeMap.put(Types.DOUBLE, DataType.FLOAT);
		typeMap.put(Types.REAL, DataType.FLOAT);
		typeMap.put(Types.FLOAT, DataType.FLOAT);
		typeMap.put(Types.DECIMAL, DataType.FLOAT);
		typeMap.put(Types.INTEGER, DataType.INTEGER);
		typeMap.put(Types.SMALLINT, DataType.INTEGER);
		typeMap.put(Types.BLOB, DataType.BLOB);
		typeMap.put(Types.BIT, DataType.BIT);

		return Collections.unmodifiableMap(typeMap);
	}

	/**
	 * Oracle creates tables with a prefix of "BIN$" to represent a recently deleted table (a 'recycle bin' of sorts), introduced in Oracle 10
	 * <p/>
	 * See this article for details: http://docs.oracle.com/cd/B19306_01/server.102/b14231/tables.htm#ADMIN01511
	 */
	protected static final String ORACLE_RECYCLE_BIN_TABLE_NAME_PREFIX = "BIN$";

	protected static final List<String> IGNORED_TABLE_NAME_PREFIXES = Arrays.asList(ORACLE_RECYCLE_BIN_TABLE_NAME_PREFIX);

	protected static final String SINGLE_QUOTE = "'";

	protected static final boolean DEFAULT_NULLABLE = true;

	protected static final ForeignKeyConstraintType DEFAULT_CONSTRAINT_RULE = ForeignKeyConstraintType.RESTRICT;

	public static List<String> getTableNames(DataSource dataSource, String schema) throws SQLException {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			DatabaseMetaData metadata = conn.getMetaData();
			return getTableNamesFromMetaData(schema, metadata);
		} finally {
			JdbcUtils.closeQuietly(dataSource, conn);
		}
	}

	// TODO Ask Andy why this is needed. Won't the regex filter out undesired table names anyway?
	public static List<String> getTableNamesFromMetaDataUsingIgnorePrefixes(String schemaName, DatabaseMetaData databaseMetaData) throws SQLException {
		List<String> results = new ArrayList<String>();

		ResultSet nameResultSet = null;
		try {
			nameResultSet = databaseMetaData.getTables(null, schemaName, null, new String[] { TABLE_META_DATA_TYPE });
			while (nameResultSet.next()) {
				String name = nameResultSet.getString(TABLE_NAME_INDEX);

				// if the name is null, skip it
				if (name == null) {
					continue;
				}

				// if the name starts with one of the ignored prefixes, skip it
				boolean ignoredPrefix = false;
				for (String prefix : IGNORED_TABLE_NAME_PREFIXES) {
					if (name.startsWith(prefix)) {
						ignoredPrefix = true;
						break;
					}
				}
				if (ignoredPrefix) {
					continue;
				}

				results.add(name);
			}
		} finally {
			JdbcUtils.closeQuietly(nameResultSet);
		}

		return results;
	}

	public static List<String> getTableNamesFromMetaData(String schema, DatabaseMetaData meta) throws SQLException {
		ResultSet rs = null;
		try {
			// Connect to the db and get a list of all the tables
			rs = meta.getTables(null, schema, null, new String[] { TABLE_META_DATA_TYPE });

			// Setup some storage for the names
			List<String> tableNames = new ArrayList<String>();

			// Iterate over the result set
			while (rs.next()) {

				// Extract the table name from the current row of the result set
				String tableName = rs.getString(TABLE_NAME_INDEX);

				// Add the table name to the list unless it is blank for some reason
				if (!StringUtils.isBlank(tableName)) {
					tableNames.add(tableName);
				}
			}

			// Return the list
			return tableNames;
		} finally {
			JdbcUtils.closeQuietly(rs);
		}
	}

	public static String extractTableComment(String tableName, String schemaName, DatabaseMetaData databaseMetaData) throws SQLException {

		ResultSet resultSet = null;
		String result = null;
		try {
			resultSet = databaseMetaData.getTables(null, schemaName, tableName, new String[] { TABLE_META_DATA_TYPE });
			if (resultSet.next()) {
				result = StringUtils.trimToNull(resultSet.getString(TABLE_COMMENT_INDEX));
			}
		} catch (SQLException e) {
			log.error("Could not extract table comment data for table: " + tableName);
			throw e;
		} finally {
			JdbcUtils.closeQuietly(resultSet);
		}

		return result;
	}

	public static List<Column> extractTableColumns(String tableName, String schemaName, DatabaseMetaData databaseMetaData) throws SQLException {
		// assume that there is only one primary key definition that has a useable name
		Map<String, List<String>> primaryKeyData = extractTablePrimaryKeyColumns(tableName, schemaName, databaseMetaData);

		List<String> primaryKeyColumns = getNamedPrimaryKeyColumns(primaryKeyData);

		List<Column> columns = new ArrayList<Column>();
		ResultSet metaDataResults = null;
		try {
			metaDataResults = databaseMetaData.getColumns(null, schemaName, tableName, null);
			while (metaDataResults.next()) {
				String name = metaDataResults.getString(COLUMN_NAME_INDEX);
				Integer sqlType = new Integer(metaDataResults.getString(COLUMN_TYPE_INDEX));
				Integer size = metaDataResults.getInt(COLUMN_TYPE_SIZE_INDEX);
				Integer typeSizeScale = metaDataResults.getInt(COLUMN_TYPE_SCALE_INDEX);
				String remarks = metaDataResults.getString(COLUMN_REMARKS_INDEX);
				String defaultValue = metaDataResults.getString(COLUMN_DEFAULT_INDEX);
				String nullType = metaDataResults.getString(COLUMN_NULLABLE_INDEX);

				// if a default value is defined
				if (StringUtils.isNotEmpty(defaultValue)) {
					// remove single quotes wrapping encoding string default values
					if (defaultValue.startsWith(SINGLE_QUOTE) && defaultValue.endsWith(SINGLE_QUOTE)) {
						// chop off the leading and trailing characters
						defaultValue = defaultValue.substring(1, defaultValue.length() - 2);
					}
				}

				// determine nullable value
				boolean nullable;
				if (StringUtils.isBlank(nullType)) {
					nullable = DEFAULT_NULLABLE;
					log.warn("Nullable value for {}.{} is UNDEFINED from DatabaseMetaData, defaulting to {}", new Object[] { tableName, name, DEFAULT_NULLABLE });
				} else {
					nullable = nullType.equals(COLUMN_RESULT_SET_NULLABLE_VALUE);
				}

				// determine if this column is a primary key column
				boolean primaryKey = primaryKeyColumns.contains(name);

				// build the type size instance for this column
				TypeSize typeSize = translateExtractedTypeSize(size, typeSizeScale);

				// build the data type for this column from the type information from the result set
				DataType dataType = SQL_TYPE_TO_DATA_TYPE.get(sqlType);

				if (dataType == null) {
					log.warn("A problem occurred defining column {}.{} Could not find a data type to match value from metaData: {}", new Object[] { tableName, name, sqlType });
				}

				Column col = new Column();
				col.setTableName(tableName);
				col.setPrimaryKey(primaryKey);
				col.setName(name);
				col.setDescription(remarks);
				col.setColumnDataType(dataType);
				col.setTypeSize(typeSize);
				col.setNullable(nullable);
				col.setDefaultValue(defaultValue);
				columns.add(col);
			}
		} finally {
			JdbcUtils.closeQuietly(metaDataResults);
		}

		Collections.sort(columns, NamedElementComparator.getInstance());

		return columns;
	}

	public static TypeSize translateExtractedTypeSize(Integer typeSize, Integer typeSizeScale) {
		if (typeSizeScale > 0) {
			return new TypeSize(typeSize, typeSizeScale);
		} else {
			return new TypeSize(typeSize);
		}
	}

	public static Map<String, List<String>> extractTablePrimaryKeyColumns(String tableName, String schemaName, DatabaseMetaData databaseMetaData) throws SQLException {
		ResultSet resultSet = null;
		Map<String, List<String>> results = new HashMap<String, List<String>>();
		try {
			resultSet = databaseMetaData.getPrimaryKeys(null, schemaName, tableName);
			while (resultSet.next()) {
				String columnName = resultSet.getString(PRIMARY_KEY_COLUMN_NAME_INDEX);
				String name = resultSet.getString(PRIMARY_KEY_NAME_INDEX);

				// if the primary key has no name, group any columns for unnamed primary keys together
				if (name == null) {
					name = PRIMARY_KEY_UNNAMED_KEY;
				}

				List<String> pkColumns = results.get(name);

				if (pkColumns == null) {
					pkColumns = new ArrayList<String>();
					results.put(name, pkColumns);
				}

				if (StringUtils.isNotBlank(columnName)) {
					pkColumns.add(columnName);
				}
			}
		} finally {
			JdbcUtils.closeQuietly(resultSet);
		}

		return results;
	}

	/**
	 * Returns the first list of primary key columns found in the primary key data that does not have an "unnamed" primary key name. Used as a convenience method to get the most
	 * relevant primary key information from the full data extracted for primary keys of a table
	 * 
	 * @param primaryKeyData
	 *            a Map assumed to be the result of calling extractPrimaryKeyColumns
	 * @return a list of strings, or an empty list if no list is found within the parameter data
	 */
	public static List<String> getNamedPrimaryKeyColumns(Map<String, List<String>> primaryKeyData) {
		List<String> primaryKeyColumns = new ArrayList<String>();
		for (String pkName : primaryKeyData.keySet()) {
			// use the first primary key definition found where the name is not the "unnamed" primary key
			if (!pkName.equals(PRIMARY_KEY_UNNAMED_KEY)) {
				primaryKeyColumns = primaryKeyData.get(pkName);
			}
		}

		return primaryKeyColumns;
	}

	/**
	 * Returns the first primary key name found in the primary key data that does not have an "unnamed" primary key name. Used as a convenience method to get the most relevant
	 * primary key information from the full data extracted for primary keys of a table
	 * 
	 * @param primaryKeyData
	 *            a Map assumed to be the result of calling extractPrimaryKeyColumns
	 * @return the name of the primary key, or null if the table has no primary key
	 */
	public static String getPrimaryKeyName(Map<String, List<String>> primaryKeyData) {
		for (String pkName : primaryKeyData.keySet()) {
			// use the first primary key definition found where the name is not the "unnamed" primary key
			if (!pkName.equals(PRIMARY_KEY_UNNAMED_KEY)) {
				return pkName;
			}
		}

		return null;
	}

	/**
	 * This method extracts raw index information for a table.
	 * 
	 * ** NOTE ** Unique constraints will be included in these indices, but not the primary key index for the table (if it has one) ** NOTE **
	 * 
	 * @param tableName
	 *            Name of the table
	 * @param schemaName
	 *            Name of the schema
	 * @param databaseMetaData
	 *            Connection to the database metadata
	 * @return All indices for the given parameters
	 * @throws SQLException
	 */
	public static List<Index> extractTableIndices(String tableName, String schemaName, DatabaseMetaData databaseMetaData) throws SQLException {

		String primaryKeyName = getPrimaryKeyName(extractTablePrimaryKeyColumns(tableName, schemaName, databaseMetaData));

		Map<String, Index> indexNames = new HashMap<String, Index>();

		ResultSet resultSet = null;
		try {
			resultSet = databaseMetaData.getIndexInfo(null, schemaName, tableName, false, true);
			while (resultSet.next()) {

				// Extract the name of the index
				String name = resultSet.getString(INDEX_NAME_INDEX);
				boolean unique = !(resultSet.getBoolean(INDEX_NON_UNIQUE_INDEX));
				if (name == null) {
					// if name is null, it is not related to the table in a useable way
					// see javadoc for DatabaseMetaData.getIndexInfo
					continue;
				}

				if (StringUtils.equals(primaryKeyName, name)) {
					// skip any index with the same name as the primary key
					continue;
				}

				String columnName = StringUtils.trimToNull(resultSet.getString(INDEX_COLUMN_NAME_INDEX));

				// if the column name for this entry is null, skip it
				// see javadoc for DatabaseMetaData.getIndexInfo for cases when column name is null
				if (columnName == null) {
					continue;
				}

				Index index = indexNames.get(name);
				// make sure we use the same Index instance if we are adding more information
				// more than one ResultSet row is returned if an index has more than one column
				if (index == null) {
					index = new Index(new ArrayList<String>(), name, unique);
					indexNames.put(name, index);
				}

				// add the retrieved column name to the list of columns for this index
				index.getColumnNames().add(columnName);
			}
		} finally {
			JdbcUtils.closeQuietly(resultSet);
		}

		List<Index> sortedIndices = new ArrayList<Index>(indexNames.values());

		Collections.sort(sortedIndices, NamedElementComparator.getInstance());

		return sortedIndices;
	}

	public static List<ForeignKey> extractForeignKeys(DatabaseMetaData meta, String schema, List<String> tables, PercentCompleteInformer informer) throws SQLException {
		Map<String, ForeignKey> foreignKeys = new HashMap<String, ForeignKey>();

		for (String table : tables) {
			ResultSet rs = null;
			try {
				rs = meta.getImportedKeys(null, schema, table);
				while (rs.next()) {
					addOrUpdateForeignKeys(rs, foreignKeys, table);
				}
				informer.incrementProgress();
			} finally {
				JdbcUtils.closeQuietly(rs);
			}
		}

		List<ForeignKey> sorted = new ArrayList<ForeignKey>();
		sorted.addAll(foreignKeys.values());
		Collections.sort(sorted, NamedElementComparator.getInstance());
		return sorted;
	}

	public static void addOrUpdateForeignKeys(ResultSet rs, Map<String, ForeignKey> foreignKeys, String tableName) throws SQLException {

		String importedTableName = rs.getString(FOREIGN_KEY_IMPORTED_TABLE_NAME_INDEX);
		String importedColumnName = rs.getString(FOREIGN_KEY_IMPORTED_COLUMN_NAME_INDEX);
		String localColumnName = rs.getString(FOREIGN_KEY_LOCAL_COLUMN_NAME_INDEX);
		String name = rs.getString(FOREIGN_KEY_NAME_INDEX);

		String onUpdateRule = rs.getString(FOREIGN_KEY_ON_UPDATE_INDEX);
		String onDeleteRule = rs.getString(FOREIGN_KEY_ON_DELETE_INDEX);

		// if the name of the foreign key is null, skip it
		if (StringUtils.isEmpty(name)) {
			return;
		}

		// check to see if a foreign key with this name has already been started
		ForeignKey fk = foreignKeys.get(name);
		if (fk == null) {
			fk = new ForeignKey(name, tableName, importedTableName);

			// set the update and delete directives when the foreign key is created, since they should be the same
			// for all the resultset entries with the same foreign key name
			fk.setOnUpdate(translateConstraintRule(onUpdateRule));
			fk.setOnDelete(translateConstraintRule(onDeleteRule));

			foreignKeys.put(fk.getName(), fk);
		}

		fk.getLocalColumnNames().add(localColumnName);
		fk.getForeignColumnNames().add(importedColumnName);
	}

	private static ForeignKeyConstraintType translateConstraintRule(String ruleString) {
		if (ruleString == null) {
			return DEFAULT_CONSTRAINT_RULE;
		}

		int ruleVal = Integer.valueOf(ruleString);

		switch (ruleVal) {
		case DatabaseMetaData.importedKeyCascade:
			return ForeignKeyConstraintType.CASCADE;
		case DatabaseMetaData.importedKeySetDefault:
			return ForeignKeyConstraintType.SET_DEFAULT;
		case DatabaseMetaData.importedKeySetNull:
			return ForeignKeyConstraintType.SET_NULL;
		case DatabaseMetaData.importedKeyNoAction:
			return ForeignKeyConstraintType.NO_ACTION;
		case DatabaseMetaData.importedKeyRestrict:
			return ForeignKeyConstraintType.RESTRICT;
		default:
			return null;
		}
	}
}
