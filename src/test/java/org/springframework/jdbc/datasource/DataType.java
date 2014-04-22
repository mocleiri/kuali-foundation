/**
 * Copyright 2010-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.jdbc.datasource;

public enum DataType {

	ARRAY(2003), //
	BIGINT(-5), //
	BINARY(-2), //
	BIT(-7), //
	BLOB(2004), //
	BOOLEAN(16), //
	CHAR(1), //
	CLOB(2005), //
	DATALINK(70), //
	DATE(91), //
	DECIMAL(3), //
	DISTINCT(2001), //
	DOUBLE(8), //
	FLOAT(6), //
	INTEGER(4), //
	JAVA_OBJECT(2000), //
	LONGNVARCHAR(-16), //
	LONGVARBINARY(-4), //
	LONGVARCHAR(-1), //
	NCHAR(-15), //
	NCLOB(2011), //
	NULL(0), //
	NUMERIC(2), //
	NVARCHAR(-9), //
	OTHER(1111), //
	REAL(7), //
	REF(2006), //
	ROWID(-8), //
	SMALLINT(5), //
	SQLXML(2009), //
	STRUCT(2002), //
	TIME(92), //
	TIMESTAMP(93), //
	TINYINT(-6), //
	VARBINARY(-3), //
	VARCHAR(12);

	private final int value;

	private DataType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
