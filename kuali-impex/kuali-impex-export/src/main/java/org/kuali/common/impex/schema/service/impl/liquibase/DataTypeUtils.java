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

package org.kuali.common.impex.schema.service.impl.liquibase;

import liquibase.structure.core.Column;
import org.kuali.common.impex.model.DataType;

/**
 * Utility methods for managing data type translation between liquibase and impex model
 */
public class DataTypeUtils {

    private static final String START_COLUMN_LENGTH_ARGUMENTS = "(";

    public static DataType getColumnDataType(Column column) {
        String colDataType = column.getType().getTypeName();

        if(colDataType.contains(START_COLUMN_LENGTH_ARGUMENTS)) {
            colDataType = colDataType.substring(0, colDataType.indexOf(START_COLUMN_LENGTH_ARGUMENTS));
        }

        return LiquibaseDataType.valueOf(colDataType).getDataType();
    }
}
