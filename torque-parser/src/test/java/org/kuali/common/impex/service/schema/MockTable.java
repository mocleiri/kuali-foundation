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

package org.kuali.common.impex.service.schema;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.UniqueConstraint;

public class MockTable implements Table {

    private String name;
    private List<Column> columns;
    private List<UniqueConstraint> uniqueConstraints;
    private List<Index> indices;
    private String description;

    public MockTable() {
        name = null;
        columns = new ArrayList<Column>();
        uniqueConstraints = new ArrayList<UniqueConstraint>();
    }

    @Override
    public List<Column> getColumns() {
        return columns;
    }

    @Override
    public List<UniqueConstraint> getUniqueConstraints() {
        return uniqueConstraints;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Index> getIndices() {
        return indices;
    }

    public void setIndices(List<Index> indices) {
        this.indices = indices;
    }

    public void setUniqueConstraints(List<UniqueConstraint> uniqueConstraints) {
        this.uniqueConstraints = uniqueConstraints;
    }
}
