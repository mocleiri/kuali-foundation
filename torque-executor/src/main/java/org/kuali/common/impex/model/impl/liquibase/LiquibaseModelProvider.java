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

package org.kuali.common.impex.model.impl.liquibase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.Database;
import liquibase.snapshot.DatabaseSnapshot;
import liquibase.snapshot.JdbcDatabaseSnapshot;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.ForeignKeyConstraintType;
import org.kuali.common.impex.model.ModelProvider;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.service.NamedElementComparator;

public class LiquibaseModelProvider implements ModelProvider {

    /**
     * Constants for result set keys from DatabaseMetaData.getImportedKeys
     */
    protected static final String METADATA_FK_NAME = "FK_NAME";
    protected static final String METADATA_SEQUENCE_NUMBER = "KEY_SEQ";
    protected static final String METADATA_FK_COLUMN_NAME = "FKCOLUMN_NAME";
    protected static final String METADATA_PK_COLUMN_NAME = "PKCOLUMN_NAME";

    protected static final String SINGLE_QUOTE = "'";

    protected static final String SYSGUID_KEYWORD_DEFAULT = "SYS_GUID()";
    protected static final String SYSDATE_KEYWORD_DEFAULT = "SYSDATE";
    protected static final String USERSESSION_KEYWORD_DEFAULT = "USERENV(\'SESSIONID\')";

    protected static final String[] RESERVED_DEFAULT_KEYWORDS = {SYSGUID_KEYWORD_DEFAULT, SYSDATE_KEYWORD_DEFAULT, USERSESSION_KEYWORD_DEFAULT};

    protected List<Table> tables;

    protected List<View> views;

    protected List<Sequence> sequences;

    protected List<ForeignKey> foreignKeys;

    /**
     * Maps table names to outbound (i.e. having the same local table name as the key in the map) foreign keys
     */
    protected Map<String, List<ForeignKey>> tableNameToForeignKeys;

    public LiquibaseModelProvider(DatabaseSnapshot snapshot) throws SQLException {
        tables = buildTables(snapshot);

        views = buildViews(snapshot);

        sequences = buildSequences(snapshot);

        foreignKeys = buildForeignKeys(snapshot);
    }

    protected List<Table> buildTables(DatabaseSnapshot snapshot) {
        Set<liquibase.structure.core.Table> sourceTables = snapshot.get(liquibase.structure.core.Table.class);

        List<Table> results = new ArrayList<Table>(sourceTables.size());
        tableNameToForeignKeys = new HashMap<String, List<ForeignKey>>(results.size());

        for (liquibase.structure.core.Table sourceTable : sourceTables) {
            Table t = new Table(sourceTable.getName());
            t.setColumns(new ArrayList<Column>(sourceTable.getColumns().size()));

            List<String> primaryKeyColumnNames;
            if(sourceTable.getPrimaryKey() == null) {
                primaryKeyColumnNames = Collections.emptyList();
            }
            else {
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

            // build an empty list entry for each table
            tableNameToForeignKeys.put(t.getName(), new ArrayList<ForeignKey>());

            results.add(t);
        }

        Collections.sort(results, NamedElementComparator.getInstance());

        return results;
    }

    protected Column buildColumn(liquibase.structure.core.Column sourceColumn, boolean primaryKey, Table modelTable) {
        Column col = new Column(sourceColumn.getName(), DataTypeUtils.getColumnDataType(sourceColumn), modelTable);

        if(sourceColumn.getDefaultValue() != null) {
            String defaultValue = sourceColumn.getDefaultValue().toString().trim();

            // if the column is a string data type, check that it may need single quotes added
            if(col.getDataType() == DataType.STRING || col.getDataType() == DataType.CLOB) {
                // ignore adding single quotes if the default value contains a reseved keyword
                if(notReservedDefaultValue(defaultValue)) {
                    if(!defaultValue.startsWith(SINGLE_QUOTE)) {
                        defaultValue = SINGLE_QUOTE + defaultValue + SINGLE_QUOTE;
                    }
                }
            }

            col.setDefaultValue(defaultValue);
        }
        col.setDescription(sourceColumn.getRemarks());
        col.setNullable(sourceColumn.isNullable());
        col.setPrimaryKey(primaryKey);

        if(sourceColumn.getType().getColumnSize() != null) {
            int size = sourceColumn.getType().getColumnSize();

            // if there are no decimal digits set, create a TypeSize with just a size
            TypeSize ts;
            if(sourceColumn.getType().getDecimalDigits() == null) {
                ts = new TypeSize(size);
            }
            else {
                int scale = sourceColumn.getType().getDecimalDigits();
                ts = new TypeSize(size, scale);
            }

            col.setTypeSize(ts);
        }

        return col;
    }

    /**
     * Check that the given column default value is not a reseved keyword
     *
     * @param defaultValue the potential column default value
     * @return true if the given value does not contain a reserved keyword,
     *      false otherwise
     */
    protected boolean notReservedDefaultValue(String defaultValue) {

        for(String reserved : Arrays.asList(RESERVED_DEFAULT_KEYWORDS)) {
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
            View v = new View(sourceView.getName(), sourceView.getDefinition());

            results.add(v);
        }

        Collections.sort(results, NamedElementComparator.getInstance());

        return results;
    }

    protected List<Sequence> buildSequences(DatabaseSnapshot snapshot) {
        Set<liquibase.structure.core.Sequence> sourceSequences = snapshot.get(liquibase.structure.core.Sequence.class);

        List<Sequence> results = new ArrayList<Sequence>(sourceSequences.size());

        // TODO KSENROLL-6764 Current liquibase structure does not retrieve current value for sequences
        for (liquibase.structure.core.Sequence sourceSequence : sourceSequences) {
            results.add(new Sequence(sourceSequence.getName(), "1"));
        }

        Collections.sort(results, NamedElementComparator.getInstance());

        return results;
    }

    protected List<ForeignKey> buildForeignKeys(DatabaseSnapshot snapshot) throws SQLException {
        Set<liquibase.structure.core.ForeignKey> sourceFks = snapshot.get(liquibase.structure.core.ForeignKey.class);

        List<ForeignKey> results = new ArrayList<ForeignKey>(sourceFks.size());

        for (liquibase.structure.core.ForeignKey sourceFk : sourceFks) {

            // In the liquibase model, Foreign keys are initialized with the "PrimaryKeyTable" as the table that is being pointed TO (i.e. the outside table)
            // and the "ForeignKeyTable" as the table that is pointed from (i.e. the source table)

            String localTableName = sourceFk.getForeignKeyTable().getName();
            String foreignTableName = sourceFk.getPrimaryKeyTable().getName();

            ForeignKey fk = new ForeignKey(sourceFk.getName(), localTableName, foreignTableName);

            setColumnNames(fk, sourceFk, snapshot);

            fk.setOnUpdate(translateForeignKeyConstraint(sourceFk.getUpdateRule()));
            fk.setOnDelete(translateForeignKeyConstraint(sourceFk.getDeleteRule()));

            tableNameToForeignKeys.get(fk.getLocalTableName()).add(fk);

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

        List<JdbcDatabaseSnapshot.CachedRow> importedKeyMetadataResultSet = null;
        liquibase.structure.core.Table fkTable = liquibaseFk.getForeignKeyTable();
        String searchCatalog = ((AbstractJdbcDatabase) database).getJdbcCatalogName(fkTable.getSchema());
        String searchSchema = ((AbstractJdbcDatabase) database).getJdbcSchemaName(fkTable.getSchema());
        String searchTableName = database.correctObjectName(fkTable.getName(), liquibase.structure.core.Table.class);

        importedKeyMetadataResultSet = ((JdbcDatabaseSnapshot) snapshot).getMetaData().getImportedKeys(searchCatalog, searchSchema, searchTableName);

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

    @Override
    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    @Override
    public List<Sequence> getSequences() {
        return sequences;
    }

    @Override
    public Map<String, List<ForeignKey>> getTableNameToForeignKeys() {
        return tableNameToForeignKeys;
    }

    @Override
    public List<Table> getTables() {
        return tables;
    }

    @Override
    public List<View> getViews() {
        return views;
    }
}
