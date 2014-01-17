package org.kuali.common.util.serviceloader;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.google.common.collect.Lists;

public class ServiceLoaderUtils {

	/**
	 * Return the first service in the list
	 * 
	 * @throws IllegalStateException
	 *             If no service can be found
	 */
	public static <T> T getFirst(Class<T> type) {
		ServiceLoader<T> loader = ServiceLoader.load(type);
		Iterator<T> itr = loader.iterator();
		checkState(itr.hasNext(), "ServiceLoader could not find a service for type [%s]", type.getCanonicalName());
		return itr.next();
	}

	/**
	 * Return all service implementations located by ServiceLoader
	 */
	public static <T> List<T> getAll(Class<T> type) {
		ServiceLoader<T> loader = ServiceLoader.load(type);
		Iterator<T> itr = loader.iterator();
		List<T> list = Lists.newArrayList();
		while (itr.hasNext()) {
			list.add(itr.next());
		}
		return list;
	}

}
