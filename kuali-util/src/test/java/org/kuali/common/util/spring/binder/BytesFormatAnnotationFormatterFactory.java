package org.kuali.common.util.spring.binder;

import java.util.HashSet;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.collect.ImmutableList;

public final class BytesFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<BytesFormat> {

	@Override
	public Set<Class<?>> getFieldTypes() {
		return new HashSet<Class<?>>(ImmutableList.of(Short.class, Integer.class, Long.class));
	}

	@Override
	public Printer<Number> getPrinter(BytesFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	@Override
	public Parser<Number> getParser(BytesFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	private Formatter<Number> configureFormatterFrom(BytesFormat annotation, Class<?> fieldType) {
		return new BytesFormatter();
	}
}