package org.kuali.common.util.file;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.kuali.common.util.SimpleScanner;

import com.google.common.collect.ImmutableList;

public class ListTest {

	@Test
	public void test() {
		try {
			List<File> repos = getRepos();
			System.out.println("repos=" + repos.size());
			for (File repo : repos) {
				System.out.println(repo.getName());
			}
			File ow2 = new File("/usr/local/sonatype-work/nexus/storage/ow2");
			printRepo(ow2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void printRepo(File repo) {
		List<File> files = getRepoFiles(repo.getPath());
		File outputFile = new File("/tmp/" + repo.getName() + ".txt");
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(outputFile);
			for (File file : files) {
				String s = file.getPath() + "\n";
				byte[] bytes = s.getBytes("UTF-8");
				out.write(bytes);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} finally {
			IOUtils.closeQuietly(out);
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
		List<String> includes = ImmutableList.of("**/**");
		List<String> excludes = ImmutableList.of("**/.index/**", "**/.meta/**", "archetype-catalog.xml", "**/maven-metadata.xml", "**/maven-metadata.xml.*");
		SimpleScanner scanner = new SimpleScanner(basedir, includes, excludes);
		List<File> files = scanner.getFiles();
		List<File> canonical = new ArrayList<File>();
		for (File file : files) {
			canonical.add(new CanonicalFile(file));
		}
		Collections.sort(canonical);
		return canonical;
	}
}
