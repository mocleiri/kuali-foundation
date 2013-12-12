package org.kuali.common.util.env.adapter;

import org.kuali.common.util.FormatUtils;

public final class SecondsAdapter implements EnvironmentAdapter<String, Integer> {

	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

	@Override
	public Integer convert(String time) {
		long millis = FormatUtils.getMillis(time);
		long seconds = millis / 1000;
		return new Long(seconds).intValue();
	}

}
