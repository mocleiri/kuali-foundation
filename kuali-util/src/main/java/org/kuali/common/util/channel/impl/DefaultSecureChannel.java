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
package org.kuali.common.util.channel.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.CommandResult;
import org.kuali.common.util.channel.model.CopyDirection;
import org.kuali.common.util.channel.model.CopyResult;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.model.Status;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.channel.util.SSHUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
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

	private final Session session;
	private final ChannelSftp sftp;
	private final ChannelContext context;

	public DefaultSecureChannel(ChannelContext context) throws IOException {
		Assert.noNulls(context);
		this.context = context;
		logOpen();
		try {
			JSch jsch = getJSch();
			this.session = openSession(jsch);
			this.sftp = openSftpChannel(session, context.getConnectTimeout());
		} catch (JSchException e) {
			throw new IOException("Unexpected error opening secure channel", e);
		}
	}

	@Override
	public void close() {
		logger.info("Closing secure channel [{}]", ChannelUtils.getLocation(context.getUsername(), context.getHostname()));
		closeQuietly(sftp);
		closeQuietly(session);
	}

	@Override
	public CommandResult executeCommand(String command) {
		return executeCommand(command, null);
	}

	@Override
	public CommandResult executeCommand(String command, Optional<String> stdin) {
		Assert.noBlanks(command);
		ChannelExec exec = null;
		InputStream stdoutStream = null;
		ByteArrayOutputStream stderrStream = null;
		Optional<InputStream> stdinStream = null;
		try {
			// Preserve start time
			long start = System.currentTimeMillis();
			// Open an exec channel
			exec = getChannelExec();
			// Convert the command string to bytes
			byte[] commandBytes = Str.getBytes(command, context.getEncoding());
			// Store the command on the exec channel
			exec.setCommand(commandBytes);
			// Prepare the stdin stream
			stdinStream = getInputStream(stdin, context.getEncoding());
			// Prepare the stderr stream
			stderrStream = new ByteArrayOutputStream();
			// Get the stdout stream from the ChannelExec object
			stdoutStream = exec.getInputStream();
			// Update the ChannelExec object with the stdin stream
			exec.setInputStream(stdinStream.orNull());
			// Update the ChannelExec object with the stderr stream
			exec.setErrStream(stderrStream);
			logger.debug("Executing [{}], stdin [{}]", command, NullUtils.trimToNone(stdin.orNull()));
			// Execute the command.
			// This consumes anything from stdin and stores output in stdout/stderr
			connect(exec, Optional.<Integer> absent());
			// Convert stdout and stderr to String's
			Optional<String> stdout = Optional.fromNullable(Str.getString(IOUtils.toByteArray(stdoutStream), context.getEncoding()));
			Optional<String> stderr = Optional.fromNullable(Str.getString(stderrStream.toByteArray(), context.getEncoding()));
			// Make sure the channel is closed
			waitForClosed(exec, context.getWaitForClosedSleepMillis());
			// Return the result of executing the command
			CommandResult result = new CommandResult(command, exec.getExitStatus(), stdin, stdout, stderr, context.getEncoding(), start, System.currentTimeMillis());
			if (context.isEcho()) {
				String elapsed = FormatUtils.getTime(result.getElapsed());
				logger.info("{} - [{}]", command, elapsed);
			}
			return result;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			// Cleanup
			IOUtils.closeQuietly(stdinStream.orNull());
			IOUtils.closeQuietly(stdoutStream);
			IOUtils.closeQuietly(stderrStream);
			closeQuietly(exec);
		}
	}

	protected ChannelExec getChannelExec() throws JSchException {
		ChannelExec exec = (ChannelExec) session.openChannel(EXEC);
		if (context.isRequestPseudoTerminal()) {
			exec.setPty(true);
		}
		return exec;
	}

	@Override
	public void executeNoWait(String command) {
		Assert.notBlank(command);
		ChannelExec exec = null;
		try {
			// Open an exec channel
			exec = getChannelExec();
			// Convert the command string to bytes
			byte[] commandBytes = Str.getBytes(command, context.getEncoding());
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

	protected Optional<InputStream> getInputStream(Optional<String> string, String encoding) {
		if (!string.isPresent()) {
			return Optional.<InputStream> absent();
		} else {
			InputStream in = new ByteArrayInputStream(Str.getBytes(string.get(), encoding));
			return Optional.of(in);
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
		logger.info("Opening secure channel [{}] encoding={}", ChannelUtils.getLocation(context.getUsername(), context.getHostname()), context.getEncoding());
		logger.debug("Private key files - {}", context.getPrivateKeyFiles().size());
		logger.debug("Private key strings - {}", context.getPrivateKeys().size());
		logger.debug("Private key config file - {}", context.getConfig());
		logger.debug("Private key config file use - {}", context.isUseConfigFile());
		logger.debug("Include default private key locations - {}", context.isIncludeDefaultPrivateKeyLocations());
		logger.debug("Known hosts file - {}", context.getKnownHosts());
		logger.debug("Port - {}", context.getPort());
		if (context.getConnectTimeout().isPresent()) {
			logger.debug("Connect timeout - {}", context.getConnectTimeout().get());
		}
		logger.debug("Strict host key checking - {}", context.isStrictHostKeyChecking());
		logger.debug("Configuring channel with {} custom options", context.getOptions().size());
		PropertyUtils.debug(context.getOptions());
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

	protected Session openSession(JSch jsch) throws JSchException {
		Session session = jsch.getSession(context.getUsername().orNull(), context.getHostname(), context.getPort());

		session.setConfig(context.getOptions());
		if (context.getConnectTimeout().isPresent()) {
			session.connect(context.getConnectTimeout().get());
		} else {
			session.connect();
		}
		return session;
	}

	protected JSch getJSch() {
		try {
			JSch jsch = getJSch(context.getPrivateKeyFiles(), context.getPrivateKeys());
			File knownHosts = context.getKnownHosts();
			if (context.isUseKnownHosts() && knownHosts.exists()) {
				String path = LocationUtils.getCanonicalPath(knownHosts);
				jsch.setKnownHosts(path);
			}
			return jsch;
		} catch (JSchException e) {
			throw new IllegalStateException("Unexpected error", e);
		}
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
			byte[] bytes = Str.getBytes(privateKeyString, context.getEncoding());
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
			throw new IllegalArgumentException("[" + ChannelUtils.getLocation(context.getUsername(), context.getHostname(), file) + "] is a directory.");
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
	public CopyResult copyFile(File source, RemoteFile destination) {
		Assert.notNull(source);
		Assert.exists(source);
		Assert.isFalse(source.isDirectory(), "[" + source + "] is a directory");
		Assert.isTrue(source.canRead(), "[" + source + "] not readable");
		return copyLocationToFile(LocationUtils.getCanonicalURLString(source), destination);
	}

	@Override
	public CopyResult copyFileToDirectory(File source, RemoteFile directory) {
		String filename = source.getName();
		String absolutePath = getAbsolutePath(directory.getAbsolutePath(), filename);
		RemoteFile file = new RemoteFile.Builder(absolutePath).clone(directory).build();
		return copyFile(source, file);
	}

	@Override
	public CopyResult copyLocationToFile(String location, RemoteFile destination) {
		Assert.notNull(location);
		Assert.isTrue(LocationUtils.exists(location), location + " does not exist");
		InputStream in = null;
		try {
			long start = System.currentTimeMillis();
			in = LocationUtils.getInputStream(location);
			copyInputStreamToFile(in, destination);
			RemoteFile meta = getMetaData(destination.getAbsolutePath());
			return new CopyResult(start, meta.getSize().get(), CopyDirection.TO_REMOTE);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public CopyResult copyStringToFile(String string, RemoteFile destination) {
		Assert.notNull(string);
		InputStream in = new ByteArrayInputStream(Str.getBytes(string, context.getEncoding()));
		CopyResult result = copyInputStreamToFile(in, destination);
		IOUtils.closeQuietly(in);
		return result;
	}

	@Override
	public String toString(RemoteFile source) {
		Assert.notNull(source);
		Assert.hasText(source.getAbsolutePath());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			copyFile(source, out);
			return out.toString(context.getEncoding());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public CopyResult copyInputStreamToFile(InputStream source, RemoteFile destination) {
		Assert.notNull(source);
		try {
			long start = System.currentTimeMillis();
			createDirectories(destination);
			sftp.put(source, destination.getAbsolutePath());
			RemoteFile meta = getMetaData(destination.getAbsolutePath());
			return new CopyResult(start, meta.getSize().get(), CopyDirection.TO_REMOTE);
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
	public CopyResult copyLocationToDirectory(String location, RemoteFile directory) {
		String filename = LocationUtils.getFilename(location);
		String absolutePath = getAbsolutePath(directory.getAbsolutePath(), filename);
		RemoteFile file = new RemoteFile.Builder(absolutePath).clone(directory).build();
		return copyLocationToFile(location, file);
	}

	@Override
	public CopyResult copyFile(RemoteFile source, File destination) {
		OutputStream out = null;
		try {
			long start = System.currentTimeMillis();
			out = new BufferedOutputStream(FileUtils.openOutputStream(destination));
			copyFile(source, out);
			return new CopyResult(start, destination.length(), CopyDirection.FROM_REMOTE);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public CopyResult copyRemoteFile(String absolutePath, OutputStream out) throws IOException {
		try {
			long start = System.currentTimeMillis();
			sftp.get(absolutePath, out);
			RemoteFile meta = getMetaData(absolutePath);
			return new CopyResult(start, meta.getSize().get(), CopyDirection.FROM_REMOTE);
		} catch (SftpException e) {
			throw new IOException("Unexpected IO error", e);
		}
	}

	@Override
	public CopyResult copyFile(RemoteFile source, OutputStream out) throws IOException {
		return copyRemoteFile(source.getAbsolutePath(), out);
	}

	@Override
	public CopyResult copyFileToDirectory(RemoteFile source, File destination) {
		String filename = FilenameUtils.getName(source.getAbsolutePath());
		File newDestination = new File(destination, filename);
		return copyFile(source, newDestination);
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
			return "[" + ChannelUtils.getLocation(context.getUsername(), context.getHostname(), existing) + "] is an existing directory. Unable to create file.";
		} else {
			return "[" + ChannelUtils.getLocation(context.getUsername(), context.getHostname(), existing) + "] is an existing file. Unable to create directory.";
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

}
