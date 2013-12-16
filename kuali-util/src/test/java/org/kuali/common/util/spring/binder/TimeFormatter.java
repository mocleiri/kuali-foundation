package org.kuali.common.util.spring.binder;

import java.text.ParseException;
import java.util.Locale;

import org.kuali.common.util.FormatUtils;
import org.springframework.format.Formatter;

public final class TimeFormatter implements Formatter<Number> {

	public TimeFormatter() {
		this(false);
	}

	public TimeFormatter(boolean parseToInteger) {
		this.parseToInteger = parseToInteger;
	}

	public boolean isPrintDecimalDigits() {
		return parseToInteger;
	}

	private final boolean parseToInteger;

	@Override
	public Number parse(String time, Locale locale) throws ParseException {
		try {
			if (parseToInteger) {
				return FormatUtils.getMillisAsInt(time);
			} else {
				return FormatUtils.getMillis(time);
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
