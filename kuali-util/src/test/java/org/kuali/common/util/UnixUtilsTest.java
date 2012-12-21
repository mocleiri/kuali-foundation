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
package org.kuali.common.util;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.kuali.common.util.UnixUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnixUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(UnixUtilsTest.class);

	@Test
	public void testSsh() throws IOException {
		try {
			UnixUtils des = new UnixUtils();
			int exitValue = des.sshsu("root@env11.ks.kuali.org", "tomcat", "/home/tomcat/foo.sh");
			logger.info("Exit value = " + exitValue);
			int exitValue1 = des.sshchown("root@env11.ks.kuali.org", "tomcat", "tomcat", "/home/tomcat/foo.sh");
			logger.info("Exit value = " + exitValue1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testScp() throws IOException {
		try {
			UnixUtils des = new UnixUtils();
			File localFile1 = new File("/Users/jeffcaddel/Downloads/jjj.zip");
			File localFile2 = new File("/Users/jeffcaddel/Downloads/foo/xyz/jjj.zip");
			int exitValue1 = des.scp(localFile1, "root@ci.fn.kuali.org:/root/jjj.zip");
			logger.info("Exit value = " + exitValue1);
			int exitValue2 = des.scp("root@ci.fn.kuali.org:/root/jjj.zip", localFile2);
			logger.info("Exit value = " + exitValue2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
