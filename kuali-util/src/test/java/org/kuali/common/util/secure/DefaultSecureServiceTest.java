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

public class DefaultSecureServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(DefaultSecureServiceTest.class);

	@Test
	public void test1() {
		try {
			String filename = "/Users/jeffcaddel/../../../../../jeffcaddel/x/y/z/.././../foo";
			File file = new File(filename);
			String url = LocationUtils.getCanonicalURLString(file);
			logger.info(url);
			String path = LocationUtils.getNormalizedPath(filename);
			logger.info(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void test() {
		try {
			logger.info("Secure service test");
			DefaultRemoteFile dst = new DefaultRemoteFile();
			dst.setHostname("ci.fn.kuali.org");
			dst.setAbsolutePath("/root/x/y/z/hello.txt");
			File src = new File("/tmp/sftp/hello.txt");
			DefaultSecureService ss = new DefaultSecureService();
			ss.copyFile(src, dst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
