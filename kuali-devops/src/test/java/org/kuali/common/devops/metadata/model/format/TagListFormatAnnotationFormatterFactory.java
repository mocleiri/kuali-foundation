package org.kuali.common.devops.metadata.model.format;

import java.util.List;
import java.util.Set;

import org.kuali.common.devops.metadata.model.EC2Tag;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public final class TagListFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<TagListFormat> {

	private static final Class<?>[] ARRAY = { List.class, ImmutableList.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	@Override
	public Printer<List<EC2Tag>> getPrinter(TagListFormat annotation, Class<?> fieldType) {
		return new TagListFormatter();
	}

	@Override
	public Parser<List<EC2Tag>> getParser(TagListFormat annotation, Class<?> fieldType) {
		return new TagListFormatter();
	}

}