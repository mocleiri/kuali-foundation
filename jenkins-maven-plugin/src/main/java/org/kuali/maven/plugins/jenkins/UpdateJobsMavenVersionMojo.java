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
import java.io.IOException;
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
			List<String> rtokens = getReplacementTokens(configFiles);
			for (String rtoken : rtokens) {
				getLog().info(rtoken);
			}
			// updateContent(configFiles, rtokens);
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected void updateContent(List<File> files, List<String> rtokens) throws IOException {
		for (File file : files) {
			String oldContent = FileUtils.readFileToString(file);
			String newContent = getReplacementContent(oldContent, rtokens);
			if (oldContent.equals(newContent)) {
				getLog().info("Updating " + file);
			}
		}
	}

	protected String getReplacementContent(String s, List<String> rtokens) {
		for (String rtoken : rtokens) {
			s = s.replace(rtoken, "<mavenName>MAVEN3</mavenName>");
		}
		return s;
	}

	protected void addTokens(String[] tokens, Map<String, Integer> map) {
		if (tokens == null) {
			return;
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

	protected List<String> getReplacementTokens(List<File> files) throws IOException {
		String open = "<mavenName>";
		String close = "</mavenName>";
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (File file : files) {
			String s = FileUtils.readFileToString(file);
			String[] tokens = StringUtils.substringsBetween(s, open, close);
			addTokens(tokens, map);
			tokens = StringUtils.substringsBetween(s, "<jdk>", "</jdk>");
			addTokens(tokens, map);
		}
		List<String> rtokens = new ArrayList<String>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			String rtoken = open + key + close;
			rtokens.add(rtoken);
		}
		return rtokens;
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