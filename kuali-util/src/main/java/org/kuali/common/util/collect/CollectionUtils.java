package org.kuali.common.util.collect;

import java.util.ArrayList;
import java.util.Collection;

import org.kuali.common.util.nullify.NullUtils;

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

	public static Collection<?> getBlanks(Collection<?> collection) {
		Collection<Object> blanks = new ArrayList<Object>();
		for (Object element : collection) {
			if (NullUtils.isBlank(element)) {
				blanks.add(element);
			}
		}
		return blanks;
	}
}
