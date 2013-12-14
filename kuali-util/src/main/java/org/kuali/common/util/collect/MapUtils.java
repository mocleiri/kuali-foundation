package org.kuali.common.util.collect;

import static org.kuali.common.util.nullify.NullUtils.isBlank;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

public class MapUtils {

	private MapUtils() {
	}

	public static boolean isMap(Class<?> type) {
		return Map.class.isAssignableFrom(type);
	}

	public static boolean isImmutable(Class<?> type) {
		return ImmutableMap.class.isAssignableFrom(type);
	}

	public static Map<?, ?> getBlankEntries(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Map<Object, Object> entries = new HashMap<Object, Object>();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (containsBlank(entry)) {
				entries.put(entry.getKey(), entry.getValue());
			}
		}
		return entries;
	}

	public static boolean containsBlank(Map.Entry<?, ?> entry) {
		return isBlank(entry.getKey()) || isBlank(entry.getValue());
	}

}
