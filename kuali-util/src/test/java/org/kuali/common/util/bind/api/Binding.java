package org.kuali.common.util.bind.api;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Binding {

	public static BinderService buildDefaultBinderFactory() {
		ServiceLoader<BinderService> loader = ServiceLoader.load(BinderService.class);
		Iterator<BinderService> itr = loader.iterator();
		checkState(itr.hasNext(), "No impl for [%s]", BinderService.class.getCanonicalName());
		BinderService service = itr.next();
		return service;
	}
}
