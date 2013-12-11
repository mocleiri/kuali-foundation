package org.kuali.common.util.spring.env.adapter;

import org.kuali.common.util.FormatUtils;

public class MillisAdapter implements EnvAdapter<Long, String> {

	@Override
	public Long convert(String time) {
		return FormatUtils.getMillis(time);
	}

}
