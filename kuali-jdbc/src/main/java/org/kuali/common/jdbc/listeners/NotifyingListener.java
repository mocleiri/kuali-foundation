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
package org.kuali.common.jdbc.listeners;

import java.util.List;

import org.kuali.common.jdbc.model.event.SqlEvent;
import org.kuali.common.jdbc.model.event.SqlExecutionEvent;
import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;

/**
 * Notify other listeners about SQL related events
 */
public final class NotifyingListener implements SqlListener {

	private final List<SqlListener> listeners;

	public NotifyingListener(List<SqlListener> listeners) {
		Assert.noNulls(listeners);
		this.listeners = ListUtils.newImmutableArrayList(listeners);
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		for (SqlListener listener : listeners) {
			listener.beforeExecution(event);
		}
	}

	@Override
	public void beforeExecuteSql(SqlEvent event) {
		for (SqlListener listener : listeners) {
			listener.beforeExecuteSql(event);
		}
	}

	@Override
	public void afterExecuteSql(SqlEvent event) {
		for (SqlListener listener : listeners) {
			listener.afterExecuteSql(event);
		}
	}

	@Override
	public void afterExecution(SqlExecutionEvent event) {
		for (SqlListener listener : listeners) {
			listener.afterExecution(event);
		}
	}

	public List<SqlListener> getListeners() {
		return listeners;
	}

}
