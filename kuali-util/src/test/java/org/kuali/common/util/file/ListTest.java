package org.kuali.common.util.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.SimpleScanner;

public class ListTest {

	@Test
	public void test() {
		try {
			List<File> repos = getRepos();
			// List<File> ow2 = getRepoFiles("/usr/local/sonatype-work/nexus/storage/ow2");
			for (File file : repos) {
				System.out.println(file.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<File> getRepos() {
		File dir = new File("/usr/local/sonatype-work/nexus/storage");
		List<File> repos = Arrays.asList(dir.listFiles());
		List<File> canonical = new ArrayList<File>();
		for (File file : repos) {
			canonical.add(new CanonicalFile(file));
		}
		Collections.sort(canonical);
		return canonical;
	}

	protected List<File> getRepoFiles(String repoDir) {
		File basedir = new File(repoDir);
		String includes = "**/**";
		SimpleScanner scanner = new SimpleScanner(basedir, includes, null);
		List<File> files = scanner.getFiles();
		List<File> canonical = new ArrayList<File>();
		for (File file : files) {
			canonical.add(new CanonicalFile(file));
		}
		Collections.sort(canonical);
		return canonical;
	}

}
