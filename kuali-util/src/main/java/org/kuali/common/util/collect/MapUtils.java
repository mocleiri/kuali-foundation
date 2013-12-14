package org.kuali.common.util.collect;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Preconditions;

public class MapUtils {

	private MapUtils() {
	}

	public static boolean isMap(Class<?> type) {
		return Map.class.isAssignableFrom(type);
	}

	public static Set<?> getBlankKeys(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Set<Object> keys = new HashSet<Object>();
		for (Object key : map.keySet()) {
			if (NullUtils.isBlank(key)) {
				keys.add(key);
			}
		}
		return keys;
	}

	public static Set<?> getBlankOrNullKeys(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Set<Object> keys = new HashSet<Object>();
		for (Object key : map.keySet()) {
			if (NullUtils.isNullOrBlank(key)) {
				keys.add(key);
			}
		}
		return keys;
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

	public static Map<?, ?> getNullEntries(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Map<Object, Object> entries = new HashMap<Object, Object>();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (containsNull(entry)) {
				entries.put(entry.getKey(), entry.getValue());
			}
		}
		return entries;
	}

	public static Map<?, ?> getBlankOrNullEntries(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Map<Object, Object> entries = new HashMap<Object, Object>();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (containsNullOrBlank(entry)) {
				entries.put(entry.getKey(), entry.getValue());
			}
		}
		return entries;
	}

	public static boolean containsBlank(Map.Entry<?, ?> entry) {
		return NullUtils.isBlank(entry.getKey()) || NullUtils.isBlank(entry.getValue());
	}

	public static boolean containsNull(Map.Entry<?, ?> entry) {
		return entry.getKey() == null || entry.getValue() == null;
	}

	public static boolean containsNullOrBlank(Map.Entry<?, ?> entry) {
		return containsNull(entry) || containsBlank(entry);
	}

}
