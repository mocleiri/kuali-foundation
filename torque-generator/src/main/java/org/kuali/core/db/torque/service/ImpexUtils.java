package org.kuali.core.db.torque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.SchemaType;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.core.db.torque.pojo.DumpTableResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class ImpexUtils {

	private static final Logger logger = LoggerFactory.getLogger(ImpexUtils.class);

	private static final String FS = File.separator;
	private static final String QUOTE = "\"";
	private static final String SPLIT_TOKEN = QUOTE + "," + QUOTE;
	private static final SchemaType[] COLUMN_DATE_TYPES = { SchemaType.DATE, SchemaType.TIMESTAMP };

	/**
	 * Split the line up into individual values and remove any .mpx related formatting
	 */
	public static String[] getOriginalValues(String line) {
		// Remove trailing/leading quotes
		String trimmed = trimQuotes(line);
		// Split the line up into individual values
		String[] values = getValues(trimmed);
		// Convert ${mpx.lf} -> \n
		for (int i = 0; i < values.length; i++) {
			values[i] = parse(values[i]);
		}
		// These are the original string values with all of the .mpx related formatting removed
		return values;
	}

	public static String[] getValues(String line) {
		return StringUtils.splitByWholeSeparator(line, SPLIT_TOKEN);
	}

	/**
	 * Remove leading and trailing quotes (if any)
	 */
	public static String trimQuotes(String line) {
		if (StringUtils.startsWith(line, QUOTE)) {
			line = StringUtils.substring(line, QUOTE.length());
		}
		int length = line.length();
		if (StringUtils.endsWith(line, QUOTE)) {
			line = StringUtils.substring(line, 0, length - QUOTE.length());
		}
		return line;
	}

	public static boolean isHeaderLine(String line) {
		return !StringUtils.isBlank(line) && !StringUtils.startsWith(line, QUOTE);
	}

	public static String parse(String s) {
		if (StringUtils.equals(s, "${mpx.null}")) {
			return null;
		}
		String converted = StringUtils.replace(s, "${mpx.cr}", "\r");
		converted = StringUtils.replace(converted, "${mpx.lf}", "\n");
		converted = StringUtils.replace(converted, "${mpx.quote}", "\"");
		return converted;
	}

	public static String format(String s) {
		if (s == null) {
			return "${mpx.null}";
		}
		String converted = StringUtils.replace(s, "\r", "${mpx.cr}");
		converted = StringUtils.replace(converted, "\n", "${mpx.lf}");
		converted = StringUtils.replace(converted, "\"", "${mpx.quote}");
		return converted;
	}

	public static void updateAndStoreDatabaseProperties(Properties properties, String location, List<DumpTableResult> results) {
		for (DumpTableResult result : results) {
			String sizeKey = result.getTable().getName() + ".size";
			String sizeValue = result.getSize() + "";
			String rowKey = result.getTable().getName() + ".rows";
			String rowValue = result.getRows() + "";
			properties.setProperty(sizeKey.toLowerCase(), sizeValue);
			properties.setProperty(rowKey.toLowerCase(), rowValue);
		}
		PropertyUtils.store(properties, new File(location));
	}

	public static void doStats(List<DumpTableResult> results) {
		long totalTime = 0;
		long wallTimeStart = Long.MAX_VALUE;
		long wallTimeStop = Long.MIN_VALUE;
		long totalSize = 0;
		long totalRows = 0;
		for (DumpTableResult result : results) {
			totalTime += result.getElapsed();
			totalSize += result.getSize();
			totalRows += result.getRows();
			wallTimeStart = Math.min(wallTimeStart, result.getStart());
			wallTimeStop = Math.max(wallTimeStop, result.getFinish());
		}
		long wallTimeElapsed = wallTimeStop - wallTimeStart;
		String rows = FormatUtils.getCount(totalRows);
		String size = FormatUtils.getSize(totalSize);
		String time = FormatUtils.getTime(wallTimeElapsed);
		String rate = FormatUtils.getRate(wallTimeElapsed, totalSize);

		// This is the amount of time saved by running multiple threads
		String threadsEffect = FormatUtils.getTime(totalTime - wallTimeElapsed);
		Object[] args = { rows, size, time, rate, threadsEffect };
		logger.info("Dump Summary - Rows: {}  Size: {}  Time: {}  Rate: {}  Threads effect: {}", args);
	}

	public static ImpexContext clone(ImpexContext source) {
		ImpexContext target = new ImpexContext();
		BeanUtils.copyProperties(source, target);
		return target;
	}

	public static ImpexContext clone(ImpexContext source, String include, String artifactId) throws IOException {
		ImpexContext clone = clone(source);
		clone.setTableIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setViewIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setSequenceIncludes(CollectionUtils.getTrimmedListFromCSV(include));
		clone.setArtifactId(artifactId);
		clone.setSchemaXmlFile(new File(clone.getWorkingDir() + FS + artifactId + ".xml"));
		return clone;
	}

	public static void prepareFileSystem(List<ImpexContext> contexts, List<String> databaseVendors) {
		try {
			for (ImpexContext context : contexts) {
				prepareFileSystem(context, databaseVendors);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		}
	}

	/**
	 * Working dir must be set before invoking this method
	 */
	public static void prepareFileSystem(ImpexContext context, List<String> databaseVendors) throws IOException {
		Assert.notNull(context.getWorkingDir(), "workingDir is null");
		// The Texen ant task requires these 2 files to be present before TorqueDataModelTask executes
		createReportFiles(context, databaseVendors);
		createContextPropertiesFiles(context, databaseVendors);
	}

	protected static void createReportFiles(ImpexContext context, List<String> databaseVendors) throws IOException {
		for (String databaseVendor : databaseVendors) {
			createReportFile(context, databaseVendor);
		}
	}

	public static ReportFile getReportFile(ImpexContext context, String databaseVendor) {
		String relativePath = ".." + FS + "reports" + FS + databaseVendor + FS + context.getArtifactId() + "-context.generation";
		String absolutePath = context.getWorkingDir() + FS + relativePath;
		File file = new File(absolutePath);
		String canonicalPath = LocationUtils.getCanonicalPath(file);
		File canonicalFile = new File(canonicalPath);

		ReportFile rf = new ReportFile();
		rf.setBaseDir(context.getWorkingDir());
		rf.setRelativePath(relativePath);
		rf.setActualFile(canonicalFile);
		return rf;
	}

	public static void createReportFile(ImpexContext context, String databaseVendor) throws IOException {
		ReportFile rf = getReportFile(context, databaseVendor);
		logger.info("Create file  [{}]", rf.getActualFile());
		FileUtils.touch(rf.getActualFile());
	}

	protected static void createContextPropertiesFiles(ImpexContext context, List<String> databaseVendors) {
		for (String databaseVendor : databaseVendors) {
			createContextPropertiesFile(context, databaseVendor);
		}
	}

	public static File getContextPropertiesFile(ImpexContext context, String databaseVendor) {
		String path = context.getWorkingDir() + "/../reports" + FS + databaseVendor + FS + context.getArtifactId() + "-context.properties";
		String canonicalPath = LocationUtils.getCanonicalPath(new File(path));
		return new File(canonicalPath);
	}

	public static void createContextPropertiesFile(ImpexContext context, String databaseVendor) {
		File file = getContextPropertiesFile(context, databaseVendor);
		Properties properties = new Properties();
		properties.setProperty("project", "impex");
		properties.setProperty("version", "2.0");
		PropertyUtils.store(properties, file);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Gets the parameterized version of the columns list from a @Table
	 *
	 * @return the List&lt;Column&gt; of columns from the table
	 */
	public static List<Column> getColumns(Table table) {
		return table.getColumns();
	}

	public static boolean isColumnDateType(Column column) {
		SchemaType columnType = getColumnType(column);

		boolean result = false;
		for (SchemaType dateType : COLUMN_DATE_TYPES) {
			if (dateType.equals(columnType)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static SchemaType getColumnType(Column column) {
		return SchemaType.getEnum((String) column.getTorqueType());
	}
}
