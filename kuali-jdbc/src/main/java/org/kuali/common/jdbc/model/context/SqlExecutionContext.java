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

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class SqlExecutionContext {

	public static final boolean DEFAULT_SKIP = false;

	private final String message;
	private final boolean skip;
	private final List<JdbcContext> contexts;

	public SqlExecutionContext(String message, JdbcContext context) {
		this(message, CollectionUtils.singletonList(context), DEFAULT_SKIP);
	}

	public SqlExecutionContext(String message, List<JdbcContext> contexts, boolean skip) {
		Assert.noBlanks(message);
		Assert.noNulls(contexts);
		this.message = message;
		this.contexts = CollectionUtils.unmodifiableCopy(contexts);
		this.skip = skip;
	}

	public String getMessage() {
		return message;
	}

	public List<JdbcContext> getContexts() {
		return contexts;
	}

	public boolean isSkip() {
		return skip;
	}
}
