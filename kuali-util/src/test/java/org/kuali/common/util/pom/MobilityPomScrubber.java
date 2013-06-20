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

		KeyValue groupId = new KeyValue("<artifactId>academics</artifactId>", "<artifactId>kme-academics</artifactId>");
		List<KeyValue> replaces = Arrays.asList(groupId);
		for (KeyValue replace : replaces) {
			content = StringUtils.replace(content, replace.getKey(), replace.getValue());
		}
		return content;
	}

}
