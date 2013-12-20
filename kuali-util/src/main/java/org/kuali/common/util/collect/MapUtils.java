package org.kuali.common.util.collect;

import java.util.HashMap;
import java.util.Map;

import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Preconditions;

public class MapUtils {

	private MapUtils() {
	}

	/**
	 * Returns a map containing any entries from <code>map</code> where the key or value are <code>null</code> or a pure whitespace <code>CharSequence</code>
	 */
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

	/**
	 * Return true if either the key or value are <code>null</code> or a pure whitespace <code>CharSequence</code>
	 */
	public static boolean containsBlank(Map.Entry<?, ?> entry) {
		return NullUtils.isBlank(entry.getKey()) || NullUtils.isBlank(entry.getValue());
	}

}
