/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.s3;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.execute.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BucketServiceExecutable implements Executable {

	private static final Logger logger = LoggerFactory.getLogger(BucketServiceExecutable.class);

	BucketService service;
	ObjectListingsContext context;

	@Override
	public void execute() {
		Assert.notNull(service, "service is null");
		Assert.notNull(context, "context is null");

		ListingResult result = service.getObjectListings(context);

		String elapsed = FormatUtils.getTime(result.getElapsed());
		String size = FormatUtils.getCount(result.getListings().size());

		logger.info("Time: {} Count: {}", elapsed, size);

	}

	public BucketService getService() {
		return service;
	}

	public void setService(BucketService service) {
		this.service = service;
	}

	public ObjectListingsContext getContext() {
		return context;
	}

	public void setContext(ObjectListingsContext context) {
		this.context = context;
	}

}
