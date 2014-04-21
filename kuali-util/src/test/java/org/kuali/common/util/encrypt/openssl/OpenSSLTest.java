package org.kuali.common.util.encrypt.openssl;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.pow;
import static java.lang.String.format;
import static org.kuali.common.util.log.LoggerUtils.logTable;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.text.NumberFormat;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;

public class OpenSSLTest {

	// 62^18
	// 183,252,712,161,030,000,000,000,000,000,000
	// 9,223,372,036,854,775,807

	private static final Logger logger = newLogger();
	private static final NumberFormat NF = getNumberFormat();

	@Test
	public void testGeneratePassword() {
		List<MathPow> list = newArrayList();
		list.add(new MathPow(2, 128));
		list.add(new MathPow(62, 21));
		list.add(new MathPow(62, 22));
		list.add(new MathPow(95, 19));
		list.add(new MathPow(95, 20));
		show(list);
	}

	protected static void show(List<MathPow> list) {
		List<String> columns = ImmutableList.of("function", "value");
		List<Object[]> rows = newArrayList();
		for (MathPow element : list) {
			String function = String.format("Math.pow(%s, %s)", element.getBase(), element.getPow());
			String value = NF.format(Math.pow(element.getBase(), element.getPow()));
			Object[] row = { function, value };
			rows.add(row);
		}
		logTable(columns, rows);
	}

	protected static void show(int a, int b) {
		info("Math.pow(%s, %s) = %s", a, b, NF.format(pow(a, b)));
	}

	protected static NumberFormat getNumberFormat() {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		nf.setGroupingUsed(true);
		return nf;
	}

	protected static void info(String msg, Object... args) {
		if (args == null) {
			logger.info(msg);
		} else {
			logger.info(format(msg, args));
		}
	}
}
