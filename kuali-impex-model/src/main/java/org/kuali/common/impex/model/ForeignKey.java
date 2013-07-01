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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ForeignKey implements NamedElement {

    protected String name;
    protected ForeignKeyConstraintType onDelete;
    protected ForeignKeyConstraintType onUpdate;
    protected List<String> localColumnNames;
    protected List<String> foreignColumnNames;
    protected String localTableName;
    protected String foreignTableName;

    public ForeignKey() {
        this(null, null, null);
    }

    public ForeignKey(String name, String localTableName, String foreignTableName) {
        this.name = name;
        this.localTableName = localTableName;
        this.foreignTableName = foreignTableName;

        localColumnNames = new ArrayList<String>();
        foreignColumnNames = new ArrayList<String>();
    }

    @XmlAttribute
    public String getForeignTableName() {
        return foreignTableName;
    }

    public void setForeignTableName(String foreignTableName) {
        this.foreignTableName = foreignTableName;
    }

    @XmlAttribute
    public String getLocalTableName() {
        return localTableName;
    }

    public void setLocalTableName(String localTableName) {
        this.localTableName = localTableName;
    }

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getForeignColumnNames() {
        return foreignColumnNames;
    }

    public void setForeignColumnNames(List<String> foreignColumnNames) {
        this.foreignColumnNames = foreignColumnNames;
    }

    public List<String> getLocalColumnNames() {
        return localColumnNames;
    }

    public void setLocalColumnNames(List<String> localColumnNames) {
        this.localColumnNames = localColumnNames;
    }

    @XmlAttribute
    public ForeignKeyConstraintType getOnDelete() {
        return onDelete;
    }

    @XmlAttribute
    public ForeignKeyConstraintType getOnUpdate() {
        return onUpdate;
    }

    public void setOnDelete(ForeignKeyConstraintType onDelete) {
        this.onDelete = onDelete;
    }

    public void setOnUpdate(ForeignKeyConstraintType onUpdate) {
        this.onUpdate = onUpdate;
    }
}
