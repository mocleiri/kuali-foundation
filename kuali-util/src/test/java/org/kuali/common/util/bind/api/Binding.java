package org.kuali.common.util.bind.api;

import org.kuali.common.util.serviceloader.ServiceLoaderUtils;

public class Binding {

	public static BinderService getDefaultBinderService() {
		return ServiceLoaderUtils.getFirst(BinderService.class);
	}
}
