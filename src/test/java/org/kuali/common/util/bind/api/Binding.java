package org.kuali.common.util.bind.api;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Binding {

	public static BinderFactory buildDefaultBinderFactory() {
		ServiceLoader<BindingProvider> loader = ServiceLoader.load(BindingProvider.class);
		Iterator<BindingProvider> itr = loader.iterator();
		checkState(itr.hasNext(), "No impl for [%s]", BindingProvider.class.getCanonicalName());
		BindingProvider provider = itr.next();
		return provider.buildBinderFactory();
	}
}
