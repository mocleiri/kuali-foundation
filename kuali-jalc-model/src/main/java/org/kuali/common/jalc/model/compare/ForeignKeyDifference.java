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

import org.kuali.common.jalc.model.ForeignKey;
import org.kuali.common.jalc.model.Schema;

public class ForeignKeyDifference {

    Schema schema1;
    Schema schema2;
    ForeignKey foreignKey1;
    ForeignKey foreignKey2;
    ForeignKeyDifferenceType type;

    public ForeignKeyDifference(Schema schema1, ForeignKey foreignKey1, Schema schema2, ForeignKey foreignKey2) {
        this.schema1 = schema1;
        this.schema2 = schema2;

        this.foreignKey1 = foreignKey1;
        this.foreignKey2 = foreignKey2;
    }

    public ForeignKey getForeignKey1() {
        return foreignKey1;
    }

    public void setForeignKey1(ForeignKey foreignKey1) {
        this.foreignKey1 = foreignKey1;
    }

    public ForeignKey getForeignKey2() {
        return foreignKey2;
    }

    public void setForeignKey2(ForeignKey foreignKey2) {
        this.foreignKey2 = foreignKey2;
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

    public ForeignKeyDifferenceType getType() {
        return type;
    }

    public void setType(ForeignKeyDifferenceType type) {
        this.type = type;
    }
}
