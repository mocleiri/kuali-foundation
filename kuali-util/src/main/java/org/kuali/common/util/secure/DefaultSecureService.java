package org.kuali.common.util.secure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class DefaultSecureService implements SecureService {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureService.class);
	private static final String SFTP = "sftp";

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
	}

	protected void validateCopyFile(File source, RemoteFile destination) {
		validateCopyFileSource(source);
		validateCopyFileDestination(destination);
	}

	protected void forceMkdirs(ChannelSftp channel, String absolutePath) throws SftpException {
		List<String> fragments = getPathFragments(absolutePath);
		for (String fragment : fragments) {
			logger.info(fragment);
		}
		String workingDirectory = channel.pwd();
		logger.info("working directory=[{}]", workingDirectory);
		Vector<?> vector = channel.ls(".");
		logger.info("vector.size()={}", vector.size());
	}

	protected List<String> getPathFragments(String filename) {
		String normalized = LocationUtils.getNormalizedPath(filename);
		String[] tokens = StringUtils.split(normalized, "/");
		List<String> fragments = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		sb.append("/");
		if (StringUtils.startsWith(filename, "/")) {
			fragments.add(sb.toString());
		}
		for (int i = 0; i < tokens.length - 1; i++) {
			if (i != 0) {
				sb.append("/");
			}
			sb.append(tokens[i]);
			fragments.add(sb.toString());
		}
		return fragments;
	}

	@Override
	public void copyFile(File source, RemoteFile destination) {
		validateCopyFile(source, destination);
		List<String> fragments = getPathFragments(destination.getAbsolutePath());
		for (String fragment : fragments) {
			logger.info(fragment);
		}
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
			forceMkdirs(channel, destination.getAbsolutePath());
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

	@Override
	public void copyFile(RemoteFile source, File destination) {
		OutputStream out = null;
		Session session = null;
		ChannelSftp channel = null;
		try {
			JSch jsch = JSchUtils.getDefaultJSch();
			session = jsch.getSession("root", source.getHostname(), 22);
			session.setConfig(SSHUtils.getDefaultOptions());
			session.connect();
			channel = (ChannelSftp) session.openChannel(SFTP);
			channel.connect();
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

}
