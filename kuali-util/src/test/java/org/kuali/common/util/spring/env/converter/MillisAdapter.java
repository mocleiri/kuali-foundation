package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public final class MillisAdapter implements EnvironmentAdapter<String, Long> {

	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

	@Override
	public Long convert(String time) {
		return FormatUtils.getMillis(time);
	}

}
