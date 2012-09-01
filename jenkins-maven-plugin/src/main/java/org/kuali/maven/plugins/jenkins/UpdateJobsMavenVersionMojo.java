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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * @goal updatejobsmavenversion
 */
public class UpdateJobsMavenVersionMojo extends AbstractMojo {
	@Override
	public void execute() throws MojoExecutionException {
		try {
			List<File> configFiles = getConfigFiles();
			getLog().info("Located " + configFiles.size() + " job config files");
			Map<String, Integer> map = new HashMap<String, Integer>();
			for (File configFile : configFiles) {
				String s = FileUtils.readFileToString(configFile);
				int pos = s.indexOf("<mavenName>Default</mavenName");
				if (pos != -1) {
					getLog().info(configFile.getAbsolutePath());
				}
				String[] tokens = StringUtils.substringsBetween(s, "<mavenName>", "</mavenName>");
				if (tokens == null) {
					continue;
				}
				for (String token : tokens) {
					if (token == null) {
						continue;
					}
					Integer count = map.get(token);
					if (count == null) {
						count = new Integer(1);
					} else {
						count++;
					}
					map.put(token, count);
				}
			}
			getLog().info(map.size() + "");
			Set<String> keys = map.keySet();
			for (String key : keys) {
				getLog().info("[" + key + "]=" + map.get(key));
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