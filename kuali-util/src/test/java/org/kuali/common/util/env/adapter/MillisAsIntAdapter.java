package org.kuali.common.util.env.adapter;

import org.kuali.common.util.FormatUtils;

public final class MillisAsIntAdapter implements Adapter<String, Integer> {

	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

	@Override
	public Integer convert(String time) {
		return FormatUtils.getMillisAsInt(time);
	}

}
