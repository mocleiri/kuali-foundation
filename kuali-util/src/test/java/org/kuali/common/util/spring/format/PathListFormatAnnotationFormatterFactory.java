package org.kuali.common.util.spring.format;

import java.io.File;
import java.util.List;

import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public final class PathListFormatAnnotationFormatterFactory extends AbstractListStringFormatAnnotationFormatterFactory<PathListFormat> {

	@Override
	public Printer<List<String>> getPrinter(PathListFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	@Override
	public Parser<List<String>> getParser(PathListFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	protected Formatter<List<String>> getFormatter(PathListFormat annotation) {
		return getFormatter(File.pathSeparatorChar, annotation.trim(), annotation.omitEmpty(), annotation.magicEmptyString());
	}

}