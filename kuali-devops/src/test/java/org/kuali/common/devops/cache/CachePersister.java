package org.kuali.common.devops.cache;

public interface CachePersister<K, V, L> {

	L persist(K key, V value) throws Exception;

}
