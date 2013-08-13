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

import java.util.ArrayList;
import java.util.List;

/**
 * Notify other listeners about SQL related events
 * 
 * @deprecated
 */
@Deprecated
public class NotifyingListener implements SqlListener {

	List<SqlListener> listeners = new ArrayList<SqlListener>();

	public NotifyingListener() {
		this(null);
	}

	public NotifyingListener(List<SqlListener> listeners) {
		super();
		this.listeners = listeners;
	}

	@Override
	public void beforeMetaData(SqlMetaDataEvent event) {
		for (SqlListener listener : listeners) {
			listener.beforeMetaData(event);
		}
	}

	@Override
	public void afterMetaData(SqlMetaDataEvent event) {
		for (SqlListener listener : listeners) {
			listener.afterMetaData(event);
		}
	}

	@Override
	public void beforeExecution(SqlExecutionEvent event) {
		for (SqlListener listener : listeners) {
			listener.beforeExecution(event);
		}
	}

	@Override
	public void bucketsCreated(BucketEvent event) {
		for (SqlListener listener : listeners) {
			listener.bucketsCreated(event);
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

	public void setListeners(List<SqlListener> listeners) {
		this.listeners = listeners;
	}
}
