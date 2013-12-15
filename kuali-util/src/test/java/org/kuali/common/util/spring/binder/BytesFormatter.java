package org.kuali.common.util.spring.binder;

import java.text.ParseException;
import java.util.Locale;

import org.kuali.common.util.FormatUtils;
import org.springframework.format.Formatter;

public final class BytesFormatter implements Formatter<Number> {

	@Override
	public Number parse(String size, Locale locale) throws ParseException {
		try {
			return FormatUtils.getBytes(size);
		} catch (Exception e) {
			throw new ParseException("Unexpected parse error: [" + e.getMessage() + "]", -1);
		}
	}

	@Override
	public String print(Number number, Locale locale) {
		return FormatUtils.getSize(number.longValue());
	}

}
