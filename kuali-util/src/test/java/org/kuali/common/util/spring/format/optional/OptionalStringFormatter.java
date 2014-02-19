package org.kuali.common.util.spring.format.optional;

import static com.google.common.base.Optional.absent;

import java.util.Locale;

import com.google.common.base.Optional;

public final class OptionalStringFormatter extends AbstractOptionalFormatter<String> {

	public OptionalStringFormatter(String absentToken) {
		super(absentToken);
	}

	@Override
	public Optional<String> parse(String text, Locale locale) {
		if (getAbsentToken().equals(text)) {
			return absent();
		} else {
			return Optional.of(text);
		}
	}

}
