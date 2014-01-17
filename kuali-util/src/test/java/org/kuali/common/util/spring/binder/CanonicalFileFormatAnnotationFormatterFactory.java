package org.kuali.common.util.spring.binder;

import java.io.File;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.collect.ImmutableSet;

public final class CanonicalFileFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<CanonicalFileFormat> {

	private static final Class<?>[] ARRAY = { File.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	@Override
	public Printer<File> getPrinter(CanonicalFileFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	@Override
	public Parser<File> getParser(CanonicalFileFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	private Formatter<File> configureFormatterFrom(CanonicalFileFormat annotation, Class<?> fieldType) {
		return new CanonicalFileFormatter();
	}
}