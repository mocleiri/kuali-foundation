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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 *
 */
public class JschScp extends BaseScp {

	private static final Logger logger = LoggerFactory.getLogger(JschScp.class);

	@Override
	protected int executeCopy(ScpContext context, List<ScpFile> sources, ScpFile destination) {
		logger.debug("Sources - {}", sources.size());
		for (ScpFile source : sources) {
			executeCopy(context, source, destination);
		}
		return 0;
	}

	protected int executeCopy(ScpContext context, ScpFile source, ScpFile destination) {
		OutputStream out = null;
		InputStream in = null;
		Session session = null;
		ChannelExec channel = null;
		try {
			JSch jsch = new JSch();
			String privateKeyPath = LocationUtils.getCanonicalPath(context.getPrivateKey());
			jsch.addIdentity(privateKeyPath);
			session = jsch.getSession(destination.getUsername(), destination.getHostname(), context.getPort());
			session.setConfig(context.getOptions());
			session.connect();
			String command = "scp -p -t " + destination.getFilename();
			channel = (ChannelExec) session.openChannel("exec");
			channel.setCommand(command);
			out = channel.getOutputStream();
			in = channel.getInputStream();
			channel.connect();
			ScpUtils.validateAck(in);
			File localFile = new File(source.getFilename());
			command = "T " + (localFile.lastModified() / 1000) + " 0 " + (localFile.lastModified() / 1000) + " 0\n";
			out.write(command.getBytes());
			out.flush();
			ScpUtils.validateAck(in);
			long filesize = localFile.length();
			File remoteFile = new File(destination.getFilename());
			command = "C0644 " + filesize + " " + remoteFile.getName() + "\n";
			out.write(command.getBytes());
			out.flush();
			ScpUtils.validateAck(in);
			FileUtils.copyFile(localFile, out);
			byte b = 0;
			out.write((byte) b);
			out.flush();
			ScpUtils.validateAck(in);
			out.close();
			channel.disconnect();
			session.disconnect();
			return 0;
		} catch (Exception e) {
			throw new IllegalStateException("Unexpected error", e);
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(in);
			JschUtils.disconnectQuietly(channel);
			JschUtils.disconnectQuietly(session);
		}
	}
}
