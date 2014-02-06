package org.kuali.common.devops.cache;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

public final class FileCacheLoader extends CacheLoader<File, Optional<String>> {

	@Override
	public Optional<String> load(File file) throws IOException {
		if (file.exists()) {
			return Optional.of(FileUtils.readFileToString(file));
		} else {
			return Optional.absent();
		}
	}

}
