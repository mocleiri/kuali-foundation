package org.kuali.common.util.spring.format;

import java.io.File;
import java.util.List;

import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public final class FileListFormatAnnotationFormatterFactory extends AbstractListStringFormatAnnotationFormatterFactory<FileListFormat> {

	@Override
	public Printer<List<String>> getPrinter(FileListFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	@Override
	public Parser<List<String>> getParser(FileListFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	protected Formatter<List<String>> getFormatter(FileListFormat annotation) {
		return getFormatter(File.separatorChar, annotation.trim(), annotation.omitEmpty(), annotation.magicEmptyString());
	}

}