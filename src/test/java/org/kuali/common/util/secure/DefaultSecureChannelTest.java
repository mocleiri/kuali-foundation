/**
 * Copyright 2010-2013 The Kuali Foundation
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
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.UnixCmds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class DefaultSecureChannelTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureChannelTest.class);

	protected SecureChannel getSecureChannel() {
		String privateKeyString = LocationUtils.toString("/Users/jeffcaddel/.ssh/ole-key.pem");
		BasicTextEncryptor bte = new BasicTextEncryptor();
		bte.setPassword("1423Morgan");
		String s = bte.encrypt(privateKeyString);
		System.out.println(s);
		DefaultSecureChannel channel = new DefaultSecureChannel();
		channel.setUsername("root");
		channel.setHostname("env7.ole.kuali.org");
		channel.setStrictHostKeyChecking(false);
		channel.setIncludeDefaultPrivateKeyLocations(false);
		channel.setUseConfigFile(false);
		channel.setPrivateKeyStrings(Arrays.asList(privateKeyString));
		return channel;
	}

	// @Test
	public void testExec() {
		try {
			UnixCmds cmds = new UnixCmds();
			SecureChannel channel = getSecureChannel();
			channel.open();
			show(channel.executeCommand(cmds.rmrf("/root/x")));
			show(channel.executeCommand(cmds.su("tomcat", "/usr/local/tomcat/bin/forced-shutdown.sh")));
			show(channel.executeCommand(cmds.su("tomcat", "/usr/local/tomcat/bin/cleanup.sh")));
			show(channel.executeCommand(cmds.su("tomcat", "/usr/local/tomcat/bin/startup.sh")));
			show(channel.executeCommand(cmds.mkdirp("/home/tomcat/x/y/z/foo")));
			show(channel.executeCommand("ls -la > /home/tomcat/x/y/z/foo.sh"));
			show(channel.executeCommand("cat", "foo\nbar"));
			show(channel.executeCommand(cmds.chmod("755", "/home/tomcat/x/y/z/foo.sh")));
			show(channel.executeCommand(cmds.chownr("tomcat", "tomcat", "/home/tomcat/x")));
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void show(Result result) throws IOException {
		int exitValue = result.getExitValue();
		if (exitValue != 0) {
			logger.info("Exit value = {}", result.getExitValue());
		}
		if (result.getStdin() == null) {
			Object[] args = { result.getCommand(), FormatUtils.getTime(result.getElapsed()) };
			logger.info("[{}] - {}", args);
		} else {
			Object[] args = { result.getCommand(), Str.flatten(result.getStdin(), "\\r", "\\n"), FormatUtils.getTime(result.getElapsed()) };
			logger.info("[{}] < [{}] - {}", args);
		}
		String[] stdout = StringUtils.split(result.getStdout(), '\n');
		for (String line : stdout) {
			logger.info(line);
		}
		String[] stderr = StringUtils.split(result.getStderr(), '\n');
		for (String line : stderr) {
			logger.info(line);
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
			Assert.isFalse(channel.isDirectory(absolutePath));
			channel.copyFile(remote, localDst);
			channel.deleteFile(absolutePath);
			Assert.isTrue(channel.exists(absolutePath));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ChannelUtils.closeQuietly(channel);
		}
	}

	@Test
	public void test1() {
		try {
			String filename = "/home/.kuali";
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
