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

package org.kuali.common.impex.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.NamedElement;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.model.compare.ColumnDifference;
import org.kuali.common.impex.model.compare.ForeignKeyDifference;
import org.kuali.common.impex.model.compare.ForeignKeyDifferenceType;
import org.kuali.common.impex.model.compare.IndexDifference;
import org.kuali.common.impex.model.compare.MissingColumn;
import org.kuali.common.impex.model.compare.MissingForeignKey;
import org.kuali.common.impex.model.compare.MissingIndex;
import org.kuali.common.impex.model.compare.MissingSequence;
import org.kuali.common.impex.model.compare.MissingTable;
import org.kuali.common.impex.model.compare.MissingUniqueConstraint;
import org.kuali.common.impex.model.compare.MissingView;
import org.kuali.common.impex.model.compare.SequenceDifference;
import org.kuali.common.impex.model.compare.SequenceDifferenceType;
import org.kuali.common.impex.model.compare.TableDifference;
import org.kuali.common.impex.model.compare.TableDifferenceType;
import org.kuali.common.impex.model.compare.UniqueConstraintDifference;
import org.kuali.common.impex.model.compare.ViewDifference;
import org.kuali.common.impex.model.compare.ViewDifferenceType;

public class CompareUtils {

    public static final int EXPECTED_MISSING_ELEMENTS_SET_COUNT = 2;

    public static Collection<ColumnDifference> compareColumns(Schema schema1, Table table1, Column col1, Schema schema2, Table table2, Column col2) {
        Collection<ColumnDifference> results = new ArrayList<ColumnDifference>();

        // Check for data type difference
        if (col1.getColumnDataType() != col2.getColumnDataType()) {
            ColumnDifference dataTypeDiff = new ColumnDifference(schema1, table1, col1, schema2, table2, col2);
            dataTypeDiff.setType(TableDifferenceType.COLUMN_DATA_TYPE);
            results.add(dataTypeDiff);
        }

        // check for primary key difference
        if (col1.isPrimaryKey() != col2.isPrimaryKey()) {
            ColumnDifference pkDiff = new ColumnDifference(schema1, table1, col1, schema2, table2, col2);
            pkDiff.setType(TableDifferenceType.COLUMN_PRIMARY_KEY);
            results.add(pkDiff);
        }

        // check for nullable difference
        if (col1.isNullable() != col2.isNullable()) {
            ColumnDifference nullableDiff = new ColumnDifference(schema1, table1, col1, schema2, table2, col2);
            nullableDiff.setType(TableDifferenceType.COLUMN_NULLABLE);
            results.add(nullableDiff);
        }

        // check for type size difference
        if(!objectsEqual(col1.getTypeSize(), col2.getTypeSize())) {
            ColumnDifference sizeDiff = new ColumnDifference(schema1, table1, col1, schema2, table2, col2);
            sizeDiff.setType(TableDifferenceType.COLUMN_DATA_TYPE_SIZE);
            results.add(sizeDiff);
        }

        // check for description difference
        if(!StringUtils.equals(col1.getDescription(), col2.getDescription())) {
            ColumnDifference descDiff = new ColumnDifference(schema1, table1, col1, schema2, table2, col2);
            descDiff.setType(TableDifferenceType.COLUMN_DESCRIPTION);
            results.add(descDiff);
        }

        // check for default value difference
        if (!StringUtils.equals(col1.getDefaultValue(), col2.getDefaultValue())) {
            ColumnDifference defaultDiff = new ColumnDifference(schema1, table1, col1, schema2, table2, col2);
            defaultDiff.setType(TableDifferenceType.COLUMN_DEFAULT_VALUE);
        }

        return results;
    }


    public static <T extends NamedElement> MissingElements<T> determineMissingElements(List<List<T>> sets) {

        MissingElements<T> results = new MissingElements<T>();

        if (sets.size() != EXPECTED_MISSING_ELEMENTS_SET_COUNT) {
            throw new IllegalArgumentException("Map of sets is expected to have exactly " + EXPECTED_MISSING_ELEMENTS_SET_COUNT + " entries");
        }

        List<T> set1Elements = sets.get(0);
        List<T> set2Elements = sets.get(1);

        // Create a mapping of names to elements for set two
        Map<String, T> set2NameMap = ModelUtils.buildNameMap(set2Elements);

        // Build a list of names in set 2 that are not in set 1
        // This list is discovered by populating it first with the names of
        // all elements in set 2, then removing each table name found in both sets
        Collection<String> namesInSet2Only = set2NameMap.keySet();

        // Build comparison objects from the perspective of set 1
        for (T n : set1Elements) {
            // if this element is in set 1 and set 2
            if (set2NameMap.containsKey(n.getName())) {
                // add this element's name to the list that is in both sets
                results.getBoth().add(new MatchingElement<T>(n, set2NameMap.get(n.getName())));

                // remove the name from the list of names in set 2 only
                namesInSet2Only.remove(n.getName());
            }
            // if this element is only in set 1, add it to the set1Only list
            else {
                results.getSet1Only().add(n);
            }
        }

        // add any elements in set2 that were not found in set1
        for (String name : namesInSet2Only) {
            results.getSet2Only().add(set2NameMap.get(name));
        }

        return results;
    }


    public static Collection<UniqueConstraintDifference> compareUniqueConstraints(Schema schema1, Table table1, UniqueConstraint uniqueConstraint1, Schema schema2, Table table2, UniqueConstraint uniqueConstraint2) {
        Collection<UniqueConstraintDifference> results = new ArrayList<UniqueConstraintDifference>();

        if (!uniqueConstraint1.getColumnNames().equals(uniqueConstraint2.getColumnNames())) {
            UniqueConstraintDifference diff = new UniqueConstraintDifference(schema1, table1, uniqueConstraint1, schema2, table2, uniqueConstraint2);
            diff.setType(TableDifferenceType.UNIQUE_CONSTRAINT_COLUMNS);
            results.add(diff);
        }

        return results;
    }

    public static Collection<IndexDifference> compareIndices(Schema schema1, Table table1, Index index1, Schema schema2, Table table2, Index index2) {
        Collection<IndexDifference> results = new ArrayList<IndexDifference>();

        if (!index1.getColumnNames().equals(index2.getColumnNames())) {
            IndexDifference diff = new IndexDifference(schema1, table1, index1, schema2, table2, index2);
            diff.setType(TableDifferenceType.INDEX_COLUMNS);
            results.add(diff);
        }

        if (index1.isUnique() != index2.isUnique()) {
            IndexDifference diff = new IndexDifference(schema1, table1, index1, schema2, table2, index2);
            diff.setType(TableDifferenceType.INDEX_UNIQUE);
            results.add(diff);
        }

        return results;
    }

    public static Collection<ForeignKeyDifference> compareForeignKeys(Schema schema1, ForeignKey foreignKey1, Schema schema2, ForeignKey foreignKey2) {

        Collection<ForeignKeyDifference> results = new ArrayList<ForeignKeyDifference>();

        if (!StringUtils.equals(foreignKey1.getLocalTableName(), foreignKey2.getLocalTableName())) {
            ForeignKeyDifference diff = new ForeignKeyDifference(schema1, foreignKey1, schema2, foreignKey2);
            diff.setType(ForeignKeyDifferenceType.LOCAL_TABLE);
            results.add(diff);
        }

        if (!StringUtils.equals(foreignKey1.getForeignTableName(), foreignKey2.getForeignTableName())) {
            ForeignKeyDifference diff = new ForeignKeyDifference(schema1, foreignKey1, schema2, foreignKey2);
            diff.setType(ForeignKeyDifferenceType.FOREIGN_TABLE);
            results.add(diff);
        }

        if (!objectsEqual(foreignKey1.getLocalColumnNames(), foreignKey2.getLocalColumnNames())) {
            ForeignKeyDifference diff = new ForeignKeyDifference(schema1, foreignKey1, schema2, foreignKey2);
            diff.setType(ForeignKeyDifferenceType.LOCAL_COLUMNS);
            results.add(diff);
        }

        if (!objectsEqual(foreignKey1.getForeignColumnNames(), foreignKey2.getForeignColumnNames())) {
            ForeignKeyDifference diff = new ForeignKeyDifference(schema1, foreignKey1, schema2, foreignKey2);
            diff.setType(ForeignKeyDifferenceType.FOREIGN_COLUMNS);
            results.add(diff);
        }

        if (!objectsEqual(foreignKey1.getOnDelete(), foreignKey2.getOnDelete())) {
            ForeignKeyDifference diff = new ForeignKeyDifference(schema1, foreignKey1, schema2, foreignKey2);
            diff.setType(ForeignKeyDifferenceType.ON_DELETE);
            results.add(diff);
        }

        if (!objectsEqual(foreignKey1.getOnUpdate(), foreignKey2.getOnUpdate())) {
            ForeignKeyDifference diff = new ForeignKeyDifference(schema1, foreignKey1, schema2, foreignKey2);
            diff.setType(ForeignKeyDifferenceType.ON_UPDATE);
            results.add(diff);
        }

        return results;
    }

    public static Collection<ViewDifference> compareViews(Schema schema1, View view1, Schema schema2, View view2) {
        Collection<ViewDifference> results = new ArrayList<ViewDifference>();

        if(!StringUtils.equals(view1.getQueryString(), view2.getQueryString())) {
            ViewDifference diff = new ViewDifference(schema1, view1, schema2, view2);
            diff.setType(ViewDifferenceType.QUERY_STRING);
            results.add(diff);
        }

        return results;
    }

    public static Collection<SequenceDifference> compareSequences(Schema schema1, Sequence seq1, Schema schema2, Sequence seq2) {
        Collection<SequenceDifference> results = new ArrayList<SequenceDifference>();

        if (!StringUtils.equals(seq1.getStartValue(), seq2.getStartValue())) {
            SequenceDifference diff = new SequenceDifference(schema1, seq1, schema2, seq2);
            diff.setType(SequenceDifferenceType.START_VALUE);
            results.add(diff);
        }

        return results;
    }

    private static boolean objectsEqual(Object o1, Object o2) {
        if(o1 == null) {
            return o2 == null;
        }

        return o1.equals(o2);
    }

    public static String tableDifferenceToString(TableDifference t) {
        switch (t.getType()) {
            case MISSING_TABLE:{
                MissingTable missing = (MissingTable) t;
                String foundToken = missing.getSourceSchema().getName() + DifferenceUtils.DOT + missing.getTable().getName();
                String notFoundToken = missing.getMissingSchema().getName();
                return DifferenceUtils.buildMissingElementToken(t.getType().getLabel(), foundToken, notFoundToken);
            }
            case MISSING_COLUMN: {
                MissingColumn missing = (MissingColumn) t;
                String foundToken = missing.getSourceSchema().getName() + DifferenceUtils.DOT + missing.getSourceTable().getName() + DifferenceUtils.DOT + missing.getColumn().getName();
                String notFoundToken = missing.getMissingSchema().getName() + DifferenceUtils.DOT + missing.getMissingTable().getName();
                return DifferenceUtils.buildMissingElementToken(t.getType().getLabel(), foundToken, notFoundToken);
            }
            case MISSING_INDEX: {
                MissingIndex missing = (MissingIndex) t;
                String foundToken = missing.getSourceSchema().getName() + DifferenceUtils.DOT + missing.getSourceTable().getName() + DifferenceUtils.DOT + missing.getIndex().getName();
                String notFoundToken = missing.getMissingSchema().getName() + DifferenceUtils.DOT + missing.getMissingTable().getName();
                return DifferenceUtils.buildMissingElementToken(t.getType().getLabel(), foundToken, notFoundToken);
            }
            case MISSING_UNIQUE_CONSTRAINT: {
                MissingUniqueConstraint missing = (MissingUniqueConstraint) t;
                String foundToken = missing.getSourceSchema().getName() + DifferenceUtils.DOT + missing.getSourceTable().getName() + DifferenceUtils.DOT + missing.getUniqueConstraint().getName();
                String notFoundToken = missing.getMissingSchema().getName() + DifferenceUtils.DOT + missing.getMissingTable().getName();
                return DifferenceUtils.buildMissingElementToken(t.getType().getLabel(), foundToken, notFoundToken);
            }
            default: {
                return DifferenceUtils.buildDifferenceToken(t.getType().getLabel(), t);
            }
        }
    }

    public static String foreignKeyDifferenceToString(ForeignKeyDifference f) {
        switch (f.getType()) {
            case MISSING_FOREIGN_KEY: {
                MissingForeignKey missing = (MissingForeignKey) f;
                String foundToken = missing.getSourceSchema().getName() + DifferenceUtils.DOT + missing.getForeignKey().getName();
                String notFoundToken = missing.getMissingSchema().getName();
                return DifferenceUtils.buildMissingElementToken(f.getType().getLabel(), foundToken, notFoundToken);
            }
            default: {
                return DifferenceUtils.buildDifferenceToken(f.getType().getLabel(), f);
            }
        }
    }

    public static String viewDifferenceToString(ViewDifference v) {
        switch (v.getType()) {
            case MISSING_VIEW: {
                MissingView missing = (MissingView) v;
                String foundToken = missing.getSourceSchema().getName() + DifferenceUtils.DOT + missing.getView().getName();
                String notFoundToken = missing.getMissingSchema().getName();
                return DifferenceUtils.buildMissingElementToken(v.getType().getLabel(), foundToken, notFoundToken);
            }
            default: {
                return DifferenceUtils.buildDifferenceToken(v.getType().getLabel(), v);
            }
        }
    }

    public static String sequenceDifferenceToString(SequenceDifference s) {
        switch (s.getType()) {
            case MISSING_SEQUENCE: {
                MissingSequence missing = (MissingSequence) s;
                String foundToken = missing.getSourceSchema().getName() + DifferenceUtils.DOT + missing.getSequence().getName();
                String notFoundToken = missing.getMissingSchema().getName();
                return DifferenceUtils.buildMissingElementToken(s.getType().getLabel(), foundToken, notFoundToken);
            }
            default: {
                return DifferenceUtils.buildDifferenceToken(s.getType().getLabel(), s);
            }
        }
    }

}
