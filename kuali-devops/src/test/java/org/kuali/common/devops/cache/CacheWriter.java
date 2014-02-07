package org.kuali.common.devops.cache;

public interface CacheWriter<K, V> {

	void write(K key, V value) throws Exception;

}
