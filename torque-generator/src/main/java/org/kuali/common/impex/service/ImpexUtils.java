package org.kuali.common.impex.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.EngineException;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.SchemaType;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.impex.DumpTableResult;
import org.kuali.common.impex.KualiDatabase;
import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ExecutionStatistics;
import org.kuali.common.threads.ThreadHandlerContext;
import org.kuali.common.threads.ThreadInvoker;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.SimpleScanner;
import org.kuali.common.util.StringFilter;
import org.kuali.common.util.SyncRequest;
import org.kuali.common.util.SyncResult;
import org.kuali.core.db.torque.KualiXmlToAppData;
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

	public static KualiDatabase getDatabase(String vendor, String location) {
		KualiXmlToAppData parser = new KualiXmlToAppData(vendor);
		try {
			return parser.parseResource(location);
		} catch (EngineException e) {
			throw new IllegalStateException(e);
		}
	}

	public static List<SyncResult> syncFiles(List<ImpexContext> contexts) {
		logger.info("Syncing {} contexts", contexts.size());
		List<SyncRequest> requests = getSyncRequests(contexts);
		try {
			return FileSystemUtils.syncFiles(requests);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	protected static List<SyncRequest> getSyncRequests(List<ImpexContext> contexts) {
		List<SyncRequest> requests = new ArrayList<SyncRequest>();
		for (ImpexContext context : contexts) {
			SyncRequest request = getSyncRequest(context);
			requests.add(request);
		}
		return requests;
	}

	protected static SyncRequest getSyncRequest(ImpexContext context) {
		File workingDir = context.getWorkingDir();
		File finalDir = context.getFinalDirectory();
		List<String> includes = context.getTableIncludes();
		String schemaIncludes = context.getSchemaFileInclude();
		logger.info("Working Dir     - {}", LocationUtils.getCanonicalPath(workingDir));
		logger.info("Final Dir       - {}", LocationUtils.getCanonicalPath(finalDir));
		logger.info("Table includes  - {}", CollectionUtils.getSpaceSeparatedString(includes));
		logger.info("Schema includes - {}", schemaIncludes);

		List<File> schemaFiles = getSchemaFiles(workingDir, schemaIncludes);
		List<File> dataFiles = getDataFiles(workingDir, context.getTableIncludes(), context.getFileExtensionInclude());
		logger.info("Schema files    - {}", schemaFiles.size());

		List<File> srcFiles = new ArrayList<File>();
		srcFiles.addAll(schemaFiles);
		if (context.isCopyDataFiles()) {
			srcFiles.addAll(dataFiles);
			logger.info("Data files      - {}", dataFiles.size());
		} else {
			logger.info("Copy data files - {}", context.isCopyDataFiles());
		}

		SyncRequest request = new SyncRequest();
		request.setDstDir(context.getFinalDirectory());
		request.setSrcDir(context.getWorkingDir());
		request.setSrcFiles(srcFiles);
		return request;
	}

	protected static List<File> getDataFiles(File workingDir, List<String> includes, String fileExtensionInclude) {
		SimpleScanner scanner = new SimpleScanner(workingDir, Arrays.asList(fileExtensionInclude), null);
		List<File> allImpexFiles = scanner.getFiles();
		logger.info("All impex files - {}", allImpexFiles.size());
		StringFilter filter = StringFilter.getInstance(includes, null);
		List<String> filenames = getFilenames(allImpexFiles);
		List<String> filtered = getFilteredFilenames(filter, filenames);
		List<File> filteredFiles = new ArrayList<File>();
		for (File file : allImpexFiles) {
			String filename = file.getName();
			boolean contains = filtered.contains(filename);
			if (contains) {
				filteredFiles.add(file);
			}
		}
		return filteredFiles;
	}

	protected static List<String> getFilteredFilenames(StringFilter filter, List<String> filenames) {
		List<String> filtered = new ArrayList<String>();
		for (String filename : filenames) {
			boolean include = filter.include(filename);
			if (include) {
				filtered.add(filename);
			}
		}
		return filtered;
	}

	protected static List<String> getFilenames(List<File> files) {
		List<String> filenames = new ArrayList<String>();
		for (File file : files) {
			String filename = file.getName();
			filenames.add(filename);
		}
		return filenames;
	}

	protected static List<File> getSchemaFiles(File workingDir, String includes) {
		SimpleScanner scanner = new SimpleScanner(workingDir, Arrays.asList(includes), null);
		return scanner.getFiles();
	}

	public static void log(ImpexContext context) {
		logger.info("---------------------------------------------------------------");
		logger.info("Impex Context Properties");
		logger.info("---------------------------------------------------------------");
		logger.info("Database Vendor - {}", context.getDatabaseVendor());
		logger.info("Url - {}", context.getUrl());
		logger.info("Schema - {}", context.getSchema());
		logger.info("Username - {}", context.getUsername());
		logger.info("Password - {}", LoggerUtils.getPassword(context.getUsername(), context.getPassword()));
		logger.info("Driver - {}", context.getDriver());
		logger.info("Table Includes - {}", CollectionUtils.getSpaceSeparatedString(context.getTableIncludes()));
		logger.info("Table Excludes - {}", CollectionUtils.getSpaceSeparatedString(context.getTableExcludes()));
		logger.info("View Includes - {}", CollectionUtils.getSpaceSeparatedString(context.getViewIncludes()));
		logger.info("View Excludes - {}", CollectionUtils.getSpaceSeparatedString(context.getViewExcludes()));
		logger.info("Sequence Includes - {}", CollectionUtils.getSpaceSeparatedString(context.getSequenceIncludes()));
		logger.info("Sequence Excludes - {}", CollectionUtils.getSpaceSeparatedString(context.getSequenceExcludes()));
		logger.info("Working Directory - {}", LocationUtils.getCanonicalPath(context.getWorkingDir()));
		logger.info("---------------------------------------------------------------");
	}

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
		Properties properties = getVelocityProperties();
		PropertyUtils.store(properties, file);
	}

	public static Properties getVelocityProperties() {
		Properties properties = new Properties();
		properties.setProperty("project", "impex");
		properties.setProperty("version", "2.0");
		return properties;
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

	public static ImpexContext getImpexContext(Properties p) {
		ImpexContext context = new ImpexContext();
		// simple property copying
		context.setArtifactId(p.getProperty("project.artifactId"));
		context.setSchema(p.getProperty("impex.schema"));
		context.setDriver(p.getProperty("impex.driver"));
		context.setUrl(p.getProperty("impex.url"));
		context.setUsername(p.getProperty("impex.username"));
		context.setPassword(p.getProperty("impex.password"));
		context.setDatabaseVendor(p.getProperty("impex.databaseVendor"));
		context.setWorkingDir(new File(p.getProperty("impex.workingDir")));
		context.setBaseDir(new File(p.getProperty("project.basedir")));
		context.setBuildDir(new File(p.getProperty("project.build.directory")));
		context.setDatabaseTablePropertiesLocation(p.getProperty("impex.databaseTablePropertiesFile"));
		context.setDataLocations(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.dataLocations")));
		context.setTablesXmlLocation(p.getProperty("impex.tablesXmlLocation"));

		// Default to [artifactId].xml
		context.setSchemaXmlFile(new File(context.getWorkingDir(), context.getArtifactId() + ".xml"));

		// Setup the datasource
		// context.setDataSource(getDataSource(p));

		// Properties that already have default values, don't override unless the corresponding property is explicitly
		// set
		if (p.getProperty("impex.storeDatabaseTableProperties") != null) {
			context.setStoreDatabaseTableProperties(new Boolean(p.getProperty("impex.storeDatabaseTableProperties")));
		}
		if (p.getProperty("impex.dateFormat") != null) {
			context.setDateFormat(p.getProperty("impex.dateFormat"));
		}
		if (p.getProperty("impex.comment") != null) {
			context.setComment(p.getProperty("impex.comment"));
		}
		if (p.getProperty("impex.schemaXMLFile") != null) {
			context.setSchemaXmlFile(new File(p.getProperty("impex.schemaXMLFile")));
		}
		if (p.getProperty("impex.metadata.threads") != null) {
			context.setMetaDataThreads(new Integer(p.getProperty("impex.metadata.threads")));
		}
		if (p.getProperty("impex.data.threads") != null) {
			context.setDataThreads(new Integer(p.getProperty("impex.data.threads")));
		}
		if (p.getProperty("impex.antCompatibilityMode") != null) {
			context.setAntCompatibilityMode(new Boolean(p.getProperty("impex.antCompatibilityMode")));
		}
		if (p.getProperty("impex.encoding") != null) {
			context.setEncoding(p.getProperty("impex.encoding"));
		}

		// Properties that need processing in some way
		Assert.notNull(context.getDatabaseTablePropertiesLocation());
		if (LocationUtils.exists(context.getDatabaseTablePropertiesLocation())) {
			context.setDatabaseTableProperties(PropertyUtils.load(context.getDatabaseTablePropertiesLocation()));
		} else {
			context.setDatabaseTableProperties(new Properties());
		}
		context.setTableIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.table.includes")));
		context.setTableExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.table.excludes")));
		context.setSequenceIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.sequence.includes")));
		context.setSequenceExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.sequence.excludes")));
		context.setViewIncludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.view.includes")));
		context.setViewExcludes(CollectionUtils.getTrimmedListFromCSV(p.getProperty("impex.view.excludes")));
		return context;
	}

	public static <T> ExecutionStatistics invokeThreads(List<T> buckets, ElementHandler<T> handler) {
		// Store some context for the thread handler
		ThreadHandlerContext<T> thc = new ThreadHandlerContext<T>();
		thc.setList(buckets);
		thc.setHandler(handler);
		thc.setMax(buckets.size());
		thc.setMin(buckets.size());
		thc.setDivisor(1);

		// Start threads to acquire table metadata concurrently
		ThreadInvoker invoker = new ThreadInvoker();
		return invoker.invokeThreads(thc);
	}

}
