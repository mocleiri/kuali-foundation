package org.kuali.common.util.service;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PrintlnStreamConsumer;

public class DefaultMySqlDumpService extends DefaultExecService implements MySqlDumpService {

	public static final String DEFAULT_EXECUTABLE = "mysqldump";

	@Override
	public void dump(MySqlDumpContext context) {
		Assert.notNull(context.getDatabase(), "database is null");
		List<String> args = getMySqlDumpArgs(context);
		ExecContext ec = getExecContext(context.getExecutable(), args, context.getOutputFile());
		dump(ec);
	}

	@Override
	public void dump(String username, String password, String hostname, String database, File outputFile) {
		MySqlDumpContext context = new MySqlDumpContext();
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
		DefaultExecContext ec = new DefaultExecContext();
		ec.setExecutable(executable);
		ec.setArgs(args);
		ec.setStandardOutConsumer(standardOutConsumer);
		return ec;
	}

	protected List<String> getMySqlDumpArgs(MySqlDumpContext context) {
		List<String> args = new ArrayList<String>();
		if (!StringUtils.isBlank(context.getUsername())) {
			args.add("-u");
			args.add(context.getUsername());
		}
		if (!StringUtils.isBlank(context.getPassword())) {
			args.add("-p" + context.getPassword());
		}
		if (!StringUtils.isBlank(context.getHostname())) {
			args.add("-h");
			args.add(context.getHostname());
		}
		if (!StringUtils.isBlank(context.getDatabase())) {
			args.add(context.getDatabase());
		}
		return args;
	}
}
