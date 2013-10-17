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
package org.kuali.common.util.pom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.KeyValue;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.PropertyUtils;

public class MobilityFixPomsForRelease {

	public static void main(String[] args) {
		try {
			Properties props = PropertyUtils.load("classpath:mobility.properties");
			List<String> keys = PropertyUtils.getSortedKeys(props);
			List<String> tokens = getTokens(keys);
			File pom = new File("/Users/jcaddel/ws/mobility-2.1.0-RC1/pom.xml");
			String content = LocationUtils.toString(pom);
			for (String token : tokens) {
				content = StringUtils.replace(content, token, "${project.version}");
			}
			FileUtils.write(pom, content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static List<String> getTokens(List<String> keys) {
		List<String> tokens = new ArrayList<String>();
		for (String key : keys) {
			String token = "${" + key + "}";
			tokens.add(token);
		}
		return tokens;
	}

	protected static Pom getPom(List<String> lines) {
		Pom pom = new Pom();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (StringUtils.startsWith(line, "    <artifactId>")) {
				pom.setArtifactId(new Line(i, line));
			} else if (StringUtils.startsWith(line, "    <version>")) {
				pom.setVersion(new Line(i, line));
			} else if (StringUtils.startsWith(line, "    <packaging>")) {
				pom.setPackaging(new Line(i, line));
			} else if (StringUtils.startsWith(line, "    <name>")) {
				pom.setName(new Line(i, line));
			} else if (StringUtils.startsWith(line, "    <description>")) {
				pom.setDescription(new Line(i, line));
			} else if (StringUtils.startsWith(line, "    </parent>")) {
				pom.setEndParentTag(new Line(i, line));
			}
		}
		return pom;
	}

	protected static void scrub3(String path) throws IOException {
		List<String> lines = LocationUtils.readLines(path);
		Pom pom = getPom(lines);
		if (reArrange(pom, lines)) {
			System.out.println(path);
			FileUtils.writeLines(new File(path), lines);
		}
	}

	protected static boolean reArrange(Pom pom, List<String> lines) {
		if (pom.getEndParentTag() == null) {
			return false;
		}

		boolean reArranged = false;

		// Setup indexes
		int endParentIndex = pom.getEndParentTag().getIndex();
		int addIndex = endParentIndex + 1;

		// Add stuff
		if (pom.getArtifactId() != null && pom.getArtifactId().getIndex() < endParentIndex) {
			lines.add(addIndex, pom.getArtifactId().getContent());
			addIndex++;
			reArranged = true;
		}
		if (pom.getVersion() != null && pom.getVersion().getIndex() < endParentIndex) {
			lines.add(addIndex, pom.getVersion().getContent());
			addIndex++;
			reArranged = true;
		}
		if (pom.getPackaging() != null && pom.getPackaging().getIndex() < endParentIndex) {
			lines.add(addIndex, pom.getPackaging().getContent());
			addIndex++;
			reArranged = true;
		}
		if (pom.getName() != null && pom.getName().getIndex() < endParentIndex) {
			lines.add(addIndex, pom.getName().getContent());
			addIndex++;
			reArranged = true;
		}
		if (pom.getDescription() != null && pom.getDescription().getIndex() < endParentIndex) {
			lines.add(addIndex, pom.getDescription().getContent());
			addIndex++;
			reArranged = true;
		}

		// Delete stuff
		if (pom.getArtifactId() != null && pom.getArtifactId().getIndex() < endParentIndex) {
			lines.set(pom.getArtifactId().getIndex(), "");
		}
		if (pom.getVersion() != null && pom.getVersion().getIndex() < endParentIndex) {
			lines.set(pom.getVersion().getIndex(), "");
		}
		if (pom.getPackaging() != null && pom.getPackaging().getIndex() < endParentIndex) {
			lines.set(pom.getPackaging().getIndex(), "");
		}
		if (pom.getName() != null && pom.getName().getIndex() < endParentIndex) {
			lines.set(pom.getName().getIndex(), "");
		}
		if (pom.getDescription() != null && pom.getDescription().getIndex() < endParentIndex) {
			lines.set(pom.getDescription().getIndex(), "");
		}

		Iterator<String> itr = lines.iterator();
		while (itr.hasNext()) {
			String line = itr.next();
			if (StringUtils.isBlank(line)) {
				itr.remove();
			}
		}
		return reArranged;
	}

	protected static void scrub2(String path) throws IOException {
		List<String> lines = LocationUtils.readLines(path);
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			if (StringUtils.contains(line, "<parent>")) {
				if (reArrange(lines, i)) {
					System.out.println(path);
					FileUtils.writeLines(new File(path), lines);
				}
			}
		}
	}

	protected static boolean reArrange(List<String> lines, int parentIndex) {
		String artifactId = lines.get(parentIndex + 1);
		String groupId = lines.get(parentIndex + 2);

		if (!StringUtils.contains(artifactId, "<artifactId>")) {
			return false;
		}

		if (!StringUtils.contains(groupId, "<groupId>")) {
			return false;
		}

		lines.set(parentIndex + 1, groupId);
		lines.set(parentIndex + 2, artifactId);
		return true;

	}

	protected static void scrub(String path) throws IOException {
		String original = LocationUtils.toString(path);
		String scrubbed = getScrubbedContent(original);
		if (!StringUtils.equals(original, scrubbed)) {
			System.out.println(path);
			FileUtils.write(new File(path), scrubbed);
		}
	}

	protected static String getScrubbedContent(String content) {

		String compile = "<scope>compile</scope>\n";
		String jar = "<packaging>jar</packaging>\n";

		List<String> removes = Arrays.asList(compile, jar);
		for (String remove : removes) {
			content = StringUtils.remove(content, remove);
		}

		List<KeyValue> replaces = getKeyValues(getModules(), getSubModules());
		for (KeyValue replace : replaces) {
			content = StringUtils.replace(content, replace.getKey(), replace.getValue());
		}
		return content;
	}

	protected static List<String> getModules() {
		List<String> modules = new ArrayList<String>();
		modules.add("academics");
		modules.add("admin");
		modules.add("alerts");
		modules.add("bus");
		modules.add("calendars");
		modules.add("computerlabs");
		modules.add("conference");
		modules.add("configparams");
		modules.add("configparams");
		modules.add("database");
		modules.add("dining");
		modules.add("emergencyinfo");
		modules.add("events");
		modules.add("feedback");
		modules.add("grades");
		modules.add("maps");
		modules.add("mdot-webapp");
		modules.add("news");
		modules.add("people");
		modules.add("push");
		modules.add("reporting");
		modules.add("security.authentication");
		modules.add("security.authorization");
		modules.add("shared");
		modules.add("tours");
		modules.add("user");
		modules.add("util.webservice.adapter");
		modules.add("weather");
		return modules;
	}

	protected static List<String> getSubModules() {
		return Arrays.asList("api", "config", "impl", "ui", "web", "webapp");
	}

	protected static List<KeyValue> getKeyValues(List<String> modules, List<String> submodules) {
		List<KeyValue> list = new ArrayList<KeyValue>();
		for (String artifactId : modules) {
			KeyValue kv = new KeyValue("<artifactId>" + artifactId + "</artifactId>", "<artifactId>kme-" + artifactId + "</artifactId>");
			list.add(kv);
			for (String submodule : submodules) {
				String original = "<artifactId>" + artifactId + "-" + submodule + "</artifactId>";
				String scrubbed = "<artifactId>kme-" + artifactId + "-" + submodule + "</artifactId>";
				KeyValue kvv = new KeyValue(original, scrubbed);
				list.add(kvv);
			}
		}
		return list;
	}

}
