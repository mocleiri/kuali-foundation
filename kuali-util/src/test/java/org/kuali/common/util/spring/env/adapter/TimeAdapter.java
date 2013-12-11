package org.kuali.common.util.spring.env.adapter;

import org.kuali.common.util.FormatUtils;

public class TimeAdapter implements EnvAdapter<Long, String> {

	@Override
	public Long convert(String time) {
		return FormatUtils.getMillis(time);
	}

}
