package org.kuali.common.util.service;

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
		return context.isTrim() ? s.trim() : s;
	}

}
