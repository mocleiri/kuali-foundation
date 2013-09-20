/**
 * Copyright 2013-2013 The Kuali Foundation
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
package org.kuali.common.http.service;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public final class HttpWaitExecutable implements Executable {

	public static final boolean DEFAULT_SKIP = false;

	private final HttpContext context;
	private final HttpService service;
	private final boolean skip;

	public HttpWaitExecutable(HttpService service, HttpContext context) {
		this(service, context, DEFAULT_SKIP);

	}

	public HttpWaitExecutable(HttpService service, HttpContext context, boolean skip) {
		Assert.noNulls(service, context);
		this.service = service;
		this.context = context;
		this.skip = skip;
	}

	@Override
	public void execute() {

		if (!skip) {
			service.wait(context);
		}

	}

	public HttpContext getContext() {
		return context;
	}

	public HttpService getService() {
		return service;
	}

	public boolean isSkip() {
		return skip;
	}

}
