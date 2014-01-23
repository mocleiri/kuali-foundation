package org.kuali.common.util.spring.format;

import java.io.File;
import java.util.Locale;

import org.kuali.common.util.file.CanonicalFile;
import org.springframework.format.Formatter;

public class CanonicalFileFormatter implements Formatter<File> {

	@Override
	public File parse(String file, Locale locale) {
		return new CanonicalFile(file);
	}

	@Override
	public String print(File file, Locale locale) {
		return new CanonicalFile(file).getPath();
	}

}
