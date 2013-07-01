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
import java.util.List;

public class SchemaCompareResult {

    List<TableDifference> tableDifferences;
    List<ForeignKeyDifference> foreignKeyDifferences;
    List<ViewDifference> viewDifferences;
    List<SequenceDifference> sequenceDifferences;

    public SchemaCompareResult() {
        tableDifferences = new ArrayList<TableDifference>();
        foreignKeyDifferences = new ArrayList<ForeignKeyDifference>();
        viewDifferences = new ArrayList<ViewDifference>();
        sequenceDifferences = new ArrayList<SequenceDifference>();
    }

    public List<TableDifference> getTableDifferences() {
        return tableDifferences;
    }

    public void setTableDifferences(List<TableDifference> tableDifferences) {
        this.tableDifferences = tableDifferences;
    }

    public List<ForeignKeyDifference> getForeignKeyDifferences() {
        return foreignKeyDifferences;
    }

    public void setForeignKeyDifferences(List<ForeignKeyDifference> foreignKeyDifferences) {
        this.foreignKeyDifferences = foreignKeyDifferences;
    }

    public List<SequenceDifference> getSequenceDifferences() {
        return sequenceDifferences;
    }

    public void setSequenceDifferences(List<SequenceDifference> sequenceDifferences) {
        this.sequenceDifferences = sequenceDifferences;
    }

    public List<ViewDifference> getViewDifferences() {
        return viewDifferences;
    }

    public void setViewDifferences(List<ViewDifference> viewDifferences) {
        this.viewDifferences = viewDifferences;
    }

    public boolean hasDifferences() {
        return !tableDifferences.isEmpty() && !foreignKeyDifferences.isEmpty() && !viewDifferences.isEmpty() && !sequenceDifferences.isEmpty();
    }
}
