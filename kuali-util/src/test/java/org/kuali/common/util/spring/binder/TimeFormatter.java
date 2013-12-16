package org.kuali.common.util.spring.binder;

import java.text.ParseException;
import java.util.Locale;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.primitives.Numbers;
import org.springframework.format.Formatter;

public final class TimeFormatter implements Formatter<Number> {

	@Override
	public Number parse(String time, Locale locale) throws ParseException {
		try {
			return Numbers.narrow(FormatUtils.getMillis(time));
		} catch (Exception e) {
			throw new ParseException("Unexpected parse error: [" + e.getMessage() + "]", -1);
		}
	}

	@Override
	public String print(Number millis, Locale locale) {
		return FormatUtils.getTime(millis.longValue());
	}

}
