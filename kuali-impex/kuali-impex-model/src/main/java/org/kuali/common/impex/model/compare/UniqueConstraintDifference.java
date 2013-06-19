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

import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.UniqueConstraint;

public class UniqueConstraintDifference extends TableDifference {

    UniqueConstraint unique1;
    UniqueConstraint unique2;

    public UniqueConstraintDifference(Schema schema1, Table table1, UniqueConstraint unique1, Schema schema2, Table table2, UniqueConstraint unique2) {
        super(schema1, table1, schema2, table2);
        this.unique1 = unique1;
        this.unique2 = unique2;
    }

    public UniqueConstraint getUnique1() {
        return unique1;
    }

    public void setUnique1(UniqueConstraint unique1) {
        this.unique1 = unique1;
    }

    public UniqueConstraint getUnique2() {
        return unique2;
    }

    public void setUnique2(UniqueConstraint unique2) {
        this.unique2 = unique2;
    }
}
