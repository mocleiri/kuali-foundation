package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public final class MillisConverter implements Converter<String, Long> {

	@Override
	public Long convert(String time) {
		return FormatUtils.getMillis(time);
	}

}
