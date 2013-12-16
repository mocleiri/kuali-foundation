package org.kuali.common.util.spring.binder;

import java.text.ParseException;
import java.util.Locale;

import org.kuali.common.util.FormatUtils;
import org.springframework.format.Formatter;

public final class TimeFormatter implements Formatter<Number> {

	@Override
	public Number parse(String time, Locale locale) throws ParseException {
		try {
			Long millis = FormatUtils.getMillis(time);
			if (millis >= Integer.MIN_VALUE && millis <= Integer.MAX_VALUE) {
				return millis.intValue();
			} else {
				return millis;
			}
		} catch (Exception e) {
			throw new ParseException("Unexpected parse error: [" + e.getMessage() + "]", -1);
		}
	}

	@Override
	public String print(Number millis, Locale locale) {
		return FormatUtils.getTime(millis.longValue());
	}

}
