package org.kuali.common.util.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.PrintlnStreamConsumer;

public class DefaultMySqlDumpService extends DefaultExecService implements MySqlDumpService {

	@Override
	public void dump(MySqlDumpContext context) {
		Assert.notNull(context.getDatabase(), "database is null");
		touch(context.getOutputFile());
		List<String> args = getMySqlDumpArgs(context);
		DefaultExecContext ec = new DefaultExecContext();
		ec.setExecutable(context.getExecutable());
		ec.setArgs(args);
		PrintStream out = getPrintStream(context.getOutputFile());
		PrintlnStreamConsumer psc = new PrintlnStreamConsumer(out);
		ec.setStandardOutConsumer(psc);
		int result = execute(ec);
		if (result != 0) {
			throw new IllegalStateException("Non-zero exit value - " + result);
		}
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

	protected PrintStream getPrintStream(File file) {
		try {
			return new PrintStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
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

	protected void touch(File file) {
		try {
			FileUtils.touch(file);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
