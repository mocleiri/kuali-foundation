package org.kuali.common.util.spring.format;

import static com.google.common.base.Optional.absent;

import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.common.base.Optional;

public final class OptionalStringFormatter implements Formatter<Optional<String>> {

	private static final String MAGIC_ABSENT_TOKEN = "${optional.absent}";

	@Override
	public String print(Optional<String> string, Locale locale) {
		if (!string.isPresent()) {
			return MAGIC_ABSENT_TOKEN;
		} else {
			return string.get();
		}
	}

	@Override
	public Optional<String> parse(String text, Locale locale) {
		if (MAGIC_ABSENT_TOKEN.equals(text)) {
			return absent();
		} else {
			return Optional.of(text);
		}
	}

}
