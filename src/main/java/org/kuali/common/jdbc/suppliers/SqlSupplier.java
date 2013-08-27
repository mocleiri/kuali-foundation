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
package org.kuali.common.jdbc.suppliers;

import java.io.IOException;
import java.util.List;

import org.kuali.common.jdbc.sql.model.SqlMetaData;

public interface SqlSupplier extends Comparable<SqlSupplier> {

	/**
	 * Open the supplier so it is ready to provide SQL
	 */
	void open() throws IOException;

	/**
	 * Return a list of SQL statements to execute. Returns <code>null</code> when the SQL supply has been exhausted.
	 */
	List<String> getSql() throws IOException;

	/**
	 * Close the supplier to free any resources it may have opened
	 */
	void close();

	/**
	 * Return SQL count and overall size
	 */
	SqlMetaData getMetaData();

}
