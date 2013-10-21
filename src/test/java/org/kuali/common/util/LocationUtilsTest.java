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

import org.junit.Ignore;
import org.junit.Test;

public class LocationUtilsTest {

	@Test
	public void testMD5() throws IOException {
		try {
			String location1 = "classpath:car.properties";
			String location2 = "classpath:chevy.properties";
			String md51 = LocationUtils.getMD5Checksum(location1);
			String md52 = LocationUtils.getMD5Checksum(location2);
			System.out.println(md51);
			System.out.println(md52);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testGetURLString() throws IOException {
		try {
			File file = new File("./temp/../temp");
			String path = LocationUtils.getCanonicalPath(file);
			File canonical = new File(path);
			System.out.println(LocationUtils.getURLString(file));
			System.out.println(LocationUtils.getURLString(canonical));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
