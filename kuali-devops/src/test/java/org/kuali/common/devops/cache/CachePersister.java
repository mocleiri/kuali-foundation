package org.kuali.common.devops.cache;

public interface CachePersister<K, V> {

	void store(K key, V value);

}
