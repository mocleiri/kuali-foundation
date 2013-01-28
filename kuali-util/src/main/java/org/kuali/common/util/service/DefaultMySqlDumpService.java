package org.kuali.common.util.service;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
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
		List<String> args = getMySqlDumpArgs(context);
		ExecContext ec = getExecContext(context.getExecutable(), args, context.getOutputFile());
		logDump(context);
		dump(ec);
	}

	protected void logDump(MySqlDumpContext context) {
		String username = context.getUsername();
		String hostname = context.getHostname();
		String database = context.getDatabase();
		String path = LocationUtils.getCanonicalPath(context.getOutputFile());
		Object[] args = { username, hostname, database, path };
		logger.info("Dumping {}@{}:{} to {}", args);
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
	public void dump(List<String> args, File outputFile) {
		ExecContext context = getExecContext(DEFAULT_EXECUTABLE, args, outputFile);
		dump(context);
	}

	protected void dump(ExecContext context) {
		int result = execute(context);
		if (result != 0) {
			throw new IllegalStateException("Non-zero exit value - " + result);
		}
	}

	protected ExecContext getExecContext(String executable, List<String> args, File outputFile) {
		PrintStream out = LocationUtils.openPrintStream(outputFile);
		PrintlnStreamConsumer standardOutConsumer = new PrintlnStreamConsumer(out);
		DefaultExecContext context = new DefaultExecContext();
		context.setExecutable(executable);
		context.setArgs(args);
		context.setStandardOutConsumer(standardOutConsumer);
		return context;
	}

	protected List<String> getMySqlDumpArgs(MySqlDumpContext context) {
		List<String> args = new ArrayList<String>();
		if (!StringUtils.isBlank(context.getUsername())) {
			args.add("--username");
			args.add(context.getUsername());
		}
		if (!StringUtils.isBlank(context.getPassword())) {
			args.add("--password" + context.getPassword());
		}
		if (!StringUtils.isBlank(context.getHostname())) {
			args.add("--host");
			args.add(context.getHostname());
		}
		if (!StringUtils.isBlank(context.getDatabase())) {
			args.add(context.getDatabase());
		}
		return args;
	}
}
