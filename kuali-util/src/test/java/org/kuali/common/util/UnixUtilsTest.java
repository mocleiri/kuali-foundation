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

public class UnixUtilsTest {

	@Test
	public void testSsh() throws IOException {
		try {
			File local = new File("/Users/jeffcaddel/Downloads/foo.sh");
			String remote = "root@env11.ks.kuali.org:/home/tomcat/foo.sh";
			UnixUtils.sshsu("root@env11.ks.kuali.org", "tomcat", "/home/tomcat/foo.sh");
			UnixUtils.sshchown("root@env11.ks.kuali.org", "tomcat", "tomcat", "/home/tomcat/foo.sh");
			UnixUtils.scp(remote, local);
			UnixUtils.sshrm("root@env11.ks.kuali.org", "/home/tomcat/foo.sh");
			UnixUtils.scp(local, remote);
			UnixUtils.sshmkdir("root@env11.ks.kuali.org", "/home/tomcat/foo");
			String newRemote = "root@env11.ks.kuali.org:/home/tomcat/foo/bar.sh";
			UnixUtils.scp(local, newRemote);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
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
