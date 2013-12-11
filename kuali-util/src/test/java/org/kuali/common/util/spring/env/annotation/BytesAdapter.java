package org.kuali.common.util.spring.env.annotation;

import org.kuali.common.util.FormatUtils;

public class BytesAdapter implements EnvAdapter<Long, String> {

	@Override
	public Long convert(String size) {
		return FormatUtils.getBytes(size);
	}

}
