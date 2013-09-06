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
package org.kuali.common.http;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class HttpWaitExecutable implements Executable {

	private final Logger logger = LoggerFactory.getLogger(HttpWaitExecutable.class);

	HttpContext context;
	HttpService service = new DefaultHttpService();
	HttpStatus expected = HttpStatus.SUCCESS;
	boolean skip;

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skip waiting for a valid http status code");
			return;
		}
		HttpWaitResult result = service.wait(context);
		HttpStatus actual = result.getStatus();
		Assert.isTrue(expected.equals(actual), "Expected status - [" + expected + "]  Actual status - [" + actual + "]");
	}

	public HttpContext getContext() {
		return context;
	}

	public void setContext(HttpContext context) {
		this.context = context;
	}

	public HttpService getService() {
		return service;
	}

	public void setService(HttpService service) {
		this.service = service;
	}

	public HttpStatus getExpected() {
		return expected;
	}

	public void setExpected(HttpStatus expected) {
		this.expected = expected;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
