package org.kuali.common.util.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PrintlnStreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultMySqlDumpService extends DefaultExecService implements MySqlDumpService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultMySqlDumpService.class);

	@Override
	public void dump(String username, String password, String hostname, String database, File outputFile) {
		MySqlDumpContext context = new MySqlDumpContext();
		context.setExecutable(DEFAULT_EXECUTABLE);
		context.setUsername(username);
		context.setPassword(password);
		context.setHostname(hostname);
		context.setDatabase(database);
		context.setOutputFile(outputFile);
		dump(context);
	}

	@Override
	public void dump(List<String> options, String database, File outputFile) {
		MySqlDumpContext context = new MySqlDumpContext();
		context.setExecutable(DEFAULT_EXECUTABLE);
		context.setOptions(options);
		context.setDatabase(database);
		context.setOutputFile(outputFile);
		dump(context);
	}

	@Override
	public void dump(MySqlDumpContext context) {
		Assert.notNull(context.getDatabase(), "database is null");
		Assert.notNull(context.getOutputFile(), "output file is null");
		Assert.notNull(context.getExecutable(), "executable is null");
		fillInOptions(context);
		DefaultExecContext dec = getExecContext(context);
		log(context);
		dump(dec, context);
	}

	protected void log(MySqlDumpContext context) {
		String username = StringUtils.trimToEmpty(context.getUsername());
		String hostname = StringUtils.trimToEmpty(context.getHostname());
		int port = context.getPort();
		String database = context.getDatabase();
		String path = LocationUtils.getCanonicalPath(context.getOutputFile());
		Object[] args = { username, hostname, port, database, path };
		logger.info("Dumping [{}@{}:{}/{}] -> [{}]", args);
	}

	protected void dump(DefaultExecContext context, MySqlDumpContext msdc) {
		PrintStream out = null;
		try {
			out = LocationUtils.openPrintStream(msdc.getOutputFile());
			StreamConsumer standardOutConsumer = new PrintlnStreamConsumer(out, msdc.getSkipLinePrefix(), msdc.getSkipLineSuffix());
			context.setStandardOutConsumer(standardOutConsumer);
			long start = System.currentTimeMillis();
			int result = execute(context);
			long elapsed = System.currentTimeMillis() - start;
			if (result != 0) {
				throw new IllegalStateException("Non-zero exit value - " + result);
			}
			logComplete(elapsed, msdc.getOutputFile());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	protected void logComplete(long elapsed, File outputFile) {
		long length = outputFile.length();
		String time = FormatUtils.getTime(elapsed);
		String size = FormatUtils.getSize(length);
		String rate = FormatUtils.getRate(elapsed, length);
		Object[] args = { time, size, rate };
		logger.info("Dump completed. [Time:{}, Size:{}, Rate:{}]", args);
	}

	protected DefaultExecContext getExecContext(MySqlDumpContext context) {
		List<String> args = getArgs(context);
		DefaultExecContext dec = new DefaultExecContext();
		dec.setExecutable(context.getExecutable());
		dec.setArgs(args);
		return dec;
	}

	/**
	 * <code>mysqldump</code> invocation looks like this:
	 *
	 * <pre>
	 * mysqldump [OPTIONS] database [tables]
	 * </pre>
	 */
	protected List<String> getArgs(MySqlDumpContext context) {
		List<String> args = new ArrayList<String>();
		args.addAll(CollectionUtils.toEmptyList(context.getOptions()));
		args.add(context.getDatabase());
		args.addAll(CollectionUtils.toEmptyList(context.getTables()));
		return args;
	}

	/**
	 * Create (or update) the list of options for this context
	 */
	protected void fillInOptions(MySqlDumpContext context) {
		// Get a handle to the existing options list, or create new one
		List<String> options = context.getOptions() == null ? new ArrayList<String>() : context.getOptions();
		// Insert the options we are explicitly managing at the front of the list
		options.add(0, "--port=" + context.getPort());
		if (!StringUtils.isBlank(context.getHostname())) {
			options.add(0, "--host=" + context.getHostname());
		}
		if (!StringUtils.isBlank(context.getPassword())) {
			options.add(0, "--password=" + context.getPassword());
		}
		if (!StringUtils.isBlank(context.getUsername())) {
			options.add(0, "--user=" + context.getUsername());
		}
		// Just in case there were no options to begin with
		context.setOptions(options);
	}
}
