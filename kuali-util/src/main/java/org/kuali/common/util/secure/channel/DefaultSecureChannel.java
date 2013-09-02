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
package org.kuali.common.util.secure.channel;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.property.ImmutableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public final class DefaultSecureChannel implements SecureChannel {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureChannel.class);

	private static final String SFTP = "sftp";
	private static final String EXEC = "exec";
	private static final String FORWARDSLASH = "/";
	private final File knownHosts;
	private final File config;
	private final boolean useConfigFile;
	private final boolean includeDefaultPrivateKeyLocations;
	private final boolean strictHostKeyChecking;
	private final int port;
	private final int waitForClosedSleepMillis;
	private final String encoding;
	private final String username;
	private final String hostname;
	private final Optional<Integer> connectTimeout;
	private final List<File> privateKeys;
	private final List<String> privateKeyStrings;
	private final Properties options;

	private Session session;
	private ChannelSftp sftp;
	private boolean open = false;

	@Override
	public synchronized void open() throws IOException {
		Assert.isFalse(open, "Already open");
		logOpen();
		try {
			JSch jsch = getJSch();
			this.session = openSession(jsch);
			this.sftp = openSftpChannel(session, connectTimeout);
			this.open = true;
		} catch (JSchException e) {
			throw new IOException("Unexpected error opening secure channel", e);
		}
	}

	@Override
	public synchronized void close() {
		if (!open) {
			return;
		}
		logger.info("Closing secure channel [{}]", ChannelUtils.getLocation(username, hostname));
		closeQuietly(sftp);
		closeQuietly(session);
		this.open = false;
	}

	@Override
	public Result executeCommand(String command) {
		return executeCommand(command, null);
	}

	@Override
	public Result executeCommand(String command, String stdin) {
		Assert.noBlanks(command);
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
			connect(exec, Optional.<Integer> absent());
			// Convert stdout and stderr to String's
			String stdout = Str.getString(IOUtils.toByteArray(stdoutStream), encoding);
			String stderr = Str.getString(stderrStream.toByteArray(), encoding);
			// Make sure the channel is closed
			waitForClosed(exec, waitForClosedSleepMillis);
			// Return the result of executing the command
			return new Result(command, exec.getExitStatus(), stdin, stdout, stderr, encoding, start, System.currentTimeMillis());
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
			connect(exec, Optional.<Integer> absent());
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
			ThreadUtils.sleep(millis);
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

	protected void logOpen() {
		logger.info("Opening secure channel [{}] encoding={}", ChannelUtils.getLocation(username, hostname), encoding);
		logger.debug("Private key files - {}", privateKeys.size());
		logger.debug("Private key strings - {}", privateKeyStrings.size());
		logger.debug("Private key config file - {}", config);
		logger.debug("Private key config file use - {}", useConfigFile);
		logger.debug("Include default private key locations - {}", includeDefaultPrivateKeyLocations);
		logger.debug("Known hosts file - {}", knownHosts);
		logger.debug("Port - {}", port);
		logger.debug("Connect timeout - {}", connectTimeout);
		logger.debug("Strict host key checking - {}", strictHostKeyChecking);
		logger.debug("Configuring channel with {} custom options", options.size());
		PropertyUtils.debug(options);
	}

	protected ChannelSftp openSftpChannel(Session session, Optional<Integer> timeout) throws JSchException {
		ChannelSftp sftp = (ChannelSftp) session.openChannel(SFTP);
		connect(sftp, timeout);
		return sftp;
	}

	protected void connect(Channel channel, Optional<Integer> timeout) throws JSchException {
		if (timeout.isPresent()) {
			channel.connect(timeout.get());
		} else {
			channel.connect();
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

	private static Properties getSessionProperties(Properties options, boolean strictHostKeyChecking) {
		Properties properties = new Properties();
		properties.putAll(options);
		if (!strictHostKeyChecking) {
			properties.setProperty(SSHUtils.STRICT_HOST_KEY_CHECKING, SSHUtils.NO);
		}
		return properties;
	}

	protected Session openSession(JSch jsch) throws JSchException {
		Session session = jsch.getSession(username, hostname, port);
		session.setConfig(options);
		if (connectTimeout.isPresent()) {
			session.connect(connectTimeout.get());
		} else {
			session.connect();
		}
		return session;
	}

	protected JSch getJSch() throws JSchException {
		JSch jsch = getJSch(privateKeys, privateKeyStrings);
		if (strictHostKeyChecking && knownHosts.exists()) {
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
		for (String privateKeyString : privateKeyStrings) {
			String name = "privateKeyString-" + Integer.toString(count++);
			byte[] bytes = Str.getBytes(privateKeyString, encoding);
			jsch.addIdentity(name, bytes, null, null);
		}
		return jsch;
	}

	protected static List<File> getUniquePrivateKeyFiles(List<File> privateKeys, boolean useConfigFile, File config, boolean includeDefaultPrivateKeyLocations) {
		List<String> paths = new ArrayList<String>();
		for (File privateKey : privateKeys) {
			paths.add(LocationUtils.getCanonicalPath(privateKey));
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
		Assert.noBlanks(absolutePath);
		return fillInAttributes(absolutePath);
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

	protected RemoteFile fillInAttributes(String path) {
		try {
			SftpATTRS attributes = sftp.stat(path);
			return fillInAttributes(path, attributes);
		} catch (SftpException e) {
			return handleNoSuchFileException(path, e);
		}
	}

	protected RemoteFile fillInAttributes(String path, SftpATTRS attributes) {
		boolean directory = attributes.isDir();
		int permissions = attributes.getPermissions();
		int userId = attributes.getUId();
		int groupId = attributes.getGId();
		long size = attributes.getSize();
		Status status = Status.EXISTS;
		return new RemoteFile.Builder(path).directory(directory).permissions(permissions).userId(userId).groupId(groupId).size(size).status(status).build();
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
	public void copyFileToDirectory(File source, RemoteFile directory) {
		String filename = source.getName();
		String absolutePath = getAbsolutePath(directory.getAbsolutePath(), filename);
		RemoteFile file = new RemoteFile.Builder(absolutePath).clone(directory).build();
		copyFile(source, file);
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

	@Override
	public void copyLocationToDirectory(String location, RemoteFile directory) {
		String filename = LocationUtils.getFilename(location);
		String absolutePath = getAbsolutePath(directory.getAbsolutePath(), filename);
		RemoteFile file = new RemoteFile.Builder(absolutePath).clone(directory).build();
		copyLocationToFile(location, file);
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
		RemoteFile remoteFile = fillInAttributes(file.getAbsolutePath());
		validate(remoteFile, directoryIndicator);
		List<String> directories = LocationUtils.getNormalizedPathFragments(file.getAbsolutePath(), file.isDirectory());
		for (String directory : directories) {
			RemoteFile parentDir = fillInAttributes(directory);
			validate(parentDir, true);
			if (!isStatus(parentDir, Status.EXISTS)) {
				mkdir(parentDir);
			}
		}
	}

	protected boolean isStatus(RemoteFile file, Status status) {
		Optional<Status> remoteStatus = file.getStatus();
		if (remoteStatus.isPresent()) {
			return remoteStatus.get().equals(status);
		} else {
			return false;
		}
	}

	protected void validate(RemoteFile file, boolean directoryIndicator) {
		// Make sure status has been filled in
		Assert.isTrue(file.getStatus().isPresent());

		// Convenience flags
		boolean missing = isStatus(file, Status.MISSING);
		boolean exists = isStatus(file, Status.EXISTS);

		// It it is supposed to be a directory, make sure it's a directory
		// If it is supposed to be a regular file, make sure it's a regular file
		boolean correctFileType = file.isDirectory() == directoryIndicator;

		// Is everything as it should be?
		boolean valid = missing || exists && correctFileType;

		Assert.isTrue(valid, getInvalidExistingFileMessage(file));
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
		if (file.getPermissions().isPresent()) {
			sftp.chmod(file.getPermissions().get(), path);
		}
		if (file.getGroupId().isPresent()) {
			sftp.chgrp(file.getGroupId().get(), path);
		}
		if (file.getUserId().isPresent()) {
			sftp.chown(file.getUserId().get(), path);
		}
	}

	protected RemoteFile handleNoSuchFileException(String path, SftpException e) {
		if (isNoSuchFileException(e)) {
			return new RemoteFile.Builder(path).status(Status.MISSING).build();
		} else {
			throw new IllegalStateException(e);
		}
	}

	protected boolean isNoSuchFileException(SftpException exception) {
		return exception.id == ChannelSftp.SSH_FX_NO_SUCH_FILE;
	}

	public static class Builder {

		// Required
		private final String username;
		private final String hostname;

		// Optional
		private File knownHosts = SSHUtils.DEFAULT_KNOWN_HOSTS;
		private File config = SSHUtils.DEFAULT_CONFIG_FILE;
		private boolean useConfigFile = true;
		private boolean includeDefaultPrivateKeyLocations = true;
		private boolean strictHostKeyChecking = true;
		private int port = SSHUtils.DEFAULT_PORT;
		private int waitForClosedSleepMillis = 10;
		private String encoding = "UTF-8";
		private Optional<Integer> connectTimeout = Optional.absent();
		private Properties options = ImmutableProperties.of();
		private List<File> privateKeys = ImmutableList.of();
		private List<String> privateKeyStrings = ImmutableList.of();

		public DefaultSecureChannel build() {
			Assert.noBlanks(username, hostname, encoding);
			Assert.noNulls(knownHosts, config, connectTimeout, options, privateKeys, privateKeyStrings);
			Assert.isTrue(SSHUtils.isValidPort(port), port + " is invalid");
			Assert.isTrue(waitForClosedSleepMillis > 0, "waitForClosedSleepMillis must be a positive integer");
			if (connectTimeout.isPresent()) {
				Assert.isTrue(connectTimeout.get() > 0, "connectTimeout must be a positive integer");
			}
			if (useConfigFile) {
				Assert.exists(config);
				Assert.isTrue(config.canRead(), "[" + config + "] exists but is not readable");
			}
			this.options = ImmutableProperties.of(getSessionProperties(options, strictHostKeyChecking));
			this.privateKeys = ImmutableList.copyOf(getUniquePrivateKeyFiles(privateKeys, useConfigFile, config, includeDefaultPrivateKeyLocations));
			this.privateKeyStrings = ImmutableList.copyOf(privateKeyStrings);
			return new DefaultSecureChannel(this);
		}

		public Builder(String username, String hostname) {
			this.username = username;
			this.hostname = hostname;
		}

		public Builder knownHosts(File knownHosts) {
			this.knownHosts = knownHosts;
			return this;
		}

		public Builder config(File config) {
			this.config = config;
			return this;
		}

		public Builder useConfigFile(boolean useConfigFile) {
			this.useConfigFile = useConfigFile;
			return this;
		}

		public Builder includeDefaultPrivateKeyLocations(boolean includeDefaultPrivateKeyLocations) {
			this.includeDefaultPrivateKeyLocations = includeDefaultPrivateKeyLocations;
			return this;
		}

		public Builder strictHostKeyChecking(boolean strictHostKeyChecking) {
			this.strictHostKeyChecking = strictHostKeyChecking;
			return this;
		}

		public Builder port(int port) {
			this.port = port;
			return this;
		}

		public Builder waitForClosedSleepMillis(int waitForClosedSleepMillis) {
			this.waitForClosedSleepMillis = waitForClosedSleepMillis;
			return this;
		}

		public Builder encoding(String encoding) {
			this.encoding = encoding;
			return this;
		}

		public Builder connectTimeout(int connectTimeout) {
			this.connectTimeout = Optional.of(connectTimeout);
			return this;
		}

		public Builder options(Properties options) {
			this.options = options;
			return this;
		}

		public Builder privateKeys(List<File> privateKeys) {
			this.privateKeys = privateKeys;
			return this;
		}

		public Builder privateKeyStrings(List<String> privateKeyStrings) {
			this.privateKeyStrings = privateKeyStrings;
			return this;
		}

	}

	private DefaultSecureChannel(Builder builder) {
		this.username = builder.username;
		this.hostname = builder.hostname;
		this.knownHosts = builder.knownHosts;
		this.config = builder.config;
		this.useConfigFile = builder.useConfigFile;
		this.includeDefaultPrivateKeyLocations = builder.includeDefaultPrivateKeyLocations;
		this.strictHostKeyChecking = builder.strictHostKeyChecking;
		this.port = builder.port;
		this.waitForClosedSleepMillis = builder.waitForClosedSleepMillis;
		this.encoding = builder.encoding;
		this.connectTimeout = builder.connectTimeout;
		this.options = builder.options;
		this.privateKeys = builder.privateKeys;
		this.privateKeyStrings = builder.privateKeyStrings;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public ChannelSftp getSftp() {
		return sftp;
	}

	public void setSftp(ChannelSftp sftp) {
		this.sftp = sftp;
	}

	public File getKnownHosts() {
		return knownHosts;
	}

	public File getConfig() {
		return config;
	}

	public boolean isUseConfigFile() {
		return useConfigFile;
	}

	public boolean isIncludeDefaultPrivateKeyLocations() {
		return includeDefaultPrivateKeyLocations;
	}

	public boolean isStrictHostKeyChecking() {
		return strictHostKeyChecking;
	}

	public int getPort() {
		return port;
	}

	public int getWaitForClosedSleepMillis() {
		return waitForClosedSleepMillis;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getUsername() {
		return username;
	}

	public String getHostname() {
		return hostname;
	}

	public Optional<Integer> getConnectTimeout() {
		return connectTimeout;
	}

	public List<File> getPrivateKeys() {
		return privateKeys;
	}

	public List<String> getPrivateKeyStrings() {
		return privateKeyStrings;
	}

	public Properties getOptions() {
		return options;
	}

}
