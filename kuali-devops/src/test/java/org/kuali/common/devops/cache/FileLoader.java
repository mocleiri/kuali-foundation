package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

public final class FileLoader extends CacheLoader<File, Optional<String>> {

	public FileLoader(String encoding) {
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final String encoding;

	@Override
	public Optional<String> load(File file) throws IOException {
		checkNotNull(file);
		if (file.exists()) {
			return Optional.of(readFileToString(file, encoding));
		} else {
			return Optional.absent();
		}
	}

	public String getEncoding() {
		return encoding;
	}

}
