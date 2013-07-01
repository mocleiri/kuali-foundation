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

import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;

public class IndexDifference extends TableDifference {

    Index index1;
    Index index2;

    public IndexDifference(Schema schema1, Table table1, Index index1, Schema schema2, Table table2, Index index2) {
        super(schema1, table1, schema2, table2);

        this.index1 = index1;
        this.index2 = index2;
    }

    public Index getIndex1() {
        return index1;
    }

    public void setIndex1(Index index1) {
        this.index1 = index1;
    }

    public Index getIndex2() {
        return index2;
    }

    public void setIndex2(Index index2) {
        this.index2 = index2;
    }
}
