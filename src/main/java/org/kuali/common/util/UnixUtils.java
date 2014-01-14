/**
 * Copyright 2010-2013 The Kuali Foundation
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
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.StreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Execute Unix utilities using java.
 */
public class UnixUtils {

	private static final Logger logger = LoggerFactory.getLogger(UnixUtils.class);

	private static final String SCP = "scp";
	private static final String SSH = "ssh";
	private static final String RSYNC = "rsync";
	private static final String FORWARD_SLASH = "/";
	public static final int SUCCESS = 0;

	private static final UnixCmds cmds = new UnixCmds();

	/**
	 * <pre>
	 *  rsync source destination
	 * </pre>
	 * 
	 * Where <code>source</code> and <code>destination</code> are both directories on the local file system. <code>source</code> must already exist. <code>destination</code> will
	 * be created if it does not exist.
	 */
	public static final int rsyncdirs(File source, File destination) {
		String sourcePath = validateRsyncSourceDir(source);
		String destinationPath = validateRsyncDestinationDir(destination);

		// Make sure source is a different directory than destination
		boolean different = !source.equals(destination);
		Assert.isTrue(different);

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
		return rsyncdirs(null, source, destinationPath);
	}

	/**
	 * <pre>
	 *  rsync [options] source destination
	 * </pre>
	 * 
	 * Where <code>source</code> and <code>destination</code> are both directories on the local file system. <code>source</code> must already exist. <code>destination</code> will
	 * be created if it does not exist.
	 */
	public static final int rsyncdirs(List<String> options, File source, File destination) {
		String sourcePath = validateRsyncSourceDir(source);
		String destinationPath = validateRsyncDestinationDir(destination);
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
		String destinationPath = validateRsyncDestinationDir(destination);
		return rsyncdirs(options, source, destinationPath);
	}

	/**
	 * <pre>
	 *  rsync [options] source destination
	 *  rsync [options] source [user@]hostname:destination
	 *  rsync [options] [user@]hostname:source destination
	 * </pre>
	 * 
	 * Always add a trailing slash to source when sync'ing directories.<br>
	 * This forces rsync to behave like <code>cp</code>
	 * 
	 * <pre>
	 * cp -R /tmp/foo/bar  /tmp/xyz  -  creates files in /tmp/xyz
	 * rsync /tmp/foo/bar/ /tmp/xyz  -  creates files in /tmp/xyz
	 * 
	 * rsync /tmp/foo/bar  /tmp/xyz  -  creates files in /tmp/xyz/bar
	 * </pre>
	 */
	public static final int rsyncdirs(List<String> options, String source, String destination) {
		List<String> rsyncDirOptions = getRsyncDirOptions(options);
		// Make sure source always has a trailing slash
		String trailingSlashSource = StringUtils.endsWith(source, FORWARD_SLASH) ? source : source + FORWARD_SLASH;
		return rsync(rsyncDirOptions, trailingSlashSource, destination);
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
		arguments.addAll(CollectionUtils.toEmptyList(options));
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
	public static final int sshchown(List<String> args, String user, String hostname, List<String> options, String owner, String group, String file) {
		Assert.notNull(owner);
		Assert.notNull(group);
		Assert.notNull(file);
		String command = cmds.chown(options, owner, group, file);
		return ssh(args, user, hostname, command);
	}

	/**
	 * <pre>
	 * ssh hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownr(String hostname, String owner, String group, String file) {
		return sshchownr(null, null, hostname, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownr(String user, String hostname, String owner, String group, String file) {
		return sshchownr(null, user, hostname, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [args] hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownr(List<String> args, String hostname, String owner, String group, String file) {
		return sshchownr(args, null, hostname, owner, group, file);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname chown -R owner:group file
	 * </pre>
	 */
	public static final int sshchownr(List<String> args, String user, String hostname, String owner, String group, String file) {
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
		return sshrm(args, user, hostname, Collections.singletonList(file));
	}

	/**
	 * <pre>
	 * ssh hostname rm -rf file ...
	 * </pre>
	 */
	public static final int sshrm(String hostname, List<String> paths) {
		return sshrm(null, null, hostname, paths);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname rm -rf file ...
	 * </pre>
	 */
	public static final int sshrm(String user, String hostname, List<String> paths) {
		return sshrm(null, user, hostname, paths);
	}

	/**
	 * <pre>
	 * ssh [args] hostname rm -rf file ...
	 * </pre>
	 */
	public static final int sshrm(List<String> args, String hostname, List<String> paths) {
		return sshrm(args, null, hostname, paths);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname rm -rf file ...
	 * </pre>
	 */
	public static final int sshrm(List<String> args, String user, String hostname, List<String> paths) {
		return sshrm(args, user, hostname, Arrays.asList("-r", "-f"), paths);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname rm [rmargs] file ...
	 * </pre>
	 */
	public static final int sshrm(List<String> args, String user, String hostname, List<String> options, List<String> paths) {
		Assert.notEmpty(paths);
		String command = cmds.rm(options, paths);
		return ssh(args, user, hostname, command);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname chmod mode file
	 * </pre>
	 */
	public static final int sshchmod(List<String> args, String user, String hostname, String mode, String path) {
		Assert.hasLength(mode);
		Assert.notNull(path);
		return ssh(args, user, hostname, cmds.chmod(mode, path));
	}

	/**
	 * <pre>
	 * ssh [user@]hostname chmod mode file
	 * </pre>
	 */
	public static final int sshchmod(String user, String hostname, String mode, String file) {
		return sshchmod(null, user, hostname, mode, file);
	}

	/**
	 * <pre>
	 * ssh [user@]hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(String user, String hostname, String path) {
		return sshmkdir(null, user, hostname, path);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(List<String> args, String user, String hostname, String path) {
		Assert.notBlank(path);
		return ssh(args, user, hostname, cmds.mkdirp(path));
	}

	/**
	 * <pre>
	 * ssh hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(String hostname, String path) {
		return sshmkdir(null, null, hostname, path);
	}

	/**
	 * <pre>
	 * ssh [args] hostname mkdir -p directory
	 * </pre>
	 */
	public static final int sshmkdir(List<String> args, String hostname, String path) {
		return sshmkdir(args, null, hostname, path);
	}

	/**
	 * <pre>
	 * ssh hostname su - login command
	 * </pre>
	 */
	public static final int sshsu(String hostname, String login, String command) {
		return sshsu(null, null, hostname, login, command);
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
	 * ssh [user@]hostname su - login command
	 * </pre>
	 */
	public static final int sshsu(String user, String hostname, String login, String command) {
		return sshsu(null, user, hostname, login, command);
	}

	/**
	 * <pre>
	 * ssh [args] [user@]hostname su - login command
	 * </pre>
	 */
	public static final int sshsu(List<String> args, String user, String hostname, String login, String command) {
		Assert.notNull(login);
		Assert.notNull(command);
		return ssh(user, hostname, cmds.su(login, command));
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
		arguments.addAll(CollectionUtils.toEmptyList(args));
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
		arguments.addAll(CollectionUtils.toEmptyList(args));
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

	public static final void validate(int exitValue, String message, Mode mode) {
		if (exitValue != UnixUtils.SUCCESS) {
			ModeUtils.validate(mode, message + " Exit value=[" + exitValue + "]");
		}
	}

	public static final void validate(int exitValue, String message) {
		validate(exitValue, message, Mode.ERROR);
	}

	public static final int execute(Commandline cl) {
		try {
			StreamConsumer stdout = new org.kuali.common.util.stream.LoggingStreamConsumer(logger, org.kuali.common.util.log.LoggerLevel.INFO);
			StreamConsumer stderr = new org.kuali.common.util.stream.LoggingStreamConsumer(logger, org.kuali.common.util.log.LoggerLevel.WARN);
			logger.info(cl.toString());
			return CommandLineUtils.executeCommandLine(cl, stdout, stderr);
		} catch (CommandLineException e) {
			throw new IllegalStateException(e);
		}
	}

	public static final String getLocation(String user, String hostname, String filename) {
		Assert.notNull(user);
		Assert.notNull(filename);
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isBlank(user)) {
			sb.append(user + "@");
		}
		sb.append(hostname);
		sb.append(":");
		sb.append(filename);
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
		if (!StringUtils.endsWith(path, FORWARD_SLASH)) {
			return path + FORWARD_SLASH;
		} else {
			return path;
		}
	}

	protected static final String validateRsyncDestinationDir(File dir) {
		try {
			FileUtils.forceMkdir(dir);
			return dir.getCanonicalPath();
		} catch (IOException e) {
			throw new IllegalArgumentException("Unexpected IO error", e);
		}
	}

	/**
	 * Return a list containing the options <code>--recursive</code>, <code>--archive</code>, and <code>--delete</code> as the first 3 elements, with additional options coming
	 * after.
	 */
	protected static final List<String> getRsyncDirOptions(List<String> options) {
		List<String> rsyncDirOptions = new ArrayList<String>();
		rsyncDirOptions.add("--recursive");
		rsyncDirOptions.add("--archive");
		rsyncDirOptions.add("--delete");
		for (String option : CollectionUtils.toEmptyList(options)) {
			if (!rsyncDirOptions.contains(option)) {
				rsyncDirOptions.add(option);
			}
		}
		return rsyncDirOptions;
	}

}
