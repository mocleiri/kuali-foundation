package org.kuali.common.util.service;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
	public void dump(MySqlDumpContext context) {
		Assert.notNull(context.getDatabase(), "database is null");
		Assert.notNull(context.getOutputFile(), "output file is null");
		Assert.notNull(context.getExecutable(), "executable is null");
		fillInOptions(context);
		ExecContext ec = getExecContext(context);
		log(context);
		dump(ec, context.getOutputFile());
	}

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

	protected void log(MySqlDumpContext context) {
		String username = StringUtils.trimToEmpty(context.getUsername());
		String hostname = StringUtils.trimToEmpty(context.getHostname());
		int port = context.getPort();
		String database = context.getDatabase();
		String path = LocationUtils.getCanonicalPath(context.getOutputFile());
		Object[] args = { username, hostname, port, database, path };
		logger.info("Dumping [{}@{}:{}/{}] -> [{}]", args);
	}

	protected void dump(ExecContext context, File outputFile) {
		long start = System.currentTimeMillis();
		int result = execute(context);
		long elapsed = System.currentTimeMillis() - start;
		if (result != 0) {
			throw new IllegalStateException("Non-zero exit value - " + result);
		}
		String time = FormatUtils.getTime(elapsed);
		String size = FormatUtils.getSize(outputFile.length());
		String rate = FormatUtils.getRate(elapsed, outputFile.length());
		Object[] args = { time, size, rate };
		logger.info("Dump completed. [Time:{}, Size:{}, Rate:{}]", args);
	}

	protected ExecContext getExecContext(MySqlDumpContext context) {
		PrintStream out = LocationUtils.openPrintStream(context.getOutputFile());
		PrintlnStreamConsumer standardOutConsumer = new PrintlnStreamConsumer(out);
		List<String> args = getArgs(context);

		DefaultExecContext dec = new DefaultExecContext();
		dec.setExecutable(context.getExecutable());
		dec.setArgs(args);
		dec.setStandardOutConsumer(standardOutConsumer);
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

	protected void fillInOptions(MySqlDumpContext context) {
		List<String> options = context.getOptions() == null ? new ArrayList<String>() : context.getOptions();
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
		context.setOptions(options);
	}
}
