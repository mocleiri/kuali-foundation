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
package org.kuali.common.util;


public class SshUtilsTest2 {
/*
	@Test
	public void test() {
		try {
			String userHome = System.getProperty("user.home");
			File localFile = new File(userHome + "/Downloads/jarc.zip");
			String base = userHome + "/.ssh/";
			List<String> privateKeys = Arrays.asList(base + "id_rsa", base + "kr-key.pem", base + "ks-key.pem");
			String remoteDir = "/root/foo/joe/x";
			String username = "root";
			String host = "ci.fn.kuali.org";
			int port = 22;
			Properties config = new Properties();
			config.setProperty("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			for (String privateKey : privateKeys) {
				jsch.addIdentity(privateKey);
			}
			Session session = jsch.getSession(username, host, port);
			session.setConfig(config);
			session.connect();
			ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();
			String s = channel.pwd();
			System.out.println(s);
			channel.mkdir(remoteDir);
			channel.cd(remoteDir);
			String filename = localFile.getName();
			InputStream in = new FileInputStream(localFile);
			channel.put(in, filename);
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
