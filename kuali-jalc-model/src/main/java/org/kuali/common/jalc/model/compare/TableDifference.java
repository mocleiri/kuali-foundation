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

package org.kuali.common.jalc.model.compare;

import org.kuali.common.jalc.model.Schema;
import org.kuali.common.jalc.model.Table;

public class TableDifference {

    protected Schema schema1;
    protected Schema schema2;

    protected Table table1;
    protected Table table2;
    protected TableDifferenceType type;

    public TableDifference(Schema schema1, Table table1, Schema schema2, Table table2) {
        this.schema1 = schema1;
        this.schema2 = schema2;
        this.table1 = table1;
        this.table2 = table2;
    }

    public Table getTable1() {
        return table1;
    }

    public void setTable1(Table table1) {
        this.table1 = table1;
    }

    public Table getTable2() {
        return table2;
    }

    public void setTable2(Table table2) {
        this.table2 = table2;
    }

    public Schema getSchema1() {
        return schema1;
    }

    public void setSchema1(Schema schema1) {
        this.schema1 = schema1;
    }

    public Schema getSchema2() {
        return schema2;
    }

    public void setSchema2(Schema schema2) {
        this.schema2 = schema2;
    }

    public TableDifferenceType getType() {
        return type;
    }

    public void setType(TableDifferenceType type) {
        this.type = type;
    }
}
