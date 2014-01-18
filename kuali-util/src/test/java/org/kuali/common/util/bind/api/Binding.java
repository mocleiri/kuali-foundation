package org.kuali.common.util.bind.api;

import org.kuali.common.util.serviceloader.ServiceProvider;

public class Binding {

	private static BinderService instance;

	public synchronized static BinderService getDefaultBinderService() {
		if (instance == null) {
			instance = ServiceProvider.getFirst(BinderService.class);
		}
		return instance;
	}
}
