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

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;

public class UnixUtilsTest {

	@Test
	@Ignore
	public void testRsync() {
		try {
			String source = "root@env7.ole.kuali.org:/home/tomcat";
			File destination = new File("/tmp/foo/man/chu/xyz");
			FileUtils.deleteDirectory(new File("/tmp/foo"));
			UnixUtils.rsyncdirs(Arrays.asList("--stats"), source, destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testSsh() {
		try {
			File source = new File("/Users/jeffcaddel/Downloads/foo.sh");
			String destination = "root@env11.ks.kuali.org:/home/tomcat/foo.sh";
			UnixUtils.sshsu("root@env11.ks.kuali.org", "tomcat", "/home/tomcat/foo.sh");
			UnixUtils.sshchown("root@env11.ks.kuali.org", "tomcat", "tomcat", "/home/tomcat/foo.sh");
			UnixUtils.sshchownr("root@env11.ks.kuali.org", "tomcat", "tomcat", "/home/tomcat/foo.sh");
			UnixUtils.scp(destination, source);
			UnixUtils.sshrm("root@env11.ks.kuali.org", "/home/tomcat/foo.sh");
			UnixUtils.scp(source, destination);
			UnixUtils.sshmkdir("root@env11.ks.kuali.org", "/home/tomcat/foo.sh");
			String newDestination = "root@env11.ks.kuali.org:/home/tomcat/foo/bar.sh";
			UnixUtils.scp(source, newDestination);
			FileUtils.deleteDirectory(new File("/tmp/tomcat"));
			UnixUtils.rsync(Arrays.asList("-r", "--stats", "-a"), "root@env11.ks.kuali.org:/home/tomcat/", "/tmp/tomcat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testScp() throws IOException {
		try {
			File localFile1 = new File("/Users/jeffcaddel/Downloads/jjj.zip");
			File localFile2 = new File("/Users/jeffcaddel/Downloads/foo/xyz/jjj.zip");
			UnixUtils.scp(localFile1, "root@ci.fn.kuali.org:/root/jjj.zip");
			UnixUtils.scp("root@ci.fn.kuali.org:/root/jjj.zip", localFile2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
