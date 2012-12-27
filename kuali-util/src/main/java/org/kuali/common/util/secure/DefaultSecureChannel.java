package org.kuali.common.util.secure;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;
import org.kuali.common.util.Str;
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
		validate();
		try {
			JSch jsch = getJSch();
			this.session = openSession(jsch);
			this.sftp = openSftpChannel(session, connectTimeout);
			this.exec = openExecChannel(session, connectTimeout);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error opening secure channel", e);
		}
	}

	protected void validate() {
		Assert.isTrue(SSHUtils.isValidPort(port));
		Assert.isTrue(!StringUtils.isBlank(hostname));
	}

	protected void logOpen() {
		logger.info("Opening secure channel - {}", getLocation(username, hostname));
		if (privateKeys != null) {
			logger.debug("Private keys - {}", privateKeys.size());
		} else {
			logger.debug("Private key config - {}", config);
		}
		logger.debug("Check default private key locations - {}", includeDefaultPrivateKeyLocations);
		logger.debug("Strict host key checking - {}", strictHostKeyChecking);
		logger.debug("Known hosts - {}", knownHosts);
		logger.debug("Port - {}", port);
		logger.debug("Connect timeout - {}", connectTimeout);
		logger.debug("Configuring channel with {} options", options.size());
		PropertyUtils.debug(options);
	}

	protected String getLocation(String username, String hostname) {
		return (username == null) ? hostname : username + "@" + hostname;
	}

	@Override
	public RemoteFile getWorkingDirectory() {
		try {
			String pwd = sftp.pwd();
			return getMetaData(pwd);
		} catch (SftpException e) {
			throw new IllegalStateException(e);
		}

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
		ChannelSftp sftp = (ChannelSftp) session.openChannel(SFTP);
		connect(sftp, timeout);
		return sftp;
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
		JSch jsch = getJSch(mergedPrivateKeys);
		if (strictHostKeyChecking && knownHosts != null) {
			String path = LocationUtils.getCanonicalPath(knownHosts);
			jsch.setKnownHosts(path);
		}
		return jsch;
	}

	protected JSch getJSch(List<File> privateKeys) throws JSchException {
		JSch jsch = new JSch();
		for (File privateKey : privateKeys) {
			String path = LocationUtils.getCanonicalPath(privateKey);
			jsch.addIdentity(path);
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

	@Override
	public RemoteFile getMetaData(String absolutePath) {
		Assert.hasLength(absolutePath);
		RemoteFile file = new RemoteFile();
		file.setHostname(hostname);
		file.setAbsolutePath(absolutePath);
		fillInAttributes(file, absolutePath);
		return file;
	}

	protected String getLocation(RemoteFile file) {
		return file.getHostname() + ":" + file.getAbsolutePath();
	}

	@Override
	public void deleteFile(String absolutePath) {
		RemoteFile file = getMetaData(absolutePath);
		if (isStatus(file, Status.MISSING)) {
			return;
		}
		if (file.isDirectory()) {
			throw new IllegalArgumentException("[" + getLocation(file) + "] is a directory.");
		}
		try {
			sftp.rm(absolutePath);
		} catch (SftpException e) {
			throw new IllegalStateException("Unexpected SFTP error", e);
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
			file.setHostname(hostname);
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
		String filename = source.getName();
		addFilenameToPath(destination, filename);
		copyFile(source, destination);
	}

	@Override
	public void copyLocationToFile(String location, RemoteFile destination) {
		Assert.notNull(location);
		Assert.isTrue(LocationUtils.exists(location));
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
		InputStream in = new ByteArrayInputStream(Str.getUTF8Bytes(string));
		copyInputStreamToFile(in, destination);
	}

	@Override
	public void copyInputStreamToFile(InputStream source, RemoteFile destination) {
		Assert.notNull(source);
		try {
			createDirectories(destination);
			sftp.put(source, destination.getAbsolutePath());
		} catch (SftpException e) {
			throw new IllegalStateException("Unexpected error", e);
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
		String filename = LocationUtils.getFilename(location);
		addFilenameToPath(destination, filename);
		copyLocationToFile(location, destination);
	}

	@Override
	public void copyFile(RemoteFile source, File destination) {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(FileUtils.openOutputStream(destination));
			sftp.get(source.getAbsolutePath(), out);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	public void copyFileToDirectory(RemoteFile source, File destination) {
		String filename = FilenameUtils.getName(source.getAbsolutePath());
		File newDestination = new File(destination, filename);
		copyFile(source, newDestination);
	}

	@Override
	public void forceMkdir(RemoteFile dir) {
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
				createDirectory(sftp, parentDir);
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
			return "[" + getLocation(existing) + "] is an existing directory. Unable to create file.";
		} else {
			return "[" + getLocation(existing) + "] is an existing file. Unable to create directory.";
		}
	}

	protected void createDirectory(ChannelSftp sftp, RemoteFile dir) throws SftpException {
		String path = dir.getAbsolutePath();
		logger.debug("Creating [{}]", path);
		sftp.mkdir(path);
		setAttributes(sftp, dir);
	}

	protected void setAttributes(ChannelSftp sftp, RemoteFile file) throws SftpException {
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
			throw new IllegalStateException("Unexpected SFTP error", e);
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

}
