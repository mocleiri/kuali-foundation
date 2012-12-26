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
import java.util.List;

import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 *
 */
public class JSchUtilss {

	private static final Logger logger = LoggerFactory.getLogger(JSchUtilss.class);

	public static final JSch getDefaultJsch() throws JSchException {
		JSch jsch = new JSch();
		List<File> privateKeys = SSHUtils.getDefaultPrivateKeys();
		for (File privateKey : privateKeys) {
			String path = LocationUtils.getCanonicalPath(privateKey);
			jsch.addIdentity(path);
		}
		return jsch;
	}

	public static final void disconnectQuietly(Session session) {
		if (session == null) {
			return;
		} else {
			logger.trace("Disconnecting session");
			session.disconnect();
		}
	}

	public static final void disconnectQuietly(Channel channel) {
		if (channel == null) {
			return;
		} else {
			logger.trace("Disconnecting channel");
			channel.disconnect();
		}
	}

}
