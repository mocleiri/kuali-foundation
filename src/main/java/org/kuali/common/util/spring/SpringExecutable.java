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
package org.kuali.common.util.spring;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.spring.service.DefaultSpringService;
import org.kuali.common.util.spring.service.SpringContext;
import org.kuali.common.util.spring.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SpringExecutable.class);

	public static final SpringService DEFAULT_SPRING_SERVICE = new DefaultSpringService();

	SpringService service = DEFAULT_SPRING_SERVICE;
	SpringContext context;
	boolean skip;

	public SpringExecutable() {
		this(null);
	}

	public SpringExecutable(SpringContext context) {
		this(DEFAULT_SPRING_SERVICE, context);
	}

	public SpringExecutable(SpringService service, SpringContext context) {
		this.service = service;
		this.context = context;
	}

	@Override
	public void execute() {
		if (skip) {
			logger.info("Skipping execution");
		} else {
			service.load(context);
		}
	}

	public SpringService getService() {
		return service;
	}

	public void setService(SpringService service) {
		this.service = service;
	}

	public SpringContext getContext() {
		return context;
	}

	public void setContext(SpringContext context) {
		this.context = context;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
