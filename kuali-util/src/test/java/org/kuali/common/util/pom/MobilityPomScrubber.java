package org.kuali.common.util.pom;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
				System.out.println(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void scrub(String path) {
		String original = LocationUtils.toString(path);
		String scrubbed = getScrubbedContent(original);
		FileUtils.write(new File(path), scrubbed);
	}

	protected static String getScrubbedContent(String content) {
		return null;
	}

}
