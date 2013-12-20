package org.kuali.common.util.collect;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Preconditions;

public class MapUtils {

	private MapUtils() {
	}

	/**
	 * Returns a Set containing any keys from <code>map</code> where the key is a pure whitespace <code>CharSequence</code>
	 */
	public static Set<String> getBlankKeys(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Set<String> blanks = new HashSet<String>();
		for (Object key : map.keySet()) {
			if (NullUtils.isBlank(key)) {
				CharSequence cs = (CharSequence) key;
				blanks.add(cs.toString());

			}
		}
		return blanks;
	}

	/**
	 * Returns a Set containing any entries from <code>map</code> where the key or value are <code>null</code> or a pure whitespace <code>CharSequence</code>
	 */
	public static Set<KeyValue> getBlankEntries(Map<?, ?> map) {
		Preconditions.checkNotNull(map, "'map' cannot be null");
		Set<KeyValue> entries = new HashSet<KeyValue>();
		for (Map.Entry<?, ?> entry : map.entrySet()) {
			if (containsBlank(entry)) {
				Object key = entry.getKey();
				Object value = entry.getValue();
				entries.add(new KeyValue(key, value));
			}
		}
		return entries;
	}

	public static class KeyValue {

		public KeyValue(Object key, Object value) {
			this.key = key;
			this.value = value;
		}

		private final Object key;
		private final Object value;

		public Object getKey() {
			return key;
		}

		public Object getValue() {
			return value;
		}
	}

	/**
	 * Return true if either the key or value are <code>null</code> or a pure whitespace <code>CharSequence</code>
	 */
	public static boolean containsBlank(Map.Entry<?, ?> entry) {
		return NullUtils.isBlank(entry.getKey()) || NullUtils.isBlank(entry.getValue());
	}

}
