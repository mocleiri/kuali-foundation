package org.kuali.common.util.env.adapter;

import org.kuali.common.util.FormatUtils;

public final class BytesAdapter implements Adapter<String, Long> {

	@Override
	public Class<String> getSourceType() {
		return String.class;
	}

	@Override
	public Long convert(String size) {
		return FormatUtils.getBytes(size);
	}

}
