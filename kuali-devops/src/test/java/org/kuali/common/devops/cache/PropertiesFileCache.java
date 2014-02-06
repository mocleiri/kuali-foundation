package org.kuali.common.devops.cache;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.AbstractLoadingCache;

public class PropertiesFileCache extends AbstractLoadingCache<String, String> {

	@Override
	public String get(String key) throws ExecutionException {
		return null;
	}

	@Override
	public String getIfPresent(Object key) {
		return null;
	}

}
