package org.kuali.common.devops.logic.function;

import java.io.File;

import org.kuali.common.devops.logic.HttpCacher;
import org.kuali.common.devops.model.FileCache;
import org.kuali.common.util.LocationUtils;

import com.google.common.base.Function;
import com.google.common.base.Optional;

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
			File cache = HttpCacher.getCacheFile(url);
			return FileCache.builder().cache(cache).content(getContent(cache)).url(url).build();
		}
	}

	private Optional<String> getContent(File file) {
		if (file.exists()) {
			String content = LocationUtils.toString(file);
			return Optional.of(content);
		} else {
			return Optional.absent();
		}
	}

}
