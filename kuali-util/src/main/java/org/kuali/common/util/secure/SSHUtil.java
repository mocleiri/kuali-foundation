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

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;

public class SSHUtil {

	private static final String FS = File.separator;
	private static final String IDENTITY_FILE = "IdentityFile";
	private static final String TILDE = "~";
	private static final String USER_HOME = System.getProperty("user.home");
	private static final File CONFIG = new File(USER_HOME + FS + ".ssh" + FS + "config");
	private static final String SSHDIR = USER_HOME + FS + ".ssh";

	private static final String IDENTITY = SSHDIR + FS + "identity";
	private static final String ID_DSA = SSHDIR + FS + "id_dsa";
	private static final String ID_RSA = SSHDIR + FS + "id_rsa";
	private static final String ID_ECDSA = SSHDIR + FS + "id_ecdsa";
	private static final String[] PRIVATE_KEY_DEFAULTS = { IDENTITY, ID_DSA, ID_RSA, ID_ECDSA };

	/**
	 * Return a non-null list containing any private keys found by examining default private key locations in <code>~/.ssh</code> and
	 * parsing <code>~/.ssh/config</code>. Any files contained in the list are guaranteed to exist and are readable.
	 */
	public static final List<File> getDefaultPrivateKeys() {
		String[] configuredPrivateKeys = getFilenames(CONFIG);
		String[] defaultPrivateKeys = PRIVATE_KEY_DEFAULTS;
		List<String> filenames = CollectionUtils.getCombined(configuredPrivateKeys, defaultPrivateKeys);
		return getExistingAndReadable(filenames);
	}

	public static final List<File> getExistingAndReadable(List<String> filenames) {
		List<File> files = new ArrayList<File>();
		for (String filename : filenames) {
			File file = new File(filename);
			if (file.exists() && file.canRead()) {
				files.add(file);
			}
		}
		return files;
	}

	public static final String[] getFilenames(File config) {
		if (!config.exists() || !config.canRead()) {
			return new String[] {};
		}
		List<String> lines = LocationUtils.readLines(config.getAbsolutePath());
		List<String> identityFileLines = getIdentityFileLines(lines);
		return CollectionUtils.toStringArray(getFilenames(identityFileLines));
	}

	public static final List<String> getIdentityFileLines(List<String> lines) {
		List<String> identityFileLines = new ArrayList<String>();
		for (String line : lines) {
			String trimmed = StringUtils.trim(line);
			if (StringUtils.startsWith(trimmed, IDENTITY_FILE)) {
				identityFileLines.add(trimmed);
			}
		}
		return identityFileLines;
	}

	public static final List<String> getFilenames(List<String> identityFileLines) {
		List<String> filenames = new ArrayList<String>();
		for (String identityFileLine : identityFileLines) {
			String originalFilename = StringUtils.substring(identityFileLine, IDENTITY_FILE.length());
			String resolvedFilename = StringUtils.replace(originalFilename, TILDE, USER_HOME);
			filenames.add(resolvedFilename);
		}
		return filenames;
	}
}
