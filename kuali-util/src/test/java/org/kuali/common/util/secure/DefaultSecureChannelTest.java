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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSecureChannelTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureChannelTest.class);

	protected SecureChannel getSecureChannel() {
		DefaultSecureChannel channel = new DefaultSecureChannel();
		channel.setUsername("root");
		channel.setHostname("ci.fn.kuali.org");
		channel.setStrictHostKeyChecking(false);
		return channel;
	}

	@Test
	public void testRoundTrip() {
		SecureChannel channel = null;
		try {
			File localSrc = new File("/tmp/sftp/src.txt");
			File localDst = new File("/tmp/sftp/dst.txt");
			channel = getSecureChannel();
			channel.open();
			RemoteFile remote = channel.getMetaData("/root/x/y/z/hello.txt");
			channel.copyFile(localSrc, remote);
			channel.copyFile(remote, localDst);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (channel != null) {
				channel.close();
			}
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
