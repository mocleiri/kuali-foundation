package org.kuali.common.util.spring.binder;

import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.collect.ImmutableSet;

public final class TimeFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<TimeFormat> {

	private static final Class<?>[] ARRAY = { Integer.class, Long.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	@Override
	public Printer<Number> getPrinter(TimeFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	@Override
	public Parser<Number> getParser(TimeFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	private Formatter<Number> configureFormatterFrom(TimeFormat annotation, Class<?> fieldType) {
		return new TimeFormatter();
	}
}