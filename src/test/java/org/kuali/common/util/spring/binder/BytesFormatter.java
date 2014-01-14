package org.kuali.common.util.spring.binder;

import java.text.ParseException;
import java.util.Locale;

import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.primitives.Numbers;
import org.springframework.format.Formatter;

public final class BytesFormatter implements Formatter<Number> {

	public BytesFormatter() {
		this(true);
	}

	public BytesFormatter(boolean printDecimalDigits) {
		this.printDecimalDigits = printDecimalDigits;
	}

	public boolean isPrintDecimalDigits() {
		return printDecimalDigits;
	}

	private final boolean printDecimalDigits;

	@Override
	public Number parse(String size, Locale locale) throws ParseException {
		try {
			return Numbers.narrow(FormatUtils.getBytes(size));
		} catch (Exception e) {
			throw new ParseException("Unexpected parse error: [" + e.getMessage() + "]", -1);
		}
	}

	@Override
	public String print(Number number, Locale locale) {
		if (printDecimalDigits) {
			return FormatUtils.getSize(number.longValue());
		} else {
			return FormatUtils.getIntegerSize(number.longValue());
		}
	}

}
