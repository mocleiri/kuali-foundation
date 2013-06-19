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

package org.kuali.common.impex.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Index extends Constraint {

    @XmlAttribute
    protected boolean unique;

    public Index() {
        this(new ArrayList<String>(), null);
    }

    public Index(List<String> colNames, String name) {
        super(colNames, name);
        this.unique = false;
    }

    public Index(List<String> colNames, String name, boolean unique) {
        this(colNames, name);
        this.unique = unique;
    }

    public boolean isUnique() {
        return unique;
    }
}
