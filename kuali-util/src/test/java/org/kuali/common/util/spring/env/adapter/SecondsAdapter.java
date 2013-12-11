package org.kuali.common.util.spring.env.adapter;

import org.kuali.common.util.FormatUtils;

public class SecondsAdapter implements EnvAdapter<Integer, String> {

	@Override
	public Integer convert(String time) {
		long millis = FormatUtils.getMillis(time);
		long seconds = millis / 1000;
		return new Long(seconds).intValue();
	}

}
