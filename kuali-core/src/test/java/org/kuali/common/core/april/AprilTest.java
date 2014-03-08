package org.kuali.common.core.april;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.List;

import org.junit.Test;
import org.kuali.common.core.april.model.SaleLines;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;

public class AprilTest {

	private static final Logger logger = newLogger();

	@Test
	public void test() {
		try {
			logger.info(format("hello world"));
			List<String> strings = LocationUtils.readLines("classpath:json/april-01.txt");
			logger.info(format("line count: %s", strings.size()));
			List<SaleLines> lines = getSaleLines(strings);
			logger.info(format("sales: %s", lines.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<SaleLines> getSaleLines(List<String> strings) {
		checkState(strings.size() == 300, "expected exactly 100 sales (3 lines per sale)");
		List<SaleLines> lines = newArrayList();
		for (int i = 0; i < 300; i += 3) {
			lines.add(SaleLines.create(strings, i));
		}
		return lines;
	}
}
