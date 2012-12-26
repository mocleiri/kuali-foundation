package org.kuali.common.util.secure;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

	/**
	 *
	 */
	protected void forceMkdir(ChannelSftp channel, RemoteFile dir) throws SftpException {
		updateRemoteFile(channel, dir);
		validateForceMkdir(dir);
		List<String> pathFragments = LocationUtils.getNormalizedPathFragments(dir.getAbsolutePath(), true);
		for (String pathFragment : pathFragments) {
			RemoteFile parentDir = getRemoteFile(channel, pathFragment);
			validateForceMkdir(parentDir);
			if (!parentDir.isDirectory()) {
				channel.mkdir(pathFragment);
			}
		}
	}

	protected boolean validateForceMkdir(RemoteFile file) {
		boolean missing = Status.MISSING.equals(file.getStatus());
		if (missing || file.isDirectory()) {
			return true;
		} else {
			throw new IllegalArgumentException("File [" + file.getAbsolutePath() + "] exists and is not a directory. Unable to create directory.");
		}
	}

	/**
	 * Return <code>true</code> if <code>path</code> exists.
	 */
	protected boolean isExistingPath(ChannelSftp channel, String absolutePath) throws SftpException {
		RemoteFile file = getRemoteFile(channel, absolutePath);
		return Status.EXISTS.equals(file.getStatus());
	}

	protected RemoteFile getRemoteFile(ChannelSftp channel, String absolutePath) throws SftpException {
		RemoteFile file = new RemoteFile();
		file.setAbsolutePath(absolutePath);
		updateRemoteFile(channel, file);
		return file;
	}

	/**
	 * Return <code>true</code> if <code>path</code> is an existing file. Return <code>false</code> if <code>path</code> does not exist or
	 * is an existing directory.
	 */
	protected boolean isExistingFile(ChannelSftp channel, String absolutePath) throws SftpException {
		RemoteFile file = getRemoteFile(channel, absolutePath);
		return Status.EXISTS.equals(file.getStatus()) && !file.isDirectory();
	}

	/**
	 * Return <code>true</code> if <code>path</code> is an existing directory. Return <code>false</code> if <code>path</code> does not exist
	 * or is an existing file.
	 */
	protected boolean isExistingDirectory(ChannelSftp channel, String absolutePath) throws SftpException {
		RemoteFile file = getRemoteFile(channel, absolutePath);
		return Status.EXISTS.equals(file.getStatus()) && file.isDirectory();
	}

	protected boolean isNoSuchFileException(SftpException exception) {
		return exception.id == ChannelSftp.SSH_FX_NO_SUCH_FILE;
	}

	protected void handleNoSuchFileException(RemoteFile file, SftpException exception) throws SftpException {
		if (isNoSuchFileException(exception)) {
			file.setStatus(Status.MISSING);
		} else {
			throw exception;
		}
	}

	protected void updateRemoteFile(ChannelSftp channel, RemoteFile file) throws SftpException {
		try {
			SftpATTRS attributes = channel.stat(file.getAbsolutePath());
			updateRemoteFile(file, attributes);
		} catch (SftpException e) {
			handleNoSuchFileException(file, e);
		}
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

	protected void updateRemoteFile(RemoteFile file, SftpATTRS attributes) {
		file.setDirectory(attributes.isDir());
		file.setPermissions(attributes.getPermissions());
		file.setUserId(attributes.getUId());
		file.setGroupId(attributes.getGId());
		file.setLastModified(new Long(attributes.getMTime() * 1000));
		file.setSize(attributes.getSize());
		file.setStatus(Status.EXISTS);
	}

	protected RemoteFile getRemoteFile(ChannelSftp.LsEntry entry, String path) {
		String absolutePath = path + FORWARD_SLASH + entry.getFilename();
		SftpATTRS attributes = entry.getAttrs();
		RemoteFile file = new RemoteFile();
		file.setAbsolutePath(absolutePath);
		updateRemoteFile(file, attributes);
		return file;
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
			forceMkdir(channel, destination);
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
			out = new BufferedOutputStream(FileUtils.openOutputStream(destination));
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
			throw new IllegalArgumentException("File [" + path + "] does not exist.");
		}
		if (file.isDirectory()) {
			throw new IllegalArgumentException("File [" + path + "] is a directory.");
		}
		if (!file.canRead()) {
			throw new IllegalArgumentException("File [" + path + "] is not readable.");
		}
	}

	protected void validateCopyFileDestination(RemoteFile destination) {
		Assert.notNull(destination);
		Assert.notNull(destination.getAbsolutePath());
		boolean notBlank = !StringUtils.isBlank(destination.getHostname());
		Assert.isTrue(notBlank);
		String normalized = LocationUtils.getNormalizedAbsolutePath(destination.getAbsolutePath());
		if (!StringUtils.equals(normalized, destination.getAbsolutePath())) {
			logger.info("Normalized path [{}] -> [{}]", destination.getAbsolutePath(), normalized);
			destination.setAbsolutePath(normalized);
		}
		Assert.hasLength(destination.getAbsolutePath());
		if (destination.isDirectory()) {
			throw new IllegalArgumentException("File [" + destination + "] is a directory.");
		}
	}

	protected void validateCopyFile(File source, RemoteFile destination) {
		validateCopyFileSource(source);
		validateCopyFileDestination(destination);
	}

}
