package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.forceDelete;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Optional;

public final class FileWriter implements CacheWriter<File, Optional<String>> {

	public FileWriter(String encoding) {
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final String encoding;

	@Override
	public void write(File file, Optional<String> string) throws IOException {
		checkNotNull(file);
		checkNotNull(string);
		if (string.isPresent()) {
			FileUtils.write(file, string.get());
		} else {
			if (file.exists()) {
				forceDelete(file);
			}
		}
	}

	public String getEncoding() {
		return encoding;
	}

}
