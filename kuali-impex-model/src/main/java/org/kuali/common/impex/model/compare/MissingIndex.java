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

public class MissingIndex extends IndexDifference {

    public MissingIndex(Schema sourceSchema, Table sourceTable, Schema missingSchema, Table missingTable, Index index) {
        super(sourceSchema, sourceTable, index, missingSchema, missingTable, null);
        setType(TableDifferenceType.MISSING_INDEX);
    }

    public Schema getSourceSchema() {
        return schema1;
    }

    public Schema getMissingSchema() {
        return schema2;
    }

    public Table getSourceTable() {
        return table1;
    }

    public Table getMissingTable() {
        return table2;
    }

    public Index getIndex() {
        return index1;
    }
}
