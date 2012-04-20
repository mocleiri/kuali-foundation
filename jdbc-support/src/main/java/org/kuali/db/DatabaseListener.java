/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.db;

/**
 * Listens for Database events
 */
public interface DatabaseListener {
	public void messageLogged(DatabaseEvent event);

	public void beginTransaction(DatabaseEvent event);

	public void finishTransaction(DatabaseEvent event);

	public void beforeExecuteSQL(DatabaseEvent event);

	public void afterExecuteSQL(DatabaseEvent event);

	public void afterProcessingSQLResults(DatabaseEvent event);
}
