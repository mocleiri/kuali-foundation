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
package org.kuali.common.util.spring;

import java.io.File;
import java.util.List;

import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.secure.JSchUtils;
import org.kuali.common.util.secure.SSHUtils;
import org.springframework.beans.factory.FactoryBean;

import com.jcraft.jsch.JSch;

public class JSchFactoryBean implements FactoryBean<JSch> {

	protected static JSch jsch;
	File sshConfig = SSHUtils.DEFAULT_CONFIG_FILE;
	boolean includeDefaultPrivateKeyLocations = true;
	List<File> privateKeys;
	File knownHosts = SSHUtils.DEFAULT_KNOWN_HOSTS;

	@Override
	public JSch getObject() throws Exception {
		return getInstance();
	}

	protected synchronized JSch getInstance() throws Exception {
		if (jsch == null) {
			List<File> mergedPrivateKeys = getMergedPrivateKeys();
			jsch = JSchUtils.getJSch(mergedPrivateKeys);
			if (knownHosts != null) {
				String path = LocationUtils.getCanonicalPath(knownHosts);
				jsch.setKnownHosts(path);
			}
		}
		return jsch;
	}

	protected List<File> getMergedPrivateKeys() {
		if (privateKeys == null) {
			return SSHUtils.getPrivateKeys(sshConfig, includeDefaultPrivateKeyLocations);
		} else {
			return SSHUtils.getPrivateKeys(privateKeys, includeDefaultPrivateKeyLocations);
		}
	}

	@Override
	public Class<JSch> getObjectType() {
		return JSch.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public File getSshConfig() {
		return sshConfig;
	}

	public void setSshConfig(File sshConfig) {
		this.sshConfig = sshConfig;
	}

	public List<File> getPrivateKeys() {
		return privateKeys;
	}

	public void setPrivateKeys(List<File> privateKeys) {
		this.privateKeys = privateKeys;
	}

}
