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

import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.spring.JSchFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.jcraft.jsch.JSch;

public class DefaultSecureServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureServiceTest.class);

	protected JSchContext getContext() throws Exception {
		FactoryBean<JSch> factory = new JSchFactoryBean();
		JSch jsch = factory.getObject();
		JSchContext context = new JSchContext();
		context.setHostname("ci.fn.kuali.org");
		context.setJsch(jsch);
		context.setUsername("root");
		context.setPort(22);
		// context.setTimeout(30000);
		return context;
	}

	protected SecureFtpClient getClient() throws Exception {
		JSchContext context = getContext();
		JSchSecureFtpClient client = new JSchSecureFtpClient();
		client.setContext(context);
		return client;
	}

	@Test
	public void testRoundTrip() {
		try {
			SecureFtpClient client = getClient();
			File source = new File("/tmp/sftp/hello.txt");
			RemoteFile remote = new RemoteFile("/root/x/y/z/hello.txt");
			File dest = new File("/tmp/sftp/goodbye.txt");

			client.copyFile(source, remote);
			// client.copyFile(remote, dest);
		} catch (Exception e) {
			e.printStackTrace();
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
