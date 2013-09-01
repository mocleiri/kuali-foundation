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
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated
 */
@Deprecated
public class SSHUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(SSHUtilsTest.class);

	@Test
	public void test() {
		try {
			List<File> files = org.kuali.common.util.secure.SSHUtils.getDefaultPrivateKeys();
			for (File file : files) {
				logger.info(file.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
