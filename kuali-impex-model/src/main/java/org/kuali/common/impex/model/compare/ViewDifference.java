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
import org.kuali.common.impex.model.View;

public class ViewDifference extends SchemaDifference {
    View view1;
    View view2;
    ViewDifferenceType type;

    public ViewDifference(Schema schema1, View view1, Schema schema2, View view2) {
        super(schema1, schema2);
        this.view1 = view1;
        this.view2 = view2;
    }

    public ViewDifferenceType getType() {
        return type;
    }

    public void setType(ViewDifferenceType type) {
        this.type = type;
    }

    public View getView1() {
        return view1;
    }

    public void setView1(View view1) {
        this.view1 = view1;
    }

    public View getView2() {
        return view2;
    }

    public void setView2(View view2) {
        this.view2 = view2;
    }
}
