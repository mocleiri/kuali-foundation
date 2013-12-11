package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public class MillisAsIntConverter implements Converter<String, Integer> {

	@Override
	public Integer convert(String time) {
		return FormatUtils.getMillisAsInt(time);
	}

}
