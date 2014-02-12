package org.kuali.common.devops.cache;

import com.google.common.cache.CacheLoader;

public abstract class PersistentCache<K, V> extends CacheLoader<K, V> implements CachePersister<K, V> {

}
