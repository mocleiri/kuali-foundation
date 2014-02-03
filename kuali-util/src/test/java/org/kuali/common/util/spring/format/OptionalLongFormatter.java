package org.kuali.common.util.spring.format;

import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.common.base.Optional;

public class OptionalLongFormatter implements Formatter<Optional<Long>> {

	@Override
	public Optional<Long> parse(String number, Locale locale) {
		if (number == null) {
			return Optional.absent();
		} else {
			return Optional.of(Long.parseLong(number));
		}
	}

	@Override
	public String print(Optional<Long> number, Locale locale) {
		if (number.isPresent()) {
			return number.get().toString();
		} else {
			return null;
		}
	}

}
