package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.AbstractLoadingCache;

public final class PropertiesFileCache extends AbstractLoadingCache<String, String> {

	public PropertiesFileCache(File file) {
		this.file = checkNotNull(file);
	}

	private final File file;

	@Override
	public String get(String key) throws ExecutionException {
		return null;
	}

	@Override
	public String getIfPresent(Object key) {
		return null;
	}

}
