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
package org.kuali.common.util.channel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class SSHUtils {

	private static final Logger logger = LoggerFactory.getLogger(SSHUtils.class);

	private static final String FS = File.separator;
	private static final String IDENTITY_FILE = "IdentityFile";
	private static final String TILDE = "~";
	private static final String USER_HOME = FileUtils.getUserDirectoryPath();
	private static final String SSHDIR = USER_HOME + FS + ".ssh";
	private static final String IDENTITY = SSHDIR + FS + "identity";
	private static final String ID_DSA = SSHDIR + FS + "id_dsa";
	private static final String ID_RSA = SSHDIR + FS + "id_rsa";
	private static final String ID_ECDSA = SSHDIR + FS + "id_ecdsa";
	private static final int PORT_NUMBER_LOWEST = 1;
	private static final int PORT_NUMBER_HIGHEST = 65535;

	public static final String STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
	public static final String NO = "no";
	public static final List<String> PRIVATE_KEY_DEFAULTS = Arrays.asList(IDENTITY, ID_DSA, ID_RSA, ID_ECDSA);
	public static final File DEFAULT_CONFIG_FILE = new File(SSHDIR + FS + "config");
	public static final int DEFAULT_PORT = 22;
	public static final File DEFAULT_KNOWN_HOSTS = new File(SSHDIR + FS + "known_hosts");

	/**
	 * Return true if <code>port &gt;= 1</code> and <code>port &lt;= 65535</code>, false otherwise.
	 */
	public static final boolean isValidPort(int port) {
		return port >= PORT_NUMBER_LOWEST && port <= PORT_NUMBER_HIGHEST;
	}

	public static final void addPort(List<String> args, String portOption, int port, int defaultPort) {
		if (port != defaultPort) {
			Assert.isTrue(SSHUtils.isValidPort(port));
			logger.debug("port={}", port);
			args.add(portOption);
			args.add(Integer.toString(port));
		}
	}

	public static final void addOptions(List<String> args, Properties options) {
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

	public static final void addConfigFile(List<String> args, File configFile, File defaultConfigFile) {
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

	public static final void addIdentityFile(List<String> args, File identityFile) {
		if (identityFile != null) {
			String path = LocationUtils.getCanonicalPath(identityFile);
			logger.debug("Private key=[{}]", path);
			args.add("-i");
			args.add(path);
		}
	}

	/**
	 * Return a non-null list containing any private keys found by examining default private key locations in <code>~/.ssh</code> and
	 * parsing <code>config</code>. Any files returned by this method are guaranteed to exist and be readable.
	 */
	public static final List<File> getPrivateKeys(File config) {
		List<String> paths = getFilenames(config);
		return getExistingAndReadable(paths);
	}

	/**
	 * Return a non-null list containing any private keys found by examining default private key locations in <code>~/.ssh</code> and
	 * parsing <code>~/.ssh/config</code>. Any files returned by this method are guaranteed to exist and be readable.
	 */
	public static final List<File> getDefaultPrivateKeys() {
		return getPrivateKeys(DEFAULT_CONFIG_FILE);
	}

	public static final Properties getDefaultOptions() {
		Properties options = new Properties();
		options.setProperty(STRICT_HOST_KEY_CHECKING, NO);
		return options;
	}

	public static final List<File> getExistingAndReadable(List<String> filenames) {
		List<File> files = new ArrayList<File>();
		for (String filename : filenames) {
			File file = new File(filename);
			if (file.exists() && file.canRead()) {
				files.add(file);
			}
		}
		return files;
	}

	public static final List<String> getFilenames(File config) {
		if (config.exists() && config.canRead()) {
			List<String> lines = LocationUtils.readLines(config);
			List<String> identityFileLines = getIdentityFileLines(lines);
			return getFilenames(identityFileLines);
		} else {
			return Collections.<String> emptyList();
		}
	}

	public static final List<String> getIdentityFileLines(List<String> lines) {
		List<String> identityFileLines = new ArrayList<String>();
		for (String line : lines) {
			String trimmed = StringUtils.trim(line);
			if (StringUtils.startsWith(trimmed, IDENTITY_FILE)) {
				identityFileLines.add(trimmed);
			}
		}
		return identityFileLines;
	}

	public static final List<String> getFilenames(List<String> identityFileLines) {
		List<String> filenames = new ArrayList<String>();
		for (String identityFileLine : identityFileLines) {
			String originalFilename = StringUtils.substring(identityFileLine, IDENTITY_FILE.length());
			String resolvedFilename = StringUtils.replace(originalFilename, TILDE, USER_HOME);
			String trimmed = StringUtils.trim(resolvedFilename);
			filenames.add(trimmed);
		}
		return filenames;
	}
}
