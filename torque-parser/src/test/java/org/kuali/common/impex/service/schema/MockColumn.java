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

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.TypeSize;

public class MockColumn implements Column {

    private DataType columnDataType;
    private String name;
    private boolean dateType;
    private boolean primaryKey;
    private TypeSize typeSize;
    private String defaultValue;
    private boolean textType;
    private boolean nullable;
    private String description;

    /**
     * Create a new instance of a MockColumn
     *
     * All values are initialized, with a special note that nullable is initially set to true
     */
    public MockColumn() {
        columnDataType = null;
        name = null;
        dateType = false;
        primaryKey = false;
        typeSize = null;
        defaultValue = null;
        textType = false;

        // As a more sensible default, set nullable to true
        nullable = true;
    }

    @Override
    public DataType getDataType() {
        return columnDataType;
    }

    public void setColumnDataType(DataType dataType) {
        this.columnDataType = dataType;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isDateType() {
        return dateType;
    }

    public void setDateType(boolean dateType) {
        this.dateType = dateType;
    }

    @Override
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public TypeSize getTypeSize() {
        return typeSize;
    }

    public void setTypeSize(TypeSize typeSize) {
        this.typeSize = typeSize;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean isTextType() {
        return textType;
    }

    public void setTextType(boolean textType) {
        this.textType = textType;
    }

    @Override
    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
