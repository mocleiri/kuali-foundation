/**
 * Copyright 2010-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Execute Unix utilities using java.
 */
public class UnixUtils {

	private static final Logger logger = LoggerFactory.getLogger(UnixUtils.class);

	private static final String SCP = "scp";
	private static final String SSH = "ssh";
	private static final String SU = "su";
	private static final String MKDIR = "mkdir";
	private static final String RM = "rm";
	private static final String CHOWN = "chown";
	private static final String RSYNC = "rsync";
	public static final int SUCCESS = 0;

	/**
	 * <pre>
	 *  rsync source destination
	 * </pre>
	 *
	 * Where <code>source</code> and <code>destination</code> are both directories on the local file system. <code>source</code> must
	 * already exist. <code>destination</code> will be created if it does not exist.
	 */
	public static final int rsyncdirs(File source, File destination) {
		String sourcePath = validateRsyncSourceDir(source);
		String destinationPath = validateRsyncSourceDir(destination);

		// Can't be the same directory
		Assert.isTrue(!source.equals(destination));

		return rsyncdirs(null, sourcePath, destinationPath);
	}

	/**
	 * <pre>
	 *  rsync source [user@]hostname:destination
	 * </pre>
	 *
	 * Where <code>source</code> is a directory on the local file system. <code>source</code> must already exist.
	 */
	public static final int rsyncdirs(File source, String destination) {
		String sourcePath = validateRsyncSourceDir(source);
		return rsyncdirs(null, sourcePath, destination);
	}

	/**
	 * <pre>
	 *  rsync [user@]hostname:source destination
	 * </pre>
	 *
	 * Where <code>destination</code> is a directory on the local file system. <code>destination</code> will be created if it does not exist
	 */
	public static final int rsyncdirs(String source, File destination) {
		String destinationPath = validateRsyncDestinationDir(destination);
		if (!StringUtils.endsWith(source, File.separator)) {
			source += File.separator;
		}
		return rsyncdirs(null, source, destinationPath);
	}

	/**
	 * <pre>
	 *  rsync [options] source destination
	 * </pre>
	 *
	 * Where <code>source</code> and <code>destination</code> are both directories on the local file system. <code>source</code> must
	 * already exist. <code>destination</code> will be created if it does not exist.
	 */
	public static final int rsyncdirs(List<String> options, File source, File destination) {
		String sourcePath = validateRsyncSourceDir(source);
		String destinationPath = validateRsyncSourceDir(destination);
		return rsyncdirs(options, sourcePath, destinationPath);
	}

	/**
	 * <pre>
	 *  rsync [options] source [user@]hostname:destination
	 * </pre>
	 *
	 * Where <code>source</code> is a directory on the local file system. <code>source</code> must already exist.
	 */
	public static final int rsync(List<String> options, File source, String destination) {
		String sourcePath = validateRsyncSourceDir(source);
		return rsyncdirs(options, sourcePath, destination);
	}

	/**
	 * <pre>
	 *  rsync [options] [user@]hostname:source destination
	 * </pre>
	 *
	 * Where <code>destination</code> is a directory on the local file system. <code>destination</code> will be created if it does not exist
	 */
	public static final int rsyncdirs(List<String> options, String source, File destination) {
		String destinationPath = validateRsyncSourceDir(destination);
		return rsyncdirs(options, source, destinationPath);
	}

	/**
	 * <pre>
	 *  rsync [options] source destination
	 *  rsync [options] source [user@]hostname:destination
	 *  rsync [options] [user@]hostname:source destination
	 * </pre>
	 */
	public static final int rsyncdirs(List<String> options, String source, String destination) {
		List<String> rsyncDirOptions = getRsyncDirOptions(options);
		return rsync(rsyncDirOptions, source, destination);
	}

	/**
	 * <pre>
	 *  rsync [options] source destination
	 *  rsync [options] source [user@]hostname:destination
	 *  rsync [options] [user@]hostname:source destination
	 * </pre>
	 */
	public static final int rsyncdirs(String source, String destination) {
		return rsyncdirs(null, source, destination);
	}

	/**
	 * <pre>
	 *  rsync source destination
	 *  rsync source [user@]hostname:destination
	 *  rsync [user@]hostname:source destination
	 * </pre>
	 */
	public static final int rsync(String source, String destination) {
		return rsync(null, source, destination);
	}

	/**
	 * <pre>
	 *  rsync [options] source destination
	 *  rsync [options] source [user@]hostname:destination
	 *  rsync [options] [user@]hostname:source destination
	 * </pre>
	 */
	public static final int rsync(List<String> options, String source, String destination) {
		Assert.notNull(source);
		Assert.notNull(destination);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(options));
		arguments.add(source);
		arguments.add(destination);
		Commandline cl = new Commandline();
		cl.setExecutable(RSYNC);
		cl.addArguments(CollectionUtils.toStringArray(arguments));
		return execute(cl);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname chown [chownargs] owner:group file
	 * </pre>
	 */
	public static final int sshchown(List<String> args, String user, String hostname, List<String> chownargs, String owner, String group, String file) {
		Assert.notNull(owner);
		Assert.notNull(group);
		Assert.notNull(file);
		String command = getChownCommand(chownargs, owner, group, file);
		return ssh(args, user, hostname, command);
	}

	/**
	 * <pre>
	 * ssh hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownrecursive(String hostname, String owner, String group, String file) {
		return sshchownrecursive(null, null, hostname, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownrecursive(String user, String hostname, String owner, String group, String file) {
		return sshchownrecursive(null, user, hostname, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [args] hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownrecursive(List<String> args, String hostname, String owner, String group, String file) {
		return sshchownrecursive(args, null, hostname, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownrecursive(List<String> args, String user, String hostname, String owner, String group, String file) {
		return sshchown(args, user, hostname, Arrays.asList("-R"), owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [args] hostname chown owner:group file
	 * </pre>
	 */
	public static final int sshchown(List<String> args, String hostname, String owner, String group, String file) {
		return sshchown(args, null, hostname, null, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname chown owner:group file
	 * </pre>
	 */
	public static final int sshchown(List<String> args, String user, String hostname, String owner, String group, String file) {
		return sshchown(args, user, hostname, null, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname chown owner:group file
	 * </pre>
	 */
	public static final int sshchown(String user, String hostname, String owner, String group, String file) {
		return sshchown(null, user, hostname, null, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh hostname chown owner:group file
	 * </pre>
	 */
	public static final int sshchown(String hostname, String owner, String group, String file) {
		return sshchown(null, null, hostname, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh hostname rm -rf file
	 * </pre>
	 */
	public static final int sshrm(String hostname, String file) {
		return sshrm(null, null, hostname, file);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname rm -rf file
	 * </pre>
	 */
	public static final int sshrm(String user, String hostname, String file) {
		return sshrm(null, user, hostname, file);
	}

	/**
	 * <pre>
	 * ssh [args] hostname rm -rf file
	 * </pre>
	 */
	public static final int sshrm(List<String> args, String hostname, String file) {
		return sshrm(args, null, hostname, file);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname rm -rf file
	 * </pre>
	 */
	public static final int sshrm(List<String> args, String user, String hostname, String file) {
		Assert.notNull(file);
		return ssh(args, user, hostname, RM + " -rf " + file);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(String user, String hostname, String directory) {
		return sshmkdir(null, user, hostname, directory);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(List<String> args, String user, String hostname, String directory) {
		Assert.notNull(directory);
		return ssh(args, user, hostname, MKDIR + " -p " + directory);
	}

	/**
	 * <pre>
	 * ssh hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(String hostname, String directory) {
		return sshmkdir(null, null, hostname, directory);
	}

	/**
	 * <pre>
	 * ssh [args] hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(List<String> args, String hostname, String directory) {
		return sshmkdir(args, null, hostname, directory);
	}

	/**
	 * <pre>
	 * ssh hostname su - login command
	 * </pre>
	 */
	public static final int sshsu(String hostname, String login, String command) {
		return sshsu(null, hostname, login, command);
	}

	/**
	 * <pre>
	 * ssh [args] hostname su - login command
	 * </pre>
	 */
	public static final int sshsu(List<String> args, String hostname, String login, String command) {
		return sshsu(args, null, hostname, login, command);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname su - login command
	 * </pre>
	 */
	public static final int sshsu(List<String> args, String user, String hostname, String login, String command) {
		Assert.notNull(login);
		Assert.notNull(command);
		return ssh(user, hostname, SU + " - " + login + " " + command);
	}

	/**
	 * <pre>
	 * ssh hostname command
	 * </pre>
	 */
	public static final int ssh(String hostname, String command) {
		return ssh(null, null, hostname, command);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname command
	 * </pre>
	 */
	public static final int ssh(String user, String hostname, String command) {
		return ssh(null, user, hostname, command);
	}

	/**
	 * <pre>
	 * ssh [args] hostname command
	 * </pre>
	 */
	public static final int ssh(List<String> args, String hostname, String command) {
		return ssh(args, null, hostname, command);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname command
	 * </pre>
	 */
	public static final int ssh(List<String> args, String user, String hostname, String command) {
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

	/**
	 * <pre>
	 * scp source destination
	 * </pre>
	 *
	 * Where both <code>source</code> and <code>destination</code> are in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(String source, String destination) {
		return scp(null, source, destination);
	}

	/**
	 * <pre>
	 * scp [args] source destination
	 * </pre>
	 *
	 * Where both <code>source</code> and <code>destination</code> are in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(List<String> args, String source, String destination) {
		Assert.notNull(source);
		Assert.notNull(destination);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(args));
		arguments.add(source);
		arguments.add(destination);
		Commandline cl = new Commandline();
		cl.setExecutable(SCP);
		cl.addArguments(CollectionUtils.toStringArray(arguments));
		return execute(cl);
	}

	/**
	 * <pre>
	 * scp [args] source destination
	 * </pre>
	 *
	 * Where <code>source</code> is a file on the local file system and <code>destination</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(List<String> args, File source, String destination) {
		Assert.notNull(source);
		String sourcePath = LocationUtils.getCanonicalPath(source);
		if (!source.exists()) {
			throw new IllegalArgumentException(sourcePath + " does not exist");
		}
		return scp(args, sourcePath, destination);
	}

	/**
	 * <pre>
	 * scp [args] source destination
	 * </pre>
	 *
	 * Where <code>destination</code> is a file on the local file system and <code>source</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(List<String> args, String source, File destination) {
		try {
			FileUtils.touch(destination);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
		String localPath = LocationUtils.getCanonicalPath(destination);
		return scp(args, source, localPath);
	}

	/**
	 * <pre>
	 * scp source destination
	 * </pre>
	 *
	 * Where <code>source</code> is a file on the local file system and <code>destination</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(File source, String destination) {
		return scp(null, source, destination);
	}

	/**
	 * <pre>
	 * scp source destination
	 * </pre>
	 *
	 * Where <code>destination</code> is a file on the local file system and <code>source</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(String source, File destination) {
		return scp(null, source, destination);
	}

	public static final void validate(int exitValue, String message, Mode nonZeroExitValueMode) {
		if (exitValue != UnixUtils.SUCCESS) {
			ModeUtils.validate(nonZeroExitValueMode, message);
		}
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

	protected static final String getChownCommand(List<String> args, String owner, String group, String file) {
		StringBuilder sb = new StringBuilder();
		sb.append(CHOWN);
		String arguments = getArguments(args);
		if (arguments != null) {
			sb.append(" ");
			sb.append(arguments);
		}
		sb.append(" ");
		sb.append(owner + ":" + group);
		sb.append(" ");
		sb.append(file);
		return sb.toString();
	}

	protected static final String getArguments(List<String> arguments) {
		if (CollectionUtils.isEmpty(arguments)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arguments.size(); i++) {
			if (i != 0) {
				sb.append(" ");
			}
			sb.append(arguments.get(i));
		}
		return sb.toString();
	}

	protected static final String validateRsyncSourceDir(File dir) {
		String path = LocationUtils.getCanonicalPath(dir);
		if (!dir.exists()) {
			throw new IllegalArgumentException(path + " does not exist");
		}
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(path + " is not a directory");
		}
		return path;
	}

	protected static final String validateRsyncDestinationDir(File dir) {
		try {
			FileUtils.forceMkdir(dir.getParentFile());
			return dir.getCanonicalPath();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	protected static final List<String> getRsyncDirOptions(List<String> options) {
		List<String> rsyncDirOptions = new ArrayList<String>();
		rsyncDirOptions.add("--recursive");
		rsyncDirOptions.add("--archive");
		rsyncDirOptions.add("--delete");
		for (String option : CollectionUtils.toEmpty(options)) {
			if (!rsyncDirOptions.contains(option)) {
				rsyncDirOptions.add(option);
			}
		}
		return rsyncDirOptions;
	}

}
