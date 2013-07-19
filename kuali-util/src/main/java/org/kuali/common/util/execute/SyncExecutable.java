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

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.file.DirRequest;
import org.kuali.common.util.sync.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(SyncExecutable.class);

	boolean skip;
	List<DirRequest> requests;
	SyncService service;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		Assert.notNull(service);

		service.sync(requests);

	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<DirRequest> getRequests() {
		return requests;
	}

	public void setRequests(List<DirRequest> requests) {
		this.requests = requests;
	}

	public SyncService getService() {
		return service;
	}

	public void setService(SyncService service) {
		this.service = service;
	}

}
