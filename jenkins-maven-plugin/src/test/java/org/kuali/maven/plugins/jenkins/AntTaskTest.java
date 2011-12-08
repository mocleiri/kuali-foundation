/**
 * Copyright 2011-2011 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins;

import java.io.File;

import org.apache.tools.ant.Project;
import org.junit.Test;

public class AntTaskTest {

	@Test
	public void testForking() {
		Project project = new Project();
		HelloWorldTask task = new HelloWorldTask();
		task.setProject(project);
		task.setFork(true);
		task.setOutput(new File("C:\\temp\\out.txt"));
		project.init();
		int result = task.executeJava();
		System.out.println(result);
	}
}
