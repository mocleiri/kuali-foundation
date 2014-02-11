package org.kuali.common.util.spring.format;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.common.base.Optional;

public final class OptionalStringFormatter implements Formatter<Optional<String>> {

	public OptionalStringFormatter(String absentToken) {
		this.absentToken = checkNotBlank(absentToken, "absentToken");
	}

	private final String absentToken;

	@Override
	public String print(Optional<String> string, Locale locale) {
		if (!string.isPresent()) {
			return absentToken;
		} else {
			return string.get();
		}
	}

	@Override
	public Optional<String> parse(String text, Locale locale) {
		if (absentToken.equals(text)) {
			return absent();
		} else {
			return Optional.of(text);
		}
	}

}
