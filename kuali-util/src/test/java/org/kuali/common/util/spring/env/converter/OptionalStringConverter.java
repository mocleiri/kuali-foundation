package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class OptionalStringConverter implements Converter<String, Optional<String>> {

	@Override
	public Optional<String> convert(String s) {
		String trimmed = NullUtils.trimToNull(s);
		if (trimmed == null) {
			return Optional.absent();
		} else {
			return Optional.of(s);
		}
	}

}
