package org.kuali.common.util.env.adapter;

import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class OptionalStringAdapter implements EnvAdapter<String, Optional<String>> {

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
