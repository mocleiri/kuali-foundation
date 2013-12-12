package org.kuali.common.util.env.adapter;

import org.kuali.common.util.FormatUtils;

public final class SecondsAdapter implements EnvAdapter<String, Integer> {

	@Override
	public Class<String> getEnvType() {
		return String.class;
	}

	@Override
	public Integer convert(String time) {
		long millis = FormatUtils.getMillis(time);
		long seconds = millis / 1000;
		return new Long(seconds).intValue();
	}

}
