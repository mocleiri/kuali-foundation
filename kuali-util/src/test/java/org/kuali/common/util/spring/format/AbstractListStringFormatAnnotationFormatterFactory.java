package org.kuali.common.util.spring.format;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;

import com.google.common.collect.ImmutableSet;

public abstract class AbstractListStringFormatAnnotationFormatterFactory<A extends Annotation> implements AnnotationFormatterFactory<A> {

	private static final Class<?>[] ARRAY = { List.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	protected Formatter<List<String>> getFormatter(char separator, boolean trim, boolean omitEmpty, String magicEmptyString) {
		return ListStringFormatter.builder().separator(separator).trim(trim).omitEmpty(omitEmpty).magicEmptyString(magicEmptyString).build();
	}

}