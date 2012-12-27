package org.kuali.common.util.secure;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class DefaultSecureChannel implements SecureChannel {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureChannel.class);
	private static final String SFTP = "sftp";
	private static final String EXEC = "exec";
	private static final String FORWARDSLASH = "/";

	File knownHosts = SSHUtils.DEFAULT_KNOWN_HOSTS;
	File config = SSHUtils.DEFAULT_CONFIG_FILE;
	boolean includeDefaultPrivateKeyLocations = true;
	boolean strictHostKeyChecking = true;
	int port = SSHUtils.DEFAULT_PORT;
	String username;
	String hostname;
	Integer connectTimeout;
	List<File> privateKeys;
	Properties options;

	protected Session session;
	protected ChannelSftp sftp;
	protected ChannelExec exec;

	@Override
	public synchronized void open() {
		logOpen();
		try {
			JSch jsch = getJSch();
			this.session = openSession(jsch);
			this.sftp = openSftpChannel(session, connectTimeout);
			this.exec = openExecChannel(session, connectTimeout);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error opening secure channel", e);
		}
	}

	protected void logOpen() {
		logger.info("Opening secure channel - {}", getLocation(username, hostname));
		if (privateKeys != null) {
			logger.info("Private keys - {}", privateKeys.size());
		} else {
			logger.info("Private key config - {}", config);
		}
		if (!includeDefaultPrivateKeyLocations) {
			logger.info("Check default private key locations - {}", includeDefaultPrivateKeyLocations);
		}
		if (!strictHostKeyChecking) {
			logger.info("Strict host key checking - {}", strictHostKeyChecking);
		}
		if (strictHostKeyChecking) {
			logger.info("Known hosts - {}", knownHosts);
		}
		if (port != SSHUtils.DEFAULT_PORT) {
			logger.info("Port - {}", port);
		}
		if (connectTimeout != null) {
			logger.info("Connect timeout - {}", connectTimeout);
		}
		if (options != null) {
			logger.info("Configuring channel with {} options", options.size());
			PropertyUtils.debug(options);
		}
	}

	protected String getLocation(String username, String hostname) {
		return (username == null) ? hostname : username + "@" + hostname;
	}

	@Override
	public synchronized void close() {
		logger.info("Closing secure channel - {}", getLocation(username, hostname));
		closeQuietly(sftp);
		closeQuietly(exec);
		closeQuietly(session);
	}

	protected ChannelExec openExecChannel(Session session, Integer timeout) throws JSchException {
		ChannelExec channel = (ChannelExec) session.openChannel(EXEC);
		connect(channel, timeout);
		return channel;
	}

	protected void connect(Channel channel, Integer timeout) throws JSchException {
		if (timeout == null) {
			channel.connect();
		} else {
			channel.connect(timeout);
		}
	}

	protected ChannelSftp openSftpChannel(Session session, Integer timeout) throws JSchException {
		ChannelSftp channel = (ChannelSftp) session.openChannel(SFTP);
		connect(channel, timeout);
		return channel;
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
		List<File> mergedPrivateKeys = getMergedPrivateKeys();
		logger.debug("Located {} private keys", mergedPrivateKeys.size());
		JSch jsch = JSchUtils.getJSch(mergedPrivateKeys);
		if (strictHostKeyChecking && knownHosts != null) {
			String path = LocationUtils.getCanonicalPath(knownHosts);
			jsch.setKnownHosts(path);
		}
		return jsch;
	}

	protected List<File> getMergedPrivateKeys() {
		if (privateKeys == null) {
			logger.debug("Examining {}", config);
			return SSHUtils.getPrivateKeys(config, includeDefaultPrivateKeyLocations);
		} else {
			return SSHUtils.getPrivateKeys(privateKeys, includeDefaultPrivateKeyLocations);
		}
	}

	/**
	 * Connect to the remote server and acquire information about <code>file</code>
	 */
	protected void updateRemoteFile(ChannelSftp channel, RemoteFile file) throws SftpException {
		try {
			SftpATTRS attributes = channel.stat(file.getAbsolutePath());
			JSchUtils.updateRemoteFile(file, attributes);
		} catch (SftpException e) {
			JSchUtils.handleNoSuchFileException(file, e);
		}
	}

	protected List<RemoteFile> getRemoteFiles(ChannelSftp channel, String path) throws SftpException {
		@SuppressWarnings("unchecked")
		Vector<ChannelSftp.LsEntry> entries = (Vector<ChannelSftp.LsEntry>) channel.ls(path);
		return JSchUtils.getRemoteFiles(entries, path);
	}

	@Override
	public void copyFile(File source, RemoteFile destination) {
		copyLocationToFile(LocationUtils.getCanonicalURLString(source), destination);
	}

	@Override
	public void copyFileToDirectory(File source, RemoteFile destination) {
		String filename = source.getName();
		updateRemoteFile(destination, filename);
		copyFile(source, destination);
	}

	@Override
	public void copyLocations(List<String> locations, List<RemoteFile> destinations) {
		Assert.isTrue(locations.size() == destinations.size());
		for (int i = 0; i < locations.size(); i++) {
			String location = locations.get(i);
			RemoteFile destination = destinations.get(i);
			copyLocationToFile(sftp, location, destination);
		}
	}

	@Override
	public void copyLocationToFile(String location, RemoteFile destination) {
		copyLocations(Collections.singletonList(location), Collections.singletonList(destination));
	}

	@Override
	public void copyStringToFile(String string, RemoteFile destination) {
		InputStream in = new ByteArrayInputStream(string.getBytes());
		copyInputStreamToFile(in, destination);
	}

	@Override
	public void copyInputStreamToFile(InputStream source, RemoteFile destination) {
		try {
			copyInputStreamToFile(sftp, source, destination);
		} catch (SftpException e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

	protected void copyInputStreamToFile(ChannelSftp channel, InputStream source, RemoteFile destination) throws SftpException {
		forceMkdirs(channel, destination);
		channel.put(source, destination.getAbsolutePath());
	}

	protected void copyLocationToFile(ChannelSftp sftp, String location, RemoteFile destination) {
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			copyInputStreamToFile(sftp, in, destination);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected String getAbsolutePath(String absolutePath, String filename) {
		if (StringUtils.endsWith(absolutePath, FORWARDSLASH)) {
			return absolutePath + filename;
		} else {
			return absolutePath + FORWARDSLASH + filename;
		}
	}

	protected void updateRemoteFile(RemoteFile destination, String filename) {
		String newAbsolutePath = getAbsolutePath(destination.getAbsolutePath(), filename);
		destination.setAbsolutePath(newAbsolutePath);
		destination.setDirectory(false);
	}

	@Override
	public void copyLocationToDirectory(String location, RemoteFile destination) {
		String filename = LocationUtils.getFilename(location);
		updateRemoteFile(destination, filename);
		copyLocationToFile(location, destination);
	}

	@Override
	public void copyFile(RemoteFile source, File destination) {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(FileUtils.openOutputStream(destination));
			sftp.get(source.getAbsolutePath(), out);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected error", e);
		} catch (SftpException e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/**
	 *
	 */
	protected void forceMkdirs(ChannelSftp channel, RemoteFile file) throws SftpException {
		boolean directoryIndicator = file.isDirectory();
		updateRemoteFile(channel, file);
		validate(file, directoryIndicator);
		List<String> pathFragments = LocationUtils.getNormalizedPathFragments(file.getAbsolutePath(), file.isDirectory());
		for (String pathFragment : pathFragments) {
			RemoteFile parentDir = new RemoteFile(pathFragment);
			updateRemoteFile(channel, parentDir);
			validate(parentDir, true);
			if (!parentDir.isDirectory()) {
				createDirectory(channel, parentDir);
			}
		}
	}

	protected boolean isStatus(RemoteFile file, Status status) {
		return file.getStatus().equals(status);
	}

	protected void validateStatus(RemoteFile file, Status... allowed) {
		for (Status status : allowed) {
			if (isStatus(file, status)) {
				return;
			}
		}
		throw new IllegalArgumentException("Invalid status - " + file.getStatus());
	}

	protected boolean validate(RemoteFile file, boolean directoryIndicator) {
		validateStatus(file, Status.MISSING, Status.EXISTS);
		boolean missing = isStatus(file, Status.MISSING);
		boolean exists = isStatus(file, Status.EXISTS);
		// Compare the actual file type to the file type it needs to be
		boolean correctFileType = file.isDirectory() == directoryIndicator;
		boolean valid = missing || exists && correctFileType;
		if (valid) {
			return true;
		} else {
			throw new IllegalArgumentException(getInvalidExistingFileMessage(file));
		}
	}

	protected String getInvalidExistingFileMessage(RemoteFile file) {
		if (file.isDirectory()) {
			return "File [" + file.getAbsolutePath() + "] is an existing directory. Unable to create file.";
		} else {
			return "File [" + file.getAbsolutePath() + "] is an existing file. Unable to create directory.";
		}
	}

	protected void createDirectory(ChannelSftp channel, RemoteFile dir) throws SftpException {
		String path = dir.getAbsolutePath();
		logger.debug("Creating [{}]", path);
		channel.mkdir(path);
		setAttributes(channel, dir);
	}

	protected void setAttributes(ChannelSftp channel, RemoteFile file) throws SftpException {
		String path = file.getAbsolutePath();
		if (file.getPermissions() != null) {
			channel.chmod(file.getPermissions(), path);
		}
		if (file.getGroupId() != null) {
			channel.chgrp(file.getGroupId(), path);
		}
		if (file.getUserId() != null) {
			channel.chown(file.getUserId(), path);
		}
	}

	/**
	 * Return <code>true</code> if <code>file</code> exists.
	 */
	protected boolean isExisting(ChannelSftp channel, RemoteFile file) throws SftpException {
		updateRemoteFile(channel, file);
		return Status.EXISTS.equals(file.getStatus());
	}

	/**
	 * Return <code>true</code> if <code>file</code> is an existing file. Return <code>false</code> if <code>file</code> does not exist or
	 * is an existing directory.
	 */
	protected boolean isExistingFile(ChannelSftp channel, RemoteFile file) throws SftpException {
		updateRemoteFile(channel, file);
		return Status.EXISTS.equals(file.getStatus()) && !file.isDirectory();
	}

	/**
	 * Return <code>true</code> if <code>path</code> is an existing directory. Return <code>false</code> if <code>path</code> does not exist
	 * or is an existing file.
	 */
	protected boolean isExistingDirectory(ChannelSftp channel, RemoteFile file) throws SftpException {
		updateRemoteFile(channel, file);
		return Status.EXISTS.equals(file.getStatus()) && file.isDirectory();
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

}
