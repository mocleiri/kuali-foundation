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
			List<String> jdkTokens = getJdkReplacementTokens(configFiles);
			List<String> mvnTokens = getMvnReplacementTokens(configFiles);
			getLog().info("Updating Maven Config");
			updateContent(configFiles, mvnTokens, "<mavenName>MAVEN3</mavenName>", ".bak.mvn");
			getLog().info("Updating JDK Config");
			updateContent(configFiles, jdkTokens, "<jdk>JDK6</jdk>", ".bak.jdk");
		} catch (Exception e) {
			throw new MojoExecutionException("Unexpected error", e);
		}
	}

	protected void updateContent(List<File> files, List<String> rtokens, String replacement, String extension) throws IOException {
		for (File file : files) {
			String oldContent = FileUtils.readFileToString(file);
			String newContent = getReplacementContent(oldContent, rtokens, replacement);
			if (oldContent.equals(newContent)) {
				continue;
			}
			getLog().info("Updating " + file);
			File bak = new File(file.getAbsolutePath() + extension);
			FileUtils.copyFile(file, bak);
			FileUtils.writeStringToFile(file, newContent);
		}
	}

	protected String getReplacementContent(String s, List<String> rtokens, String replacement) {
		for (String rtoken : rtokens) {
			s = s.replace(rtoken, replacement);
		}
		return s;
	}

	protected void addTokens(String[] tokens, Map<String, Integer> map, String open, String close) {
		if (tokens == null) {
			return;
		}
		for (String token : tokens) {
			if (token == null) {
				continue;
			}
			String s = open + token + close;
			Integer count = map.get(s);
			if (count == null) {
				count = new Integer(1);
			} else {
				count++;
			}
			map.put(s, count);
		}
	}

	protected List<String> getJdkReplacementTokens(List<File> files) throws IOException {
		String open = "<jdk>";
		String close = "</jdk>";
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (File file : files) {
			String s = FileUtils.readFileToString(file);
			String[] tokens = StringUtils.substringsBetween(s, open, close);
			addTokens(tokens, map, open, close);
		}
		List<String> rtokens = new ArrayList<String>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			String rtoken = key;
			getLog().info(key + "=" + map.get(key));
			rtokens.add(rtoken);
		}
		return rtokens;
	}

	protected List<String> getMvnReplacementTokens(List<File> files) throws IOException {
		String open = "<mavenName>";
		String close = "</mavenName>";
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (File file : files) {
			String s = FileUtils.readFileToString(file);
			String[] tokens = StringUtils.substringsBetween(s, open, close);
			addTokens(tokens, map, open, close);
		}
		List<String> rtokens = new ArrayList<String>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			String rtoken = key;
			getLog().info(key + "=" + map.get(key));
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