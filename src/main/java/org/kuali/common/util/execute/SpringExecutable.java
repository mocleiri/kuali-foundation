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
package org.kuali.common.util.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class SpringExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SpringExecutable.class);

	public static final org.kuali.common.util.service.SpringService DEFAULT_SPRING_SERVICE = new org.kuali.common.util.service.DefaultSpringService();

	org.kuali.common.util.service.SpringService service = DEFAULT_SPRING_SERVICE;
	org.kuali.common.util.service.SpringContext context;
	boolean skip;

	public SpringExecutable() {
		this(null);
	}

	public SpringExecutable(org.kuali.common.util.service.SpringContext context) {
		super();
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

	public org.kuali.common.util.service.SpringService getService() {
		return service;
	}

	public void setService(org.kuali.common.util.service.SpringService service) {
		this.service = service;
	}

	public org.kuali.common.util.service.SpringContext getContext() {
		return context;
	}

	public void setContext(org.kuali.common.util.service.SpringContext context) {
		this.context = context;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
}
