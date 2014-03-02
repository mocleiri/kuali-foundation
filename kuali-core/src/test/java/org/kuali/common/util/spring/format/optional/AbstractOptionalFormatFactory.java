package org.kuali.common.util.spring.format.optional;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public abstract class AbstractOptionalFormatFactory<T extends Annotation> implements AnnotationFormatterFactory<T> {

	private static final Class<?>[] ARRAY = { Optional.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

}