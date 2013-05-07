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
import java.util.List;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Table;

/**
 * Implementation of the impex Table model that wraps a Liquibase Table
 */
public class LiquibaseTableImpl implements Table {


    private final liquibase.structure.core.Table sourceTable;

    private final List<Column> columns;

    public LiquibaseTableImpl(liquibase.structure.core.Table sourceTable) {
        this.sourceTable = sourceTable;
        columns = new ArrayList<Column>(sourceTable.getColumns().size());
        List<String> primaryKeyColumnNames = sourceTable.getPrimaryKey().getColumnNamesAsList();

        boolean primaryKey;
        for(liquibase.structure.core.Column sourceColumn : sourceTable.getColumns()) {
            primaryKey = primaryKeyColumnNames.contains(sourceColumn.getName());
            LiquibaseColumnImpl newCol = new LiquibaseColumnImpl(sourceColumn, primaryKey);
            columns.add(newCol);
        }
    }

    @Override
    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public String getName() {
        return sourceTable.getName();
    }
}
