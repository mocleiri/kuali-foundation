/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.secure;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 *
 */
public class JSchUtils {

	private static final Logger logger = LoggerFactory.getLogger(JSchUtils.class);
	private static final String FORWARD_SLASH = "/";

	public static void handleNoSuchFileException(RemoteFile file, SftpException exception) throws SftpException {
		if (isNoSuchFileException(exception)) {
			file.setStatus(Status.MISSING);
		} else {
			throw exception;
		}
	}

	public static final boolean isNoSuchFileException(SftpException exception) {
		return exception.id == ChannelSftp.SSH_FX_NO_SUCH_FILE;
	}

	public static final void validateCopyFileSource(File file) {
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

	public static final void validateCopyFileDestination(RemoteFile file) {
		Assert.notNull(file);
		Assert.notNull(file.getAbsolutePath());
		String normalized = LocationUtils.getNormalizedAbsolutePath(file.getAbsolutePath());
		if (!StringUtils.equals(normalized, file.getAbsolutePath())) {
			logger.info("Normalized path [{}] -> [{}]", file.getAbsolutePath(), normalized);
			file.setAbsolutePath(normalized);
		}
		Assert.hasLength(file.getAbsolutePath());
		if (file.isDirectory()) {
			throw new IllegalArgumentException("File [" + file + "] is a directory.");
		}
	}

	public static final void validateCopyLocation(String location, RemoteFile destination) {
		if (LocationUtils.isExistingFile(location)) {
			File source = new File(location);
			validateCopyFileSource(source);
		}
		validateCopyFileDestination(destination);
	}

	public static final List<RemoteFile> getRemoteFiles(Vector<ChannelSftp.LsEntry> entries, String path) {
		List<RemoteFile> remoteFiles = new ArrayList<RemoteFile>();
		for (ChannelSftp.LsEntry entry : entries) {
			RemoteFile remoteFile = getRemoteFile(entry, path);
			remoteFiles.add(remoteFile);
		}
		return remoteFiles;
	}

	public static final void updateRemoteFile(RemoteFile file, SftpATTRS attributes) {
		file.setDirectory(attributes.isDir());
		file.setPermissions(attributes.getPermissions());
		file.setUserId(attributes.getUId());
		file.setGroupId(attributes.getGId());
		file.setLastModified(new Long(attributes.getMTime() * 1000));
		file.setSize(attributes.getSize());
		file.setStatus(Status.EXISTS);
	}

	public static final RemoteFile getRemoteFile(ChannelSftp.LsEntry entry, String path) {
		String absolutePath = path + FORWARD_SLASH + entry.getFilename();
		SftpATTRS attributes = entry.getAttrs();
		RemoteFile file = new RemoteFile();
		file.setAbsolutePath(absolutePath);
		updateRemoteFile(file, attributes);
		return file;
	}

	public static final JSch getDefaultJSch() throws JSchException {
		List<File> privateKeys = SSHUtils.getDefaultPrivateKeys();
		return getJSch(privateKeys);
	}

	public static final JSch getJSch(List<File> privateKeys) throws JSchException {
		JSch jsch = new JSch();
		for (File privateKey : privateKeys) {
			String path = LocationUtils.getCanonicalPath(privateKey);
			jsch.addIdentity(path);
		}
		return jsch;
	}

}
