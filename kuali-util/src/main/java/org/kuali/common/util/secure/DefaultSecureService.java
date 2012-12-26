package org.kuali.common.util.secure;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class DefaultSecureService implements SecureService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureService.class);

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
				logger.debug("Creating [{}]", pathFragment);
				channel.mkdir(pathFragment);
			}
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
	public void copyFile(JSch jsch, SessionContext context, File source, RemoteFile destination) {
		copyLocation(jsch, context, LocationUtils.getCanonicalURLString(source), destination);
	}

	@Override
	public void copyLocation(JSch jsch, SessionContext context, String location, RemoteFile destination) {
		if (LocationUtils.isExistingFile(location)) {
			File source = new File(location);
			JSchUtils.validateCopyFile(source, destination);
		}
		InputStream in = null;
		Session session = null;
		ChannelSftp channel = null;
		try {
			session = JSchUtils.openSession(jsch, context);
			channel = JSchUtils.openSftpChannel(session, context.getTimeout());
			forceMkdirs(channel, destination);
			in = LocationUtils.getInputStream(location);
			channel.put(in, destination.getAbsolutePath());
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(in);
			JSchUtils.disconnectQuietly(channel);
			JSchUtils.disconnectQuietly(session);
		}
	}

	@Override
	public void copyFile(JSch jsch, SessionContext context, RemoteFile source, File destination) {
		OutputStream out = null;
		Session session = null;
		ChannelSftp channel = null;
		try {
			session = JSchUtils.openSession(jsch, context);
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
