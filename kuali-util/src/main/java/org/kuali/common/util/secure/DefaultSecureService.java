package org.kuali.common.util.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class DefaultSecureService implements SecureService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureService.class);
	private static final String SFTP = "sftp";
	private static final String FORWARD_SLASH = "/";

	protected void forceMkdirs(ChannelSftp channel, RemoteFile file) throws SftpException {
		List<String> pathFragments = LocationUtils.getNormalizedPathFragments(file.getAbsolutePath(), file.isDirectory());
		for (String path : pathFragments) {
			Vector<?> vector = channel.ls(path);
			displayVector(vector);
		}
	}

	/**
	 * Return <code>true</code> if <code>path</code> exists.
	 */
	protected boolean isExistingPath(ChannelSftp channel, String path) throws SftpException {
		try {
			channel.stat(path);
			return true;
		} catch (SftpException e) {
			return handleExistingFileCheckException(e);
		}
	}

	/**
	 * Return <code>false</code> if <code>path</code> does not exist or is a directory. Return <code>true</code> if <code>path</code> is an
	 * existing file.
	 */
	protected boolean isExistingFile(ChannelSftp channel, String path) throws SftpException {
		try {
			RemoteFile file = getRemoteFile(channel, path);
			return !file.isDirectory();
		} catch (SftpException e) {
			return handleExistingFileCheckException(e);
		}
	}

	/**
	 * Return <code>false</code> if <code>path</code> does not exist or is a file. Return <code>true</code> if <code>path</code> is an
	 * existing directory.
	 */
	protected boolean isExistingDirectory(ChannelSftp channel, String path) throws SftpException {
		try {
			RemoteFile file = getRemoteFile(channel, path);
			return file.isDirectory();
		} catch (SftpException e) {
			return handleExistingFileCheckException(e);
		}
	}

	protected boolean isNoSuchFileException(SftpException exception) {
		return exception.id == ChannelSftp.SSH_FX_NO_SUCH_FILE;
	}

	protected boolean handleExistingFileCheckException(SftpException exception) throws SftpException {
		if (isNoSuchFileException(exception)) {
			return false;
		} else {
			throw exception;
		}
	}

	protected RemoteFile getRemoteFile(ChannelSftp channel, String path) throws SftpException {
		SftpATTRS attributes = channel.stat(path);
		return getRemoteFile(path, attributes);
	}

	protected List<RemoteFile> getRemoteFiles(ChannelSftp channel, String path) throws SftpException {
		@SuppressWarnings("unchecked")
		Vector<ChannelSftp.LsEntry> entries = (Vector<ChannelSftp.LsEntry>) channel.ls(path);
		return getRemoteFiles(entries, path);
	}

	protected List<RemoteFile> getRemoteFiles(Vector<ChannelSftp.LsEntry> entries, String path) {
		List<RemoteFile> remoteFiles = new ArrayList<RemoteFile>();
		for (ChannelSftp.LsEntry entry : entries) {
			RemoteFile remoteFile = getRemoteFile(entry, path);
			remoteFiles.add(remoteFile);
		}
		return remoteFiles;
	}

	protected RemoteFile getRemoteFile(String absolutePath, SftpATTRS attributes) {
		DefaultRemoteFile file = new DefaultRemoteFile();
		file.setAbsolutePath(absolutePath);
		file.setDirectory(attributes.isDir());
		file.setPermissions(attributes.getPermissions());
		file.setUserId(attributes.getUId());
		file.setGroupId(attributes.getGId());
		file.setLastModified(new Long(attributes.getMTime() * 1000));
		file.setSize(attributes.getSize());
		return file;
	}

	protected RemoteFile getRemoteFile(ChannelSftp.LsEntry entry, String path) {
		SftpATTRS attributes = entry.getAttrs();
		return getRemoteFile(path + FORWARD_SLASH + entry.getFilename(), attributes);
	}

	protected void displayVector(Vector<?> v) {
		List<?> elements = new ArrayList<Object>(v);
		for (Object element : elements) {
			ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) element;
			displayEntry(entry);
		}
	}

	protected void displayEntry(ChannelSftp.LsEntry entry) {
		String filename = entry.getFilename();
		String longname = entry.getLongname();
		SftpATTRS attributes = entry.getAttrs();
		int groupId = attributes.getGId();
		int userId = attributes.getUId();
		int lastModified = attributes.getMTime();
		int permissions = attributes.getPermissions();
		boolean directory = attributes.isDir();

		logger.info("----------------------");
		logger.info("filename - [{}]", filename);
		logger.info("directory - [{}]", directory);
		logger.info("longname - [{}]", longname);
		logger.info("Group Id - [{}]", groupId);
		logger.info("User Id - [{}]", userId);
		logger.info("permissions - [{}]", permissions);
		logger.info("lastModified - [{}]", lastModified);
		logger.info("----------------------");
	}

	@Override
	public void copyFile(File source, RemoteFile destination) {
		validateCopyFile(source, destination);
		InputStream in = null;
		Session session = null;
		ChannelSftp channel = null;
		try {
			JSch jsch = JSchUtils.getDefaultJSch();
			session = jsch.getSession("root", destination.getHostname(), 22);
			session.setConfig(SSHUtils.getDefaultOptions());
			session.connect();
			channel = (ChannelSftp) session.openChannel(SFTP);
			channel.connect();
			forceMkdirs(channel, destination);
			in = new FileInputStream(source);
			channel.put(in, destination.getAbsolutePath());
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(in);
			JSchUtils.disconnectQuietly(channel);
			JSchUtils.disconnectQuietly(session);
		}
	}

	protected Session openSession(JSch jsch, String username, String hostname, int port, int timeout, Properties options) throws JSchException {
		Session session = jsch.getSession(username, hostname, port);
		session.setConfig(options);
		session.connect(timeout);
		return session;
	}

	protected ChannelSftp openSftpChannel(Session session) throws JSchException {
		ChannelSftp channel = (ChannelSftp) session.openChannel(SFTP);
		channel.connect();
		return channel;
	}

	@Override
	public void copyFile(RemoteFile source, File destination) {
		OutputStream out = null;
		Session session = null;
		ChannelSftp channel = null;
		try {
			JSch jsch = JSchUtils.getDefaultJSch();
			session = openSession(jsch, "root", source.getHostname(), 22, 0, SSHUtils.getDefaultOptions());
			channel = openSftpChannel(session);
			out = new FileOutputStream(destination);
			channel.get(source.getAbsolutePath(), out);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(out);
			JSchUtils.disconnectQuietly(channel);
			JSchUtils.disconnectQuietly(session);
		}
	}

	protected void validateCopyFileSource(File file) {
		Assert.notNull(file);
		String path = LocationUtils.getCanonicalPath(file);
		if (!file.exists()) {
			throw new IllegalArgumentException("[" + path + "] does not exist");
		}
		if (!file.canRead()) {
			throw new IllegalArgumentException("[" + path + "] is not readable");
		}
	}

	protected void validateCopyFileDestination(RemoteFile destination) {
		Assert.notNull(destination);
		Assert.hasLength(destination.getAbsolutePath());
		if (destination.isDirectory()) {
			throw new IllegalArgumentException("[" + destination + "] is a directory");
		}
		LocationUtils.getNormalizedAbsolutePath(destination.getAbsolutePath());
	}

	protected void validateCopyFile(File source, RemoteFile destination) {
		validateCopyFileSource(source);
		validateCopyFileDestination(destination);
	}

}
