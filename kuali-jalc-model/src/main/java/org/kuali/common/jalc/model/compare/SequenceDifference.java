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
import org.kuali.common.jalc.model.Sequence;

public class SequenceDifference extends SchemaDifference {

    Sequence sequence1;
    Sequence sequence2;

    SequenceDifferenceType type;

    public SequenceDifference(Schema schema1, Sequence sequence1, Schema schema2, Sequence sequence2) {
        super(schema1, schema2);
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
    }

    public Sequence getSequence1() {
        return sequence1;
    }

    public void setSequence1(Sequence sequence1) {
        this.sequence1 = sequence1;
    }

    public Sequence getSequence2() {
        return sequence2;
    }

    public void setSequence2(Sequence sequence2) {
        this.sequence2 = sequence2;
    }

    public SequenceDifferenceType getType() {
        return type;
    }

    public void setType(SequenceDifferenceType type) {
        this.type = type;
    }
}
