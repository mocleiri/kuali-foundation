/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.service;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.spring.ToStringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class LocationService {
	private static final Logger logger = LoggerFactory.getLogger(LocationService.class);

	public String toString(ToStringContext context) {
		Assert.notNull(context.getLocation());
		String s = LocationUtils.toString(context.getLocation(), context.getEncoding());
		if (context.isDelete()) {
			boolean deleted = LocationUtils.deleteFileQuietly(context.getLocation());
			if (!deleted) {
				logger.warn("{} was not deleted", context.getLocation());
			}
		}
		return context.isTrim() ? StringUtils.trim(s) : s;
	}

}
