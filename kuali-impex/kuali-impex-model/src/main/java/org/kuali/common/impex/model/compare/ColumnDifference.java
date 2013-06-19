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

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;

public class ColumnDifference extends TableDifference {

    Column column1;

    Column column2;

    public ColumnDifference(Schema schema1, Table table1, Column col1, Schema schema2, Table table2, Column col2) {
        super(schema1, table1, schema2, table2);
        this.column1 = col1;
        this.column2 = col2;
    }

    public Column getColumn1() {
        return column1;
    }

    public void setColumn1(Column column1) {
        this.column1 = column1;
    }

    public Column getColumn2() {
        return column2;
    }

    public void setColumn2(Column column2) {
        this.column2 = column2;
    }
}
