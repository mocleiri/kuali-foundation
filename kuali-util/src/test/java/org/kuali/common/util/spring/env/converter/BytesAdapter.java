package org.kuali.common.util.spring.env.converter;

import org.kuali.common.util.FormatUtils;

public class BytesAdapter implements Converter<Long, String> {

	@Override
	public Long convert(String size) {
		return FormatUtils.getBytes(size);
	}

}
