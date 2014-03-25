package org.kuali.common.util.spring.format;

import java.util.Properties;
import java.util.Set;

import org.kuali.common.util.property.ImmutableProperties;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.collect.ImmutableSet;

public final class XmlPropertiesFormatFactory implements AnnotationFormatterFactory<XmlPropertiesFormat> {

	private static final Class<?>[] ARRAY = { Properties.class, ImmutableProperties.class };
	private static final Set<Class<?>> TYPES = ImmutableSet.copyOf(ARRAY);

	@Override
	public Set<Class<?>> getFieldTypes() {
		return TYPES;
	}

	@Override
	public Printer<Properties> getPrinter(XmlPropertiesFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	@Override
	public Parser<Properties> getParser(XmlPropertiesFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation);
	}

	protected Formatter<Properties> getFormatter(XmlPropertiesFormat annotation) {
		return new XmlPropertiesFormatter(annotation.encoding(), annotation.emptyPropertiesToken());
	}

}