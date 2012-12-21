package org.kuali.common.util.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.LoggerLevel;
import org.kuali.common.util.LoggingStreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultExecService {
	private static final Logger logger = LoggerFactory.getLogger(DefaultExecService.class);
	private static final String SCP = "scp";
	private static final String SSH = "ssh";

	public int ssh(String user, String hostname, String command) {
		return ssh(user, hostname, command, null);
	}

	public int ssh(String user, String hostname, String command, List<String> args) {
		Assert.notNull(hostname);
		Assert.notNull(command);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(args));
		if (!StringUtils.isBlank(user)) {
			arguments.add(user + "@" + hostname);
		} else {
			arguments.add(hostname);
		}
		arguments.add(command);
		Commandline cl = new Commandline();
		cl.setExecutable(SSH);
		cl.addArguments(CollectionUtils.toStringArray(arguments));
		return execute(cl);
	}

	protected int scp(String location1, String location2, List<String> args) {
		Assert.notNull(location1);
		Assert.notNull(location2);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(args));
		arguments.add(location1);
		arguments.add(location2);
		Commandline cl = new Commandline();
		cl.setExecutable(SCP);
		cl.addArguments(CollectionUtils.toStringArray(arguments));
		return execute(cl);
	}

	public int scp(File localFile, String remoteLocation, List<String> args) {
		Assert.notNull(localFile);
		String localFilePath = LocationUtils.getCanonicalPath(localFile);
		if (!localFile.exists()) {
			throw new IllegalArgumentException(localFilePath + " does not exist");
		}
		return scp(localFilePath, remoteLocation, args);
	}

	public int scp(String remoteLocation, File localFile, List<String> args) {
		try {
			FileUtils.touch(localFile);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		String localFilePath = LocationUtils.getCanonicalPath(localFile);
		return scp(remoteLocation, localFilePath, args);
	}

	public int scp(File localFile, String remoteLocation) {
		return scp(localFile, remoteLocation, null);
	}

	public int scp(String remoteLocation, File localFile) {
		return scp(remoteLocation, localFile, null);
	}

	protected int execute(Commandline cl) {
		try {
			StreamConsumer stdout = new LoggingStreamConsumer(logger, LoggerLevel.INFO);
			StreamConsumer stderr = new LoggingStreamConsumer(logger, LoggerLevel.WARN);
			return CommandLineUtils.executeCommandLine(cl, stdout, stderr);
		} catch (CommandLineException e) {
			throw new IllegalStateException(e);
		}
	}
}
