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
package org.kuali.common.jdbc.model.event;

import org.kuali.common.jdbc.model.context.JdbcContext;

public final class SqlMetaDataEvent {

	private final JdbcContext context;
	private final long startTimeMillis;
	private final long stopTimeMillis;

	public SqlMetaDataEvent(JdbcContext context, long startTimeMillis, long stopTimeMillis) {
		this.context = context;
		this.startTimeMillis = startTimeMillis;
		this.stopTimeMillis = stopTimeMillis;
	}

	public JdbcContext getContext() {
		return context;
	}

	public long getStartTimeMillis() {
		return startTimeMillis;
	}

	public long getStopTimeMillis() {
		return stopTimeMillis;
	}

}
