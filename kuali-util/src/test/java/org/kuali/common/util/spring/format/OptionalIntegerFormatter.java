package org.kuali.common.util.spring.format;

import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.common.base.Optional;

public class OptionalIntegerFormatter implements Formatter<Optional<Integer>> {

	@Override
	public Optional<Integer> parse(String number, Locale locale) {
		if (number == null) {
			return Optional.absent();
		} else {
			return Optional.of(Integer.parseInt(number));
		}
	}

	@Override
	public String print(Optional<Integer> number, Locale locale) {
		if (number.isPresent()) {
			return number.get().toString();
		} else {
			return null;
		}
	}

}
