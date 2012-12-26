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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSecureServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureServiceTest.class);

	@Test
	public void test() {
		try {
			logger.info("Secure service test");
			DefaultRemoteFile dst = new DefaultRemoteFile();
			dst.setUsername("root");
			dst.setHostname("ci.fn.kuali.org");
			dst.setFilename("/root/hello.txt");
			File src = new File("/tmp/sftp/hello.txt");
			SecureService ss = new DefaultSecureService();
			ss.copyFile(src, dst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
