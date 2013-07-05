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

package org.kuali.common.impex.schema.impl;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.schema.DataTypeMapping;
import org.kuali.common.impex.schema.DataTypeMappingProvider;

public class DefaultDataTypeMappingProvider implements DataTypeMappingProvider {

	protected Map<String, DataTypeMapping> columnNameStartsWith = new HashMap<String, DataTypeMapping>();

	protected Map<DataType, DataTypeMapping> dataTypeMatches = new HashMap<DataType, DataTypeMapping>();

	@Override
	public DataTypeMapping getDataTypeMapping(Column column) {

		// attempt to match by data type first
		for (DataType dataType : dataTypeMatches.keySet()) {
			if (column.getDataType() == dataType) {
				return dataTypeMatches.get(dataType);
			}
		}

		// find by column name "starts with" characters next
		for (String s : columnNameStartsWith.keySet()) {
			if (column.getName().startsWith(s)) {
				return columnNameStartsWith.get(s);
			}
		}

		return null;
	}

	public Map<String, DataTypeMapping> getColumnNameStartsWith() {
		return columnNameStartsWith;
	}

	public void setColumnNameStartsWith(Map<String, DataTypeMapping> columnNameStartsWith) {
		this.columnNameStartsWith = columnNameStartsWith;
	}

	public Map<DataType, DataTypeMapping> getDataTypeMatches() {
		return dataTypeMatches;
	}

	public void setDataTypeMatches(Map<DataType, DataTypeMapping> dataTypeMatches) {
		this.dataTypeMatches = dataTypeMatches;
	}
}
