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
import java.io.IOException;

import org.junit.Test;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.SimpleFormatter;
import org.kuali.common.util.UnixCmds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DefaultSecureChannelTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureChannelTest.class);
	SimpleFormatter formatter = new SimpleFormatter();

	protected SecureChannel getSecureChannel() {
		DefaultSecureChannel channel = new DefaultSecureChannel();
		channel.setUsername("root");
		channel.setHostname("env7.ole.kuali.org");
		channel.setStrictHostKeyChecking(false);
		return channel;
	}

	@Test
	public void testExec() {
		try {
			UnixCmds cmds = new UnixCmds();
			SecureChannel channel = getSecureChannel();
			channel.open();
			show(channel.execute(cmds.rmrf("/root/x")));
			show(channel.execute(cmds.su("tomcat", "/usr/local/tomcat/bin/forced-shutdown.sh")));
			show(channel.execute(cmds.su("tomcat", "/usr/local/tomcat/bin/cleanup.sh")));
			show(channel.execute(cmds.su("tomcat", "/usr/local/tomcat/bin/startup.sh")));
			show(channel.execute(cmds.mkdirp("/home/tomcat/x/y/z/foo")));
			show(channel.execute("ls -la > /home/tomcat/x/y/z/foo.sh"));
			show(channel.execute(cmds.chmod("755", "/home/tomcat/x/y/z/foo.sh")));
			show(channel.execute(cmds.chownr("tomcat", "tomcat", "/home/tomcat/x")));
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void show(Result result) throws IOException {
		logger.info("[{}] - {}", result.getCommand(), formatter.getTime(result.getElapsed()));
		for (String line : CollectionUtils.getLines(result.getStdout())) {
			logger.info(line);
		}
		for (String line : CollectionUtils.getLines(result.getStderr())) {
			logger.error(line);
		}
	}

	// @Test
	public void testGetWorkingDirectory() {
		SecureChannel channel = null;
		try {
			channel = getSecureChannel();
			channel.open();
			RemoteFile file = channel.getWorkingDirectory();
			logger.info(file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	// @Test
	public void testRoundTrip() {
		SecureChannel channel = null;
		try {
			String absolutePath = "/root/x/y/z/hello.txt";
			File localSrc = new File("/tmp/sftp/src.txt");
			File localDst = new File("/tmp/sftp/dst.txt");
			channel = getSecureChannel();
			channel.open();
			RemoteFile remote = channel.getMetaData(absolutePath);
			channel.copyFile(localSrc, remote);
			Assert.isTrue(channel.exists(absolutePath));
			Assert.isTrue(!channel.isDirectory(absolutePath));
			channel.copyFile(remote, localDst);
			channel.deleteFile(absolutePath);
			boolean missing = !channel.exists(absolutePath);
			Assert.isTrue(missing);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	// @Test
	public void test1() {
		try {
			String filename = "/Users/jeffcaddel/../../../../../jeffcaddel/x/y/z/.././../foo";
			File file = new File(filename);
			String url = LocationUtils.getCanonicalURLString(file);
			logger.info(url);
			String path = LocationUtils.getNormalizedAbsolutePath(filename);
			logger.info(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
