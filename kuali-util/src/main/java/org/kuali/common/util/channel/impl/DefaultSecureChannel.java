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

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static org.kuali.common.util.CollectionUtils.toCSV;
import static org.kuali.common.util.base.Exceptions.illegalState;
import static org.kuali.common.util.base.Precondition.checkNotNull;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import org.kuali.common.util.base.Threads;
import org.kuali.common.util.channel.api.SecureChannel;
import org.kuali.common.util.channel.model.ChannelContext;
import org.kuali.common.util.channel.model.CommandContext;
import org.kuali.common.util.channel.model.CommandResult;
import org.kuali.common.util.channel.model.CopyDirection;
import org.kuali.common.util.channel.model.CopyResult;
import org.kuali.common.util.channel.model.RemoteFile;
import org.kuali.common.util.channel.model.Status;
import org.kuali.common.util.channel.util.ChannelUtils;
import org.kuali.common.util.channel.util.SSHUtils;
import org.slf4j.Logger;

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

	private static final Logger logger = newLogger();

	private static final String SFTP = "sftp";
	private static final String EXEC = "exec";
	private static final String FORWARDSLASH = "/";

	private final Session session;
	private final ChannelSftp sftp;
	private final ChannelContext context;

	private boolean closed = false;

	public DefaultSecureChannel(ChannelContext context) throws IOException {
		checkNotNull(context, "context");
		this.context = context;
		log();
		try {
			JSch jsch = getJSch();
			this.session = openSession(jsch);
			this.sftp = openSftpChannel(session, context.getConnectTimeout());
		} catch (JSchException e) {
			throw new IOException("Unexpected error opening secure channel", e);
		}
	}

	@Override
	public synchronized void close() {
		if (closed) {
			return;
		}
		if (context.isEcho()) {
			logger.info("Closing secure channel [{}]", ChannelUtils.getLocation(context.getUsername(), context.getHostname()));
		} else {
			logger.debug("Closing secure channel [{}]", ChannelUtils.getLocation(context.getUsername(), context.getHostname()));
		}
		closeQuietly(sftp);
		closeQuietly(session);
		this.closed = true;
	}

	@Override
	public List<CommandResult> exec(String... commands) {
		List<CommandResult> results = new ArrayList<CommandResult>();
		List<String> copy = ImmutableList.copyOf(commands);
		for (String command : copy) {
			CommandResult result = exec(command);
			results.add(result);
		}
		return results;
	}

	@Override
	public List<CommandResult> exec(CommandContext... contexts) {
		List<CommandResult> results = new ArrayList<CommandResult>();
		List<CommandContext> copy = ImmutableList.copyOf(contexts);
		for (CommandContext context : copy) {
			CommandResult result = exec(context);
			results.add(result);
		}
		return results;
	}

	@Override
	public CommandResult exec(String command) {
		return exec(new CommandContext.Builder(command).build());
	}

	@Override
	public CommandResult exec(CommandContext context) {
		StreamHandler handler = new StreamHandler(context);
		ChannelExec exec = null;
		try {
			// Echo the command, if requested
			if (this.context.isEcho()) {
				logger.info(format("[%s]", new String(context.getCommand(), this.context.getEncoding())));
			}
			// Preserve start time
			long start = currentTimeMillis();
			// Open an exec channel
			exec = getChannelExec();
			// Convert the command string to a byte array and store it on the exec channel
			exec.setCommand(context.getCommand());
			// Update the ChannelExec object with the stdin stream
			exec.setInputStream(context.getStdin().orNull());
			// Setup handling of stdin, stdout, and stderr
			handler.openStreams(exec, this.context.getEncoding());
			// Get ready to consume anything on stdin, and pump stdout/stderr back out to the consumers
			handler.startPumping();
			// This invokes the command on the remote system, consumes whatever is on stdin, and produces output to stdout/stderr
			connect(exec, context.getTimeout());
			// Wait until the channel reaches the "closed" state
			waitForClosed(exec, this.context.getWaitForClosedSleepMillis());
			// Wait for the streams to finish up
			handler.waitUntilDone();
			// Make sure there were no exceptions
			handler.validate();
			// Construct a result object
			CommandResult result = new CommandResult(context.getCommand(), exec.getExitStatus(), start);
			// Validate that things turned out ok (or that we don't care)
			validate(context, result, this.context.getEncoding());
			// Echo the command, if requested
			if (this.context.isDebug()) {
				String elapsed = FormatUtils.getTime(result.getElapsed());
				logger.info(format("[%s] - [elapsed: %s]", new String(context.getCommand(), this.context.getEncoding()), elapsed));
			}
			// Return the result
			return result;
		} catch (Exception e) {
			// Make sure the streams are disabled
			handler.disableQuietly();
			throw new IllegalStateException(e);
		} finally {
			// Clean everything up
			IOUtils.closeQuietly(context.getStdin().orNull());
			closeQuietly(exec);
			handler.closeQuietly();
		}
	}

	protected void validate(CommandContext context, CommandResult result, String encoding) throws UnsupportedEncodingException {
		if (context.isIgnoreExitValue()) {
			return;
		}
		if (context.getSuccessCodes().size() == 0) {
			return;
		}
		List<Integer> codes = context.getSuccessCodes();
		int exitValue = result.getExitValue();
		for (int successCode : codes) {
			if (exitValue == successCode) {
				return;
			}
		}
		String command = new String(context.getCommand(), encoding);
		throw illegalState("\nerror: [%s]\ninvalid exit value [%s].  valid values are [%s]", command, result.getExitValue(), toCSV(context.getSuccessCodes()));
	}

	protected ChannelExec getChannelExec() throws JSchException {
		ChannelExec exec = (ChannelExec) session.openChannel(EXEC);
		if (context.isRequestPseudoTerminal()) {
			exec.setPty(true);
		}
		return exec;
	}

	@Override
	public void execNoWait(String command) {
		execNoWait(Str.getBytes(command, context.getEncoding()));
	}

	@Override
	public void execNoWait(byte[] command) {
		Assert.noNulls(command);
		ChannelExec exec = null;
		try {
			if (context.isEcho()) {
				logger.info("{}", Str.getString(command, context.getEncoding()));
			}
			// Open an exec channel
			exec = getChannelExec();
			// Store the command on the exec channel
			exec.setCommand(command);
			// Execute the command.
			// This consumes anything from stdin and stores output in stdout/stderr
			connect(exec, Optional.<Integer> absent());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			closeQuietly(exec);
		}
	}

	protected void waitForClosed(ChannelExec exec, long millis) {
		while (!exec.isClosed()) {
			Threads.sleep(millis);
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

	protected void log() {
		if (context.isEcho()) {
			logger.info("Opening secure channel [{}] encoding={}", ChannelUtils.getLocation(context.getUsername(), context.getHostname()), context.getEncoding());
		} else {
			logger.debug("Opening secure channel [{}] encoding={}", ChannelUtils.getLocation(context.getUsername(), context.getHostname()), context.getEncoding());
		}
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
			if (context.isEcho()) {
				logger.info("deleted -> [{}]", absolutePath);
			}
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
	public CopyResult scp(File source, RemoteFile destination) {
		Assert.notNull(source);
		Assert.exists(source);
		Assert.isFalse(source.isDirectory(), "[" + source + "] is a directory");
		Assert.isTrue(source.canRead(), "[" + source + "] not readable");
		return scp(LocationUtils.getCanonicalURLString(source), destination);
	}

	@Override
	public CopyResult scpToDir(File source, RemoteFile directory) {
		String filename = source.getName();
		String absolutePath = getAbsolutePath(directory.getAbsolutePath(), filename);
		RemoteFile file = new RemoteFile.Builder(absolutePath).clone(directory).build();
		return scp(source, file);
	}

	@Override
	public CopyResult scp(String location, RemoteFile destination) {
		Assert.notNull(location);
		Assert.isTrue(LocationUtils.exists(location), location + " does not exist");
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			return scp(in, destination);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public CopyResult scpString(String string, RemoteFile destination) {
		Assert.notNull(string);
		InputStream in = new ByteArrayInputStream(Str.getBytes(string, context.getEncoding()));
		CopyResult result = scp(in, destination);
		IOUtils.closeQuietly(in);
		return result;
	}

	@Override
	public String toString(RemoteFile source) {
		Assert.notNull(source);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			scp(source, out);
			return out.toString(context.getEncoding());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public CopyResult scp(InputStream source, RemoteFile destination) {
		Assert.notNull(source);
		try {
			long start = System.currentTimeMillis();
			createDirectories(destination);
			sftp.put(source, destination.getAbsolutePath());
			RemoteFile meta = getMetaData(destination.getAbsolutePath());
			CopyResult result = new CopyResult(start, meta.getSize().get(), CopyDirection.TO_REMOTE);
			to(destination, result);
			return result;
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
	public CopyResult scpToDir(String location, RemoteFile directory) {
		String filename = LocationUtils.getFilename(location);
		String absolutePath = getAbsolutePath(directory.getAbsolutePath(), filename);
		RemoteFile file = new RemoteFile.Builder(absolutePath).clone(directory).build();
		return scp(location, file);
	}

	@Override
	public CopyResult scp(RemoteFile source, File destination) {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(FileUtils.openOutputStream(destination));
			return scp(source, out);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public CopyResult scp(String absolutePath, OutputStream out) throws IOException {
		try {
			long start = System.currentTimeMillis();
			sftp.get(absolutePath, out);
			RemoteFile meta = getMetaData(absolutePath);
			CopyResult result = new CopyResult(start, meta.getSize().get(), CopyDirection.FROM_REMOTE);
			from(absolutePath, result);
			return result;
		} catch (SftpException e) {
			throw new IOException("Unexpected IO error", e);
		}
	}

	/**
	 * Show information about the transfer of data to a remote server
	 */
	protected void to(RemoteFile destination, CopyResult result) {
		if (context.isEcho()) {
			String elapsed = FormatUtils.getTime(result.getElapsedMillis());
			String rate = FormatUtils.getRate(result.getElapsedMillis(), result.getAmountInBytes());
			Object[] args = { destination.getAbsolutePath(), elapsed, rate };
			logger.info("created -> [{}] - [{}, {}]", args);
		}
	}

	/**
	 * Show information about the transfer of data from a remote server
	 */
	protected void from(String absolutePath, CopyResult result) {
		if (context.isEcho()) {
			String elapsed = FormatUtils.getTime(result.getElapsedMillis());
			String rate = FormatUtils.getRate(result.getElapsedMillis(), result.getAmountInBytes());
			Object[] args = { absolutePath, elapsed, rate };
			logger.info("copied <- [{}] - [{}, {}]", args);
		}
	}

	@Override
	public CopyResult scp(RemoteFile source, OutputStream out) throws IOException {
		return scp(source.getAbsolutePath(), out);
	}

	@Override
	public CopyResult scpToDir(RemoteFile source, File destination) {
		String filename = FilenameUtils.getName(source.getAbsolutePath());
		File newDestination = new File(destination, filename);
		return scp(source, newDestination);
	}

	@Override
	public void createDirectory(RemoteFile dir) {
		Assert.isTrue(dir.isDirectory());
		try {
			createDirectories(dir);
			if (context.isEcho()) {
				logger.info("mkdir -> [{}]", dir.getAbsolutePath());
			}
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
