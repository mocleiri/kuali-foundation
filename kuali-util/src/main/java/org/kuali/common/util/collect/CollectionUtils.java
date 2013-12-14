package org.kuali.common.util.collect;

import java.util.Collection;

import com.google.common.collect.ImmutableCollection;

public class CollectionUtils {

	private CollectionUtils() {
	}

	public static boolean isCollection(Class<?> type) {
		return Collection.class.isAssignableFrom(type);
	}

	public static boolean isImmutable(Class<?> type) {
		return ImmutableCollection.class.isAssignableFrom(type);
	}
}
