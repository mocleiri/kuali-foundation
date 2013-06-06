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

package org.kuali.common.jalc.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents any named connection between columns
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class Constraint implements NamedElement {

    @XmlAttribute
    protected String name;

    protected List<String> columnNames;

    public Constraint(List<String> colNames, String name) {
        this.columnNames = colNames;
        this.name = name;
    }

    @XmlElement(name = "column")
    public List<String> getColumnNames() {
        return columnNames;
    }

    public String getName() {
        return name;
    }
}
