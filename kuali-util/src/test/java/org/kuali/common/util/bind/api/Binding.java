package org.kuali.common.util.bind.api;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.google.common.base.Preconditions;

public class Binding {

	public static BinderFactory buildDefaultBinderFactory() {
		ServiceLoader<BinderFactory> loader = ServiceLoader.load(BinderFactory.class);
		Iterator<BinderFactory> itr = loader.iterator();
		Preconditions.checkState(itr.hasNext(), "No impl for [%s]", BinderFactory.class.getCanonicalName());
		return itr.next();
	}
}
