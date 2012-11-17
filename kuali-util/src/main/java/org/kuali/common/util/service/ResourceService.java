package org.kuali.common.util.service;

import org.kuali.common.util.ResourceUtils;
import org.kuali.common.util.spring.ToStringContext;
import org.springframework.util.Assert;

public class ResourceService {

	public String toString(ToStringContext context) {
		Assert.notNull(context.getLocation());
		String s = ResourceUtils.toString(context.getLocation(), context.getEncoding());
		if (context.isDelete()) {
			ResourceUtils.delete(context.getLocation(), context.isDeleteQuietly());
		}
		return context.isTrim() ? s.trim() : s;
	}

}
