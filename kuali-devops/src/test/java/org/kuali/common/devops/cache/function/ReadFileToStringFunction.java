package org.kuali.common.devops.cache.function;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.File;
import java.io.IOException;

import org.kuali.common.util.base.Exceptions;

import com.google.common.base.Function;

public class ReadFileToStringFunction implements Function<File, String> {

	public ReadFileToStringFunction(String encoding) {
		this.encoding = checkNotBlank(encoding, "encoding");
	}

	private final String encoding;

	@Override
	public String apply(File file) {
		checkNotNull(file);
		try {
			return readFileToString(file, encoding);
		} catch (IOException e) {
			throw Exceptions.illegalState(e, "unexpected io error reading from [%s]", file);
		}
	}

}
