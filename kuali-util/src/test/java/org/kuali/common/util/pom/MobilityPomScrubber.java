package org.kuali.common.util.pom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.KeyValue;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.SimpleScanner;

public class MobilityPomScrubber {

	public static void main(String[] args) {
		try {
			File basedir = new File("/Users/jcaddel/ws/mobility-devops");
			SimpleScanner ss = new SimpleScanner(basedir, "**/pom.xml", "**/target/**");
			List<File> files = ss.getFiles();
			List<String> paths = new ArrayList<String>();
			for (File file : files) {
				String path = LocationUtils.getCanonicalPath(file);
				paths.add(path);
			}
			Collections.sort(paths);
			for (String path : paths) {
				scrub(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
