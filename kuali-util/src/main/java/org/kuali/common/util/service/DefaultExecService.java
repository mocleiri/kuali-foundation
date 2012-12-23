package org.kuali.common.util.service;

import java.util.List;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.kuali.common.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExecService implements ExecService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExecService.class);

	protected int execute(ExecContext context, Commandline cl) {
		try {
			logger.info(cl.toString());
			return CommandLineUtils.executeCommandLine(cl, context.getStdout(), context.getStderr());
		} catch (CommandLineException e) {
			throw new IllegalStateException(e);
		}
	}

	protected Commandline getCommandLine(ExecContext context) {
		Commandline cl = new Commandline();
		cl.setExecutable(context.getExecutable());
		if (!CollectionUtils.isEmpty(context.getArgs())) {
			cl.addArguments(CollectionUtils.toStringArray(context.getArgs()));
		}
		if (context.getWorkingDirectory() != null) {
			cl.setWorkingDirectory(context.getWorkingDirectory());
		}
		return cl;
	}

	@Override
	public int execute(ExecContext context) {
		Commandline cl = getCommandLine(context);
		return execute(context, cl);
	}

	@Override
	public int execute(String executable, List<String> args) {
		DefaultExecContext context = new DefaultExecContext();
		context.setExecutable(executable);
		context.setArgs(args);
		return execute(context);
	}

	public static final Logger getLogger() {
		return logger;
	}
}
