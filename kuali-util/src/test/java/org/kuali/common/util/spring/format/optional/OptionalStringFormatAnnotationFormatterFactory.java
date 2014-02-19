package org.kuali.common.util.spring.format.optional;

import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.base.Optional;

public final class OptionalStringFormatAnnotationFormatterFactory extends AbstractOptionalFormatAnnotationFormatterFactory<OptionalStringFormat> {

	@Override
	public Printer<Optional<String>> getPrinter(OptionalStringFormat annotation, Class<?> fieldType) {
		return new OptionalStringFormatter(annotation.value());
	}

	@Override
	public Parser<Optional<String>> getParser(OptionalStringFormat annotation, Class<?> fieldType) {
		return new OptionalStringFormatter(annotation.value());
	}

}