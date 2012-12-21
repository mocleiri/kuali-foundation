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
	private static final String RSYNC = "rsync";

	public static final int rsync(String src, String dst) {
		return rsync(null, src, dst);
	}

	public static final int rsync(List<String> args, String src, String dst) {
		Assert.notNull(src);
		Assert.notNull(dst);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(args));
		arguments.add(src);
		arguments.add(dst);
		Commandline cl = new Commandline();
		cl.setExecutable(RSYNC);
		cl.addArguments(CollectionUtils.toStringArray(arguments));
		return execute(cl);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(String host, String owner, String group, String file, boolean recursive) {
		Assert.notNull(owner);
		Assert.notNull(group);
		Assert.notNull(file);
		return ssh(host, CHOWN + ((recursive) ? " -R " : "") + " " + owner + ":" + group + " " + file);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(String host, String owner, String group, String file) {
		return sshchown(host, owner, group, file, false);
	}

	/**
	 * If file is a directory, recursively remove it and all sub-directories from the indicated host, otherwise just remove the file.
	 */
	public static final int sshrm(String host, String file) {
		Assert.notNull(file);
		return ssh(host, RM + " -rf " + file);
	}

	/**
	 * Create a directory (and any necessary parent directories) on the indicated host
	 */
	public static final int sshmkdir(String host, String directory) {
		Assert.notNull(directory);
		return ssh(host, MKDIR + " -p " + directory);
	}

	/**
	 * Execute <code>script</code> as <code>user</code> on <code>host</code>
	 */
	public static final int sshsu(String host, String user, String script) {
		return ssh(host, SU + " - " + user + " " + script);
	}

	/**
	 * Execute <code>command</code> on <code>host</code>
	 */
	public static final int ssh(String host, String command) {
		return ssh(host, command, null);
	}

	/**
	 * Execute <code>command</code> on <code>host</code> with an optional list of arguments
	 */
	public static final int ssh(String host, String command, List<String> args) {
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

	protected static final int scp(String location1, String location2, List<String> args) {
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

	/**
	 * Use <code>scp</code> to copy a file from the local file system to a remote server.
	 */
	public static final int scp(File local, String remote, List<String> args) {
		Assert.notNull(local);
		String localPath = LocationUtils.getCanonicalPath(local);
		if (!local.exists()) {
			throw new IllegalArgumentException(localPath + " does not exist");
		}
		return scp(localPath, remote, args);
	}

	/**
	 * Use <code>scp</code> to copy a file from a remote server to the local file system.
	 */
	public static final int scp(String remote, File local, List<String> args) {
		try {
			FileUtils.touch(local);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
		String localPath = LocationUtils.getCanonicalPath(local);
		return scp(remote, localPath, args);
	}

	/**
	 * Use <code>scp</code> to copy a file from the local file system to a remote server.
	 */
	public static final int scp(File local, String remote) {
		return scp(local, remote, null);
	}

	/**
	 * Use <code>scp</code> to copy a file from a remote server to the local file system.
	 */
	public static final int scp(String remote, File local) {
		return scp(remote, local, null);
	}

	protected static final int execute(Commandline cl) {
		try {
			StreamConsumer stdout = new LoggingStreamConsumer(logger, LoggerLevel.INFO);
			StreamConsumer stderr = new LoggingStreamConsumer(logger, LoggerLevel.WARN);
			logger.info(cl.toString());
			return CommandLineUtils.executeCommandLine(cl, stdout, stderr);
		} catch (CommandLineException e) {
			throw new IllegalStateException(e);
		}
	}
}
