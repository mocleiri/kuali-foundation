package org.kuali.common.core.april;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.String.format;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.kuali.common.core.april.model.Area;
import org.kuali.common.core.april.model.DefaultSaleComparator;
import org.kuali.common.core.april.model.Level;
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

import com.google.common.base.Splitter;

public class AprilTest {

	private static final Logger logger = newLogger();
	private final VirtualSystem vs = VirtualSystem.create();
	private final String jsonDir = "json";
	private final String jsonFile = "april.json";
	private final String jsonPath = CLASSPATH_URL_PREFIX + jsonDir + "/" + jsonFile;

	@Test
	public void test() {
		try {
			updateJson("april-01.txt", "april-02.txt");
			List<String> lines = LocationUtils.readLines(jsonPath);
			logger.info(format("lines %s", lines.size()));
			JsonService service = new JacksonJsonService();
			List<Sale> sales = newArrayList();
			for (String line : lines) {
				Sale sale = service.readString(line, Sale.class);
				sales.add(sale);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void updateJson(String... textFiles) {
		List<Sale> sales = newArrayList();
		for (String textFile : textFiles) {
			String location = CLASSPATH_URL_PREFIX + jsonDir + "/" + textFile;
			List<String> strings = LocationUtils.readLines(location);
			logger.info(format("line count: %s", strings.size()));
			List<SaleLines> lines = getSaleLines(strings);
			logger.info(format("sales: %s", lines.size()));
			sales.addAll(getSales(lines));
		}
		Set<Sale> set = newHashSet(sales);
		int duplicates = sales.size() - set.size();
		logger.info(format("duplicates: %s", duplicates));
		JsonService service = new JacksonJsonService(JacksonContext.builder().noPrettyPrint().build());
		List<Sale> unique = newArrayList(set);
		Collections.sort(unique, DefaultSaleComparator.INSTANCE);
		List<String> lines = newArrayList();
		for (Sale sale : reverse(unique)) {
			lines.add(service.writeString(sale));
		}
		File basedir = new File("./src/test/resources");
		File file = new CanonicalFile(basedir, jsonDir + vs.getFileSeparator() + jsonFile);
		logger.info(format("creating -> %s", file));
		try {
			FileUtils.writeLines(file, lines);
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

		List<String> tokens1 = splitter.splitToList(saleLines.getLine1());
		checkState(tokens1.size() == 4, "expected 4 tokens");

		List<String> tokens3 = splitter.splitToList(saleLines.getLine3().replace('\t', separator));
		checkState(tokens3.size() == 3, "expected 3 tokens");

		String section = tokens1.get(tokens1.size() - 1);
		Area area = Area.valueOf(tokens1.get(tokens1.size() - 2).toUpperCase());
		Level level = Level.valueOf(tokens1.get(0).toUpperCase());
		double price = getPrice(saleLines.getLine2().trim());
		int row = Integer.parseInt(tokens3.get(0));
		int quantity = Integer.parseInt(tokens3.get(1));
		long date = getDate(tokens3.get(2));

		Sale.Builder builder = Sale.builder();
		builder.withLevel(level).withArea(area).withSection(section);
		builder.withPrice(price);
		builder.withRow(row).withQuantity(quantity).withDate(date);
		return builder.build();
	}

	protected long getDate(String source) {
		// 03/08/14
		SimpleDateFormat parser = new SimpleDateFormat("MM/dd/yy");
		try {
			return parser.parse(source).getTime();
		} catch (ParseException e) {
			throw illegalState("unexpected parse error", e);
		}
	}

	protected double getPrice(String source) {
		NumberFormat parser = NumberFormat.getCurrencyInstance();
		try {
			return parser.parse(source).doubleValue();
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
