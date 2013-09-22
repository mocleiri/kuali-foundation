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

import org.kuali.common.jdbc.JdbcUtils;
import org.kuali.common.util.PercentCompleteInformer;

/**
 * Print a dot to the console each time 1% of the SQL finishes executing
 * 
 * @deprecated
 */
@Deprecated
public class ProgressListener extends NoOpSqlListener {

	PercentCompleteInformer informer = new PercentCompleteInformer();
	boolean startInformer = true;

	@Override
	public synchronized void beforeExecution(SqlExecutionEvent event) {
		// The total number of SQL statements being executed
		informer.setTotal(JdbcUtils.getSqlCount(event.getContext().getSuppliers()));
	}

	@Override
	public synchronized void afterExecuteSql(SqlEvent event) {
		// The first SQL statement was just executed
		if (startInformer) {
			informer.start();
			startInformer = false;
		}

		// Increment the counter
		informer.incrementProgress();
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		informer.stop();
	}

	public PercentCompleteInformer getInformer() {
		return informer;
	}

	public void setInformer(PercentCompleteInformer informer) {
		this.informer = informer;
	}

}
