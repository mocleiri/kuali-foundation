package org.kuali.common.core.april;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.core.april.model.Sale;
import org.kuali.common.core.april.model.SaleLines;
import org.kuali.common.core.json.api.JsonService;
import org.kuali.common.core.json.jackson.JacksonContext;
import org.kuali.common.core.json.jackson.JacksonJsonService;
import org.kuali.common.core.system.VirtualSystem;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.base.Exceptions;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;
import org.springframework.util.ResourceUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class AprilTest {

	private static final Logger logger = newLogger();
	private final String jsonDir = "json";
	private final String jsonFile = "april.json";

	@Test
	public void test() {
		try {
			updateJson("april-01.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateJson(String... textFiles) {
		VirtualSystem vs = VirtualSystem.create();
		List<Sale> sales = newArrayList();
		for (String textFile : textFiles) {
			String location = ResourceUtils.CLASSPATH_URL_PREFIX + jsonDir + vs.getFileSeparator() + textFile;
			List<String> strings = LocationUtils.readLines(location);
			logger.info(format("line count: %s", strings.size()));
			List<SaleLines> lines = getSaleLines(strings);
			logger.info(format("sales: %s", lines.size()));
			sales.addAll(getSales(lines));
		}
		Collections.sort(sales);
		List<Sale> reversed = Lists.reverse(sales);
		JsonService service = new JacksonJsonService(JacksonContext.builder().noPrettyPrint().build());
		StringBuilder sb = new StringBuilder();
		for (Sale sale : reversed) {
			sb.append(service.writeString(sale));
			sb.append(vs.getLineSeparator());
		}
		File basedir = new File("./src/test/resources");
		File file = new CanonicalFile(basedir, jsonDir + vs.getFileSeparator() + jsonFile);
		logger.info(format("creating -> %s", file));
		try {
			FileUtils.write(file, sb.toString());
		} catch (IOException e) {
			throw Exceptions.illegalState(e);
		}
	}

	protected List<Sale> getSales(List<SaleLines> list) {
		List<Sale> sales = newArrayList();
		for (SaleLines element : list) {
			Sale sale = getSale(element);
			sales.add(sale);
		}
		return sales;
	}

	protected Sale getSale(SaleLines saleLines) {
		char separator = ' ';
		Splitter splitter = Splitter.on(separator).trimResults().omitEmptyStrings();
		Joiner joiner = Joiner.on(separator);

		List<String> tokens1 = splitter.splitToList(saleLines.getLine1());
		checkState(tokens1.size() == 4, "expected 4 tokens");

		List<String> tokens3 = splitter.splitToList(saleLines.getLine3().replace('\t', separator));
		checkState(tokens3.size() == 3, "expected 3 tokens");

		String section = tokens1.get(tokens1.size() - 1);
		String area = tokens1.get(tokens1.size() - 2);
		String level = joiner.join(ImmutableList.of(tokens1.get(0), tokens1.get(1)));
		double price = getPrice(saleLines.getLine2().trim());
		int row = Integer.parseInt(tokens3.get(0));
		int quantity = Integer.parseInt(tokens3.get(1));
		Date date = getDate(tokens3.get(2));

		Sale.Builder builder = Sale.builder();
		builder.withLevel(level).withArea(area).withSection(section);
		builder.withPrice(price);
		builder.withRow(row).withQuantity(quantity).withDate(date.getTime());
		return builder.build();
	}

	protected Date getDate(String source) {
		// 03/08/14
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			throw illegalState("unexpected parse error", e);
		}
	}

	protected double getPrice(String source) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		try {
			Number number = nf.parse(source);
			return number.doubleValue();
		} catch (ParseException e) {
			throw illegalState("unexpected parse error", e);
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
