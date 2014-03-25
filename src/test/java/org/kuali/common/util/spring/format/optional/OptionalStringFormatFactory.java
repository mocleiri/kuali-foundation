package org.kuali.common.util.spring.format.optional;

import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.base.Optional;

public final class OptionalStringFormatFactory extends AbstractOptionalFormatFactory<OptionalStringFormat> {

	@Override
	public Printer<Optional<String>> getPrinter(OptionalStringFormat annotation, Class<?> fieldType) {
		return new OptionalStringFormatter(annotation.absentToken());
	}

	@Override
	public Parser<Optional<String>> getParser(OptionalStringFormat annotation, Class<?> fieldType) {
		return new OptionalStringFormatter(annotation.absentToken());
	}

}