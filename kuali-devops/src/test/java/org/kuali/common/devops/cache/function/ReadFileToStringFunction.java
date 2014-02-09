package org.kuali.common.devops.cache.function;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.kuali.common.devops.cache.FilePersister.GLOBAL_MAGIC_ABSENT_STRING;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class ReadFileToStringFunction implements Function<File, Optional<String>> {

	public ReadFileToStringFunction(String encoding) {
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final String encoding;

	@Override
	public Optional<String> apply(File file) {
		checkNotNull(file);
		try {
			String content = readFileToString(file, encoding);
			if (GLOBAL_MAGIC_ABSENT_STRING.equals(content)) {
				return absent();
			} else {
				return Optional.of(content);
			}
		} catch (IOException e) {
			throw illegalState(e, "unexpected io error reading from [%s]", file);
		}
	}
}
