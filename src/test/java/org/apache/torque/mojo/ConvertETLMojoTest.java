/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.apache.torque.mojo;

import java.io.File;

public class ConvertETLMojoTest {

	public static void main(String[] args) {
		try {
			ConvertCloverETLMojo mojo = new ConvertCloverETLMojo();
			mojo.setSourceDir(new File("/Users/jeffcaddel/ws/ole-kfs-5.0/ole/work/db/ole-db/development"));
			mojo.setOutputDir(new File("/Users/jeffcaddel/ws/maven-impex-plugin/target/impex"));
			mojo.handleDataDTD();
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
}
