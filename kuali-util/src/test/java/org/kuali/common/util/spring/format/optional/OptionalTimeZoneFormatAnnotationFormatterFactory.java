package org.kuali.common.util.spring.format.optional;

import java.util.TimeZone;

import org.springframework.format.Parser;
import org.springframework.format.Printer;

import com.google.common.base.Optional;

public final class OptionalTimeZoneFormatAnnotationFormatterFactory extends AbstractOptionalFormatAnnotationFormatterFactory<OptionalTimeZoneFormat> {

	@Override
	public Printer<Optional<TimeZone>> getPrinter(OptionalTimeZoneFormat annotation, Class<?> fieldType) {
		return new OptionalTimeZoneFormatter(annotation.value());
	}

	@Override
	public Parser<Optional<TimeZone>> getParser(OptionalTimeZoneFormat annotation, Class<?> fieldType) {
		return new OptionalTimeZoneFormatter(annotation.value());
	}

}