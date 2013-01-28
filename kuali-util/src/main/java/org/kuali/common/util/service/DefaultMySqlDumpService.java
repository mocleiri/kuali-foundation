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
		List<String> options = getOptions(context);
		ExecContext ec = getExecContext(context.getExecutable(), options, context.getOutputFile());
		logDump(context);
		dump(ec, context.getOutputFile());
	}

	protected void logDump(MySqlDumpContext context) {
		String username = context.getUsername();
		String hostname = context.getHostname();
		int port = context.getPort();
		String database = context.getDatabase();
		String path = LocationUtils.getCanonicalPath(context.getOutputFile());
		Object[] args = { username, hostname, port, database, path };
		logger.info("Dumping [{}@{}:{}/{}] to [{}]", args);
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
		ExecContext context = getExecContext(DEFAULT_EXECUTABLE, options, outputFile);
		dump(context, outputFile);
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

	protected ExecContext getExecContext(String executable, List<String> options, File outputFile) {
		PrintStream out = LocationUtils.openPrintStream(outputFile);
		PrintlnStreamConsumer standardOutConsumer = new PrintlnStreamConsumer(out);
		DefaultExecContext context = new DefaultExecContext();
		context.setExecutable(executable);
		context.setArgs(options);
		context.setStandardOutConsumer(standardOutConsumer);
		return context;
	}

	protected List<String> getOptions(MySqlDumpContext context) {
		List<String> options = new ArrayList<String>();
		if (!StringUtils.isBlank(context.getUsername())) {
			options.add("--user=" + context.getUsername());
		}
		if (!StringUtils.isBlank(context.getPassword())) {
			options.add("--password=" + context.getPassword());
		}
		if (!StringUtils.isBlank(context.getHostname())) {
			options.add("--host=" + context.getHostname());
		}
		options.add("--port=" + context.getPort());
		for (String option : CollectionUtils.toEmptyList(context.getOptions())) {
			options.add(option);
		}
		return options;
	}
}
