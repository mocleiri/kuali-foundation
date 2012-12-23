package org.kuali.common.util.service;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggingStreamConsumer;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.secure.SSHUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

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

	protected void addPort(List<String> args, String portOption, int port, int defaultPort) {
		if (port != defaultPort) {
			Assert.isTrue(SSHUtils.isValidPort(port));
			logger.debug("port={}", port);
			args.add(portOption);
			args.add(Integer.toString(port));
		}
	}

	protected void addOptions(List<String> args, Properties options) {
		if (options == null) {
			return;
		}
		List<String> keys = PropertyUtils.getSortedKeys(options);
		for (String key : keys) {
			String value = options.getProperty(key);
			logger.debug("Adding option [-o {}={}]", key, value);
			args.add("-o");
			args.add(key + "=" + value);
		}
	}

	protected void addConfigFile(List<String> args, File configFile, File defaultConfigFile) {
		if (configFile == null) {
			return;
		}
		String defaultPath = LocationUtils.getCanonicalPath(defaultConfigFile);
		String configFilePath = LocationUtils.getCanonicalPath(configFile);
		if (!StringUtils.equals(defaultPath, configFilePath)) {
			logger.debug("SSH config=[{}]", configFilePath);
			args.add("-F");
			args.add(configFilePath);
		}
	}

	protected void addIdentityFile(List<String> args, File identityFile) {
		if (identityFile != null) {
			String path = LocationUtils.getCanonicalPath(identityFile);
			logger.debug("Private key=[{}]", path);
			args.add("-i");
			args.add(path);
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
