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
package org.kuali.common.jdbc.listener;


/**
 * @deprecated
 */
@Deprecated
public interface SqlListener {

	void beforeExecution(SqlExecutionEvent event);

	void beforeMetaData(SqlMetaDataEvent event);

	void afterMetaData(SqlMetaDataEvent event);

	void bucketsCreated(BucketEvent event);

	void beforeExecuteSql(SqlEvent event);

	void afterExecuteSql(SqlEvent event);

	void afterExecution(SqlExecutionEvent event);

}
