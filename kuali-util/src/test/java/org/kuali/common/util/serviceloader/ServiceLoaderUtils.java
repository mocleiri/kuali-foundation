package org.kuali.common.util.serviceloader;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.google.common.collect.Lists;

public class ServiceLoaderUtils {

	/**
	 * Return the first service located by ServiceLoader
	 * 
	 * @throws IllegalStateException
	 *             If no service can be found
	 */
	public static <T> T getFirst(Class<T> type) {
		return getFirst(type, Thread.currentThread().getContextClassLoader());
	}

	/**
	 * Return the first service located by ServiceLoader
	 * 
	 * @throws IllegalStateException
	 *             If no service can be found
	 */
	public static <T> T getFirst(Class<T> type, ClassLoader classLoader) {
		ServiceLoader<T> loader = ServiceLoader.load(type, classLoader);
		Iterator<T> itr = loader.iterator();
		checkState(itr.hasNext(), "ServiceLoader could not find a service for type [%s]", type.getCanonicalName());
		return itr.next();
	}

	/**
	 * Return all service implementations located by ServiceLoader
	 */
	public static <T> List<T> getAll(Class<T> type) {
		return getAll(type, Thread.currentThread().getContextClassLoader());
	}

	/**
	 * Return all service implementations located by ServiceLoader
	 */
	public static <T> List<T> getAll(Class<T> type, ClassLoader classLoader) {
		ServiceLoader<T> loader = ServiceLoader.load(type);
		Iterator<T> itr = loader.iterator();
		List<T> list = Lists.newArrayList();
		while (itr.hasNext()) {
			list.add(itr.next());
		}
		return list;
	}

}
