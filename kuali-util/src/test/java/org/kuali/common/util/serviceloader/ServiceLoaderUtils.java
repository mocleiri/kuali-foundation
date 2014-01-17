package org.kuali.common.util.serviceloader;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import com.google.common.collect.Lists;

public class ServiceLoaderUtils {

	public static <T> T getFirst(Class<T> type) {
		List<T> list = getAll(type);
		checkState(list.size() > 0, "ServiceLoader could not find service for type [%s]", type.getCanonicalName());
		return list.get(0);
	}

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
