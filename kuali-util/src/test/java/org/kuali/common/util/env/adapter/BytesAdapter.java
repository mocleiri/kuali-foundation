package org.kuali.common.util.env.adapter;

import org.kuali.common.util.FormatUtils;

public final class BytesAdapter implements EnvAdapter<String, Long> {

	@Override
	public Class<String> getEnvType() {
		return String.class;
	}

	@Override
	public Long convert(String size) {
		return FormatUtils.getBytes(size);
	}

}
