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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import liquibase.snapshot.DatabaseSnapshot;
import liquibase.structure.core.ForeignKeyConstraintType;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.ModelProvider;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.service.NamedElementComparator;
import org.kuali.common.util.CollectionUtils;

public class LiquibaseModelProvider implements ModelProvider {

    protected List<Table> tables;

    protected List<View> views;

    protected List<Sequence> sequences;

    protected List<ForeignKey> foreignKeys;

    /**
     * Maps table names to outbound (i.e. having the same local table name as the key in the map) foreign keys
     */
    protected Map<String, List<ForeignKey>> tableNameToForeignKeys;

    public LiquibaseModelProvider(DatabaseSnapshot snapshot) {
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

            List<String> primaryKeyColumnNames = sourceTable.getPrimaryKey().getColumnNamesAsList();
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
        }

        Collections.sort(results, NamedElementComparator.getInstance());

        return results;
    }

    protected Column buildColumn(liquibase.structure.core.Column sourceColumn, boolean primaryKey, Table modelTable) {
        Column col = new Column(sourceColumn.getName(), DataTypeUtils.getColumnDataType(sourceColumn), modelTable);

        col.setDefaultValue(sourceColumn.getDefaultValue().toString());
        col.setDescription(sourceColumn.getRemarks());
        col.setNullable(sourceColumn.isNullable());
        col.setPrimaryKey(primaryKey);

        int size = sourceColumn.getType().getColumnSize();
        int scale = sourceColumn.getType().getDecimalDigits();

        TypeSize ts = new TypeSize(size, scale);
        col.setTypeSize(ts);

        return col;
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

    protected List<ForeignKey> buildForeignKeys(DatabaseSnapshot snapshot) {
        Set<liquibase.structure.core.ForeignKey> sourceFks = snapshot.get(liquibase.structure.core.ForeignKey.class);

        List<ForeignKey> results = new ArrayList<ForeignKey>(sourceFks.size());

        for (liquibase.structure.core.ForeignKey sourceFk : sourceFks) {

            ForeignKey fk = new ForeignKey(sourceFk.getName(), sourceFk.getPrimaryKeyTable().getName(), sourceFk.getForeignKeyTable().getName());

            fk.getLocalColumnNames().addAll(CollectionUtils.getTrimmedListFromCSV(sourceFk.getPrimaryKeyColumns()));
            fk.getForeignColumnNames().addAll(CollectionUtils.getTrimmedListFromCSV(sourceFk.getForeignKeyColumns()));

            fk.setOnUpdate(translateForeignKeyConstraint(sourceFk.getUpdateRule()));
            fk.setOnDelete(translateForeignKeyConstraint(sourceFk.getDeleteRule()));

            tableNameToForeignKeys.get(fk.getLocalTableName()).add(fk);
        }

        Collections.sort(results, NamedElementComparator.getInstance());

        return results;
    }

    public String translateForeignKeyConstraint(ForeignKeyConstraintType rule) {
        if (rule == null) {
            return null;
        } else {
            switch (rule) {
                case importedKeyCascade:
                    return "CASCADE";
                case importedKeySetNull:
                    return "SET NULL";
                case importedKeySetDefault:
                    return "SET DEFAULT";
                case importedKeyRestrict:
                    return "RESTRICT";
                case importedKeyNoAction:
                    return "NO ACTION";
                default:
                    throw new IllegalArgumentException("Unknown ForeignKeyConstraintType value: " + rule.toString());
            }
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
