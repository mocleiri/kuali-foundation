/**
 * Copyright 2009-2012 The Kuali Foundation
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
package org.codehaus.mojo.properties;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class WriteProjectPropertiesTest {

	@Test
	public void test() {
		try {
			WriteProjectProperties mojo = new WriteProjectProperties();
			List<String> escapeTokens = mojo.getEscapeChars("cr,lf,tab,backslash,:,#,=");
			File outputFile = new File("/Users/jeffcaddel/props.properties");
			String comment = "comment";

			Properties properties = new Properties();
			properties.setProperty("filename", "C:\\temp\\mvn.txt :   #   =");
			mojo.writeProperties(outputFile, comment, properties, escapeTokens);
			File props2 = new File("/Users/jeffcaddel/props2.properties");
			OutputStream out = FileUtils.openOutputStream(props2);
			properties.store(out, comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
