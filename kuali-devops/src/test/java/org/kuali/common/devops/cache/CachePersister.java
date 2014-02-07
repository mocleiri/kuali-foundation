package org.kuali.common.devops.cache;

public interface CachePersister<K, V> {

	void persist(K key, V value) throws Exception;

}
