package org.kuali.common.util.service;

import java.util.List;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggingStreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultExecService implements ExecService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultExecService.class);

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

	protected int execute(ExecContext context, Commandline cl) {
		try {
			logger.info(cl.toString());
			StreamConsumer stdout = getStreamConsumer(context.getStandardOutConsumer(), logger, LoggerLevel.INFO);
			StreamConsumer stderr = getStreamConsumer(context.getStandardErrConsumer(), logger, LoggerLevel.WARN);
			return CommandLineUtils.executeCommandLine(cl, context.getInput(), stdout, stderr, context.getTimeoutInSeconds());
		} catch (CommandLineException e) {
			throw new IllegalStateException(e);
		}
	}

	protected StreamConsumer getStreamConsumer(StreamConsumer provided, Logger logger, LoggerLevel level) {
		if (provided != null) {
			return provided;
		} else {
			return new LoggingStreamConsumer(logger, level);
		}
	}

	protected Commandline getCommandLine(ExecContext context) {
		Commandline cl = new Commandline();
		cl.setExecutable(context.getExecutable());
		if (context.getArgs() != null) {
			cl.addArguments(CollectionUtils.toStringArray(context.getArgs()));
		}
		if (context.getWorkingDirectory() != null) {
			cl.setWorkingDirectory(context.getWorkingDirectory());
		}
		return cl;
	}

}
