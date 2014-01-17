package org.kuali.common.util.spring.binder;

import java.io.File;
import java.text.ParseException;
import java.util.Locale;

import org.kuali.common.util.file.CanonicalFile;
import org.springframework.format.Formatter;

public class CanonicalFileFormatter implements Formatter<File> {

	@Override
	public File parse(String file, Locale locale) throws ParseException {
		try {
			return new CanonicalFile(file);
		} catch (Exception e) {
			// TODO This masks the original exception, be smarter somehow
			throw new ParseException("Unexpected parse error: [" + e.getMessage() + "]", -1);
		}
	}

	@Override
	public String print(File file, Locale locale) {
		return new CanonicalFile(file).getPath();
	}

}
