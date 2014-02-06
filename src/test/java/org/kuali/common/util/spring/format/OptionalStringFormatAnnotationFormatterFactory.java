package org.kuali.common.util.spring.format;

import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public final class OptionalStringFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<OptionalStringFormat> {

	private static final Class<?>[] ARRAY = { Optional.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	@Override
	public Printer<Optional<String>> getPrinter(OptionalStringFormat annotation, Class<?> fieldType) {
		return new OptionalStringFormatter();
	}

	@Override
	public Parser<Optional<String>> getParser(OptionalStringFormat annotation, Class<?> fieldType) {
		return new OptionalStringFormatter();
	}

}