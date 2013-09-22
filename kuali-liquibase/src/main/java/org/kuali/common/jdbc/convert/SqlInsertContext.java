/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.jdbc.convert;

import java.io.BufferedReader;

import org.kuali.common.jdbc.reader.SqlReader;

public class SqlInsertContext {

	String sql;
	SqlReader reader;
	BufferedReader input;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public SqlReader getReader() {
		return reader;
	}

	public void setReader(SqlReader reader) {
		this.reader = reader;
	}

	public BufferedReader getInput() {
		return input;
	}

	public void setInput(BufferedReader input) {
		this.input = input;
	}
}
