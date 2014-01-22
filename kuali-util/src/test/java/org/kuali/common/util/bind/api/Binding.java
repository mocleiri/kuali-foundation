package org.kuali.common.util.bind.api;

import org.kuali.common.util.serviceloader.ServiceProvider;

public class Binding {

	private static Binder instance;

	public synchronized static Binder getDefaultBinderService() {
		if (instance == null) {
			instance = ServiceProvider.getFirst(Binder.class);
		}
		return instance;
	}
}
