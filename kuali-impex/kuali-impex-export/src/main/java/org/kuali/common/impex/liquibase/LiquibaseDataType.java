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

package org.kuali.common.impex.liquibase;

import org.kuali.common.impex.model.DataType;

public enum LiquibaseDataType {

	CHAR(DataType.CHAR), //
	CLOB(DataType.CLOB), //
	LONGTEXT(DataType.CLOB), //
	VARCHAR(DataType.STRING), //
	VARCHAR2(DataType.STRING), //
	DATE(DataType.DATE), //
	TIMESTAMP(DataType.TIMESTAMP), //
	DATETIME(DataType.TIMESTAMP), //
	DECIMAL(DataType.FLOAT), //
	NUMBER(DataType.FLOAT), //
	BIT(DataType.BIT), //
	BLOB(DataType.BLOB), //
	LONGBLOB(DataType.BLOB), //
	BIGINT(DataType.INTEGER);

	private final DataType dataType;

	private LiquibaseDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public DataType getDataType() {
		return dataType;
	}
}
