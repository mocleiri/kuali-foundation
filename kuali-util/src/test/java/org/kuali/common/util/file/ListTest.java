package org.kuali.common.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.SimpleScanner;

public class ListTest {

	@Test
	public void test() {
		try {
			File basedir = new File("/usr/local/sonatype-work/nexus/storage/ow2");
			String includes = "**/**";
			SimpleScanner scanner = new SimpleScanner(basedir, includes, null);
			List<File> files = scanner.getFiles();
			List<File> canonical = new ArrayList<File>();
			for (File file : files) {
				canonical.add(new CanonicalFile(file));
			}
			Collections.sort(canonical);
			for (File file : canonical) {
				System.out.println(file.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
