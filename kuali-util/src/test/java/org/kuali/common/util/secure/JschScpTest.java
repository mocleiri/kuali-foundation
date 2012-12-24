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

public class JschScpTest {
	private static final Logger logger = LoggerFactory.getLogger(JschScpTest.class);

	@Test
	public void testSimple() {
		try {
			SecureContext context = new SecureContext();
			context.setOptions(SSHUtils.getDefaultOptions());
			context.setPrivateKey(new File("/Users/jeffcaddel/.ssh/kr-key.pem"));
			ScpFile source = ScpUtils.getScpFile("/root/1.pdf");
			ScpFile destination = new ScpFile("root", "ci.fn.kuali.org", "/root/file.txt");
			Scp scp = new JschScp();
			logger.info("SCP exit value - [{}]", scp.copy(context, source, destination));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
