package org.kuali.common.util.spring.format.optional;

import java.util.Locale;

import com.google.common.base.Optional;

public final class OptionalStringFormatter extends AbstractOptionalFormatter<String> {

	public OptionalStringFormatter(String absentToken) {
		super(absentToken);
	}

	@Override
	public Optional<String> getOptional(String text, Locale locale) {
		return Optional.of(text);
	}

}
