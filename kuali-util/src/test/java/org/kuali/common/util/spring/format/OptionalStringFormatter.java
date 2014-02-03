package org.kuali.common.util.spring.format;

import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.common.base.Optional;

public class OptionalStringFormatter implements Formatter<Optional<String>> {

	@Override
	public Optional<String> parse(String text, Locale locale) {
		return Optional.fromNullable(text);
	}

	@Override
	public String print(Optional<String> string, Locale locale) {
		return string.orNull();
	}

}
