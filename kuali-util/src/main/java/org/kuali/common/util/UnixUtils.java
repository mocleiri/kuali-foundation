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
	 * Invoke <code>rsync</code> to synchronize <code>src</code> with <code>dst</code>
	 */
	public static final int rsync(String src, String dst) {
		return rsync(null, src, dst);
	}

	/**
	 * Invoke <code>rsync</code> to synchronize <code>src</code> with <code>dst</code>
	 */
	public static final int rsync(List<String> options, String src, String dst) {
		Assert.notNull(src);
		Assert.notNull(dst);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(options));
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
	public static final int sshchown(List<String> sshargs, String user, String hostname, String owner, String group, String file, boolean recursive) {
		Assert.notNull(owner);
		Assert.notNull(group);
		Assert.notNull(file);
		return ssh(sshargs, user, hostname, CHOWN + (recursive ? " -R " : "") + " " + owner + ":" + group + " " + file);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(List<String> sshargs, String hostname, String owner, String group, String file, boolean recursive) {
		return sshchown(null, null, hostname, owner, group, file, recursive);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(String hostname, String owner, String group, String file, boolean recursive) {
		return sshchown(null, hostname, owner, group, file, recursive);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(List<String> sshargs, String hostname, String owner, String group, String file) {
		return sshchown(sshargs, hostname, owner, group, file, false);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(String hostname, String owner, String group, String file) {
		return sshchown(null, hostname, owner, group, file);
	}

	/**
	 * If file is a directory, recursively remove it and all sub-directories from the indicated host, otherwise just remove the file.
	 */
	public static final int sshrm(String host, String file) {
		return sshrm(null, host, file);
	}

	/**
	 * If file is a directory, recursively remove it and all sub-directories from the indicated host, otherwise just remove the file.
	 */
	public static final int sshrm(List<String> sshargs, String hostname, String file) {
		Assert.notNull(file);
		return ssh(sshargs, hostname, RM + " -rf " + file);
	}

	/**
	 * Create a directory (and any necessary parent directories) on the indicated host
	 */
	public static final int sshmkdir(String hostname, String directory) {
		return sshmkdir(null, hostname, directory);
	}

	/**
	 * Create a directory (and any necessary parent directories) on the indicated host
	 */
	public static final int sshmkdir(List<String> sshargs, String host, String directory) {
		Assert.notNull(directory);
		return ssh(sshargs, host, MKDIR + " -p " + directory);
	}

	/**
	 * <code>ssh [hostname] [su - &lt;login&gt; &lt;script&gt;]</code>
	 */
	public static final int sshsu(String hostname, String login, String script) {
		return sshsu(null, hostname, login, script);
	}

	/**
	 * <code>ssh [sshargs] [hostname] [su - &lt;login&gt; &lt;script&gt;]</code>
	 */
	public static final int sshsu(List<String> sshargs, String hostname, String login, String script) {
		return sshsu(sshargs, null, hostname, login, script);
	}

	/**
	 * <code>ssh [sshargs] [user@][hostname] [su - &lt;login&gt; &lt;script&gt;]</code>
	 */
	public static final int sshsu(List<String> sshargs, String user, String hostname, String login, String script) {
		return ssh(user, hostname, SU + " - " + login + " " + script);
	}

	/**
	 * <code>ssh [hostname] [command]</code>
	 */
	public static final int ssh(String hostname, String command) {
		return ssh(null, null, hostname, command);
	}

	/**
	 * <code>ssh [user@][hostname] [command]</code>
	 */
	public static final int ssh(String user, String hostname, String command) {
		return ssh(null, user, hostname, command);
	}

	/**
	 * <code>ssh [args] [hostname] [command]</code>
	 */
	public static final int ssh(List<String> args, String hostname, String command) {
		return ssh(args, null, hostname, command);
	}

	/**
	 * <code>ssh [args] [user@][hostname] [command]</code>
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
	public static final int scp(String location1, String location2) {
		return scp(null, location1, location2);
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
}
