package org.kuali.common.core.april;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.apache.commons.io.FileUtils.writeLines;
import static org.kuali.common.util.LocationUtils.readLines;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.log.Loggers.newLogger;
import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class AprilTest {

	private static final Logger logger = newLogger();
	private final VirtualSystem vs = VirtualSystem.create();
	private final File basedir = new CanonicalFile("./src/test/resources");
	private final String jsonDir = "json";
	private final String jsonFilename = "april.json";
	private final String csvFilename = "april.txt";
	private final File jsonFile = new CanonicalFile(basedir, jsonDir + vs.getFileSeparator() + jsonFilename);
	private final File csvFile = new CanonicalFile(basedir, jsonDir + vs.getFileSeparator() + csvFilename);
	private final Joiner csvJoiner = Joiner.on(',');
	private static final NumberFormat numberFormat = NumberFormat.getInstance();
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd");
	static {
		numberFormat.setMaximumFractionDigits(0);
		numberFormat.setMinimumFractionDigits(0);
		numberFormat.setGroupingUsed(false);
	}

	@Test
	public void test() {
		try {
			updateJson("01", "02", "03", "04", "05", "06", "07");
			List<String> lines = readLines(jsonFile);
			logger.info(format("lines %s", lines.size()));
			JsonService service = new JacksonJsonService();
			List<Sale> sales = newArrayList();
			for (String line : lines) {
				Sale sale = service.readString(line, Sale.class);
				sales.add(sale);
			}
			List<String> csv = toCSV(sales);
			logger.info(format("creating -> %s", csvFile));
			writeLines(csvFile, csv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<String> toCSV(List<Sale> sales) {
		List<String> lines = newArrayList();
		lines.add(csvJoiner.join("date", "level", "area", "section", "row", "price", "quantity"));
		for (Sale sale : sales) {
			lines.add(toCSV(sale));
		}
		return lines;
	}

	protected String toCSV(Sale sale) {
		List<String> tokens = newArrayList();
		tokens.add(sdf.format(new Date(sale.getDate())));
		tokens.add(sale.getLevel().toString());
		tokens.add(sale.getArea().toString());
		tokens.add(sale.getSection().toString());
		tokens.add(sale.getRow() + "");
		tokens.add(numberFormat.format(sale.getPrice()));
		tokens.add(sale.getQuantity() + "");
		return csvJoiner.join(tokens);
	}

	protected void updateJson(String... numbers) {
		List<Sale> sales = newArrayList();
		for (String number : numbers) {
			String location = CLASSPATH_URL_PREFIX + jsonDir + "/april-" + number + ".txt";
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
		logger.info(format("creating -> %s", jsonFile));
		try {
			writeLines(jsonFile, lines);
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

		String line1 = saleLines.getLine1().replace("Ring Of Honor", "RingOfHonor Level").replace("- No Alcohol", "").replace("Goal Line", "GoalLine");
		List<String> tokens1 = splitter.splitToList(line1);
		checkState(tokens1.size() == 4, "expected 4 tokens");

		List<String> tokens3 = splitter.splitToList(saleLines.getLine3().replace('\t', separator));
		checkState(tokens3.size() == 3, "expected 3 tokens");

		String section = tokens1.get(tokens1.size() - 1);
		Area area = Area.valueOf(tokens1.get(tokens1.size() - 2).toUpperCase());
		Level level = Level.valueOf(tokens1.get(0).toUpperCase());
		double price = getPrice(saleLines.getLine2().trim());
		String row = tokens3.get(0);
		int quantity = parseInt(tokens3.get(1));
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
