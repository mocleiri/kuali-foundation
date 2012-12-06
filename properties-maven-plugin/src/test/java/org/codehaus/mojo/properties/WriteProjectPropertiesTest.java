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
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class WriteProjectPropertiesTest {
	WriteProjectProperties mojo = new WriteProjectProperties();

	@Test
	public void test1() {
		try {
			Properties properties = new Properties();
			properties.setProperty("user.home", "foo");
			mojo.override(properties, new ArrayList<String>());
			System.out.println(properties.getProperty("user.home"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		try {
			File outputFile = new File("/Users/jeffcaddel/jbc.properties");

			Properties properties = new Properties();
			properties.setProperty("filename", "C:\\temp\\mvn.txt :   #   =");
			properties.putAll(System.getenv());
			properties.putAll(WriteProjectProperties.getEnvironmentVariables());
			mojo.trim(properties, "filename", null);
			mojo.writeProperties(outputFile, properties, OutputStyle.ENVIRONMENT_VARIABLE, "nightlytag");
			File props2 = new File("/Users/jeffcaddel/jvm.properties");
			OutputStream out = FileUtils.openOutputStream(props2);
			properties.store(out, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
