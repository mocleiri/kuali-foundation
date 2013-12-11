package org.kuali.common.util.spring.env.adapter;

import org.kuali.common.util.FormatUtils;

public class MillisAsIntAdapter implements EnvAdapter<Integer, String> {

	@Override
	public Integer convert(String time) {
		return FormatUtils.getMillisAsInt(time);
	}

}
