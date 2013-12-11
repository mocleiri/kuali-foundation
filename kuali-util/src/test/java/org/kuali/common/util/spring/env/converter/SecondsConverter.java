package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public class SecondsConverter implements Converter<Integer, String> {

	@Override
	public Integer convert(String time) {
		long millis = FormatUtils.getMillis(time);
		long seconds = millis / 1000;
		return new Long(seconds).intValue();
	}

}
