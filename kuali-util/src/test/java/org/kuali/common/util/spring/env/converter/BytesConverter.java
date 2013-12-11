package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public final class BytesConverter implements Converter<String, Long> {

	@Override
	public Long convert(String size) {
		return FormatUtils.getBytes(size);
	}

}
