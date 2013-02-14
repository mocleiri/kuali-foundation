package org.kuali.core.db.torque.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
			values[i] = unformat(values[i]);
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

	public static String unformat(String s) {
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
		clone.setWorkingDir(new File(clone.getWorkingDir() + FS + artifactId));
		clone.setSchemaXmlFile(new File(clone.getWorkingDir() + FS + "schema.xml"));
		return clone;
	}

	public static void prepareFileSystem(List<ImpexContext> contexts) {
		try {
			for (ImpexContext context : contexts) {
				prepareFileSystem(context);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error");
		}
	}

	/**
	 * Working dir must be set before invoking this method
	 */
	public static void prepareFileSystem(ImpexContext context) throws IOException {
		Assert.notNull(context.getWorkingDir(), "workingDir is null");
		// The Texen ant task requires these 2 files to be present or the parsing of schema.xml will fail
		createReportFile(context);
		createContextPropertiesFile(context);
	}

	protected static void createReportFile(ImpexContext context) throws IOException {
		String relativePath = "../reports/" + context.getArtifactId() + "/context.datadtd.generation";
		String absolutePath = context.getWorkingDir() + FS + relativePath;
		File file = new File(absolutePath);
		String canonicalPath = LocationUtils.getCanonicalPath(file);
		File canonicalFile = new File(canonicalPath);
		logger.debug("Create file  [{}]", canonicalPath);
		FileUtils.touch(canonicalFile);
		context.setReportFile(relativePath);
	}

	protected static void createContextPropertiesFile(ImpexContext context) {
		String path = context.getWorkingDir() + "/../reports/" + context.getArtifactId() + "/context.datadtd.properties";
		String canonicalPath = LocationUtils.getCanonicalPath(new File(path));
		File file = new File(canonicalPath);
		context.setContextProperties(file);

		Properties properties = new Properties();
		properties.setProperty("project", "impex");
		properties.setProperty("version", "2.0");
		PropertyUtils.store(properties, context.getContextProperties());
	}

}
