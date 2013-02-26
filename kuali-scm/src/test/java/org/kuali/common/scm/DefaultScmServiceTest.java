/**
 * Copyright 2011-2013 The Kuali Foundation
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
package org.kuali.common.scm;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultScmServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultScmServiceTest.class);
	ScmService service = new DefaultScmService();
	String username = "jcaddel";
	String password = System.getProperty("svn.password");

	// @Test
	public void testGetExternalsFromWorkingCopyPath2() {
		logger.info("");
		try {
			File workingCopyPath = new File("/Users/jeffcaddel/ws/aggregate");
			System.out.println(workingCopyPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
