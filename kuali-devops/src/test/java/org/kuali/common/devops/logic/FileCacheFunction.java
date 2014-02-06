package org.kuali.common.devops.logic;

import org.kuali.common.devops.model.FileCache;

import com.google.common.base.Function;

public class FileCacheFunction implements Function<String, FileCache> {

	@Override
	public FileCache apply(String url) {
		return HttpCacher.refresh(url);
	}

}
