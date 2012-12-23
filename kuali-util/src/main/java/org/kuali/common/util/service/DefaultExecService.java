package org.kuali.common.util.service;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
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

	protected void addConfigFile(File configFile, File defaultConfigFile, List<String> args) {
		if (configFile == null) {
			return;
		}
		String defaultPath = LocationUtils.getCanonicalPath(defaultConfigFile);
		String configFilePath = LocationUtils.getCanonicalPath(configFile);
		if (!StringUtils.equals(defaultPath, configFilePath)) {
			args.add("-F");
			args.add(configFilePath);
		}
	}

	protected void addIdentityFile(File identityFile, List<String> args) {
		if (identityFile != null) {
			String path = LocationUtils.getCanonicalPath(identityFile);
			args.add("-i");
			args.add(path);
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
