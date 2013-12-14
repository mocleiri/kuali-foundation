package org.kuali.common.util.collect;

import java.util.Collection;

public class CollectionUtils {

	private CollectionUtils() {
	}

	public static boolean isCollection(Class<?> type) {
		return Collection.class.isAssignableFrom(type);
	}
}
