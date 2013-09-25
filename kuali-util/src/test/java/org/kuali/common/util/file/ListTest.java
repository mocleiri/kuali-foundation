package org.kuali.common.util.file;

import java.io.File;
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
			System.out.println(files.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
