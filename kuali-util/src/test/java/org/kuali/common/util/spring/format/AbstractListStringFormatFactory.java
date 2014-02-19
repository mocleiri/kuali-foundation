package org.kuali.common.util.spring.format;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;

import com.google.common.collect.ImmutableSet;

public abstract class AbstractListStringFormatFactory<A extends Annotation> implements AnnotationFormatterFactory<A> {

	private static final Class<?>[] ARRAY = { List.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	private static final String emptyListToken = "${list.empty}";

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	protected Formatter<List<String>> getFormatter(char separator) {
		return ListStringFormatter.builder().separator(separator).emptyListToken(emptyListToken).build();
	}

}