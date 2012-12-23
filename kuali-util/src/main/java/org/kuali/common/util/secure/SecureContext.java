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
import java.util.Arrays;
import java.util.List;

public class SecureContext {
	private static final String FS = File.separator;
	private static final String USER_HOME = System.getProperty("user.home");
	private static final String SSH_DIR = USER_HOME + FS + ".ssh";
	private static final File IDENTITY = new File(SSH_DIR + FS + "identity");
	private static final File ID_DSA = new File(SSH_DIR + FS + "id_dsa");
	private static final File ID_RSA = new File(SSH_DIR + FS + "id_rsa");
	private static final File ID_ECDSA = new File(SSH_DIR + FS + "id_ecdsa");
	private static final String CONFIG = "config";

	File sshDir = new File(USER_HOME + FS + SSH_DIR);
	List<File> privateKeys = Arrays.asList(IDENTITY, ID_DSA, ID_RSA, ID_ECDSA);
	File config = new File(USER_HOME + FS + CONFIG);
	String username;
	String password;
	String hostname;
	File privateKey;
	String passphrase;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public File getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(File privateKey) {
		this.privateKey = privateKey;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
}
