package org.kuali.common.util.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class SimpleCache<K, V> implements Cache<K, V> {

	private final Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());

	@Override
	public void put(K key, V value) {
		map.put(key, value);
	}

	@Override
	public V get(K key) {
		return map.get(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

}
