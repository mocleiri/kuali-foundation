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
	public static final int sshchown(List<String> sshargs, String host, String owner, String group, String file, boolean recursive) {
		Assert.notNull(owner);
		Assert.notNull(group);
		Assert.notNull(file);
		return ssh(sshargs, host, CHOWN + (recursive ? " -R " : "") + " " + owner + ":" + group + " " + file);
	}

	/**
	 * /** Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(String host, String owner, String group, String file, boolean recursive) {
		return sshchown(null, host, owner, group, file, recursive);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(List<String> sshargs, String host, String owner, String group, String file) {
		return sshchown(sshargs, host, owner, group, file, false);
	}

	/**
	 * Change the ownership of a file on the indicated host
	 */
	public static final int sshchown(String host, String owner, String group, String file) {
		return sshchown(null, host, owner, group, file);
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
	public static final int sshrm(List<String> sshargs, String host, String file) {
		Assert.notNull(file);
		return ssh(sshargs, host, RM + " -rf " + file);
	}

	/**
	 * Create a directory (and any necessary parent directories) on the indicated host
	 */
	public static final int sshmkdir(String host, String directory) {
		return sshmkdir(null, host, directory);
	}

	/**
	 * Create a directory (and any necessary parent directories) on the indicated host
	 */
	public static final int sshmkdir(List<String> sshargs, String host, String directory) {
		Assert.notNull(directory);
		return ssh(sshargs, host, MKDIR + " -p " + directory);
	}

	/**
	 * Execute <code>script</code> as <code>user</code> on <code>host</code>
	 */
	public static final int sshsu(String host, String user, String script) {
		return sshsu(null, host, user, script);
	}

	/**
	 * Execute <code>script</code> as <code>user</code> on <code>host</code>
	 */
	public static final int sshsu(List<String> sshargs, String host, String user, String script) {
		return ssh(host, SU + " - " + user + " " + script);
	}

	/**
	 * Execute <code>command</code> on <code>host</code>
	 */
	public static final int ssh(String host, String command) {
		return ssh(null, host, command);
	}

	/**
	 * Execute <code>command</code> on <code>host</code> with an optional list of arguments
	 */
	public static final int ssh(List<String> args, String host, String command) {
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

	protected static final int scp(List<String> args, String location1, String location2) {
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
	public static final int scp(List<String> scpargs, File local, String remote) {
		Assert.notNull(local);
		String localPath = LocationUtils.getCanonicalPath(local);
		if (!local.exists()) {
			throw new IllegalArgumentException(localPath + " does not exist");
		}
		return scp(scpargs, localPath, remote);
	}

	/**
	 * Use <code>scp</code> to copy a file from a remote server to the local file system.
	 */
	public static final int scp(List<String> scpargs, String remote, File local) {
		try {
			FileUtils.touch(local);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
		String localPath = LocationUtils.getCanonicalPath(local);
		return scp(scpargs, remote, localPath);
	}

	/**
	 * Use <code>scp</code> to copy a file from the local file system to a remote server.
	 */
	public static final int scp(File local, String remote) {
		return scp(null, local, remote);
	}

	/**
	 * Use <code>scp</code> to copy a file from a remote server to the local file system.
	 */
	public static final int scp(String remote, File local) {
		return scp(null, remote, local);
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
