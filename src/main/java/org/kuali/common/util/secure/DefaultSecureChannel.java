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
package org.kuali.common.util.secure;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.Encodings;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * @deprecated
 */
@Deprecated
public class DefaultSecureChannel implements SecureChannel {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureChannel.class);
	private static final String SFTP = "sftp";
	private static final String EXEC = "exec";
	private static final String FORWARDSLASH = "/";
	private static final int DEFAULT_SLEEP_MILLIS = 10;
	private static final String DEFAULT_ENCODING = Encodings.UTF8;

	File knownHosts = SSHUtils.DEFAULT_KNOWN_HOSTS;
	File config = SSHUtils.DEFAULT_CONFIG_FILE;
	boolean useConfigFile = true;
	boolean includeDefaultPrivateKeyLocations = true;
	boolean strictHostKeyChecking = true;
	int port = SSHUtils.DEFAULT_PORT;
	int waitForClosedSleepMillis = DEFAULT_SLEEP_MILLIS;
	String encoding = DEFAULT_ENCODING;
	String username;
	String hostname;
	Integer connectTimeout;
	List<File> privateKeys;
	List<String> privateKeyStrings;
	Properties options;

	protected Session session;
	protected ChannelSftp sftp;

	@Override
	public synchronized void open() throws IOException {
		logOpen();
		validate();
		try {
			JSch jsch = getJSch();
			this.session = openSession(jsch);
			this.sftp = openSftpChannel(session, connectTimeout);
		} catch (JSchException e) {
			throw new IOException("Unexpected error opening secure channel", e);
		}
	}

	@Override
	public synchronized void close() {
		logger.info("Closing secure channel [{}]", ChannelUtils.getLocation(username, hostname));
		closeQuietly(sftp);
		closeQuietly(session);
	}

	@Override
	public Result executeCommand(String command) {
		return executeCommand(command, null);
	}

	@Override
	public Result executeCommand(String command, String stdin) {
		Assert.notBlank(command);
		ChannelExec exec = null;
		InputStream stdoutStream = null;
		ByteArrayOutputStream stderrStream = null;
		InputStream stdinStream = null;
		try {
			// Preserve start time
			long start = System.currentTimeMillis();
			// Open an exec channel
			exec = (ChannelExec) session.openChannel(EXEC);
			// Convert the command string to bytes
			byte[] commandBytes = Str.getBytes(command, encoding);
			// Store the command on the exec channel
			exec.setCommand(commandBytes);
			// Prepare the stdin stream
			stdinStream = getInputStream(stdin, encoding);
			// Prepare the stderr stream
			stderrStream = new ByteArrayOutputStream();
			// Get the stdout stream from the ChannelExec object
			stdoutStream = exec.getInputStream();
			// Update the ChannelExec object with the stdin stream
			exec.setInputStream(stdinStream);
			// Update the ChannelExec object with the stderr stream
			exec.setErrStream(stderrStream);
			// Execute the command.
			// This consumes anything from stdin and stores output in stdout/stderr
			connect(exec, null);
			// Convert stdout and stderr to String's
			String stdout = Str.getString(IOUtils.toByteArray(stdoutStream), encoding);
			String stderr = Str.getString(stderrStream.toByteArray(), encoding);
			// Make sure the channel is closed
			waitForClosed(exec, waitForClosedSleepMillis);
			// Return the result of executing the command
			return ChannelUtils.getExecutionResult(exec.getExitStatus(), start, command, stdin, stdout, stderr, encoding);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// Cleanup
			IOUtils.closeQuietly(stdinStream);
			IOUtils.closeQuietly(stdoutStream);
			IOUtils.closeQuietly(stderrStream);
			closeQuietly(exec);
		}
	}

	@Override
	public void executeNoWait(String command) {
		Assert.notBlank(command);
		ChannelExec exec = null;
		try {
			// Open an exec channel
			exec = (ChannelExec) session.openChannel(EXEC);
			// Convert the command string to bytes
			byte[] commandBytes = Str.getBytes(command, encoding);
			// Store the command on the exec channel
			exec.setCommand(commandBytes);
			// Execute the command.
			// This consumes anything from stdin and stores output in stdout/stderr
			connect(exec, null);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			closeQuietly(exec);
		}
	}

	protected InputStream getInputStream(String s, String encoding) {
		if (s == null) {
			return null;
		} else {
			return new ByteArrayInputStream(Str.getBytes(s, encoding));
		}
	}

	protected void waitForClosed(ChannelExec exec, long millis) {
		while (!exec.isClosed()) {
			sleep(millis);
		}
	}

	protected void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public RemoteFile getWorkingDirectory() {
		try {
			String workingDirectory = sftp.pwd();
			return getMetaData(workingDirectory);
		} catch (SftpException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void validate() {
		Assert.isTrue(SSHUtils.isValidPort(port));
		Assert.notBlank(hostname);
		Assert.notBlank(encoding);
	}

	protected void logOpen() {
		logger.info("Opening secure channel [{}] encoding={}", ChannelUtils.getLocation(username, hostname), encoding);
		logger.debug("Private key files - {}", CollectionUtils.toEmptyList(privateKeys).size());
		logger.debug("Private key strings - {}", CollectionUtils.toEmptyList(privateKeyStrings).size());
		logger.debug("Private key config file - {}", config);
		logger.debug("Private key config file use - {}", useConfigFile);
		logger.debug("Include default private key locations - {}", includeDefaultPrivateKeyLocations);
		logger.debug("Known hosts file - {}", knownHosts);
		logger.debug("Port - {}", port);
		logger.debug("Connect timeout - {}", connectTimeout);
		logger.debug("Strict host key checking - {}", strictHostKeyChecking);
		logger.debug("Configuring channel with {} custom options", PropertyUtils.toEmpty(options).size());
		if (options != null) {
			PropertyUtils.debug(options);
		}
	}

	protected ChannelSftp openSftpChannel(Session session, Integer timeout) throws JSchException {
		ChannelSftp sftp = (ChannelSftp) session.openChannel(SFTP);
		connect(sftp, timeout);
		return sftp;
	}

	protected void connect(Channel channel, Integer timeout) throws JSchException {
		if (timeout == null) {
			channel.connect();
		} else {
			channel.connect(timeout);
		}
	}

	protected void closeQuietly(Session session) {
		if (session != null) {
			session.disconnect();
		}
	}

	protected void closeQuietly(Channel channel) {
		if (channel != null) {
			channel.disconnect();
		}
	}

	@Deprecated
	protected Properties getSessionProperties(Properties options, boolean strictHostKeyChecking) {
		Properties properties = new Properties();
		if (options != null) {
			properties.putAll(options);
		}
		if (!strictHostKeyChecking) {
			properties.setProperty(SSHUtils.STRICT_HOST_KEY_CHECKING, SSHUtils.NO);
		}
		return properties;
	}

	protected Session openSession(JSch jsch) throws JSchException {
		Session session = jsch.getSession(username, hostname, port);
		session.setConfig(getSessionProperties(options, strictHostKeyChecking));
		if (connectTimeout == null) {
			session.connect();
		} else {
			session.connect(connectTimeout);
		}
		return session;
	}

	protected JSch getJSch() throws JSchException {
		List<File> uniquePrivateKeyFiles = getUniquePrivateKeyFiles();
		logger.debug("Located {} private keys on the file system", uniquePrivateKeyFiles.size());
		JSch jsch = getJSch(uniquePrivateKeyFiles, privateKeyStrings);
		if (strictHostKeyChecking && knownHosts != null) {
			String path = LocationUtils.getCanonicalPath(knownHosts);
			jsch.setKnownHosts(path);
		}
		return jsch;
	}

	protected JSch getJSch(List<File> privateKeys, List<String> privateKeyStrings) throws JSchException {
		JSch jsch = new JSch();
		for (File privateKey : privateKeys) {
			String path = LocationUtils.getCanonicalPath(privateKey);
			jsch.addIdentity(path);
		}
		int count = 0;
		for (String privateKeyString : CollectionUtils.toEmptyList(privateKeyStrings)) {
			String name = "privateKeyString-" + Integer.toString(count++);
			byte[] bytes = Str.getBytes(privateKeyString, encoding);
			jsch.addIdentity(name, bytes, null, null);
		}
		return jsch;
	}

	@Deprecated
	protected List<File> getUniquePrivateKeyFiles() {
		List<String> paths = new ArrayList<String>();
		if (privateKeys != null) {
			for (File privateKey : privateKeys) {
				paths.add(LocationUtils.getCanonicalPath(privateKey));
			}
		}
		if (useConfigFile) {
			for (String path : SSHUtils.getFilenames(config)) {
				paths.add(path);
			}
		}
		if (includeDefaultPrivateKeyLocations) {
			for (String path : SSHUtils.PRIVATE_KEY_DEFAULTS) {
				paths.add(path);
			}
		}
		List<String> uniquePaths = CollectionUtils.getUniqueStrings(paths);
		return SSHUtils.getExistingAndReadable(uniquePaths);
	}

	@Override
	public RemoteFile getMetaData(String absolutePath) {
		Assert.hasLength(absolutePath);
		RemoteFile file = new RemoteFile();
		file.setAbsolutePath(absolutePath);
		fillInAttributes(file, absolutePath);
		return file;
	}

	@Override
	public void deleteFile(String absolutePath) {
		RemoteFile file = getMetaData(absolutePath);
		if (isStatus(file, Status.MISSING)) {
			return;
		}
		if (file.isDirectory()) {
			throw new IllegalArgumentException("[" + ChannelUtils.getLocation(username, hostname, file) + "] is a directory.");
		}
		try {
			sftp.rm(absolutePath);
		} catch (SftpException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public boolean exists(String absolutePath) {
		RemoteFile file = getMetaData(absolutePath);
		return isStatus(file, Status.EXISTS);
	}

	@Override
	public boolean isDirectory(String absolutePath) {
		RemoteFile file = getMetaData(absolutePath);
		return isStatus(file, Status.EXISTS) && file.isDirectory();
	}

	protected void fillInAttributes(RemoteFile file) {
		fillInAttributes(file, file.getAbsolutePath());
	}

	protected void fillInAttributes(RemoteFile file, String path) {
		try {
			SftpATTRS attributes = sftp.stat(path);
			fillInAttributes(file, attributes);
		} catch (SftpException e) {
			handleNoSuchFileException(file, e);
		}
	}

	protected void fillInAttributes(RemoteFile file, SftpATTRS attributes) {
		file.setDirectory(attributes.isDir());
		file.setPermissions(attributes.getPermissions());
		file.setUserId(attributes.getUId());
		file.setGroupId(attributes.getGId());
		file.setSize(attributes.getSize());
		file.setStatus(Status.EXISTS);
	}

	@Override
	public void copyFile(File source, RemoteFile destination) {
		Assert.notNull(source);
		Assert.isTrue(source.exists());
		Assert.isTrue(!source.isDirectory());
		Assert.isTrue(source.canRead());
		copyLocationToFile(LocationUtils.getCanonicalURLString(source), destination);
	}

	@Override
	public void copyFileToDirectory(File source, RemoteFile destination) {
		RemoteFile clone = clone(destination);
		String filename = source.getName();
		addFilenameToPath(clone, filename);
		copyFile(source, clone);
	}

	protected RemoteFile clone(RemoteFile file) {
		try {
			RemoteFile clone = new RemoteFile();
			BeanUtils.copyProperties(clone, file);
			return clone;
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void copyLocationToFile(String location, RemoteFile destination) {
		Assert.notNull(location);
		Assert.isTrue(LocationUtils.exists(location), location + " does not exist");
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			copyInputStreamToFile(in, destination);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public void copyStringToFile(String string, RemoteFile destination) {
		Assert.notNull(string);
		Assert.notBlank(encoding);
		InputStream in = new ByteArrayInputStream(Str.getBytes(string, encoding));
		copyInputStreamToFile(in, destination);
		IOUtils.closeQuietly(in);
	}

	@Override
	public String toString(RemoteFile source) {
		Assert.notNull(source);
		Assert.hasText(source.getAbsolutePath());
		Assert.notBlank(encoding);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			copyFile(source, out);
			return out.toString(encoding);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public void copyInputStreamToFile(InputStream source, RemoteFile destination) {
		Assert.notNull(source);
		try {
			createDirectories(destination);
			sftp.put(source, destination.getAbsolutePath());
		} catch (SftpException e) {
			throw new IllegalStateException(e);
		}
	}

	protected String getAbsolutePath(String absolutePath, String filename) {
		if (StringUtils.endsWith(absolutePath, FORWARDSLASH)) {
			return absolutePath + filename;
		} else {
			return absolutePath + FORWARDSLASH + filename;
		}
	}

	protected void addFilenameToPath(RemoteFile destination, String filename) {
		String newAbsolutePath = getAbsolutePath(destination.getAbsolutePath(), filename);
		destination.setAbsolutePath(newAbsolutePath);
		destination.setDirectory(false);
	}

	@Override
	public void copyLocationToDirectory(String location, RemoteFile destination) {
		RemoteFile clone = clone(destination);
		String filename = LocationUtils.getFilename(location);
		addFilenameToPath(clone, filename);
		copyLocationToFile(location, clone);
	}

	@Override
	public void copyFile(RemoteFile source, File destination) {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(FileUtils.openOutputStream(destination));
			copyFile(source, out);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public void copyRemoteFile(String absolutePath, OutputStream out) throws IOException {
		try {
			sftp.get(absolutePath, out);
		} catch (SftpException e) {
			throw new IOException("Unexpected IO error", e);
		}
	}

	@Override
	public void copyFile(RemoteFile source, OutputStream out) throws IOException {
		copyRemoteFile(source.getAbsolutePath(), out);
	}

	@Override
	public void copyFileToDirectory(RemoteFile source, File destination) {
		String filename = FilenameUtils.getName(source.getAbsolutePath());
		File newDestination = new File(destination, filename);
		copyFile(source, newDestination);
	}

	@Override
	public void createDirectory(RemoteFile dir) {
		Assert.isTrue(dir.isDirectory());
		try {
			createDirectories(dir);
		} catch (SftpException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void createDirectories(RemoteFile file) throws SftpException {
		boolean directoryIndicator = file.isDirectory();
		fillInAttributes(file);
		validate(file, directoryIndicator);
		List<String> directories = LocationUtils.getNormalizedPathFragments(file.getAbsolutePath(), file.isDirectory());
		for (String directory : directories) {
			RemoteFile parentDir = new RemoteFile(directory);
			fillInAttributes(parentDir);
			validate(parentDir, true);
			if (!isStatus(parentDir, Status.EXISTS)) {
				mkdir(parentDir);
			}
		}
	}

	protected boolean isStatus(RemoteFile file, Status status) {
		return file.getStatus().equals(status);
	}

	protected void validate(RemoteFile file, Status... allowed) {
		for (Status status : allowed) {
			if (isStatus(file, status)) {
				return;
			}
		}
		throw new IllegalArgumentException("Invalid status - " + file.getStatus());
	}

	protected boolean validate(RemoteFile file, boolean directoryIndicator) {
		// Make sure file is not in UNKNOWN status
		validate(file, Status.MISSING, Status.EXISTS);

		// Convenience flags
		boolean missing = isStatus(file, Status.MISSING);
		boolean exists = isStatus(file, Status.EXISTS);

		// Compare the actual file type to the file type it needs to be
		boolean correctFileType = file.isDirectory() == directoryIndicator;

		// Is everything as it should be?
		boolean valid = missing || exists && correctFileType;
		if (valid) {
			return true;
		} else {
			// Something has gone awry
			throw new IllegalArgumentException(getInvalidExistingFileMessage(file));
		}
	}

	protected String getInvalidExistingFileMessage(RemoteFile existing) {
		if (existing.isDirectory()) {
			return "[" + ChannelUtils.getLocation(username, hostname, existing) + "] is an existing directory. Unable to create file.";
		} else {
			return "[" + ChannelUtils.getLocation(username, hostname, existing) + "] is an existing file. Unable to create directory.";
		}
	}

	protected void mkdir(RemoteFile dir) {
		try {
			String path = dir.getAbsolutePath();
			logger.debug("Creating [{}]", path);
			sftp.mkdir(path);
			setAttributes(dir);
		} catch (SftpException e) {
			throw new IllegalStateException(e);
		}
	}

	protected void setAttributes(RemoteFile file) throws SftpException {
		String path = file.getAbsolutePath();
		if (file.getPermissions() != null) {
			sftp.chmod(file.getPermissions(), path);
		}
		if (file.getGroupId() != null) {
			sftp.chgrp(file.getGroupId(), path);
		}
		if (file.getUserId() != null) {
			sftp.chown(file.getUserId(), path);
		}
	}

	protected void handleNoSuchFileException(RemoteFile file, SftpException e) {
		if (isNoSuchFileException(e)) {
			file.setStatus(Status.MISSING);
		} else {
			throw new IllegalStateException(e);
		}
	}

	protected boolean isNoSuchFileException(SftpException exception) {
		return exception.id == ChannelSftp.SSH_FX_NO_SUCH_FILE;
	}

	public File getKnownHosts() {
		return knownHosts;
	}

	public void setKnownHosts(File knownHosts) {
		this.knownHosts = knownHosts;
	}

	public File getConfig() {
		return config;
	}

	public void setConfig(File config) {
		this.config = config;
	}

	public boolean isIncludeDefaultPrivateKeyLocations() {
		return includeDefaultPrivateKeyLocations;
	}

	public void setIncludeDefaultPrivateKeyLocations(boolean includeDefaultPrivateKeyLocations) {
		this.includeDefaultPrivateKeyLocations = includeDefaultPrivateKeyLocations;
	}

	public boolean isStrictHostKeyChecking() {
		return strictHostKeyChecking;
	}

	public void setStrictHostKeyChecking(boolean strictHostKeyChecking) {
		this.strictHostKeyChecking = strictHostKeyChecking;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public List<File> getPrivateKeys() {
		return privateKeys;
	}

	public void setPrivateKeys(List<File> privateKeys) {
		this.privateKeys = privateKeys;
	}

	public Properties getOptions() {
		return options;
	}

	public void setOptions(Properties options) {
		this.options = options;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getWaitForClosedSleepMillis() {
		return waitForClosedSleepMillis;
	}

	public void setWaitForClosedSleepMillis(int waitForClosedSleepMillis) {
		this.waitForClosedSleepMillis = waitForClosedSleepMillis;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public List<String> getPrivateKeyStrings() {
		return privateKeyStrings;
	}

	public void setPrivateKeyStrings(List<String> privateKeyStrings) {
		this.privateKeyStrings = privateKeyStrings;
	}

	public boolean isUseConfigFile() {
		return useConfigFile;
	}

	public void setUseConfigFile(boolean useConfigFile) {
		this.useConfigFile = useConfigFile;
	}

}
