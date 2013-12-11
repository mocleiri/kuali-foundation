package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public class MillisAdapter implements Converter<Long, String> {

	@Override
	public Long convert(String time) {
		return FormatUtils.getMillis(time);
	}

}
