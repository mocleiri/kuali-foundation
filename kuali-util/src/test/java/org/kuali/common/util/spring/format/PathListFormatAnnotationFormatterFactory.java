package org.kuali.common.util.spring.format;

import java.io.File;
import java.util.List;

import org.springframework.format.Parser;
import org.springframework.format.Printer;

public final class PathListFormatAnnotationFormatterFactory extends AbstractListStringFormatAnnotationFormatterFactory<PathListFormat> {

	private static final char SEPARATOR = File.pathSeparatorChar;

	@Override
	public Printer<List<String>> getPrinter(PathListFormat annotation, Class<?> fieldType) {
		return getFormatter(SEPARATOR, annotation.trim(), annotation.omitEmpty(), annotation.magicEmptyString());
	}

	@Override
	public Parser<List<String>> getParser(PathListFormat annotation, Class<?> fieldType) {
		return getFormatter(SEPARATOR, annotation.trim(), annotation.omitEmpty(), annotation.magicEmptyString());
	}

}