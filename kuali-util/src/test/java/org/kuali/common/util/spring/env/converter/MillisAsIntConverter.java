package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public final class MillisAsIntConverter implements EnvironmentAdapter<String, Integer> {

	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

	@Override
	public Integer convert(String time) {
		return FormatUtils.getMillisAsInt(time);
	}

}
