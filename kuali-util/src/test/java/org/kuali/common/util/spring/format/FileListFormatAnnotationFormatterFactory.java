package org.kuali.common.util.spring.format;

import java.util.List;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.collect.ImmutableSet;

public final class FileListFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<FileListFormat> {

	private static final Class<?>[] ARRAY = { List.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	@Override
	public Printer<List<String>> getPrinter(FileListFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	@Override
	public Parser<List<String>> getParser(FileListFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	private Formatter<List<String>> configureFormatterFrom(FileListFormat annotation, Class<?> fieldType) {
		return new FileListFormatter();
	}
}