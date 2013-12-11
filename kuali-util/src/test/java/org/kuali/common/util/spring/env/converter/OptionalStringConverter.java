package org.kuali.common.util.spring.env.converter;

import com.google.common.base.Optional;

public class OptionalStringConverter implements Converter<String, Optional<String>> {

	@Override
	public Optional<String> convert(String s) {
		if (s == null) {
			return Optional.absent();
		} else {
			return Optional.of(s);
		}
	}

}
