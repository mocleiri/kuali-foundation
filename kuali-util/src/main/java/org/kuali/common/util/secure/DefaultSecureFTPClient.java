package org.kuali.common.util.secure;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class DefaultSecureFTPClient implements SecureFTPClient {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureFTPClient.class);

	/**
	 *
	 */
	protected void forceMkdirs(ChannelSftp channel, RemoteFile file) throws SftpException {
		updateRemoteFile(channel, file);
		JSchUtils.validateForceMkdir(file);
		List<String> pathFragments = LocationUtils.getNormalizedPathFragments(file.getAbsolutePath(), file.isDirectory());
		for (String pathFragment : pathFragments) {
			RemoteFile parentDir = new RemoteFile(pathFragment);
			updateRemoteFile(channel, parentDir);
			JSchUtils.validateForceMkdir(parentDir);
			if (!parentDir.isDirectory()) {
				createDirectory(channel, parentDir);
			}
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
	public void copyFile(File source, ChannelSftp channel, RemoteFile destination) {
		copyLocationToFile(LocationUtils.getCanonicalURLString(source), channel, destination);
	}

	@Override
	public void copyFileToDirectory(File source, ChannelSftp channel, RemoteFile destination) {
		String filename = source.getName();
		updateRemoteFile(destination, filename);
		copyFile(source, channel, destination);
	}

	@Override
	public void copyLocationToFile(String location, ChannelSftp channel, RemoteFile destination) {
		JSchUtils.validateCopyLocation(location, destination);
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			copyInputStreamToFile(in, channel, destination);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@Override
	public void copyInputStreamToFile(InputStream source, ChannelSftp channel, RemoteFile destination) {
		try {
			forceMkdirs(channel, destination);
			channel.put(source, destination.getAbsolutePath());
		} catch (SftpException e) {
			throw new IllegalStateException("Unexpected error", e);
		}
	}

	protected String getAbsolutePath(String absolutePath, String filename) {
		if (StringUtils.endsWith(absolutePath, "/")) {
			return absolutePath + filename;
		} else {
			return absolutePath + "/" + filename;
		}
	}

	protected void updateRemoteFile(RemoteFile destination, String filename) {
		String newAbsolutePath = getAbsolutePath(destination.getAbsolutePath(), filename);
		destination.setAbsolutePath(newAbsolutePath);
		destination.setDirectory(false);
	}

	@Override
	public void copyLocationToDirectory(String location, ChannelSftp channel, RemoteFile destination) {
		String filename = LocationUtils.getFilename(location);
		updateRemoteFile(destination, filename);
		copyLocationToFile(location, channel, destination);
	}

	public void copyFile(ChannelSftp channel, RemoteFile source, File destination) {
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(FileUtils.openOutputStream(destination));
			channel.get(source.getAbsolutePath(), out);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

}
