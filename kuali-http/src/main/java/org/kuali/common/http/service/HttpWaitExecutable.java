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

public class HttpWaitExecutable implements Executable {

	private final HttpContext context;
	private final HttpService service;
	private final boolean skip;

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

	public static class Builder {

		// Required
		private final HttpService service;
		private final HttpContext context;

		private boolean skip = false;

		public Builder(HttpService service, HttpContext context) {
			this.service = service;
			this.context = context;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public HttpWaitExecutable build() {
			Assert.noNulls(service, context);
			return new HttpWaitExecutable(this);
		}

	}

	private HttpWaitExecutable(Builder builder) {
		this.service = builder.service;
		this.context = builder.context;
		this.skip = builder.skip;
	}

}
