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
package org.kuali.common.jdbc.model.context;

import org.kuali.common.jdbc.model.enums.SqlMode;
import org.kuali.common.util.Assert;

public final class SqlExecutionContext {

	private final String key;
	private final String group;
	private final SqlMode mode;
	private final String context;

	public SqlExecutionContext(String key, String group, SqlMode mode, String context) {
		Assert.noBlanks(key, group, context);
		Assert.noNulls(mode);
		this.key = key;
		this.group = group;
		this.mode = mode;
		this.context = context;
	}

	public String getGroup() {
		return group;
	}

	public SqlMode getMode() {
		return mode;
	}

	public String getKey() {
		return key;
	}

	public String getContext() {
		return context;
	}

}
