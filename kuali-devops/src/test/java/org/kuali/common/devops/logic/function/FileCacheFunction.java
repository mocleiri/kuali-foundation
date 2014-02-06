package org.kuali.common.devops.logic.function;

import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.devops.model.FileCache;

import com.google.common.base.Function;

public final class FileCacheFunction implements Function<String, FileCache> {

	public FileCacheFunction() {
		this(true);
	}

	public FileCacheFunction(boolean refresh) {
		this.refresh = refresh;
	}

	private final boolean refresh;

	@Override
	public FileCache apply(String url) {
		if (refresh) {
			return HttpCacher.refresh(url);
		} else {
			return HttpCacher.loadFromCache(url);
		}
	}

}
