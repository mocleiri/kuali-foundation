package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class OptionalStringAdapter implements EnvironmentAdapter<String, Optional<String>> {

	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

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
