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

package org.kuali.common.impex.model.compare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Sequence;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.model.View;
import org.kuali.common.impex.model.util.CompareUtils;
import org.kuali.common.impex.model.util.MatchingElement;
import org.kuali.common.impex.model.util.MissingElements;

public class SchemaCompare {

    public Schema schema1;

    public Schema schema2;

    public SchemaCompare(Schema s1, Schema s2) {
        schema1 = s1;
        schema2 = s2;
    }

    public SchemaCompareResult compare() {
        SchemaCompareResult result = new SchemaCompareResult();

        result.getTableDifferences().addAll(compareTables());
        result.getForeignKeyDifferences().addAll(compareForeignKeys());
        result.getViewDifferences().addAll(compareViews());
        result.getSequenceDifferences().addAll(compareSequences());

        return result;
    }

    protected List<TableDifference> compareTables() {

        List<TableDifference> results = new ArrayList<TableDifference>();

        List<List<Table>> tableSets = new ArrayList<List<Table>>(CompareUtils.EXPECTED_MISSING_ELEMENTS_SET_COUNT);

        tableSets.add(schema1.getTables());
        tableSets.add(schema2.getTables());

        MissingElements<Table> missingTables = CompareUtils.determineMissingElements(tableSets);

        // add a missing table entry for each element found missing
        for (Table t : missingTables.getSet1Only()) {
            results.add(new MissingTable(schema1, schema2, t));
        }

        for (Table t : missingTables.getSet2Only()) {
            results.add(new MissingTable(schema2, schema1, t));
        }

        // build a map of table names from schema 1 and schema 2
        for (MatchingElement<Table> match : missingTables.getBoth()) {
            results.addAll(compareTable(match.getSet1Element(), schema1, match.getSet2Element(), schema2));
        }

        return results;
    }

    protected Collection<? extends TableDifference> compareTable(Table table1, Schema schema1, Table table2, Schema schema2) {

        List<TableDifference> results = new ArrayList<TableDifference>();

        results.addAll(compareColumns(table1, schema1, table2, schema2));
        results.addAll(compareUniques(table1, schema1, table2, schema2));
        results.addAll(compareIndices(table1, schema1, table2, schema2));

        if(!StringUtils.equals(table1.getDescription(), table2.getDescription())) {
            TableDifference diff = new TableDifference(schema1, table1, schema2, table2);
            diff.setType(TableDifferenceType.TABLE_DESCRIPTION);
            results.add(diff);
        }

        return results;
        
    }

    protected Collection<ColumnDifference> compareColumns(Table table1, Schema schema1, Table table2, Schema schema2) {
        List<ColumnDifference> results = new ArrayList<ColumnDifference>();

        List<List<Column>> columnSets = new ArrayList<List<Column>>(CompareUtils.EXPECTED_MISSING_ELEMENTS_SET_COUNT);

        columnSets.add(table1.getColumns());
        columnSets.add(table2.getColumns());

        MissingElements<Column> missingColumns = CompareUtils.determineMissingElements(columnSets);

        // add a missing column entry for each element found missing
        for (Column c : missingColumns.getSet1Only()) {
            results.add(new MissingColumn(schema1, table1, schema2, table2, c));
        }

        for (Column c : missingColumns.getSet2Only()) {
            results.add(new MissingColumn(schema2, table2, schema1, table1, c));
        }

        for (MatchingElement<Column> match : missingColumns.getBoth()) {
            results.addAll(CompareUtils.compareColumns(schema1, table1, match.getSet1Element(), schema2, table2, match.getSet2Element()));
        }

        return results;
    }

    protected Collection<UniqueConstraintDifference> compareUniques(Table table1, Schema schema1, Table table2, Schema schema2) {

        Collection<UniqueConstraintDifference> results = new ArrayList<UniqueConstraintDifference>();

        List<List<UniqueConstraint>> uniqueSets = new ArrayList<List<UniqueConstraint>>(CompareUtils.EXPECTED_MISSING_ELEMENTS_SET_COUNT);

        uniqueSets.add(table1.getUniqueConstraints());
        uniqueSets.add(table2.getUniqueConstraints());

        MissingElements<UniqueConstraint> missingUniques = CompareUtils.determineMissingElements(uniqueSets);

        // add a missing unique constraint entry for each element found missing
        for (UniqueConstraint u : missingUniques.getSet1Only()) {
            results.add(new MissingUniqueConstraint(schema1, table1, schema2, table2, u));
        }

        for (UniqueConstraint u : missingUniques.getSet2Only()) {
            results.add(new MissingUniqueConstraint(schema2, table2, schema1, table1, u));
        }

        for (MatchingElement<UniqueConstraint> match : missingUniques.getBoth()) {
            results.addAll(CompareUtils.compareUniqueConstraints(schema1, table1, match.getSet1Element(), schema2, table2, match.getSet2Element()));
        }

        return results;
    }

    protected Collection<IndexDifference> compareIndices(Table table1, Schema schema1, Table table2, Schema schema2) {

        Collection<IndexDifference> results = new ArrayList<IndexDifference>();

        List<List<Index>> uniqueSets = new ArrayList<List<Index>>(CompareUtils.EXPECTED_MISSING_ELEMENTS_SET_COUNT);

        uniqueSets.add(table1.getIndices());
        uniqueSets.add(table2.getIndices());

        MissingElements<Index> missingIndices = CompareUtils.determineMissingElements(uniqueSets);

        // add a missing index entry for each element found missing
        for (Index index : missingIndices.getSet1Only()) {
            results.add(new MissingIndex(schema1, table1, schema2, table2, index));
        }

        for (Index index : missingIndices.getSet2Only()) {
            results.add(new MissingIndex(schema2, table2, schema1, table1, index));
        }

        for (MatchingElement<Index> match : missingIndices.getBoth()) {
            results.addAll(CompareUtils.compareIndices(schema1, table1, match.getSet1Element(), schema2, table2, match.getSet2Element()));
        }

        return results;
        
    }

    protected Collection<ForeignKeyDifference> compareForeignKeys() {

        List<ForeignKeyDifference> results = new ArrayList<ForeignKeyDifference>();

        List<List<ForeignKey>> foreignKeySets = new ArrayList<List<ForeignKey>>(CompareUtils.EXPECTED_MISSING_ELEMENTS_SET_COUNT);

        foreignKeySets.add(schema1.getForeignKeys());
        foreignKeySets.add(schema2.getForeignKeys());

        MissingElements<ForeignKey> missingForeignKeys = CompareUtils.determineMissingElements(foreignKeySets);

        // add a missing foreign key entry for each element found missing
        for (ForeignKey fk : missingForeignKeys.getSet1Only()) {
            results.add(new MissingForeignKey(schema1, schema2, fk));
        }

        for (ForeignKey t : missingForeignKeys.getSet2Only()) {
            results.add(new MissingForeignKey(schema2, schema1, t));
        }

        for (MatchingElement<ForeignKey> match : missingForeignKeys.getBoth()) {
            results.addAll(CompareUtils.compareForeignKeys(schema1, match.getSet1Element(), schema2, match.getSet2Element()));
        }

        return results;
    }

    protected Collection<ViewDifference> compareViews() {
        Collection<ViewDifference> results = new ArrayList<ViewDifference>();

        List<List<View>> viewSets = new ArrayList<List<View>>(CompareUtils.EXPECTED_MISSING_ELEMENTS_SET_COUNT);

        viewSets.add(schema1.getViews());
        viewSets.add(schema2.getViews());

        MissingElements<View> missingViews = CompareUtils.determineMissingElements(viewSets);

        for (View v : missingViews.getSet1Only()) {
            results.add(new MissingView(schema1, schema2, v));
        }

        for (View v : missingViews.getSet2Only()) {
            results.add(new MissingView(schema2, schema1, v));
        }

        for (MatchingElement<View> match : missingViews.getBoth()) {
            results.addAll(CompareUtils.compareViews(schema1, match.getSet1Element(), schema2, match.getSet2Element()));
        }
        
        return results;
    }

    protected Collection<SequenceDifference> compareSequences() {
        Collection<SequenceDifference> results = new ArrayList<SequenceDifference>();

        List<List<Sequence>> sequenceSets = new ArrayList<List<Sequence>>(CompareUtils.EXPECTED_MISSING_ELEMENTS_SET_COUNT);

        sequenceSets.add(schema1.getSequences());
        sequenceSets.add(schema2.getSequences());

        MissingElements<Sequence> missingSequences = CompareUtils.determineMissingElements(sequenceSets);

        for (Sequence s : missingSequences.getSet1Only()) {
            results.add(new MissingSequence(schema1, schema2, s));
        }

        for (Sequence s : missingSequences.getSet2Only()) {
            results.add(new MissingSequence(schema2, schema1, s));
        }

        for (MatchingElement<Sequence> match : missingSequences.getBoth()) {
            results.addAll(CompareUtils.compareSequences(schema1, match.getSet1Element(), schema2, match.getSet2Element()));
        }

        return results;
    }

}
