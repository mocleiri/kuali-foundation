package org.kuali.common.devops.cache;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.kuali.common.util.Encodings.UTF8;

import java.io.File;
import java.io.IOException;

import org.kuali.common.util.base.Exceptions;

import com.google.common.base.Function;
import com.google.common.base.Optional;

public class ToStringFunction implements Function<File, Optional<String>> {

	private final String encoding = UTF8;

	@Override
	public Optional<String> apply(File file) {
		checkNotNull(file);
		if (!file.exists()) {
			return Optional.absent();
		}
		try {
			return Optional.of(readFileToString(file, encoding));
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error reading from [%s]", file);
		}
	}

}
