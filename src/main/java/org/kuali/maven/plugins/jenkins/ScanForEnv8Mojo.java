/**
 * Copyright 2011-2012 The Kuali Foundation
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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal env8
 */
public class ScanForEnv8Mojo extends AbstractMojo {
	@Override
	public void execute() throws MojoExecutionException {
		try {
			List<File> configFiles = getConfigFiles();
			getLog().info("Located " + configFiles.size() + " job config files");
			for (File configFile : configFiles) {
				String s = FileUtils.readFileToString(configFile);
				String token = "-Ddeploy.env=8";
				int pos = s.indexOf(token);
				if (pos != -1) {
					getLog().info(configFile.getAbsolutePath());
				}
			}
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected List<File> getConfigFiles() {
		File directory = new File("/var/lib/jenkins/jobs");
		File[] files = directory.listFiles();
		List<File> list = new ArrayList<File>();
		for (File file : files) {
			File config = new File(file.getAbsolutePath() + "/config.xml");
			if (config.exists()) {
				list.add(config);
			}
		}
		return list;
	}
}