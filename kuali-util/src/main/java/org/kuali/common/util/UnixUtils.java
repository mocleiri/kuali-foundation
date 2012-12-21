package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class UnixUtils {
	private static final Logger logger = LoggerFactory.getLogger(UnixUtils.class);
	private static final String SCP = "scp";
	private static final String SSH = "ssh";
	private static final String SU = "su";
	private static final String MKDIR = "mkdir";
	private static final String RM = "rm";
	private static final String CHOWN = "chown";

	public int sshchown(String host, String owner, String group, String fileOrDirectory, boolean recursive) {
		Assert.notNull(owner);
		Assert.notNull(group);
		Assert.notNull(fileOrDirectory);
		return ssh(host, CHOWN + ((recursive) ? " -R " : "") + " " + owner + ":" + group + " " + fileOrDirectory);
	}

	public int sshchown(String host, String owner, String group, String fileOrDirectory) {
		return sshchown(host, owner, group, fileOrDirectory, false);
	}

	public int sshrm(String host, String fileOrDirectory) {
		Assert.notNull(fileOrDirectory);
		return ssh(host, RM + " -rf " + fileOrDirectory);
	}

	public int sshmkdir(String host, String directory) {
		Assert.notNull(directory);
		return ssh(host, MKDIR + " -p " + directory);
	}

	public int sshsu(String host, String user, String script) {
		return ssh(host, SU + " - " + user + " " + script);
	}

	public int ssh(String host, String command) {
		return ssh(host, command, null);
	}

	public int ssh(String host, String command, List<String> args) {
		Assert.notNull(host);
		Assert.notNull(command);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(args));
		arguments.add(host);
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

	public int scp(File local, String remote, List<String> args) {
		Assert.notNull(local);
		String localPath = LocationUtils.getCanonicalPath(local);
		if (!local.exists()) {
			throw new IllegalArgumentException(localPath + " does not exist");
		}
		return scp(localPath, remote, args);
	}

	public int scp(String remote, File local, List<String> args) {
		try {
			FileUtils.touch(local);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		String localPath = LocationUtils.getCanonicalPath(local);
		return scp(remote, localPath, args);
	}

	public int scp(File local, String remote) {
		return scp(local, remote, null);
	}

	public int scp(String remote, File local) {
		return scp(remote, local, null);
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
