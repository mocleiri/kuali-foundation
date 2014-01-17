package org.kuali.common.util.bind.api;

import org.kuali.common.util.serviceloader.ServiceLoaderUtils;

public class Binding {

	private static BinderService instance;

	public synchronized static BinderService getDefaultBinderService() {
		if (instance == null) {
			instance = ServiceLoaderUtils.getFirst(BinderService.class);
		}
		return instance;
	}
}
