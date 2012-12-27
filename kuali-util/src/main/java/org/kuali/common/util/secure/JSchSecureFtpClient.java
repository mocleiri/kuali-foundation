package org.kuali.common.util.secure;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class JSchSecureFtpClient implements SecureFtpClient {

	private static final Logger logger = LoggerFactory.getLogger(JSchSecureFtpClient.class);

	JSchContext context;

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
		Session session = null;
		ChannelSftp channel = null;
		try {
			Assert.isTrue(locations.size() == destinations.size());
			session = JSchUtils.openSession(context);
			channel = JSchUtils.openSftpChannel(session, context.getTimeout());
			for (int i = 0; i < locations.size(); i++) {
				String location = locations.get(i);
				RemoteFile destination = destinations.get(i);
				copyLocationToFile(channel, location, destination);
			}
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			JSchUtils.disconnectQuietly(channel);
			JSchUtils.disconnectQuietly(session);
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
		Session session = null;
		ChannelSftp channel = null;
		try {
			session = JSchUtils.openSession(context);
			channel = JSchUtils.openSftpChannel(session, context.getTimeout());
			copyInputStreamToFile(channel, source, destination);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			JSchUtils.disconnectQuietly(channel);
			JSchUtils.disconnectQuietly(session);
		}
	}

	protected void copyInputStreamToFile(ChannelSftp channel, InputStream source, RemoteFile destination) throws SftpException {
		forceMkdirs(channel, destination);
		channel.put(source, destination.getAbsolutePath());
	}

	protected void copyLocationToFile(ChannelSftp channel, String location, RemoteFile destination) {
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			copyInputStreamToFile(channel, in, destination);
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(in);
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
	public void copyLocationToDirectory(String location, RemoteFile destination) {
		String filename = LocationUtils.getFilename(location);
		updateRemoteFile(destination, filename);
		copyLocationToFile(location, destination);
	}

	@Override
	public void copyFile(RemoteFile source, File destination) {
		Session session = null;
		ChannelSftp channel = null;
		OutputStream out = null;
		try {
			session = JSchUtils.openSession(context);
			channel = JSchUtils.openSftpChannel(session, context.getTimeout());
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

}
