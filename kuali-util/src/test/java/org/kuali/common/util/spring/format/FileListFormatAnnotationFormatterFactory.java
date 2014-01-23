package org.kuali.common.util.spring.format;

import java.io.File;
import java.util.List;

import org.springframework.format.Parser;
import org.springframework.format.Printer;

public final class FileListFormatAnnotationFormatterFactory extends ListStringFormatAnnotationFormatterFactory<FileListFormat> {

	@Override
	public Printer<List<String>> getPrinter(FileListFormat annotation, Class<?> fieldType) {
		return getFormatter(File.separatorChar, annotation.trim(), annotation.omitEmpty());
	}

	@Override
	public Parser<List<String>> getParser(FileListFormat annotation, Class<?> fieldType) {
		return getFormatter(File.separatorChar, annotation.trim(), annotation.omitEmpty());
	}

}