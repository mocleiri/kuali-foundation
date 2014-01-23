package org.kuali.common.util.spring.format;

import java.util.Locale;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.primitives.Numbers;
import org.springframework.format.Formatter;

public final class TimeFormatter implements Formatter<Number> {

	@Override
	public Number parse(String time, Locale locale) {
		return Numbers.narrow(FormatUtils.getMillis(time));
	}

	@Override
	public String print(Number millis, Locale locale) {
		return FormatUtils.getTime(millis.longValue());
	}

}
