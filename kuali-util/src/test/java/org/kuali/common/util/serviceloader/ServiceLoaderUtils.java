package org.kuali.common.util.serviceloader;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ServiceLoaderUtils {

	public static <T> T getFirst(Class<T> type) {
		ServiceLoader<T> loader = ServiceLoader.load(type);
		Iterator<T> itr = loader.iterator();
		checkState(itr.hasNext(), "ServiceLoader could not find service for type [%s]", type.getCanonicalName());
		T service = itr.next();
		return service;
	}

}
