package org.kuali.common.util.encrypt.openssl;

import static com.google.common.base.Stopwatch.createStarted;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.pow;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.codehaus.plexus.util.Base64.decodeBase64;
import static org.kuali.common.util.Str.getAsciiBytes;
import static org.kuali.common.util.encrypt.openssl.OpenSSL.generatePassword;
import static org.kuali.common.util.log.LoggerUtils.logTable;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.text.NumberFormat;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.FormatUtils;
import org.slf4j.Logger;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;

public class OpenSSLTest {

	private static final Logger logger = newLogger();
	private static final NumberFormat NF = getNumberFormat();

	@Test
	public void testGeneratePassword() {
		try {
			Stopwatch sw = createStarted();
			int iterations = 10000;
			int length = 22;
			for (int i = 0; i < iterations; i++) {
				randomAlphanumeric(length);
			}
			long elapsed1 = sw.elapsed(MILLISECONDS);
			for (int i = 0; i < iterations; i++) {
				generatePassword(length);
			}
			long elapsed2 = sw.elapsed(MILLISECONDS);
			info("random=%s secure random=%s", FormatUtils.getTime(elapsed1), FormatUtils.getTime(elapsed2));
			List<MathPow> list = newArrayList();
			list.add(new MathPow("java int", 2, 32));
			list.add(new MathPow("java long", 2, 64));
			list.add(new MathPow("128 bit", 2, 128));
			list.add(new MathPow("alphanumeric", 62, 21));
			list.add(new MathPow("alphanumeric", 62, 22));
			list.add(new MathPow("printable ascii", 95, 19));
			list.add(new MathPow("printable ascii", 95, 20));
			list.add(new MathPow("256 bit", 2, 256));
			list.add(new MathPow("alphanumeric", 62, 42));
			list.add(new MathPow("alphanumeric", 62, 43));
			list.add(new MathPow("printable ascii", 95, 38));
			list.add(new MathPow("printable ascii", 95, 39));
			// AWS uses 20 character access keys composed of uppercase letters (A-Z) and digits (0-9)
			list.add(new MathPow("aws access key", 36, 20));
			// AWS uses 40 character base64 secret keys which equates to 30 bytes which equates to 240 bits
			list.add(new MathPow("aws secret key", 2, 240));
			list.add(new MathPow("atoms on planet earth", 10, 50));
			show(list);
			String password = generatePassword(40);
			byte[] asciiBytes = getAsciiBytes(password);
			byte[] base64Bytes = decodeBase64(asciiBytes);
			info("password.length()=%s", password.length());
			info("asciiBytes.length=%s", asciiBytes.length);
			info("base64Bytes.length=%s", base64Bytes.length);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	protected static void show(List<MathPow> list) {
		List<String> columns = ImmutableList.of("label", "function", "value");
		List<Object[]> rows = newArrayList();
		for (MathPow element : list) {
			String label = element.getLabel();
			String function = format("Math.pow(%s, %s)", element.getBase(), element.getPow());
			String value = NF.format(Math.pow(element.getBase(), element.getPow()));
			Object[] row = { label, function, value };
			rows.add(row);
		}
		logTable(columns, rows);
	}

	protected static void show(double a, double b) {
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
